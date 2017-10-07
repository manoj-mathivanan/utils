function viewDealDetails(categoryID){
	
	var dealDetails = new sap.ui.commons.layout.VerticalLayout();
	
	var dealDetailsBtnDiv = new sap.ui.commons.layout.HorizontalLayout().addStyleClass('dealDetailsBtnDiv');
		
	var selectedCategory = categoryID;
	var itemData = "";
			
		var oSegmentedButton = new sap.ui.commons.SegmentedButton({id:"SBText",
			buttons:[new sap.ui.commons.Button({id:"gridView",icon:"images/gridIcon.png"}),
			         new sap.ui.commons.Button({id:"listView",icon:"images/listIcon.png"})
			         ]});
		
		oSegmentedButton.setSelectedButton("gridView");
		oSegmentedButton.attachSelect(function(oEvent) {
			//alert("Selected button id: "+oEvent.getParameters().selectedButtonId);
			if(oEvent.getParameters().selectedButtonId == "listView" ){
				sap.ui.getCore().byId('categoryListItemDiv').destroy();
				var categoryListItemDiv = new sap.ui.core.HTML("categoryListItemDiv",{
     				content : "<div>"+buildDealsItemList(itemData,selectedCategory)+"</div>"
     			});
            	 dealDetails.addContent(categoryListItemDiv);
			}else{
				sap.ui.getCore().byId('categoryListItemDiv').destroy();
				var categoryListItemDiv = new sap.ui.core.HTML("categoryListItemDiv",{
     				content : "<div>"+buildDealsItem(itemData,selectedCategory)+"</div>"
     			});
            	 dealDetails.addContent(categoryListItemDiv);
			}
			
		});
		
		var oSearch = new sap.ui.commons.SearchField("simpleSearch", {
			enableListSuggest: false,
			startSuggestion: 0,
			//tooltip: "Search by SubCategory, Item, Brand",
			search: function(oEvent){
				//alert("Search triggered: " + oEvent.getParameter("query"));
			},
			suggest: function(oEvent){
         		
         		var sVal = oEvent.getParameter("value");
         		var aSuggestions = filterDeal(sVal); // Find the deal
         	}
		});
		var oRichTooltip = new sap.ui.commons.RichTooltip({
			text : "You can Search by SubCategory, Item Name or Brand Name",
			title: "Search",
			//imageSrc : "images/Tip.gif"
		});
		oSearch.setTooltip(oRichTooltip);
		
		dealDetailsBtnDiv.addContent(oSegmentedButton);
		dealDetailsBtnDiv.addContent(oSearch);
		dealDetails.addContent(dealDetailsBtnDiv);
		
		
		
		 jQuery.ajax({
             url: "/DealsAroundMe/Services/",
             headers: {
                 Accept: "application/json",
                 "Access-Control-Allow-origin": "*"
             },
             dataType: "json",
             type: "GET",
             async: false,
             beforeSend: function (xhr) {
                 xhr.setRequestHeader('X-SAP-SERVICES', 'ITEMS');
             },
             success: function (data, textStatus, jqXHR) {
            	 var vendorId = localStorage.getItem('VendorID');
            	 var selectedObjs = sql({
                     select: "categoryID,category,itemId,subCategory,item,brand,description,price,discount,dealType,startDate,endDate",
                     from: data,
                     where: [{
                         "KEY": "vendorId",
                         "OPERATOR": "=",
                         "VALUE": vendorId
                     }],
                     conditions: [{
                         "Condition1": "0",
                         "CONDITION": "Or",
                         "Condition2": "1"
                     }]
                 });
                 data = selectedObjs;
                 //localStorage.setItem('dealItems', JSON.stringify(data));
            	 itemData = data;
            	 var categoryListItemDiv = new sap.ui.core.HTML("categoryListItemDiv",{
     				content : "<div>"+buildDealsItem(data,selectedCategory)+"</div>"
     			});
            	 dealDetails.addContent(categoryListItemDiv);
            	
                 
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 alert("error occurred");
             }
         });
		
		
	
	
	
	
	return dealDetails;

	

}

