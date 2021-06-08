<%--
  Created by IntelliJ IDEA.
  User: Huy Hoang
  Date: 6/8/2021
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh Sách</h1>
<button type="submit">
    <a href="/products?action=create">
        Add New Product
    </a>
</button>
<table>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Color</td>
        <td>Description</td>
        <td>Category</td>
    </tr>
    <c:forEach items="${product}" var="product">

        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.address}</td>
            <td>${product.age}</td>
            <td>${product.licenseName}</td>
            <td>
                <form action="/product" method="get">
                    <input type="number" name="id" value="${product.id}" hidden>
                    <input type="text" name="action" value="update" hidden>
                    <button type="submit">Update</button>
                </form>
            </td>
            <td>
                <form action="/product" method="get">
                    <input type="number" name="id" value="${product.id}" hidden>
                    <input type="text" name="action" value="remove" hidden>
                    <button type="submit">Delete</button>

                </form>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
