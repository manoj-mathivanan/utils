function buildAddCategory(){
	var addCategoryList = new sap.ui.commons.layout.VerticalLayout()
	.addStyleClass('categoryList');
	
	var categoryTxt1 =  new sap.ui.core.HTML({
		content : "<div style='font-size: 18px;'>Choose Category</div>"
				
	});
	addCategoryList.addContent(categoryTxt1);
	
	for (var i = 1; i < 7; i++) {
		switch(i){
		case 1 : var imgIcon = "ClothIcon";
				 var categoryName = "Chothing";
				 var catID = 1001;
				break;
		case 2 : var imgIcon = "phoneIcon";
		         var categoryName = "Mobile Phone";
		         var catID = 1002;
		         break;
		case 3 : var imgIcon = "spaIcon";
		         var categoryName = "Spa";
		         var catID = 1003;
		         break;
		case 4 : var imgIcon = "booksIcon";
		         var categoryName = "Books";
		         var catID = 1004;
		         break;
		case 5 : var imgIcon = "footIcon";
		         var categoryName = "Footwear";
		         var catID = 1005;
		         break;
		case 6 : var imgIcon = "othersIcon";
		        var categoryName = "Others";
		        var catID = 1006;
		        break;
		}
	if(i == 1){
	var categoryListItems =  new sap.ui.core.HTML({
		content : 
				"<div id='"+catID+"' class='categoryListItem categoryListItemH'>" +
				"<div class='icondiv "+imgIcon +"'>"+
				"</div>"+
				"<div class='categoryName'>"+categoryName+"</div>"+
				
				"</div>"
		});
	}
	else{
			var categoryListItems =  new sap.ui.core.HTML({
				content : 
						"<div id='"+catID+"' class='categoryListItem'>" +
						"<div class='icondiv "+imgIcon +"'>"+
						"</div>"+
						"<div class='categoryName'>"+categoryName+"</div>"+
						
						"</div>"
			});
		}
	
	
	addCategoryList.addContent(categoryListItems);
	}
	
	return  addCategoryList;
	
	
	
}

