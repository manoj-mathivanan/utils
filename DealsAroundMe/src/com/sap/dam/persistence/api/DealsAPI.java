package com.sap.dam.persistence.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import com.sap.dam.persistence.Deals;
import com.sap.dam.persistence.Vendor;
import com.sap.dam.persistence.database.HDBHandler;

public class DealsAPI {
	public void CreateDeals(Deals deals) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != deals) {
				em.getTransaction().begin();
				em.persist(deals);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public void updateDeals(Deals deals) throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		try {
			if (null != deals) {
				em.getTransaction().begin();
				em.merge(deals);
				em.getTransaction().commit();
			}
		} catch (Exception hdbex) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	public List<Deals> getAllDeals() throws ServletException {
		EntityManager em = new HDBHandler().getEntitiyManager();
		List<Deals> results = em.createNamedQuery("AllDeals").getResultList();
		em.close();
		return results;

	}
	
	public List<Deals> getDealsByLocation(Double lat, Double longt) throws ServletException {
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByLocation(lat - 0.002,
				lat + 0.002, longt - 0.002, longt + 0.002);
		List<Deals> results = new ArrayList<Deals>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("ItemByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Deals> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
		}
		return results;
	}
	
	public List<Deals> getDealsByVID(Double lat, Double longt) throws ServletException {
		VendorAPI api = new VendorAPI();

		List<Vendor> entries = api.getVendorByLocation(lat - 0.002,
				lat + 0.002, longt - 0.002, longt + 0.002);
		List<Deals> results = new ArrayList<Deals>();
		if (entries.size() > 0) {
			Iterator<Vendor> vendorIterator = entries.iterator();

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				EntityManager em = new HDBHandler().getEntitiyManager();
				Query query = em.createNamedQuery("DealsByVID");
				query.setParameter("vendorId", vendor.getVendorId());
				List<Deals> subResults = query.getResultList();
				em.close();
				if (subResults.size() > 0) {
					results.addAll(subResults);
				}
			}
		}
		return results;
	}
	
//	public List<Deals> getVendorByArea(String area) throws ServletException {
//		EntityManager em = new HDBHandler().getEntitiyManager();
//		Query query= em.createNamedQuery("VendorByArea");
//		query.setParameter("area", area);
//		List<Deals> results = query.getResultList();
//		em.close();
//		return results;
//	}
}
