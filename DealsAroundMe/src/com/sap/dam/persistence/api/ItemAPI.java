package com.sap.dam.persistence.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import com.sap.dam.persistence.Deals;
import com.sap.dam.persistence.Item;
import com.sap.dam.persistence.Vendor;
import com.sap.dam.persistence.database.HDBHandler;

public class ItemAPI {
	public void CreateItem(Item item) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != item) {
				em.getTransaction().begin();
				em.persist(item);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public void updateItem(Item item) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != item) {
				em.getTransaction().begin();
				em.merge(item);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}
	
	public void deleteItem(Item item) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != item) {
				em.getTransaction().begin();
				em.remove(item);
				em.getTransaction().commit();
				
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public List<Item> getAllItems() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<Item> results = em.createNamedQuery("AllItems").getResultList();
		em.close();
		return results;

	}
	public List<String> getAllArea() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<String> results = em.createNamedQuery("AllArea").getResultList();
		em.close();
		return results;

	}
	
	public List<Item> getItemsbyLoc(Double lat, Double longt) throws ServletException{
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByLocation(lat - 0.002,
				lat + 0.002, longt - 0.002, longt + 0.002);
		List<Item> results = new ArrayList<Item>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("ItemByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Item> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
		}
		return results;
	}
	
	public List<Item> getItemsbyArea(String area) throws ServletException{
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByArea(area);
		List<Item> results = new ArrayList<Item>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("ItemByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Item> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
		}
		return results;
	}
	
	public List<Item> getItemsBySID(int subcategoryId) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query= em.createNamedQuery("ItemBySID");
		query.setParameter("subcategoryId", subcategoryId);
		List<Item> results = query.getResultList();
		em.close();
		return results;
	}
	
	public List<Item> getItemsByIID(int itemId) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		Query query= em.createNamedQuery("ItemByIID");
		query.setParameter("itemId", itemId);
		List<Item> results = query.getResultList();
		em.close();
		return results;
	}
	
}
