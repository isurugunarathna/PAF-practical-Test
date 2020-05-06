$(document).ready(function() 
	{  
	if ($("#alertSuccess").text().trim() == "")  {   
		$("#alertSuccess").hide();  }  
	$("#alertError").hide(); 
	}); 


$(document).on("click", "#btnSave", function(event) { 
	// Clear alerts---------------------
$("#alertSuccess").text("");  
$("#alertSuccess").hide();  
$("#alertError").text("");  
$("#alertError").hide(); 
	 
	 // Form validation-------------------  
var status = validateDoctorForm();  
if (status != true)  
{   
	$("#alertError").text(status);   
	$("#alertError").show();   
	return;  
	} 
	 
	 // If valid------------------------  

var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 

$.ajax( 
		{  url : "DoctorAPI2",
			type : type,
			data : $("#formDoctor").serialize(),
			dataType : "text",
			complete : function(response, status)  
			{   
				onItemSaveComplete(response.responseText, status);  
				} 
		}); 
});
		
function onItemSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 


		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 
			 document.getElementById('doctorName').value = "";
			
			 document.getElementById('contact_no').value = "";
			 document.getElementById('email').value = "";
			 
			 
		
			$("#divItemsGrid").html(resultSet.data);   
		 }
		else if (resultSet.status.trim() == "error")   
		{   
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

} 
else if (status == "error")  
{   
	$("#alertError").text("Error while saving.");   
	$("#alertError").show();  
}
else  {  
	$("#alertError").text("Unknown error while saving..");   
	$("#alertError").show();  
	} 

$("#hidItemIDSave").val("");  
$("#formItem")[0].reset();
} 

$(document).on("click", ".btnRemove", function(event) 
		
	  
		
		{  
	$.ajax(  
			{   
				url : "DoctorAPI2",
				type : "DELETE",
				data : "id=" + $(this).data("itemid"),
				dataType : "text",
				complete : function(response, status)   
				{    
					onItemDeleteComplete(response.responseText, status);   
				}
		});
		
});


function onItemDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success")   
{    
	$("#alertSuccess").text("Successfully deleted.");    
	$("#alertSuccess").show(); 

 $("#divItemsGrid").html(resultSet.data);   
 } else if (resultSet.status.trim() == "error")   
 {    
	 $("#alertError").text(resultSet.data);    
	 $("#alertError").show();   
	 } 

} else if (status == "error")  
{   
	$("#alertError").text("Error while deleting.");   
	$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show(); 
		} 
	} 

$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());     
	$("#doctorName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#ddlsection").val($(this).closest("tr").find('td:eq(1)').text());     


	$("#contact_no").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
	
	}); 


function validateDoctorForm() { 
 
	 
	 // NAME  
	if ($("#doctorName").val().trim() == "")  
	{   
		return "Insert Doctor Name.";  
		} 
	 
	 // special
	if ($("#ddlSpecialization").val() == "0") 
	{  
		return "Select Specialization."; 
		}
	 
	//email
	if ($("#email").val().trim() == "")  
	{   
		return "Insert Email.";  
		
		}
	//email type
	if(! $("#email").val().match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)){
		return "Invalid Email type.";
	}
	
	//phone
	if ($("#contact_no").val().trim() == "")  
	{   
		return "Insert Phone.";  
		} 

	
	 return true; 
	 } 

