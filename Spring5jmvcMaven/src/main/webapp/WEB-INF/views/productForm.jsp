<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<head>
<link  href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><spring:message code="productForm.heading"/></title>
</head>
<body>

<div class="container">
  <h2><spring:message code="productForm.heading"/></h2>
  <mvc:form cssClass="form-horizontal" modelAttribute="product" action="addProduct" method="post">
  		<table class="table table-striped">        
			<tr>
				<td>
					<mvc:label path="productCode">
						<spring:message code="productForm.productCode"/>
					</mvc:label>
				</td>
				<td><mvc:input path="productCode" /></td>
				<td><mvc:errors path="productCode" cssClass="alert alert-danger"/></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="productDescription">
						<spring:message code="productForm.productDescription"/>
					</mvc:label>
				</td>
				<td><mvc:input path="productDescription" /></td>
				<td><mvc:errors path="productDescription" cssClass="alert alert-danger"/></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="unitPrice">
						<spring:message code="productForm.unitPrice"/>
					</mvc:label>
				</td>
				<td><mvc:input path="unitPrice" /></td>
				<td><mvc:errors path="unitPrice" cssClass="alert alert-danger"/></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="qoh">
						<spring:message code="productForm.qoh"/>
					</mvc:label>
				</td>
				<td><mvc:input path="qoh" /></td>
				<td><mvc:errors path="qoh" cssClass="alert alert-danger"/></td>
			</tr>
			<tr>
				<td>
					<mvc:label path="category">
						<spring:message code="productForm.category"/>
					</mvc:label>
				</td>
				<td>
					<mvc:select path="category">
						<mvc:option value="STAN">Stationary</mvc:option>
						<mvc:option value="ELEC">Electronics</mvc:option>
					</mvc:select>
				</td>
				<td> </td>*
			</tr>
			<tr>
				<td></td>
				<td>
        			<button type="submit" class="btn btn-default"><spring:message code="productForm.submit"/></button>
        			<button type="reset" class="btn btn-default"><spring:message code="productForm.clear"/></button>
    			</td>
    			<td></td>  
			</tr>
		</table>
  </mvc:form>
  </div>
</body>
</html>