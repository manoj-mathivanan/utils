package com.sap.dam.persistence.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import com.sap.dam.persistence.Vendor;
import com.sap.dam.persistence.database.HDBHandler;


public class VendorAPI {
	public void CreateVendor(Vendor vendor) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		
		System.out.println("Vender Info "+vendor.getVendorName()+"\n"+vendor.getPhone()+"\n"+vendor.getPassword()+"\n"+vendor.getUsername());
		try {
			if (null != vendor) {
				em.getTransaction().begin();
				em.persist(vendor);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			System.out.println("Throwing Exception "+hdbex.getMessage());
			em.getTransaction().rollback();
		}
		em.close();
	}

	public void updateVendor(Vendor vendor) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != vendor) {
				em.getTransaction().begin();
				em.merge(vendor);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public List<Vendor> getAllVendor() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<Vendor> results = em.createNamedQuery("AllVendors").getResultList();
		em.close();
		return results;

	}
	
	public List<Vendor> getVendorByLocation(Double latitudeLOW, Double latitudeHIGH, Double longitudeLOW, Double longitudeHIGH) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query= em.createNamedQuery("VendorByLatitude");
		query.setParameter("latitudeLOW", latitudeLOW);
		query.setParameter("latitudeHIGH", latitudeHIGH);
		query.setParameter("longitudeLOW", longitudeLOW);
		query.setParameter("longitudeHIGH", longitudeHIGH);
		List<Vendor> results = query.getResultList();
		em.close();
		return results;
	}
	
	public List<Vendor> getVendorByArea(String area) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query= em.createNamedQuery("VendorByArea");
		query.setParameter("area", area);
		List<Vendor> results = query.getResultList();
		em.close();
		return results;
	}
	
	public String validateLogin(Vendor vendor)
	{
		if(vendor.getUsername().equalsIgnoreCase(vendor.getPassword()))
		{
			return "loginSuccess";
		}
		else
		{
			return "loginFailure";
		}
		
		
	}
}
