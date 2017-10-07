sap.ui.controller("spotdeals.spotdealsView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf spotdeals.spotdealsView
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf spotdeals.spotdealsView
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf spotdeals.spotdealsView
*/
	onAfterRendering: function() {
		/*var pnl = new sap.ui.commons.Panel({text: 'Enter Data'});
		var layout = new sap.ui.commons.layout.VerticalLayout();
		
		var lblFirstName = new sap.ui.commons.Label({text: 'First Name'});   
		var txtFirstName = new sap.ui.commons.TextField();
		
		var lblLastName = new sap.ui.commons.Label({text: 'Last Name'});         
		var txtLastName = new sap.ui.commons.TextField();
		
		var btnSubmit = new sap.ui.commons.Button({
			text: 'Submit',
			press: function() {
				alert("Hello " + txtFirstName.getValue() + " " + txtLastName.getValue() + "!");
			}
			});
		
		//pnl.placeAt('content');
		layout.addContent(lblFirstName);
		layout.addContent(txtFirstName);
		layout.addContent(lblLastName);
		layout.addContent(txtLastName);
		layout.addContent(btnSubmit);
		//layout.placeAt('content');
		return layout;*/
	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf spotdeals.spotdealsView
*/
//	onExit: function() {
//
//	}

});

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