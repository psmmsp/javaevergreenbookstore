<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<img src="../images/BookstoreAdminLogo.png" />
	<div>
		Welcome, <c:out value="${sessionScope.useremail}"></c:out> | <a href="logout">Logout</a>
		<br/><br/>
	</div>
	<div id="headermenu">
		<div>
			<a href="list_users">
				<img src="../images/users.png"/><br/>Users
			</a> &nbsp;|
		</div>
		
		<div>
			<a href="list_category">
				<img src="../images/category.png"/><br/>Categories
			</a> &nbsp;|
		</div>
		
		<div>
			<a href="list_books">
				<img src="../images/bookstack.png"/><br/>Books
			</a> &nbsp;|
		</div>
		
		<div>
			<a href="customers"> 
				<img src="../images/customer.png"/><br/>Customers
			</a> &nbsp;|
		</div>
		
		<div>
			<a href="reviews">
				<img src="../images/review.png"/><br/>Reviews
			</a> &nbsp;|
		</div>
		<div>
			<a href="orders">
				<img src="../images/order.png"/><br/>Orders
			</a>	
		</div>
		
		
	</div>
</div>