function buildDealsItem(data,selectedCategory ){
	if(selectedCategory == 1001){
		var SubCategories = ["Bottom wear","Indian wear","Top wear","Western wear","Winter wear","Others"];
		var subCategoryImg = ["bottomwear","indianwear","topwear","western","winterwear","ClothOthers"];
		
	}else if(selectedCategory == 1002){
		var SubCategories = ["Landline phone","Smart phone","Tablet","Accessories"];
		var subCategoryImg = ["landline","smartPhone","tablet","phoneAccessories"];
	}
	else if(selectedCategory == 1003){
		var SubCategories = ["Bleach/Facial","Hair care","Hand/Foot care","Makeup", "Massage", "Others"];
		var subCategoryImg = ["bleachFacial","hairCare","handFootCare","makeUp", "massage", "spaOthers"];
	}
	else if(selectedCategory == 1004){
		var SubCategories = ["Acadamic","Biography Autobiography","Children","Fiction", "Others"];
		var subCategoryImg = ["book1","bookOthers","book1","bookOthers", "book1"];
	}
	else if(selectedCategory == 1005){
		var SubCategories = ["Flip-flops","Heels","Sandles","Shoes","Others"];
		var subCategoryImg = ["flipFlop","heels","sandles","shoes","footwearOthers"];
	}
	else if(selectedCategory == 1006){
		var SubCategories = ["Bags/Wallets","Belts","Jewellery","SunGlasses","Watches","Others"];
		var subCategoryImg = ["bags","belt","jewellery","sunglass","watch","others_icon"];
	}
	var dealDiv = "";
	 for(var i=0; i< data.length ; i++){
    	 if(data[i].categoryID == selectedCategory){
    		var imgClass = SubCategories.indexOf(data[i].subCategory);
    		
    		 dealDiv = dealDiv + "<div class='DealDiv' id='"+i+"' title='Click to edit the deal'>" +
    		 		"<div class='itemName'>" +
    		 		"<div class='subicondiv "+subCategoryImg[imgClass]+"'></div>" +
    		 				"<div class='itemNameTxt'>"+data[i].subCategory+"</div>" +
    		 				"<div class='editDiv'></div>" +
    		 		"</div>" +
    		 		"<div class='passJson' style='display:none'>"+JSON.stringify(data[i])+"</div>"+
    		 		"<table style='width:200px'>" +
    		 		"<tr>  " +
    		 			"<td>Item</td>" +
    		 			"<td>"+data[i].item+"</td> " +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td>Brand</td>" +
    		 			"<td>"+data[i].brand+"</td>" +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td>Price</td>" +
    		 			"<td>"+data[i].price+"</td>" +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td>Discount</td>" +
    		 			"<td>"+data[i].discount+"</td>" +
    		 		"</tr>" +
    		 		"</table>"+
    		 		"</div>";
    		 
    		 
    	 }
     }
	 return dealDiv;
}

