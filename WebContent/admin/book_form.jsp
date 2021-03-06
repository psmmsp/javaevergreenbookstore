<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New Book</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${book != null}">
			<form action="update_user" method="post" id="userForm">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<c:if test="${book == null}">
			<form action="create_user" method="post" id="bookForm">
		</c:if>
		
		<table class="form">
			<tr>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.categoryId}">
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">title:</td>
				<td align="left"><input type="text" id="title" name="title" size="20" value="${book.title}" /></td>
			</tr>
			<tr>
				<td align="right">Author:</td>
				<td align="left"><input type="text" id="author" name="author" size="20" value="${book.Author}" /></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" id="isbn" name="isbn" size="20" value="${book.isbn}" /></td>
			</tr>
			<tr>
				<td align="right">Published Date:</td>
				<td align="left"><input type="text" id="publishdate" name="publishdate" size="20" value="${book.publishDate}" /></td>
			</tr>
			<tr>
				<td align="right">Book Image:</td>
				<td align="left"><input type="file" id="bookimage" name="bookimage" size="20"/></td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price" size="20" value="${book.price}" /></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="5" id="description" name="description"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>				
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$("#userForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
		
				fullname: "required",
				
				<c:if test="${user == null}">
				password: "required"
				</c:if>
			},
			
			messages: { 
				email: {
					required: "Please enter email",
					email: "Please enter an valid email address"
				},
				
				fullname: "Please enter full name",
				
				<c:if test="${user == null}">
				password: "Please enter password"
				</c:if>				
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
</script>
</html>