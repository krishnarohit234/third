<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link  href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<script src="<c:url value='/resources/js/jquery-2.1.1.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/myscript.js'/>" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><spring:message code="productForm.heading"/></title>
</head>
<body>

<div class="container">
  <h2><spring:message code="productForm.heading"/></h2>
  <form id="productForm">
  		<table class="table table-striped">        
			<tr>
			
				<td>
					<label for="productCode">
						<spring:message code="productForm.productCode"/>
					</label>
				</td>
				<td> <input type="text" id="productCode" /> 
					 <button type="button" id="search" class="btn btn-search">Search</button>
					 <br/><span id="productCodeMsg"></span>
				</td>
			</tr>
			<tr>
				<td>
					<label for="productDescription">
						<spring:message code="productForm.productDescription"/>
					</label>
				</td>
				<td> <input type="text" id="productDescription" />
				     <span id="productDescriptionMsg"></span>
			     </td>
			</tr>
			<tr>
				<td>
					<label for="unitPrice">
						<spring:message code="productForm.unitPrice"/>
					</label>
				</td>
				<td> <input type="text" id="unitPrice" />
					 <span id="unitPriceMsg"></span>
				 </td>
			</tr>
			<tr>
				<td>
					<label for="qoh">
						<spring:message code="productForm.qoh"/>
					</label>
				
				</td>
				<td><input type="text" id="qoh" />
					<span id="qohMsg"></span>
				</td>
			</tr>
			<tr>
				<td>
					<label for="category">
						<spring:message code="productForm.category"/>
					</label>
				</td>
				<td>
					<select id="category">
						<option value="STAN">Stationary</option>
						<option value="ELEC">Electronics</option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
        			<button id="uptProd" type="button" class="btn btn-default"><spring:message code="productForm.edit"/></button>
        			<button id="dletProd" type="button" class="btn btn-default"><spring:message code="productForm.delete"/></button>
        			<h2 id="statusMessage"></h2>
    			</td>
			</tr>
		</table>
		
  </form>
  </div>
</body>
</html>