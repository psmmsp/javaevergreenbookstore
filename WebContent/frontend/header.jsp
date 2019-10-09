<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<div>
		<img src="images/BookstoreLogo.png"/>
	</div>
	
	<div>
		<input type="text" name="Keyword" size="50"/>
		<input type="button" value="Search"/>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="login">Sign In</a> |
		<a href="register">Register</a> |
		<a href="view_cart">Cart</a>
	</div>
	<div>&nbsp;</div>
	<div align="center">
		<c:forEach var="cat" items="${catLst}" varStatus="status">
		<a href="view_category?id=${cat.categoryId}">
			<b><c:out value="${cat.name}"/></b> 
		</a>
		<c:if test="${not status.last}">
		&nbsp; | &nbsp;
		</c:if>
		</c:forEach>
	</div>
</div>