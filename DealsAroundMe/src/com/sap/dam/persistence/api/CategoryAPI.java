package com.sap.dam.persistence.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.sap.dam.persistence.Category;
import com.sap.dam.persistence.Vendor;
import com.sap.dam.persistence.database.HDBHandler;

public class CategoryAPI {
	public void CreateCategory(Category category) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != category) {
				em.getTransaction().begin();
				em.persist(category);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public void updateCategory(Category category) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != category) {
				em.getTransaction().begin();
				em.merge(category);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public List<Category> getAllCategory() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<Category> results = em.createNamedQuery("AllCategory")
				.getResultList();
		em.close();
		return results;

	}

	public List<Category> getCategoryByVID(String vendorId)
			throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query = em.createNamedQuery("CategoryByVID");
		query.setParameter("vendorId", vendorId);
		List<Category> results = query.getResultList();
		em.close();
		return results;
	}

	@SuppressWarnings("null")
	public List<Category> getCategoryByLocation(Double lat, Double longt)
			throws ServletException {
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByLocation(lat - 0.002,
				lat + 0.002, longt - 0.002, longt + 0.002);
		List<Category> results = new ArrayList<Category>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("CategoryByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Category> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
			// results.

		}
		return results;
	}
	
	public List<Category> getCategoryByArea(String area)
			throws ServletException {
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByArea(area);
		List<Category> results = new ArrayList<Category>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("CategoryByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Category> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
			// results.

		}
		return results;
	}
}
