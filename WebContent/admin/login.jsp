<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css"/>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<meta charset="UTF-8">
<title>Admin login</title>
</head>
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		<h2>Admin login</h2>
		
		<c:if test="${message != null}">
			<div align="center">
				<h4><i class="message">${message}</i></h4>
			</div>
		</c:if>		
		<form action="login" id="formlogin" method="post">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" id="email" name="email" size="20" /></td>
				</tr>
				
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password" size="20" /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><button type="submit">Login</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#formlogin").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				password: "required"
			},
			
			messages: {
				email: {
					required: "please enter email",
					email: "Please enter valid email"	
				},
				password: "please enter the password"
			}
		});
		
	});
	
</script>

</html>