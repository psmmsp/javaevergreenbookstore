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
<title>Category List - Evergreen Bookstore Administration</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		<h2>Category Management</h2>
		<h3><a href="category_form.jsp">Create New Category</a></h3>
	</div>
	<c:if test="${message != null}">
		<div align="center">
			<h4><i class="message">${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="cat" items="${catLst}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${cat.categoryId}</td>
					<td>${cat.name}</td>
					<td>
						<a href="edit_category?id=${cat.categoryId}">Edit</a> &nbsp;
						<a href="javascript:void(0);" class="deletelink" id="${cat.categoryId}">Delete</a>
					</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:directive.include file="footer.jsp"/>
	<script type="text/javascript">
			$(document).ready(function(){
				$(".deletelink").each(function(){
					$(this).on("click", function(){
						categoryId = $(this).attr("id");
						if(confirm('Are you sure you want to delete the category with ID: '+categoryId)){
							window.location = 'delete_category?id='+ categoryId;
						}
					});
				});
			});
  </script>
</body>
</html>