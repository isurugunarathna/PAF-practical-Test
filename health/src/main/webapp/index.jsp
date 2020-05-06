<%@page import="repository.DoctorRepository"%>
<html>
<head>
<title>Doctor Service | API</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Doctor.js"></script>

<body>

<div class="container">
<div class="row"> 
<div class="col-6">  
<h1>Hospital Management </h1> 
 
 <form id="formDoctor" name="formDoctor" method="post" >
    
  <!--  <br> Doctor id:   <input id="doctorID" name="doctorID" type="text" class="form-control form-control-sm">-->
 
  <br> Doctor name:   <input id="doctorName" name="doctorName" type="text" class="form-control form-control-sm"> 
 
  <br><div class="input-group input-group-sm mb-3">  
  <div class="input-group-prepend">   
  <span class="input-group-text" id="lblName"> section: </span>  </div>  
  <select id="ddlsection" name="ddlsection">   
  <option value="0">--Select section--</option>   
  <option value="1">1 - ALLERGY & IMMUNOLOGY</option>   
  <option value="2">2 - ANESTHESIOLOGY</option>   
  <option value="3">3 - DERMATOLOGY</option>   
  <option value="4">4 - DIAGNOSTIC RADIOLOGY</option>
  <option value="5">5 - EMERGENCY MEDICINE</option>
  <option value="6">6 - FAMILY MEDICINE</option>
  <option value="7">7 - INTERNAL MEDICINE</option>
  <option value="8">8 - MEDICAL GENETICS</option>
  <option value="9">9 - NEUROLOGY</option>
  <option value="10">10 - NUCLEAR MEDICINE</option>  </select> 
  </div> 
 
 
	<br> Contact Number:   <input id="contact_no" name="contact_no" type="text" class="form-control form-control-sm">  
 
  	<br> Email Address:   <input id="email" name="email" type="text" class="form-control form-control-sm"> 
  
  
  
 	
  <br> <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
   <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">  
   
   </form> 
   
   <div id="alertSuccess" class="alert alert-success"></div>  
   <div id="alertError" class="alert alert-danger"></div>
   
   <br>  <div id="divItemsGrid">   
   <%    DoctorRepository itemObj = new DoctorRepository();    
   out.print(itemObj.viewDoctor());   %>  
   </div>
</div>
</div>
</div>

</body>
</html>