function buildAddDealMiddle(catID){
	var addCategoryDeatils = new sap.ui.commons.layout.VerticalLayout()
	.addStyleClass('addCategoryDeatils');
	
	var SubcategoryTxt1 =  new sap.ui.core.HTML({
		content : "<div style='font-size: 21px;margin: 10px 10px 10px 18px;'>Choose SubCategory</div>"					
				
	});
	addCategoryDeatils.addContent(SubcategoryTxt1);
	if(catID == 1001){
		var SubCategories = ["Bottom wear","Indian wear","Top wear","Western wear","Winter wear","Others"];
		var subCategoryImg = ["bottomwear","indianwear","topwear","western","winterwear","ClothOthers"];
		var sItem = [{name: "Jeans"},{name: "Shirt"},{name: "Shorts"},{name: "Saree"},{name: "Kurtha"},{name: "Bridal Makeup"},{name: "Sweat shirt"},
						{name: "Leggings"},{name: "T shirt"},{name: "Ladies Tops"},{name: "Night wear"},
						
		             ];
		var sBrand = [
						{name: "Levi's"},
						{name: "Peter England"},
						{name: "John Players"},
						{name: "Puma"},
						{name: "W"},
						{name: "Biba"},
						{name: "Jealous 21"},
						{name: "UCB"},
						{name: "Proline"},
						{name: "Duke"},
						{name: "The Vanca"},
						{name: "Louis Philipe"},
						{name: "Wills Lifestyle"},
						{name: "Aurilie"},
						
					];
		
	}else if(catID == 1002){
		var SubCategories = ["Landline phone","Smart phone","Tablet","Accessories"];
		var subCategoryImg = ["landline","smartPhone","tablet","phoneAccessories"];
		var sItem = [{name: "Mobile Phone"},{name: "Tablet"},{name: "Ear phones"},{name: "Headset"},{name: "Charger"},{name: "USB Cabel"},					
		             ];
		var sBrand = [
						{name: "Nokia"},
						{name: "Samsung"},
						{name: "SONY"},
						{name: "Motorola"},
						{name: "Siemens"},
						{name: "Micromax"},
						{name: "LG"},
						{name: "UCB"},
						
					];
	}
	else if(catID == 1003){
		var SubCategories = ["Bleach/Facial","Hair care","Hand/Foot care","Makeup", "Massage", "Others"];
		var subCategoryImg = ["bleachFacial","hairCare","handFootCare","makeUp", "massage", "spaOthers"];
		var sItem = [{name: "Bridal makeup"},{name: "Hydrating Facial"},{name: "Ayurvedic Facial"},{name: "Traditional Thai Massage"},{name: "Body Massage"},{name: "Nail Art"},
		             {name: "Hair cut"},{name: "Hair Treatment"},{name: "Head Massage"},{name: "Manicure"},{name: "Nail Repair"},{name: "Waxing"},
		             {name: "Threading"},
		             ];
		var sBrand = [
						{name: "VLCC"},
						{name: "Lakme"},
						{name: "Banjaras"},
						{name: "L'oreal Paris"},
						{name: "Garnier"},
						{name: "Maybelline"},
						
					];
	}
	else if(catID == 1004){
		var SubCategories = ["Acadamic","Biography Autobiography","Children","Fiction", "Others"];
		var subCategoryImg = ["book1","bookOthers","book1","bookOthers", "book1"];
		var sItem = [{name: "Acadamic"},{name: "Biography"},{name: "Autobiography"},{name: "Malgudi days"},{name: "Harry Potter"},{name: "Sherlock  Holms"},
		             
		             ];
		var sBrand = [
						{name: "McGraw-Hill Education"},
						{name: "Random House"},
						{name: "Shueisha"},
						{name: "Wolters Kluwer"},
						
						
					];
	}
	else if(catID == 1005){
		var SubCategories = ["Flip-flops","Heels","Sandles","Shoes","Others"];
		var subCategoryImg = ["flipFlop","heels","sandles","shoes","footwearOthers"];
		var sItem = [{name: "Flip-flops"},{name: "Heels"},{name: "Sandles"},{name: "Shoes"},{name: "Traditional Slipper"},{name: "Socks"},		             
		             ];
		var sBrand = [
						{name: "Puma"},
						{name: "Addidas"},
						{name: "Catwalk"},
						{name: "Nike"},
						{name: "Bata"},
						{name: "Reebok"},
						
					];
	}
	else if(catID == 1006){
		var SubCategories = ["Bags/Wallets","Belts","Jewellery","SunGlasses","Watches","Others"];
		var subCategoryImg = ["bags","belt","jewellery","sunglass","watch","others_icon"];
		var sItem = [{name: "Bags"},{name: "Wallets"},{name: "Necklace"},{name: "SunGlasses"},{name: "Watches"},{name: "Earings"},		             
		             ];
		var sBrand = [
						{name: "Estela"},
						{name: "Fastrack"},
						{name: "Baggit"},
						{name: "Ray ban"},
						{name: "Titan"},
						{name: "Casio"},
						
					];
	}
	var SubcategorysDiv =  new sap.ui.core.HTML({
		content : "<div class='SubcategorysDiv'>" +
					SubCategoriesBuild(SubCategories, subCategoryImg)+
				"</div>"
								
				
	});
	
	var sDealType = [{name: "SALE"},{name: "Special Offer"},{name: "End of season sale"},{name: "Weekend OFF"},{name: "Offer"},{name: "Buy one Get One"},
					
	             ];
	

	

	
	var time = new Date();
	time.setDate(time.getDate()+30);
	
	var oModel = new sap.ui.model.json.JSONModel();
	oModel.setData({
		dateValue: new Date(),
		endDate: time
	});
	sap.ui.getCore().setModel(oModel);
	
	
	addCategoryDeatils.addContent(SubcategorysDiv);
	
	 var oLayout = new sap.ui.commons.form.GridLayout("L33");
	 var oForm = new sap.ui.commons.form.Form("F11", {
        
         layout: oLayout,
         formContainers: [
             
             new sap.ui.commons.form.FormContainer("C22", {
                 title: new sap.ui.commons.Title({
                     text: "Add Items Details",
                     
                 }),
                 formElements: [
                    
                     new sap.ui.commons.form.FormElement({
                         label: "Item Name*",
                         tooltip: "Eg: Jeans, Shirt",
                         
                         fields: [
                                  //new sap.ui.commons.TextField("ItemName1",{tooltip: "Eg: Jeans, Shirt",})
                                  new sap.ui.commons.AutoComplete("ItemName1",{
                                		tooltip: "Enter Item name",
                                		maxPopupItems: 5,
                                		suggest: function(oEvent){
                                			this.destroyItems();
                                			
                                			for(var i=0; i<sItem.length; i++){
                                				
                                					this.addItem(new sap.ui.core.ListItem({text: sItem[i].name}));
                                					
                                				
                                			}
                                		}
                                	})
                                  ]
                     }),
                     new sap.ui.commons.form.FormElement({
                         label: new sap.ui.commons.Label({
                             text: "Brand*",
                             
                         }),
                         fields: [
                                /*  new sap.ui.commons.TextField("Brand1", {
	                        	 tooltip: "Eg: Levis",
	                             layoutData: new sap.ui.core.VariantLayoutData({
                                 multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                     hCells: "2"
                                 })]
                             			})
                                  })*/
                                 new sap.ui.commons.AutoComplete("Brand1",{
                                		tooltip: "Eg: Levis",
                                		maxPopupItems: 5,
                                		suggest: function(oEvent){
                                			this.destroyItems();
                                			
                                			for(var i=0; i<sBrand.length; i++){
                                				
                                					this.addItem(new sap.ui.core.ListItem({text: sBrand[i].name}));
                                					
                                				
                                			}
                                		}
                                	})
                                  ]
                     }),
                     new sap.ui.commons.form.FormElement({
                         label: "Description",
                         fields: [new sap.ui.commons.TextField("Description1",{tooltip: "Enter Description of the Item",})]
                     }),
                     new sap.ui.commons.form.FormElement({
                         label: "MRP Price*",

                         fields: [new sap.ui.commons.TextField("Price1",{tooltip: "Enter MRP Price of the Item",})]
                     }),
                     new sap.ui.commons.form.FormElement({
                         label: "Offer*",
                         fields: [new sap.ui.commons.TextField("Discount1",{tooltip: "Eg: 20%, 500/- Rs",})]
                     }),
                     new sap.ui.commons.form.FormElement({
                         label: "Deal Type",
                         fields: [
                                  //new sap.ui.commons.TextField("DealType1", {tooltip: "Eg:SALE, Winter Offer, ",})
                                    new sap.ui.commons.AutoComplete("DealType1",{
                                		tooltip: "Eg:SALE, Winter Offer",
                                		maxPopupItems: 5,
                                		suggest: function(oEvent){
                                			this.destroyItems();
                                			
                                			for(var i=0; i<sDealType.length; i++){
                                				
                                					this.addItem(new sap.ui.core.ListItem({text: sDealType[i].name}));
                                					
                                				
                                			}
                                		}
                                	})
                                  ]
                     })
                 ],
                 layoutData: new sap.ui.core.VariantLayoutData({
                     multipleLayoutData: [new sap.ui.commons.form.GridContainerData({
                         halfGrid: true
                     })]
                 })
             }),
             new sap.ui.commons.form.FormContainer("C33", {
                 title: new sap.ui.commons.Title({
                     text: "Deal Period",
                     emphasized: true
                 }),
                
                 //expandable: true,
                 formElements: [
                     
                     new sap.ui.layout.form.FormElement({
							label: new sap.ui.commons.Label({text:"Deal Starts on",
								layoutData: new sap.ui.core.VariantLayoutData({
									multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
									                  	     new sap.ui.layout.form.GridElementData({hCells: "3"})]
									})
								}),
							fields: [new sap.ui.commons.DatePicker("startDate",{
				        		width: "15em",
				        		value: {
				        			path: "/dateValue",
				        			type: new sap.ui.model.type.Date({style: "long"})
				        	}
				        	})
							],
							layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
						}),
						new sap.ui.layout.form.FormElement({
							label: new sap.ui.commons.Label({text:"Deal Ends on",
								layoutData: new sap.ui.core.VariantLayoutData({
									multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
									                  	     new sap.ui.layout.form.GridElementData({hCells: "3"})]
									})
								}),
							fields: [
							         new sap.ui.commons.DatePicker("endDate",{
							        		width: "15em",
							        		value: {
							        			path: "/endDate",
							        			type: new sap.ui.model.type.Date({style: "long"})
							        	}
							        	})
							],
							layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
						}),
                     
                 ],
                 layoutData: new sap.ui.core.VariantLayoutData({
                     multipleLayoutData: [new sap.ui.commons.form.GridContainerData({
                         halfGrid: true
                     })]
                 })
             }),
             new sap.ui.commons.form.FormContainer("C44", {
                 formElements: [
                     new sap.ui.commons.form.FormElement({
                         fields: [new sap.ui.commons.Button({
                                 text: 'Add Deal',
                                 press: function () {
                                	 var vendorId = localStorage.getItem('VendorID');
                                	 var vendorName = localStorage.getItem('VendorName');
                                 	var vendorLatitude = localStorage.getItem('VendorLatitude');
                                 	var vendorLongitude = localStorage.getItem('VendorLongitude');
                                 	var city = localStorage.getItem('VendorCity');
                                 	var area = localStorage.getItem('VendorArea');
                                 	var phone = localStorage.getItem('VendorPhone');
                                	 var categoryID = $('.categoryListItemH').attr('id');
                                	 var category = $('.categoryListItemH .categoryName').html();
                                	 var SubCategory = $('.subCategoryNameH .subCatTxt').html();
                                	 
                                	 if(SubCategory == null){
                                		 sap.ui.commons.MessageBox.alert("Please Select the Sub Category",'' , "Error");
                                	 }
                                	 else if(sap.ui.getCore().byId("ItemName1").getValue() == "" ||
                                			 sap.ui.getCore().byId("Brand1").getValue() == "" ||
                                             sap.ui.getCore().byId("Price1").getValue()  == "" ||
                                             sap.ui.getCore().byId("Discount1").getValue() == ""){
                                		 sap.ui.commons.MessageBox.alert("Please Enter all mandatory fields",'' , "Error");
                                	 }
                                	                              	
                                	 else{

                                     //Check if the Category is already created
                                     var tempcheckIfCategoryCreated = checkIfCategoryCreated(category);
                                     if (tempcheckIfCategoryCreated == false) {
                                     	
                                       
                                         var jsonPOSTCategory = {
                                             vendorId: vendorId,
                                             categoryId: categoryID,
                                             category: category
                                         };                                                                               

                                         jQuery.ajax({
                                             url: "/DealsAroundMe/Services/",
                                             dataType: "json",
                                             type: "POST",
                                             data: JSON.stringify(jsonPOSTCategory),
                                             async: false,
                                             beforeSend: function (xhr) {
                                                 xhr.setRequestHeader('X-SAP-SERVICES', 'CATEGORY');
                                             },
                                             success: function (data, textStatus, jqXHR) {
                                                 //alert("CATEGORY SUCCESFULLY CREATED ");

                                             },
                                             error: function (jqXHR, textStatus, errorThrown) {
                                                 alert("error occurred in category creation");
                                             }
                                         });
                                     }
                                     var randomItemID = Math.floor((Math.random()*99999)+1000);
                                     var jsonPOSTITEMS = {
                                         vendorId: vendorId,
                                         categoryID: categoryID,
                                         itemId: randomItemID,
                                         subCategory: SubCategory,
                                         category: category,
                                         vendorName: vendorName,
                                         vendorLatitude: vendorLatitude,
                                         vendorLongitude: vendorLongitude,
                                         city: city,
                                         area: area,
                                         phone: phone,
                                         item: sap.ui.getCore().byId("ItemName1").getValue(),
                                         brand: sap.ui.getCore().byId("Brand1").getValue(),
                                         description: sap.ui.getCore().byId("Description1").getValue(),
                                         price: sap.ui.getCore().byId("Price1").getValue(),
                                         discount: sap.ui.getCore().byId("Discount1").getValue(),
                                         dealType: sap.ui.getCore().byId("DealType1").getValue(),
                                         startDate: sap.ui.getCore().byId("startDate").getValue(),
                                         endDate: sap.ui.getCore().byId("endDate").getValue()
                                     };
                                     jQuery.ajax({
                                         url: "/DealsAroundMe/Services/",
                                         dataType: "json",
                                         type: "POST",
                                         data: JSON.stringify(jsonPOSTITEMS),
                                         async: false,
                                         beforeSend: function (xhr) {
                                             xhr.setRequestHeader('X-SAP-SERVICES', 'ITEMS');
                                         },
                                         success: function (data, textStatus, jqXHR) {
                                        	 sap.ui.commons.MessageBox.alert("Successfully added your Deal",'' , "Success");
                                        	 },
                                         error: function (jqXHR, textStatus, errorThrown) {
                                        	 sap.ui.commons.MessageBox.alert("Error Occurred in Deal Creation",'' , "Failure");
                                         }
                                     });



                                 }
                                 },
                                 layoutData: new sap.ui.core.VariantLayoutData({
                                     multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                         hCells: "2"
                                     })]
                                 })
                             }),
                             new sap.ui.commons.Button({
                                 text: 'Cancel',
                                 layoutData: new sap.ui.core.VariantLayoutData({
                                     multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                         hCells: "2"
                                     })]
                                 }),
                                 press: function () {
                                	 $('.subCategoryNameH').removeClass('subCategoryNameH');
                                	 sap.ui.getCore().byId("ItemName1").setValue("");
                             		sap.ui.getCore().byId("Brand1").setValue("");
                             		sap.ui.getCore().byId("Description1").setValue("");
                             		sap.ui.getCore().byId("Price1").setValue("");
                             		sap.ui.getCore().byId("Discount1").setValue("");
                             		sap.ui.getCore().byId("DealType1").setValue("");
                             		sap.ui.getCore().byId("ChkBXMale1").setChecked(false);
                             		sap.ui.getCore().byId("ChkBXFemale1").setChecked(false);
                             		sap.ui.getCore().byId("ChkBXKids1").setChecked(false);
                                 }
                             })
                         ]
                     })
                 ],
                 layoutData: new sap.ui.core.VariantLayoutData({
                     multipleLayoutData: [new sap.ui.commons.form.GridContainerData({
                         halfGrid: true
                     })]
                 })
             }),
             new sap.ui.commons.form.FormContainer("C55", {
                 visible: false,
                 title: "invisible",
                 formElements: [
                     new sap.ui.commons.form.FormElement({
                         fields: [new sap.ui.commons.TextField({
                             value: 'Hello'
                         })]
                     })
                 ]
             })
         ]
     });
	 
	 addCategoryDeatils.addContent(oForm);
	
	
	return addCategoryDeatils;
}

