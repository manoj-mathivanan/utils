sap.ui.jsview("spotdeals.spotdealsView", {
    /** SPOT DEAL LOGIN FORM page
     */
    /** Specifies the Controller belonging to this View. 
     * In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
     * @memberOf spotdeals.spotdealsView
     */
    getControllerName: function () {
        return "spotdeals.spotdealsView";
    },

    /** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
     * Since the Controller is given to this method, its event handlers can be attached right away.
     * @memberOf spotdeals.spotdealsView
     */
    createContent: function (oController) {

    	this.mainContainer = new sap.ui.commons.layout.VerticalLayout(
				{	
					  width: "100%",
					id : "mainContainer"
				});

        var oLogin = new sap.ui.commons.layout.VerticalLayout("loginDiv");

        //APPLICATON HEADER layer
        var oAppHeader = new sap.ui.commons.ApplicationHeader("appHeader");

        //configure the branding area
        oAppHeader.setLogoSrc("images/DAM_Logo.png");
        oAppHeader.setLogoText("Welcome to Deals Around Me Vendor portal");
        oAppHeader.addStyleClass("damHeader");


        //configure the welcome area
        oAppHeader.setDisplayWelcome(false);
        oAppHeader.setUserName(" ");

        //configure the log off area
        oAppHeader.setDisplayLogoff(false);
        
        var loginPageDiv = new sap.ui.layout.HorizontalLayout("loginPageDiv");
        
        
        
        var DescriptionLyt = new sap.ui.commons.layout.MatrixLayout({
        	id : "DescriptionLyt",
        	layoutFixed : false
        	});

        var oImage1 = new sap.ui.commons.Image({
        	id : "registerIoc",
        	src : "images/register.png"
        	});

        var RegTxt = new sap.ui.commons.TextView({
        	text : 'Register your Store in our Portal'}).addStyleClass('descriptionTxt');

        DescriptionLyt.createRow( oImage1, RegTxt );	

        var oImage2 = new sap.ui.commons.Image({
        	src : "images/update.png",
        	alt : "alternative text for image"
        	});

        var updateTxt = new sap.ui.commons.TextView({
        	text : 'Update your current Deals and Offers'}).addStyleClass('descriptionTxt');

        DescriptionLyt.createRow( oImage2, updateTxt );
        
        var oImage3 = new sap.ui.commons.Image({
        	src : "images/profit.png",
        	alt : "alternative text for image"
        	});

        var profitTxt = new sap.ui.commons.TextView({
        	text : 'Increase your Profit by attracting more customers'}).addStyleClass('descriptionTxt');

        DescriptionLyt.createRow( oImage3, profitTxt );
        
        var loginPageDescriptionDiv = new sap.ui.layout.VerticalLayout("loginPageDescriptionDiv",{
        	content : DescriptionLyt
        });
        

        
        oLogin.addContent(
            new sap.ui.commons.TextView({
                text: 'Vendor UserName ',
                design: sap.ui.commons.TextViewDesign.h6,
                textAlign: "Left"
            }), {
                right: "60%",
                top: "100px",
                color : "#fff"
            }
        );


        var oNameInput = new sap.ui.commons.TextField('userName');
        oNameInput.setWidth("200px");
        oLogin.addContent(
            oNameInput, {
                left: "42%",
                right: "35%",
                top: "100px"
            }
        );


        ///
        oLogin.addContent(
            new sap.ui.commons.TextView({
                text: 'Password ',
                design: sap.ui.commons.TextViewDesign.h6,
                textAlign: "Left"
            }), {
                right: "60%",
                top: "130px",
                color : "#fff"
            }
        );


        var oNameP = new sap.ui.commons.PasswordField('vPassword');
        oNameP.setWidth("200px");
        oLogin.addContent(
            oNameP, {
                left: "42%",
                right: "35%",
                top: "130px"
            }
        );

        var oNameLogin = new sap.ui.commons.Button({
            text: "Login"
        }).addStyleClass("logBtn");
        oNameLogin.attachPress(function () {
        	    	
        	loginPageDiv.setBusy(true);
        	
            var jsonPOSTVendor = {
                
                username: sap.ui.getCore().byId("userName").getValue(),
                password: sap.ui.getCore().byId("vPassword").getValue()
                
            };

            jQuery.ajax({
                url: "/DealsAroundMe/Services/",
                dataType: "JSON",
                type: "POST",
                data: JSON.stringify(jsonPOSTVendor),
                async: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('X-SAP-SERVICES', 'VENDOR_LOGIN');                    
                },
                success: function (data, textStatus, jqXHR) {
                	if(data == null){
                    	sap.ui.getCore().byId("loginPageDiv").setBusy(false);
                    	sap.ui.commons.MessageBox.alert("Incorrect login credintials",'' , "Login Failed");
                    }
                    else{
                    	
                    	var Data = JSON.stringify(data);
                    	Data = Data.substring(1);
                    	Data = Data.substring(0, Data.length - 1);
                    	var vendorData = JSON.parse(Data);
                    	console.log(vendorData.vendorId);
                    	localStorage.setItem("VendorID", vendorData.vendorId);
                    	localStorage.setItem("VendorName", vendorData.vendorName);
                    	localStorage.setItem("VendorCity", vendorData.city);
                    	localStorage.setItem("VendorArea", vendorData.area);
                    	localStorage.setItem("VendorPhone", vendorData.phone);
                    	localStorage.setItem("VendorLatitude", vendorData.latitude);
                    	localStorage.setItem("VendorLongitude", vendorData.longitude);
                    	sap.ui.getCore().byId("idspotdealsView1").destroyContent();
                    	//loginPageDiv.setBusy(false);
                    	//sap.ui.getCore().byId("loginPageDiv").setBusy(false);
                    	var view = sap.ui.view({
                            id: "idspotdealsView2",
                            viewName: "spotdeals.VendorList",
                            type: sap.ui.core.mvc.ViewType.JS
                        });
                        
                        view.placeAt("content");
                    }
                              

                },
                error: function (jqXHR, textStatus, errorThrown) {
                	sap.ui.getCore().byId("loginPageDiv").setBusy(false);                 
                    sap.ui.commons.MessageBox.alert("Error Occured : "+errorThrown,'' , "Error");
                }
            });
        
        });
        
        var Register = new sap.ui.commons.Button({
            text: "Register"
        }).addStyleClass("logBtn");
        var allUsers;
        Register.attachPress(function () {
        	
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
                    xhr.setRequestHeader('X-SAP-SERVICES', 'VENDOR');
                },
                success: function (data, textStatus, jqXHR) {
                	   	 //console.log(data[0].username);
                	allUsers = data;
                	 }
			});
        	
        	
        	 var oLayout = new sap.ui.commons.form.GridLayout();
        	 var oRegForm = new sap.ui.commons.form.Form({
                
                 layout: oLayout,
                 formContainers: [
                     
                     new sap.ui.commons.form.FormContainer({
                         
                         formElements: [
                            
                             new sap.ui.commons.form.FormElement({
                            	 label: new sap.ui.commons.Label({
                                     text: "Vendor Name",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("name", {
                                	 
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "City",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("city",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Area",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("area",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Phone number",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),

                                 fields: [new sap.ui.commons.TextField("phone",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Latitude",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("latitude",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Longitude",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("longitude",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "User Name",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.TextField("userName1",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Password",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.PasswordField("password1",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             }),
                             new sap.ui.commons.form.FormElement({
                                 label: new sap.ui.commons.Label({
                                     text: "Confirm Password",
                                     layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 }),
                                 fields: [new sap.ui.commons.PasswordField("Cpassword1",{
                                	 layoutData: new sap.ui.core.VariantLayoutData({
                                         multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                                             hCells: "7"
                                         })]
                                     })
                                 })]
                             })
                         ],
                        
                        
                     }),
                                   
                                        
                 ],
                 layoutData: new sap.ui.core.VariantLayoutData({
                     multipleLayoutData: [new sap.ui.commons.form.GridElementData({
                         hCells: "6"
                     })]
                 })
                 
             });
        	
        	
        	jQuery.sap.require("sap.ui.core.Popup");
         
            
            var oFirstDialog = new sap.ui.commons.Dialog({modal: true});
        	oFirstDialog.setTitle("Register Vendor");
        	
        	oFirstDialog.addContent(oRegForm);
        	oFirstDialog.setWidth("500px");
        	oFirstDialog.addButton(new sap.ui.commons.Button({text: "Register", 
        		press:function(){
        			var userExist = false;
        		for(var i=0; i< allUsers.length ; i++){
        			
        			if(sap.ui.getCore().byId("userName1").getValue() == allUsers[i].username){
        				sap.ui.commons.MessageBox.alert("User Name already exists ",'' , "Error");
        				userExist = true;
        			}
        		}
        			
        		
        		 if(sap.ui.getCore().byId("name").getValue() == "" ||
                			sap.ui.getCore().byId("city").getValue() == "" ||
                			sap.ui.getCore().byId("area").getValue() == "" ||
                			sap.ui.getCore().byId("latitude").getValue() == "" ||
                			sap.ui.getCore().byId("longitude").getValue() == "" ||
                			sap.ui.getCore().byId("phone").getValue() == "" ||
                			sap.ui.getCore().byId("userName1").getValue() == ""){
        			 sap.ui.commons.MessageBox.alert("All fields are mandatory. Pleae enter all the details. ",'' , "Error");
                	 }
        		 else if(sap.ui.getCore().byId("Cpassword1").getValue().length < 6){
        			 sap.ui.commons.MessageBox.alert("Password must be more than 6 characters ",'' , "Password Error");
        		 }
        		 else if(sap.ui.getCore().byId("Cpassword1").getValue() != sap.ui.getCore().byId("password1").getValue() ){
               		 sap.ui.commons.MessageBox.alert("Passwords doesn't Match ",'' , "Password Error");
               	 }
               
               	 else if(userExist == false){
               	 var randomID = Math.floor((Math.random()*999)+100);
                                                                 
                    var jsonPOSTVendor = {
                        vendorId: randomID,
                        vendorName: sap.ui.getCore().byId("name").getValue(),
                        city: sap.ui.getCore().byId("city").getValue(),
                        area: sap.ui.getCore().byId("area").getValue(),
                        latitude: sap.ui.getCore().byId("latitude").getValue(),
                        longitude: sap.ui.getCore().byId("longitude").getValue(),
                        phone: sap.ui.getCore().byId("phone").getValue(),
                        username: sap.ui.getCore().byId("userName1").getValue(),
                        password: sap.ui.getCore().byId("password1").getValue()
                        
                    };

                    jQuery.ajax({
                        url: "/DealsAroundMe/Services/",
                        dataType: "json",
                        type: "POST",
                        data: JSON.stringify(jsonPOSTVendor),
                        async: false,
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader('X-SAP-SERVICES', 'VENDOR');
                        },
                        success: function (data, textStatus, jqXHR) {
                            
                            jQuery.sap.require("sap.ui.core.Popup");

                            sap.ui.commons.MessageBox.alert("Successfully Created the Vendor : "+sap.ui.getCore().byId("name").getValue(),'' , "Success");
                          
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                       	 sap.ui.commons.MessageBox.alert("Error occured ",'' , "Error");
                        }
                    });


                    oFirstDialog.close();
                    
                
                }
               	
                
        			
        			
        			
        		}}));
        	oFirstDialog.addButton(new sap.ui.commons.Button({
        		text: "Clear",
        		press :function(){
        			 sap.ui.getCore().byId("city").setValue("");
                     sap.ui.getCore().byId("area").setValue("");
                     sap.ui.getCore().byId("latitude").setValue("");
                    sap.ui.getCore().byId("longitude").setValue("");
        		}}));
        	oFirstDialog.open();
        	
        	 oFirstDialog.attachClosed(function(){
        		 oFirstDialog.destroy();
             });
        	
        	
        	
            //oRoot.addContent(oRegLayout);

            //oRegLayout.setWidth("300px");
            //oRegLayout.setHeight("300px");

           /* var oPopup =
                new sap.ui.core.Popup(oRegLayout, // content
                    true, // modality
                    true, // shadow
                    true // autoclose
            );*/
            
            //oPopup.open();
            
       	 if (navigator.geolocation)
 	    {
 	    navigator.geolocation.getCurrentPosition(showPosition);
 	    
 	    function showPosition(position)
   	  {
   	  
   	  
   	  sap.ui.getCore().byId("latitude").setValue(position.coords.latitude);
   	  sap.ui.getCore().byId("longitude").setValue(position.coords.longitude);
   	  
   	jQuery.ajax({
        url: "http://maps.googleapis.com/maps/api/geocode/json?latlng="+position.coords.latitude+","+position.coords.longitude+"&sensor=true",
        dataType: "json",
        type: "GET",
        async: false,
        success: function (data, textStatus, jqXHR) {
        	 sap.ui.getCore().byId("city").setValue(data.results[0].address_components[3].long_name);
        	 sap.ui.getCore().byId("area").setValue(data.results[0].address_components[2].long_name);
            
          
        },
        error: function (jqXHR, textStatus, errorThrown) {
       	 sap.ui.commons.MessageBox.alert("Error occured in geo",'' , "Error");
        }
    });
   	  }
 	    }
        	
        });
        
       

        oLogin.addContent(
            oNameLogin, {
                left: "45%",
                right: "45%",
                top: "170px"
            }
        );
    
        oLogin.addContent(
        		Register, {
                    left: "55%",
                    right: "35%",
                    top: "170px"
                }
            );
        

       /* return oLogin;*/
        loginPageDiv.addContent(loginPageDescriptionDiv);
        loginPageDiv.addContent(oLogin);

        this.mainContainer.addContent(oAppHeader);
        
        
        this.mainContainer.addContent(loginPageDiv);

        return this.mainContainer;


    }

});