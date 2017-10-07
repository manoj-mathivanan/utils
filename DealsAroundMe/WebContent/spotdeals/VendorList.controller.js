sap.ui.controller("spotdeals.VendorList", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf spotdeals.VendorList
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf spotdeals.VendorList
*/
	onBeforeRendering: function() {
		
	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf spotdeals.VendorList
*/
	onAfterRendering: function() {
		
		
		$(document).on('click', ".DealDiv", function() {
			
			var selectedItem = $(this).find('.passJson').text();
			
			var data = JSON.parse(selectedItem);
			
			var start = data.startDate;
			var end = data.endDate;
			var startDate = new Date(start);
			var endDate = new Date(end);
			
			var Category = data.category;
			var subcategory = data.subCategory;
			
			var oModel = new sap.ui.model.json.JSONModel();
			oModel.setData({
				startDate: startDate,
				endDate: endDate
			});
			sap.ui.getCore().setModel(oModel);
			
			var oEditDealDialog = new sap.ui.commons.Dialog({modal: true});
			oEditDealDialog.setTitle(Category + " : "+ subcategory );
			
			 var oLayout = new sap.ui.commons.form.GridLayout();
        	 var oEditDeal = new sap.ui.commons.form.Form({
        		 
                 layout: oLayout,
                 formContainers: [
                     
                     new sap.ui.commons.form.FormContainer({
                         
                         formElements: [
                            
                             new sap.ui.commons.form.FormElement({
                            	 label: new sap.ui.commons.Label({
                                     text: "Item Name",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("itemNameE", {
                                	 text: data.item,
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Brand",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("brandE",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Description",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("DescriptionE",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "MRP Price",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("mrpPriceE",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Offer",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),

                                 fields: [new sap.ui.commons.TextField("offerE",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Deal Type",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("dealTypeE",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Deal Starts on",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [
                                          new sap.ui.commons.DatePicker("startDateE",{
  							        		width: "15em",
  							        		value: {
  							        			path: "/startDate",
  							        			type: new sap.ui.model.type.Date({style: "long"})
  							        	}
  							        	})
                                 ]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Deal Ends on",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [
                                          new sap.ui.commons.DatePicker("endDateE",{
  							        		width: "15em",
  							        		value: {
  							        			path: "/endDate",
  							        			type: new sap.ui.model.type.Date({style: "long"})
  							        	}
  							        	})
                                 ]
                             }),
                            
                         
                         ],
                        
                        
                     }),
                   
                                        
                 ],
                 layoutData: new sap.ui.core.VariantLayoutData({
                     multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                         hCells: "6"
                     })]
                 })
                 
             });
        	 var ItemId = data.itemId;
        	 console.log("ghgh "+ItemId);
        	 sap.ui.getCore().byId("itemNameE").setValue(data.item);
        	 sap.ui.getCore().byId("brandE").setValue(data.brand);
        	 sap.ui.getCore().byId("DescriptionE").setValue(data.description);
        	 sap.ui.getCore().byId("mrpPriceE").setValue(data.price);
        	 sap.ui.getCore().byId("offerE").setValue(data.discount);
        	 sap.ui.getCore().byId("dealTypeE").setValue(data.dealType);
        	 sap.ui.getCore().byId("startDateE").setValue(data.startDate);
        	 sap.ui.getCore().byId("endDateE").setValue(data.endDate);
        	 
        	 oEditDealDialog.addContent(oEditDeal);
        	 oEditDealDialog.setWidth("500px");
			oEditDealDialog.addButton(new sap.ui.commons.Button({text: "Submit",
				press:function(){
					//oEditDealDialog.close();
					  
                     	 var vendorId = localStorage.getItem('VendorID');
                     	 var vendorName = localStorage.getItem('VendorName');
                      	var vendorLatitude = localStorage.getItem('VendorLatitude');
                      	var vendorLongitude = localStorage.getItem('VendorLongitude');
                      	var area = localStorage.getItem('VendorArea');
                      	var city = localStorage.getItem('VendorCity');
                      	var phone = localStorage.getItem('VendorPhone');
                     	 var categoryID = data.categoryID;
                     	 var category = data.category;
                     	 var SubCategory = data.subCategory;
                     	 if(SubCategory == null){
                     		 sap.ui.commons.MessageBox.alert("Please Select the Sub Category",'' , "Error");
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
                          var jsonPOSTITEMS = {
                              vendorId: vendorId,
                              categoryID: categoryID,
                              itemId: ItemId,
                              area: area,
                              city: city,
                              phone:phone,
                              subCategory: SubCategory,
                              category: category,
                              vendorName: vendorName,
                              vendorLatitude: vendorLatitude,
                              vendorLongitude: vendorLongitude,
                              item: sap.ui.getCore().byId("itemNameE").getValue(),
                              brand: sap.ui.getCore().byId("brandE").getValue(),
                              description: sap.ui.getCore().byId("DescriptionE").getValue(),
                              price: sap.ui.getCore().byId("mrpPriceE").getValue(),
                              discount: sap.ui.getCore().byId("offerE").getValue(),
                              dealType: sap.ui.getCore().byId("dealTypeE").getValue(),
                              startDate: sap.ui.getCore().byId("startDateE").getValue(),
                              endDate: sap.ui.getCore().byId("endDateE").getValue()
                          };
                          jQuery.ajax({
                              url: "/DealsAroundMe/Services/",
                              dataType: "json",
                              type: "POST",
                              data: JSON.stringify(jsonPOSTITEMS),
                              async: false,
                              beforeSend: function (xhr) {
                                  xhr.setRequestHeader('X-SAP-SERVICES', 'ITEMSEDIT');
                              },
                              success: function (data, textStatus, jqXHR) {
                            	  oEditDealDialog.close();
                             	 sap.ui.commons.MessageBox.alert("Successfully Updated your Deal",'' , "Success");
                             	 },
                              error: function (jqXHR, textStatus, errorThrown) {
                             	 sap.ui.commons.MessageBox.alert("Error Occurred in Deal Edit",'' , "Failure");
                              }
                          });
                          
                          
                          sap.ui.getCore().byId('ViewDetailMiddle').destroyContent();
                          var DealDetails = viewDealDetails(clickedCategory);
                          sap.ui.getCore().byId('ViewDetailMiddle').addContent(DealDetails);


                      }
				
				
				}
			})
			);
			oEditDealDialog.open();
			
			oEditDealDialog.attachClosed(function(){
				oEditDealDialog.destroy();
            });
			
			
		});
		
		$(document).on('click', ".subCategoryName", function() {
			//
			$('.subCategoryNameH').removeClass('subCategoryNameH');
			$(this).addClass('subCategoryNameH');        
		});
		
		$(document).on('mouseenter', ".DealDiv", function() {
			
			$(this).find('.editDiv').show();        
		});
		
		$(document).on('mouseleave', ".DealDiv", function() {
			
			$(this).find('.editDiv').hide();        
		});
		$(document).on('click', ".categoryListItemC", function() {
			clickedCategory = this.id;
			
			$('.categoryListItemCH').removeClass('categoryListItemCH');
			$(this).addClass('categoryListItemCH');     
			sap.ui.getCore().byId('ViewDetailMiddle').destroyContent();
            var DealDetails = viewDealDetails(this.id);
            sap.ui.getCore().byId('ViewDetailMiddle').addContent(DealDetails);
		});
		
		setTimeout(function(){
			
			$(".categoryListItem").click(function(e){
				  
				  $('.categoryListItemH').removeClass('categoryListItemH');
				  $(this).addClass('categoryListItemH');
				  var hicon = $(this).find(':first-child');
				 var categoryID = this.id;
				 sap.ui.getCore().byId('addDealMiddle').destroyContent();
				  var addDealDetails = buildAddDealMiddle(categoryID);
				 
				  sap.ui.getCore().byId('addDealMiddle').addContent(addDealDetails);
				});
			},4000);	
		
		
		
		
	}

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf spotdeals.VendorList
*/
//	onExit: function() {
//
//	}

});

function shopDetails(sLabel, sText, sUrl){
	var shopDetails =  new sap.ui.core.HTML({
		content : 
				"<div>" +
				"<div"+sLabel+
				"</div>"+
				"<div>"+sText+"</div>"+
				
				"</div>"
	});
	return shopDetails;
	
}

var sql = function sql(s) {
    var returnObj = new Array();
    var cntr = 0;
    var cnt;
    for (var bb = 0; bb < s.from.length; bb++) {
        //$.each(s.from, function(bb) {
        var ifConditions = new Array();
        for (cnt = 0; cnt < s.where.length; cnt++) {
            ifConditions[cnt] = new Object();
            var where = "";
            if (s.where[cnt].OPERATOR.indexOf("=") == 0)
                where = "==";
            if (s.where[cnt].VALUE.indexOf("'") > -1)
                ifConditions[cnt] = eval("'" + eval("s.from[bb]." + (eval("s.where[" + cnt + "].KEY"))) + "'" + where + eval("s.where[" + cnt + "].VALUE"));
            else
                ifConditions[cnt] = eval(eval("s.from[bb]." + (eval("s.where[" + cnt + "].KEY"))) + where + eval("s.where[" + cnt + "].VALUE"));
        }
        var comparedOutput = true;
        for (cnt = 0; cnt < s.conditions.length; cnt++) {
            var condition = "";
            switch (s.conditions[cnt].CONDITION.toUpperCase()) {
            case "AND":
                condition = "&&";
                break;
            case "OR":
                condition = "||";
                break;
            }
            comparedOutput = comparedOutput && eval("ifConditions[" + s.conditions[cnt].Condition1 + "]" + condition + "ifConditions[" + s.conditions[cnt].Condition2 + "]");
        }
        if (comparedOutput) {
            var result = {};
            var cols = s.select.split(",");
            for (var cnt = 0; cnt < cols.length; cnt++) {
                result[cols[cnt]] = eval("s.from[bb]." + cols[cnt]);
            }
            returnObj.push(result);
        }
    }
    return returnObj;
};