function SubCategoriesBuild(SubCategories, subCategoryImg){
	var divcontent = "";
	for(var i=0; i < SubCategories.length; i++){
		if(SubCategories[i] != "Biography Autobiography"){
		divcontent = divcontent+"<div class='subCategoryName'><div class='subicondiv "+
		subCategoryImg[i]+"'></div><div class='subCatTxt'>"
		+SubCategories[i]+
		"</div></div>";
		}
		else{
			divcontent = divcontent+"<div class='subCategoryName'><div class='subicondiv "+
			subCategoryImg[i]+"'></div><div class='subCatTxt' style='line-height: 20px;width: 145px;'>"
			+SubCategories[i]+
			"</div></div>";
		}
		}
	return divcontent;
}


var checkIfCategoryCreated = function checkIfCategoryCreated(category) {
	console.log(category);
    //Check if the category 1 is created for vendor 200 for DEMo
    var returnValue = false;
    jQuery.ajax({
        url: "/DealsAroundMe/Services/",
        dataType: "json",
        type: "GET",
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-SAP-SERVICES', 'CATEGORY');
        },
        success: function (data, textStatus, jqXHR) {
        	var vendorId = localStorage.getItem('VendorID');
        	
            var selectedObjs = sql({
                select: "vendorId,categoryId,category",
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
           /* if (selectedObjs.length > 0) {
                returnValue = true;
            } else {
                returnValue = false;
            }*/
            checkLoop : for (var i = 0; i < selectedObjs.length; i++) {
            	if(selectedObjs[i].category == category){
            		returnValue = true;
            		break checkLoop;
            	}
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error occurred");
        }
    });
    return returnValue;

};

