function buildCategory(data) {
	$('#categoryList').empty();
	var categoryList = new sap.ui.commons.layout.VerticalLayout()
			.addStyleClass('categoryList');
	if (data.length != 0) {
		var categoryTxt = new sap.ui.core.HTML({
			content : "<div style='font-size: 18px;'>Category</div>"

		});
		categoryList.addContent(categoryTxt);
	}
	for (var i = 0; i < data.length; i++) {
		
		if (data[i].categoryId == 1001) {
			var imgIcon = "ClothIcon";
		}
		else if (data[i].categoryId == 1002) {
			var imgIcon = "phoneIcon";
		}
		else if (data[i].categoryId == 1003) {
			var imgIcon = "spaIcon";
		}
		else if (data[i].categoryId == 1004) {
			var imgIcon = "booksIcon";
		}
		else if (data[i].categoryId == 1005) {
			var imgIcon = "footIcon";
		}
		else if (data[i].categoryId == 1006) {
			var imgIcon = "othersIcon";
			
		}
		
		if(i==0){
			clickedCategory = data[i].categoryId;
		var categoryListItem = new sap.ui.core.HTML({
			content : "<div id='"+data[i].categoryId+ "' class='categoryListItemC categoryListItemCH'>" + "<div class='icondiv "
					+ imgIcon + "'>" + "</div>" + 
					"<div class='categoryName'>"+ data[i].category + "</div>" +

					"</div>"
		});
		categoryList.addContent(categoryListItem);
		}
		else{
			var categoryListItem = new sap.ui.core.HTML({
				content : "<div id='"+data[i].categoryId+ "' class='categoryListItemC'>" + "<div class='icondiv "
						+ imgIcon + "'>" + "</div>" + 
						"<div class='categoryName'>"+ data[i].category + "</div>" +

						"</div>"
			});
			categoryList.addContent(categoryListItem);
		}
	}
	
	
	return categoryList;

	
}