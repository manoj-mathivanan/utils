sap.ui.jsview("spotdeals.VendorList", {

    /** Specifies the Controller belonging to this View. 
     * In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
     * @memberOf spotdeals.VendorList
     */
    getControllerName: function () {
        return "spotdeals.VendorList";
    },

    /** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
     * Since the Controller is given to this method, its event handlers can be attached right away.
     * @memberOf spotdeals.VendorList
     */
    createContent: function (oController) {
    	
    	this.vendorPortal = new sap.ui.commons.layout.VerticalLayout(
				{	
					  width: "100%",
					id : "vendorPortal"
				});
    	
    	 var viewDetailMiddle = new sap.ui.layout.VerticalLayout("ViewDetailMiddle", {
         	content: []
         });
    	 
    	
    	 
    	 var addDealMiddle = new sap.ui.layout.VerticalLayout("addDealMiddle", {
          	content: []
          });
    	 
    	  var categoryDiv = new sap.ui.layout.VerticalLayout('categoryDiv');
    	  
    	  var addingCategory = buildAddCategory();
    	  var addCategoryDiv = new sap.ui.layout.VerticalLayout('addCategoryDiv');
    	  addCategoryDiv.addContent(addingCategory);
    	  
    	  
    	  var addDealDetails = buildAddDealMiddle("1001");
    	  addDealMiddle.addContent(addDealDetails);
    	  

        var checkCategoryCreated = function checkCategoryCreated() {
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
                    var selectedObjs = sql({
                        select: "vendorId,categoryId,category",
                        from: data,
                        where: [{
                            "KEY": "vendorId",
                            "OPERATOR": "=",
                            "VALUE": "316"
                        }],
                        conditions: [{
                            "Condition1": "0",
                            "CONDITION": "Or",
                            "Condition2": "1"
                        }]
                    });
                    if (selectedObjs.length > 0) {
                        returnValue = true;
                    } else {
                        returnValue = false;
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error occurred");
                }
            });
            return returnValue;

        };



        //Add the header logo and welcome user.
        var oAppHeaderVen = new sap.ui.commons.ApplicationHeader("appHeaderVendor");

        //configure the branding area
        oAppHeaderVen.setLogoSrc("images/sap_logo.svg");
        oAppHeaderVen.setLogoText("SAP SAPUI5 Library");
        oAppHeaderVen.addStyleClass("damHeader");


        //configure the welcome area
        oAppHeaderVen.setDisplayWelcome(true);
        oAppHeaderVen.setUserName("Central");


        //configure the log off area
        oAppHeaderVen.setDisplayLogoff(true);

        //ADD SHELL to add controls
        var oShell = new sap.ui.ux3.Shell({
            appTitle: 'Deals around me Vendor Portal',
            showSearchTool: false,
            showFeederTool: false,
            worksetItems: [
                  new sap.ui.ux3.NavigationItem({
                    key: "wi_addDeals",
                    text: "Add Deals"
                }),
                /*new sap.ui.ux3.NavigationItem({
                    key: "wi_details",
                    text: "Deal Details ' s"
                }),
                new sap.ui.ux3.NavigationItem({
                    key: "wi_add",
                    text: "Add New Deal"
                }),*/
                new sap.ui.ux3.NavigationItem({
                    key: "wi_viewDetails",
                    text: "View Details"
                }),
               
            ],
            logout:function(){
            	location.reload();
            	localStorage.clear();
        	},
        });
        
        var oLayoutTable = new sap.ui.commons.layout.MatrixLayout({
            width: "100%", // table width
            widths: ["100%"] // the widths of the columns
        });


        //CONTENT FOR DISPLAYING THE TABLE

        //Define some sample data 
        // AJAX call to load the data
        // create a JSONModel and bind the Table to this model
        var oAjaxModel = new sap.ui.model.json.JSONModel();
        var callTable1oAjaxModel = function callTable1oAjaxModel() {
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
                    xhr.setRequestHeader('X-SAP-SERVICES', 'CATEGORY');
                },
                success: function (data, textStatus, jqXHR) {
                    oAjaxModel.setData({
                        data: data
                    });
                    var selectedObjs = sql({
                        select: "vendorId,categoryId,category",
                        from: data,
                        where: [{
                            "KEY": "vendorId",
                            "OPERATOR": "=",
                            "VALUE": "316"
                        }],
                        conditions: [{
                            "Condition1": "0",
                            "CONDITION": "Or",
                            "Condition2": "1"
                        }]
                    });
                    data = selectedObjs;
                    //alert(JSON.stringify(selectedObjs));
                    //alert(data);
                    oAjaxModel.setData({
                        data: data
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error occurred");
                }
            });

        };
        
        
        
        function getCategoriesList() {
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
                    xhr.setRequestHeader('X-SAP-SERVICES', 'CATEGORY');
                },
                success: function (data, textStatus, jqXHR) {
                    oAjaxModel.setData({
                        data: data
                    });
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
                    data = selectedObjs;
                    sap.ui.getCore().byId('categoryDiv').destroyContent();
                    if(data.length > 0){
                    clickedCategory = data[0].categoryId;}
                    var categoryItems = buildCategory(data);
                    categoryDiv.addContent(categoryItems);
                    //categoryList(data);
                    if(data.length != 0){
                    sap.ui.getCore().byId('ViewDetailMiddle').destroyContent();
                    var DealDetails = viewDealDetails(data[0].categoryId);
                    viewDetailMiddle.addContent(DealDetails);
                    }
                    
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error occurred");
                }
            });

        };
        
       
        

        callTable1oAjaxModel();
        //Create an instance of the table control
        var oTable1 = new sap.ui.table.Table({
            title: "View/ Modify the offers",
            visibleRowCount: 7,
            firstVisibleRow: 3,
            selectionMode: sap.ui.table.SelectionMode.Single,
            navigationMode: sap.ui.table.NavigationMode.Paginator,
            fixedColumnCount: 2
        });

        //Define the columns and the control templates to be used
        oTable1.addColumn(new sap.ui.table.Column({
            label: new sap.ui.commons.Label({
                text: "category"
            }),
            template: new sap.ui.commons.TextView().bindProperty("text", "category"),
            sortProperty: "category",
            filterProperty: "category",
            width: "200px"
        }));

        //Create a model and bind the table rows to this model

        oTable1.setModel(oAjaxModel);
        oTable1.bindRows("/data");

        //Initially sort the table
        oTable1.sort(oTable1.getColumns()[0]);

        //Bring the table onto the UI 
        //oTable1.placeAt("sample2");




        var oTable2 = new sap.ui.table.Table({
            id: "oTable2",
            editable: true,
            columns: [{
                label: "item",
                template: "item",
                sortProperty: "item"
            }, {
                label: "price",
                template: "price",
                sortProperty: "price"
            }, {
                label: "discount",
                template: "discount",
                sortProperty: "discount"
            }, {
                label: "dealType",
                template: "dealType",
                sortProperty: "dealType"
            }]
        });

        //oTable2.setModel(oModel2); // bind model to Table

        //oTable2.bindRows({path:"/Orders/Order_Details"});



        oTable1.attachRowSelectionChange(function (event) {
            //var table = event.getSource();
            //var selectedIndices = table.getSelectedIndices();
            var rowContext = event.getParameter("rowContext");
            //  alert(rowContext.getProperty("ProductName"));

            //suraj //oTable2.setBindingContext(rowContext);
            //suraj //oTable2.bindRows("/modelData");

            //#############
            var iIndex = event.getParameter("rowIndex");

            //#############
            var oAjaxModelTable2 = new sap.ui.model.json.JSONModel();
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
                    var selectedObjs = sql({
                        select: "item,price,discount,dealType",
                        from: data,
                        where: [{
                            "KEY": "vendorId",
                            "OPERATOR": "=",
                            "VALUE": "316"
                        }, {
                            "KEY": "subcategoryId",
                            "OPERATOR": "=",
                            "VALUE": "1"
                        }],
                        conditions: [{
                            "Condition1": "0",
                            "CONDITION": "And",
                            "Condition2": "1"
                        }]
                    });
                    data = selectedObjs;
                    //alert(JSON.stringify(selectedObjs));
                    oAjaxModelTable2.setData({
                        data: data
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error occurred");
                }
            });
            oTable2.setVisibleRowCount(10);
            oTable2.setModel(oAjaxModelTable2);
            oTable2.bindRows("/data");



        });


        //oTable2.placeAt("detail");




        //CONTENT FOR ADD DETAILS

        var oLayout = new sap.ui.commons.form.GridLayout("L3");

        var oForm = new sap.ui.commons.form.Form("F1", {
            title: new sap.ui.commons.Title({
                text: "Add Items Details",
                icon: "images/ui5_small.png",
                tooltip: "Add Items Details"
            }),
            tooltip: "Form tooltip",
            layout: oLayout,
            formContainers: [
                new sap.ui.commons.form.FormContainer("C1", {
                    title: "category",
                    formElements: [
                        new sap.ui.commons.form.FormElement({
                            label: "Category",
                            fields: [new sap.ui.commons.DropdownBox("Category", {
                                items: [new sap.ui.core.ListItem({
                                        text: "Auto"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Clothing"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Education"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Mobile"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Travel"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Hotels"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Books"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Jewelry"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "SPA"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Shoes"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Music"
                                    })
                                ]
                            })]
                        })
                    ]
                }),
                new sap.ui.commons.form.FormContainer("C2", {
                    title: new sap.ui.commons.Title({
                        text: "Sub Category",
                        icon: "images/ui5_small.png",
                        tooltip: "Title tooltip"
                    }),
                    formElements: [
                        new sap.ui.commons.form.FormElement({
                            label: new sap.ui.commons.Label({
                                text: "Sub Category Name"
                            }),
                            fields: [new sap.ui.commons.DropdownBox("SubCategory", {
                            	change: (function(){
                            		sap.ui.getCore().byId("ItemName").setValue("");
                            		sap.ui.getCore().byId("Brand").setValue("");
                            		sap.ui.getCore().byId("Description").setValue("");
                            		sap.ui.getCore().byId("Price").setValue("");
                            		sap.ui.getCore().byId("Discount").setValue("");
                            		sap.ui.getCore().byId("DealType").setValue("");
                            		sap.ui.getCore().byId("ChkBXMale").setChecked(false);
                            		
                            	}),
                                items: [new sap.ui.core.ListItem({
                                        text: " "
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Jeans"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Shirts"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Jackets"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Trousers"
                                    }),
                                    new sap.ui.core.ListItem({
                                        text: "Leather"
                                    })
                                ]
                            })]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: "Item Name",
                            fields: [new sap.ui.commons.TextField("ItemName")]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: new sap.ui.commons.Label({
                                text: "Brand",
                                icon: "images/help.gif"
                            }),
                            fields: [new sap.ui.commons.TextField("Brand", {
                                layoutData: new sap.ui.core.VariantLayoutData({
                                    multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                        hCells: "2"
                                    })]
                                })
                            })]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: "Description",
                            fields: [new sap.ui.commons.TextField("Description")]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: "Price",

                            fields: [new sap.ui.commons.TextField("Price")]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: "Discount",
                            fields: [new sap.ui.commons.TextField("Discount")]
                        }),
                        new sap.ui.commons.form.FormElement({
                            label: "Deal Type",
                            fields: [new sap.ui.commons.TextField("DealType")]
                        })
                    ],
                    layoutData: new sap.ui.core.VariantLayoutData({
                        multipleLayoutData: [new sap.ui.commons.form.GridContainerData({
                            halfGrid: true
                        })]
                    })
                }),
                new sap.ui.commons.form.FormContainer("C3", {
                    title: new sap.ui.commons.Title({
                        text: "Type",
                        emphasized: true
                    }),
                    tooltip: "This container is expandable",
                    expandable: true,
                    formElements: [
                        new sap.ui.commons.form.FormElement("CheckBox", {
                            fields: [new sap.ui.commons.CheckBox("ChkBXMale",{
                                    text: 'Male'
                                }),
                                new sap.ui.commons.CheckBox({
                                    text: 'Female'
                                }),
                                new sap.ui.commons.CheckBox({
                                    text: 'Kids'
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
                new sap.ui.commons.form.FormContainer("C4", {
                    formElements: [
                        new sap.ui.commons.form.FormElement({
                            fields: [new sap.ui.commons.Button({
                                    text: 'OK',
                                    press: function () {

                                        //Add CATEGORY for clothing for DEMO
                                        var tempcheckCategoryCreated = checkCategoryCreated();
                                        if (!tempcheckCategoryCreated) {
                                        	var vendorId = localStorage.getItem('VendorID');
                                            //{"vendorId":101,"categoryId":1,"category":"Clothing"}
                                            var jsonPOSTCategory = {
                                                vendorId: 316,
                                                categoryId: "1",
                                                category: "Clothing"
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
                                            vendorId: 316,
                                            subcategoryId: 1,
                                            itemId: Math.floor((Math.random() * 1000) + 1),
                                            item: sap.ui.getCore().byId("ItemName").getValue(),
                                            brand: sap.ui.getCore().byId("Brand").getValue(),
                                            description: sap.ui.getCore().byId("Description").getValue(),
                                            price: sap.ui.getCore().byId("Price").getValue(),
                                            discount: sap.ui.getCore().byId("Discount").getValue(),
                                            dealType: sap.ui.getCore().byId("DealType").getValue()
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
                                                //alert("ITEMS POSTED " );
                                                jQuery.sap.require("sap.ui.core.Popup");
                                                var oSimpleFormKItems = new sap.ui.commons.form.SimpleForm({
                                                    maxContainerCols: 1,
                                                    minWidth: 500,
                                                    content: [
                                                        new sap.ui.commons.Label({
                                                            text: "ITEMS POSTED"
                                                        }, {
                                                            design: sap.ui.commons.TextViewDesign.h1,
                                                            textAlign: "Center"
                                                        }),
                                                        new sap.ui.commons.Button({
                                                            text: "OK",
                                                            press: function () {
                                                                oPopupItem.close();
                                                            }
                                                        })
                                                    ]
                                                });


                                                var oAbsLayoutItems = new sap.ui.commons.layout.AbsoluteLayout();
                                                oAbsLayoutItems.addContent(oSimpleFormKItems);
                                                //oRoot.addContent(oAbsLayout);

                                                oAbsLayoutItems.setWidth("185px");
                                                oAbsLayoutItems.setHeight("90px");


                                                var oPopupItem =
                                                    new sap.ui.core.Popup(oAbsLayoutItems, // content
                                                        false, // modality
                                                        true, // shadow
                                                        true // autoclose
                                                );
                                                oPopupItem.open();

                                            },
                                            error: function (jqXHR, textStatus, errorThrown) {
                                                alert("error occurred");
                                            }
                                        });



                                    },
                                    layoutData: new sap.ui.core.VariantLayoutData({
                                        multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                            hCells: "1"
                                        })]
                                    })
                                }),
                                new sap.ui.commons.Button({
                                    text: 'Cancel',
                                    layoutData: new sap.ui.core.VariantLayoutData({
                                        multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                            hCells: "1"
                                        })]
                                    })
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
                new sap.ui.commons.form.FormContainer("C5", {
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
        
        function row(sLabel, sText, sUrl) {
        	var oControl;
        	
        	if(!sUrl){
        		oControl = new sap.ui.commons.TextView({
        			text: sText,
        			tooltip: sText
        		});
        	}else{
        		oControl = new sap.ui.commons.Link({
        			text: sText,
        			href: sUrl,
        			tooltip: sText,
        			target: "_blank"
        		});
        	}
        		
        		var oLabel = new sap.ui.commons.Label({
        			text: sLabel + ":",
        			labelFor: oControl
        		});

        		var oMLCell1 = new sap.ui.commons.layout.MatrixLayoutCell({
        			hAlign : sap.ui.commons.layout.HAlign.Begin,
        			vAlign : sap.ui.commons.layout.VAlign.Top,
        			content : [ oLabel ],
        			width : "70px"
        		});
        		var oMLCell2 = new sap.ui.commons.layout.MatrixLayoutCell({
        			hAlign : sap.ui.commons.layout.HAlign.Begin,
        			vAlign : sap.ui.commons.layout.VAlign.Top,
        			content : [ oControl ]
        		});

        		return new sap.ui.commons.layout.MatrixLayoutRow({
        			cells : [ oMLCell1, oMLCell2 ],
        			widths : ["70px", "100px"]
        		});
        	
        	}
        
        var VImage = new sap.ui.commons.Image({
            src : "images/vendor_icon.png",
            height : "100px",
            width : "100px"
            });
                
        var vendorName = new sap.ui.commons.Label({
        	 id : 'VName',
            text : localStorage.getItem('VendorName') }).setDesign(sap.ui.commons.LabelDesign.Bold);
        
        var VImageNew = new sap.ui.commons.Image({
            src : "images/vendor_icon.png",
            height : "100px",
            width : "100px"
            });
                
        var vendorNameNew = new sap.ui.commons.Label({
        	 id : 'VNameNew',
            text : localStorage.getItem('VendorName') }).setDesign(sap.ui.commons.LabelDesign.Bold);
        var VimgName = new sap.ui.layout.HorizontalLayout('imgNmediv', {
        	content: [VImage, vendorName]
        });
        
        var VimgNameNew = new sap.ui.layout.HorizontalLayout('imgNmedivNew', {
        	content: [VImageNew, vendorNameNew]
        });
        
        
        
      
        
        var viewDetailLeft = new sap.ui.layout.VerticalLayout("ViewDetailLeft", {
        	content: [VimgName,
        	          new sap.ui.commons.layout.MatrixLayout({rows: [
        	                                     					row("Address", localStorage.getItem('VendorCity')+" , "+localStorage.getItem('VendorArea')),
        	                                     					row("Phone", localStorage.getItem('VendorPhone'))
        	                                     				]}),
        	          categoryDiv,
        	           new sap.ui.commons.Label().bindProperty("text", "category"),
        	                                     		
        	          
        	          ]
        });
        
        var addDealLeft = new sap.ui.layout.VerticalLayout("addDealLeft", {
        	content: [VimgNameNew,
        	          new sap.ui.commons.layout.MatrixLayout({rows: [
        	                                     					row("Address", localStorage.getItem('VendorCity')+" , "+localStorage.getItem('VendorArea')),
        	                                     					row("Phone", localStorage.getItem('VendorPhone'))
        	                                     				]}),
        	          addCategoryDiv
        	                 	                                     		
        	          
        	          ]
        });
       
        
        var addDeal = new sap.ui.layout.HorizontalLayout("addDeal", {
        	content: [addDealLeft, addDealMiddle]
        });
        
        var viewDetail = new sap.ui.layout.HorizontalLayout("ViewDetailDiv", {
        	content: [viewDetailLeft, viewDetailMiddle]
        });


        
        //oShell.setContent(oForm);
        oLayoutTable.createRow(oTable1);
        oLayoutTable.createRow(oTable2);
        oShell.setContent(oAppHeaderVen);
        oShell.setContent(addDeal);
        oShell.attachWorksetItemSelected(function (oEvent) {
            var key = oEvent.getParameter("key");
            if (key === "wi_details") {
            	callTable1oAjaxModel();
            	oTable1.setModel(oAjaxModel);
                oTable1.bindRows("/data");
            	oShell.setContent(oLayoutTable);
                //oShell.setContent(oTable2);
            } else if (key === "wi_add") {
                oShell.setContent(oForm);
            }
            else if (key === "wi_viewDetails"){
            	getCategoriesList();
            	  oShell.setContent(viewDetail);
            }
            else if (key === "wi_addDeals"){
            	
            	  oShell.setContent(addDeal);
            	  
            	  
            }
        });


        /*return oShell;*/
        /*this.vendorPortal.addContent(oAppHeaderVen);*/
        this.vendorPortal.addContent(oShell);

        return this.vendorPortal;
    }

});

