package com.sap.dam.persistence.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import com.sap.dam.persistence.Subcategory;
import com.sap.dam.persistence.database.HDBHandler;

public class SubcategoryAPI {
	public void CreateSubcategory(Subcategory subCategory) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != subCategory) {
				em.getTransaction().begin();
				em.persist(subCategory);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public void updateSubcategory(Subcategory subCategory) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != subCategory) {
				em.getTransaction().begin();
				em.merge(subCategory);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public List<Subcategory> getAllSubcategory() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<Subcategory> results = em.createNamedQuery("AllSubcategory").getResultList();
		em.close();
		return results;

	}
	
	public List<Subcategory> getSubcategoryByCID(int categoryId) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query= em.createNamedQuery("SubcategoryByCID");
		query.setParameter("categoryId", categoryId);
		List<Subcategory> results = query.getResultList();
		em.close();
		return results;
	}
}
