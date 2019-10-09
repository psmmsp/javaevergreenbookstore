<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css"/>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<meta charset="UTF-8">

		<c:if test="${category != null}">
			<title>Edit Category</title>
		</c:if>
		<c:if test="${category == null}">
			<title>Create New Category</title>
		</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${category != null}">
			<h2>Edit Category</h2>
		</c:if>
		<c:if test="${category == null}">
			<h2>Create New Category</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post" id="categoryform">
			<input type="hidden" name="categoryId" value="${category.categoryId }"/>
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post" id="categoryform">
		</c:if>
		
			<table class="form">
				<tr>
					<td align="right">Category Name:</td>
					<td align="left"><input type="text" id="categoryname" name="categoryname" size="20" value="${category.name}"/></td>
				</tr>
				<tr><td>&nbsp; </td> </tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit">Save</button> 
					<button type="button" id="cancelbutton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#categoryform").validate({
			rules: {
				categoryname: "required"
				
			},
			
			messages: {
				categoryname: "please enter catogory name"	
			}	
		});
		
		$("#cancelbutton").click(function() {
			history.go(-1);
		});
	});

</script>
</html>