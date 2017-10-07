package com.sap.dam.persistence.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBHandler {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HDBHandler.class);

	private DataSource ds;
	private EntityManagerFactory emf;

	public EntityManager getEntitiyManager() throws ServletException {
		EntityManager em = null;
		Connection connection = null;
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

			Map properties = new HashMap();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			connection = ds.getConnection();
			String databaseProductName = connection.getMetaData()
					.getDatabaseProductName();
			
			System.out.println("databaseProductName "+databaseProductName);
			if (databaseProductName.equals("HDB")) {
				properties.put("eclipselink.target-database",
						"com.sap.persistence.platform.database.HDBPlatform");
			}

			emf = Persistence.createEntityManagerFactory(
					"DAM", properties);

		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (SQLException e) {
			LOGGER.error("Could not determine database product.", e);
			throw new ServletException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException x) {
					LOGGER.debug("Unable to close connection.", x);
				}
			}
		}
		if (null != emf)
			em = emf.createEntityManager();

		return em;
	}

}
