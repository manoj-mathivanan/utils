package com.sap.dam.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sap.dam.persistence.Category;
import com.sap.dam.persistence.Deals;
import com.sap.dam.persistence.Item;
import com.sap.dam.persistence.Subcategory;
import com.sap.dam.persistence.Vendor;
import com.sap.dam.persistence.api.CategoryAPI;
import com.sap.dam.persistence.api.DealsAPI;
import com.sap.dam.persistence.api.ItemAPI;
import com.sap.dam.persistence.api.SubcategoryAPI;
import com.sap.dam.persistence.api.VendorAPI;

public class DealsAroundMe extends HttpServlet {
	Gson gsonAppBuilder = new GsonBuilder().serializeNulls().create();
	private static final long serialVersionUID = 1L;

	public void init() {
		System.out.println("servlet started");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet get called");
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version");
		response.setHeader("Access-Control-Allow-Origin", "*");

		System.out.println("Calling Service :"+request.getHeader("X-SAP-SERVICES"));
		if ("VENDOR".equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			VendorAPI api = new VendorAPI();
			List<Vendor> entries = api.getAllVendor();

			Iterator<Vendor> vendorIterator = entries.iterator();
			JsonArray vendors = new JsonArray();
			JsonElement serviceJsonElement;

			while (vendorIterator.hasNext()) {
				Vendor vendor = vendorIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(vendor);
				vendors.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(vendors.size()));
			response.getWriter().write(vendors.toString());
		} else if ("CATEGORY".equalsIgnoreCase(request
				.getHeader("X-SAP-SERVICES"))) {
			CategoryAPI api = new CategoryAPI();
			List<Category> entries = api.getAllCategory();

			Iterator<Category> categoryIterator = entries.iterator();
			JsonArray categorys = new JsonArray();
			JsonElement serviceJsonElement;

			while (categoryIterator.hasNext()) {
				Category category = categoryIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(category);
				categorys.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(categorys.size()));
			response.getWriter().write(categorys.toString());
		} else if ("SUBCATEGORY".equalsIgnoreCase(request
				.getHeader("X-SAP-SERVICES"))) {
			SubcategoryAPI api = new SubcategoryAPI();
			List<Subcategory> entries = api.getAllSubcategory();

			Iterator<Subcategory> subcategoryIterator = entries.iterator();
			JsonArray subcategorys = new JsonArray();
			JsonElement serviceJsonElement;

			while (subcategoryIterator.hasNext()) {
				Subcategory subcategory = subcategoryIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(subcategory);
				subcategorys.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(subcategorys.size()));
			response.getWriter().write(subcategorys.toString());
		} else if ("ITEMS"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			ItemAPI api = new ItemAPI();
			List<Item> entries = api.getAllItems();

			Iterator<Item> itemIterator = entries.iterator();
			JsonArray items = new JsonArray();
			JsonElement serviceJsonElement;

			while (itemIterator.hasNext()) {
				Item item = itemIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(item);
				items.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(items.size()));
			response.getWriter().write(items.toString());
		} 
		else if ("ITEMS_AREA"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			try
			{
				ItemAPI api = new ItemAPI();
				List<String> entries = api.getAllArea();

				Iterator<String> itemIterator = entries.iterator();
				JsonArray items = new JsonArray();
				JsonElement serviceJsonElement;

				while (itemIterator.hasNext()) {
					String item = (String)itemIterator.next();
					serviceJsonElement = gsonAppBuilder.toJsonTree(item);
					items.add(serviceJsonElement);
				}
				response.setHeader("count", String.valueOf(items.size()));
				response.getWriter().write(items.toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
		}else if ("DEALS"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			DealsAPI api = new DealsAPI();
			List<Deals> entries = api.getAllDeals();

			Iterator<Deals> dealsIterator = entries.iterator();
			JsonArray deals = new JsonArray();
			JsonElement serviceJsonElement;

			while (dealsIterator.hasNext()) {
				Deals deal = dealsIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(deal);
				deals.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(deals.size()));
			response.getWriter().write(deals.toString());
		}else if ("CATEGORY_BY_LOCATION".equalsIgnoreCase(request
				.getHeader("X-SAP-SERVICES"))) {

			Double latitude = Double.parseDouble(request.getHeader("latitude"));
			Double longitude = Double.parseDouble(request
					.getHeader("longitude"));
			// Unit
			CategoryAPI api = new CategoryAPI();
			List<Category> entries = api.getCategoryByLocation(latitude,
					longitude);

			Iterator<Category> categoryIterator = entries.iterator();
			JsonArray categorys = new JsonArray();
			JsonElement serviceJsonElement;

			while (categoryIterator.hasNext()) {
				Category category = categoryIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(category);
				categorys.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(categorys.size()));
			response.getWriter().write(categorys.toString());
		} else if ("CATEGORY_BY_AREA".equalsIgnoreCase(request
				.getHeader("X-SAP-SERVICES"))) {

			String area = request.getHeader("area");
			// Unit
			CategoryAPI api = new CategoryAPI();
			List<Category> entries = api.getCategoryByArea(area);

			Iterator<Category> categoryIterator = entries.iterator();
			JsonArray categorys = new JsonArray();
			JsonElement serviceJsonElement;

			while (categoryIterator.hasNext()) {
				Category category = categoryIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(category);
				categorys.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(categorys.size()));
			response.getWriter().write(categorys.toString());
		}else if ("SUBCATEGORY_BY_CID".equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			int cid = Integer.parseInt(request.getHeader("categoryid"));
			SubcategoryAPI api = new SubcategoryAPI();
			List<Subcategory> entries = api.getSubcategoryByCID(cid);

			Iterator<Subcategory> subcategoryIterator = entries.iterator();
			JsonArray subcategorys = new JsonArray();
			JsonElement serviceJsonElement;

			while (subcategoryIterator.hasNext()) {
				Subcategory subcategory = subcategoryIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(subcategory);
				subcategorys.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(subcategorys.size()));
			response.getWriter().write(subcategorys.toString());
		} else if ("ITEM_BY_SID".equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			int sid = Integer.parseInt(request.getHeader("subcategorryid"));
			ItemAPI api = new ItemAPI();
			List<Item> entries = api.getItemsBySID(sid);

			Iterator<Item> itemIterator = entries.iterator();
			JsonArray items = new JsonArray();
			JsonElement serviceJsonElement;

			while (itemIterator.hasNext()) {
				Item item = itemIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(item);
				items.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(items.size()));
			response.getWriter().write(items.toString());
		}  else if ("ITEM_BY_IID".equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			int iid = Integer.parseInt(request.getHeader("itemid"));
			ItemAPI api = new ItemAPI();
			List<Item> entries = api.getItemsByIID(iid);

			Iterator<Item> itemIterator = entries.iterator();
			JsonArray items = new JsonArray();
			JsonElement serviceJsonElement;

			while (itemIterator.hasNext()) {
				Item item = itemIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(item);
				items.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(items.size()));
			response.getWriter().write(items.toString());
		}
		else if ("ITEMS_BY_LOCATION"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			Double latitude = Double.parseDouble(request.getHeader("latitude"));
			Double longitude = Double.parseDouble(request.getHeader("longitude"));
			ItemAPI api = new ItemAPI();
			List<Item> entries = api.getItemsbyLoc(latitude, longitude);

			Iterator<Item> itemIterator = entries.iterator();
			JsonArray items = new JsonArray();
			JsonElement serviceJsonElement;

			while (itemIterator.hasNext()) {
				Item item = itemIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(item);
				items.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(items.size()));
			response.getWriter().write(items.toString());
		}
		else if ("ITEMS_BY_AREA"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			String area = request.getHeader("area");
			
			ItemAPI api = new ItemAPI();
			List<Item> entries = api.getItemsbyArea(area);

			Iterator<Item> itemIterator = entries.iterator();
			JsonArray items = new JsonArray();
			JsonElement serviceJsonElement;

			while (itemIterator.hasNext()) {
				Item item = itemIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(item);
				items.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(items.size()));
			response.getWriter().write(items.toString());
		}
		else if ("DEALS_BY_LOCATION"
				.equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			Double latitude = Double.parseDouble(request.getHeader("latitude"));
			Double longitude = Double.parseDouble(request.getHeader("longitude"));
			DealsAPI api = new DealsAPI();
			List<Deals> entries = api.getDealsByLocation(latitude, longitude);

			Iterator<Deals> dealsIterator = entries.iterator();
			JsonArray deals = new JsonArray();
			JsonElement serviceJsonElement;

			while (dealsIterator.hasNext()) {
				Deals deal = dealsIterator.next();
				serviceJsonElement = gsonAppBuilder.toJsonTree(deal);
				deals.add(serviceJsonElement);
			}
			response.setHeader("count", String.valueOf(deals.size()));
			response.getWriter().write(deals.toString());
		}else {
			response.setStatus(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		System.out.println("servlet post called");
		Gson gson = new GsonBuilder().serializeNulls().create();
		InputStream myInputStream = request.getInputStream();
		InputStreamReader reader = new InputStreamReader(myInputStream);
		BufferedReader br = new BufferedReader(reader);
		String postBody = br.readLine();

		JsonParser parser = new JsonParser();
		JsonElement elem = parser.parse(postBody);
		JsonObject jsonObject = new JsonObject();
		jsonObject = elem.getAsJsonObject();

		if (request.getHeader("X-SAP-SERVICES").equalsIgnoreCase("VENDOR")) {
			Vendor entry = gson.fromJson(jsonObject, Vendor.class);
			VendorAPI uApi = new VendorAPI();
			uApi.CreateVendor(entry);
		} else if (request.getHeader("X-SAP-SERVICES").equalsIgnoreCase(
				"CATEGORY")) {
			Category entry = gson.fromJson(jsonObject, Category.class);
			CategoryAPI uApi = new CategoryAPI();
			uApi.CreateCategory(entry);
		} else if (request.getHeader("X-SAP-SERVICES").equalsIgnoreCase(
				"SUBCATEGORY")) {
			Subcategory entry = gson.fromJson(jsonObject, Subcategory.class);
			SubcategoryAPI uApi = new SubcategoryAPI();
			uApi.CreateSubcategory(entry);
		} else if (request.getHeader("X-SAP-SERVICES")
				.equalsIgnoreCase("ITEMS")) {
			Item entry = gson.fromJson(jsonObject, Item.class);
			ItemAPI uApi = new ItemAPI();
			uApi.CreateItem(entry);
		}else if (request.getHeader("X-SAP-SERVICES")
				.equalsIgnoreCase("ITEMSEDIT")) {
			Item entry = gson.fromJson(jsonObject, Item.class);
			ItemAPI uApi = new ItemAPI();
			uApi.updateItem(entry);
		}		
		else if (request.getHeader("X-SAP-SERVICES")
				.equalsIgnoreCase("DEALS")) {
			Deals entry = gson.fromJson(jsonObject, Deals.class);
			DealsAPI uApi = new DealsAPI();
			uApi.CreateDeals(entry);
		} 
		else if (request.getHeader("X-SAP-SERVICES")
				.equalsIgnoreCase("VENDOR_USER")){
			Vendor entry = gson.fromJson(jsonObject, Vendor.class);
			boolean avaliableFlag =false;
					
				VendorAPI api = new VendorAPI();
				List<Vendor> entries = api.getAllVendor();

				Iterator<Vendor> vendorIterator = entries.iterator();
				JsonArray vendors = new JsonArray();
				JsonElement serviceJsonElement;

				while (vendorIterator.hasNext()) {
					Vendor vendor = vendorIterator.next();
					
					if(entry.getUsername().equalsIgnoreCase(vendor.getUsername()))
					{
						avaliableFlag =true;
						serviceJsonElement = gsonAppBuilder.toJsonTree(vendor);
						vendors.add(serviceJsonElement);
						response.getWriter().write(vendors.toString());
						break;
					}
					
					
				}
		}
				
				/*if(loginFlag)
				{
					response.getWriter().write("loginSuccess");
				}
				else
				{
					response.getWriter().write("loginFailure");
				}*/
//				response.setHeader("count", String.valueOf(vendors.size()));
//				response.getWriter().write(vendors.toString());
		
		else if (request.getHeader("X-SAP-SERVICES")
				.equalsIgnoreCase("VENDOR_LOGIN")) {
			Vendor entry = gson.fromJson(jsonObject, Vendor.class);
			boolean loginFlag =false;
			//VendorAPI uApi = new VendorAPI();
//			String loginResult = uApi.validateLogin(entry);
//			//response.getWriter().write("<LOGINRESULT>loginResult</LOGINRESULT>");
//			response.getWriter().write(loginResult);
			
				VendorAPI api = new VendorAPI();
				List<Vendor> entries = api.getAllVendor();

				Iterator<Vendor> vendorIterator = entries.iterator();
				JsonArray vendors = new JsonArray();
				JsonElement serviceJsonElement;

				while (vendorIterator.hasNext()) {
					Vendor vendor = vendorIterator.next();
					
					if(entry.getUsername().equalsIgnoreCase(vendor.getUsername()) && entry.getPassword().equals(vendor.getPassword()))
					{
						loginFlag =true;
						serviceJsonElement = gsonAppBuilder.toJsonTree(vendor);
						vendors.add(serviceJsonElement);
						response.getWriter().write(vendors.toString());
						break;
					}
					
					System.out.println("vendor :"+vendor.getUsername());
					System.out.println("vendor :"+vendor.getPassword());
					
				}
				
				/*if(loginFlag)
				{
					response.getWriter().write("loginSuccess");
				}
				else
				{
					response.getWriter().write("loginFailure");
				}*/
//				response.setHeader("count", String.valueOf(vendors.size()));
//				response.getWriter().write(vendors.toString());
		}
		else {	
			response.setStatus(404);
			return;
		}
		response.setStatus(201);
	}
/*	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version");
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Servelet Delete called");
		Gson gson = new GsonBuilder().serializeNulls().create();
		InputStream myInputStream = request.getInputStream();
		InputStreamReader reader = new InputStreamReader(myInputStream);
		BufferedReader br = new BufferedReader(reader);
		String postBody = br.readLine();

		JsonParser parser = new JsonParser();
		JsonElement elem = parser.parse(postBody);
		JsonObject jsonObject = new JsonObject();
		jsonObject = elem.getAsJsonObject();
		if ("ITEM_DELETE".equalsIgnoreCase(request.getHeader("X-SAP-SERVICES"))) {
			Item entry = gson.fromJson(jsonObject, Item.class);
			ItemAPI uApi = new ItemAPI();
			uApi.deleteItem(entry);
		}
		else {	
			response.setStatus(404);
			return;
		}
		response.setStatus(201);
		
	}*/
	protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version");
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Servelet PUT called");
		
		
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
	}
}