function buildDealsItemList(data,selectedCategory ){
	if(selectedCategory == 1001){
		var SubCategories = ["Bottom wear","Indian wear","Top wear","Western wear","Winter wear","Others"];
		var subCategoryImg = ["bottomwear","indianwear","topwear","western","winterwear","ClothOthers"];
		
	}else if(selectedCategory == 1002){
		var SubCategories = ["Landline phone","Smart phone","Tablet","Accessories"];
		var subCategoryImg = ["landline","smartPhone","tablet","phoneAccessories"];
	}
	else if(selectedCategory == 1003){
		var SubCategories = ["Bleach/Facial","Hair care","Hand/Foot care","Makeup", "Massage", "Others"];
		var subCategoryImg = ["bleachFacial","hairCare","handFootCare","makeUp", "massage", "spaOthers"];
	}
	else if(selectedCategory == 1004){
		var SubCategories = ["Acadamic","Biography Autobiography","Children","Fiction", "Others"];
		var subCategoryImg = ["book1","bookOthers","book1","bookOthers", "book1"];
	}
	else if(selectedCategory == 1005){
		var SubCategories = ["Flip-flops","Heels","Sandles","Shoes","Others"];
		var subCategoryImg = ["flipFlop","heels","sandles","shoes","footwearOthers"];
	}
	else if(selectedCategory == 1006){
		var SubCategories = ["Bags/Wallets","Belts","Jewellery","SunGlasses","Watches","Others"];
		var subCategoryImg = ["bags","belt","jewellery","sunglass","watch","others_icon"];
	}
	var dealDiv = "";
	 for(var i=0; i< data.length ; i++){
    	 if(data[i].categoryID == selectedCategory){
    		var imgClass = SubCategories.indexOf(data[i].subCategory);
    		 dealDiv = dealDiv + "<div class='DealDivList'>" +
    		 		"<div class='itemNameList'>" +
    		 		"<div class='subicondivList "+subCategoryImg[imgClass]+"'></div>" +
    		 				"<div class='itemNameTxtList'>"+data[i].subCategory+"</div>" +
    		 		"</div>" +
    		 		"<div class='itmDetList'><table style='width:200px;margin: 3% 0;'>" +
    		 		"<tr>  " +
    		 			"<td><b>Item :</b></td>" +
    		 			"<td>"+data[i].item+"</td> " +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td><b>Brand : </b></td>" +
    		 			"<td>"+data[i].brand+"</td>" +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td><b>Price :</b></td>" +
    		 			"<td>"+data[i].price+"</td>" +
    		 		"</tr>" +
    		 		"<tr>" +
    		 			"<td><b>Discount :</b></td>" +
    		 			"<td>"+data[i].discount+"</td>" +
    		 		"</tr>" +
    		 		"</table></div>"+
    		 		"<div class='descList'> <b>Description</b> <br>"+data[i].description+"</div>"+
    		 		"<div class='typeDiv'>"+data[i].dealType+"</div>"+
    		 		"<div class='dateDiv'>"+data[i].startDate+"<br><label style='margin: 0 0 0 25%;'>TO</label><br>"+data[i].endDate+"</div>"+
    		 		"</div>";
    		 
    		 
    	 }
     }
	 return dealDiv;
}

function filterDeal(sVal){
	//console.log(sVal);
	
	var list = [];
	var found = 0;
	$('#categoryListItemDiv .DealDiv').each(function(){
		$(this).show();
		
		
		if($(this).find('.itemNameTxt').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1 
				&& $(this).find('table tbody tr:nth-child(1) td:nth-child(2)').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1
				&& $(this).find('table tbody tr:nth-child(2) td:nth-child(2)').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1 ){
			$(this).hide();
		}
		
		
	});
	
	$('#categoryListItemDiv .DealDivList').each(function(){
		$(this).show();
		
		
		if($(this).find('.itemNameTxtList').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1 
				&& $(this).find('table tbody tr:nth-child(1) td:nth-child(2)').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1
				&& $(this).find('table tbody tr:nth-child(2) td:nth-child(2)').text().toLowerCase().indexOf(sVal.toLowerCase()) == -1 ){
			$(this).hide();
		}
		
		
	});
			
	for (var i = 0, count = 0; i < list.length; i++) {
	    value = list[i];
	    $('.DealDiv').hide();
	   
	    if (value.toLowerCase().indexOf(sVal.toLowerCase()) != -1) {
	        
	        $('#storeNotFound').remove();
	    	$('#listContainer'+i).show();
	    	found = 1;

	    }
	}
}

function clickedDeal(i,data){
	//console.log("hi");
	//console.log(data.brand);
}