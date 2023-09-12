<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
</head>
<body>
<div>
    <a href='<c:url value="/view/book/add"/>'>add book</a>
</div>
<div>
    <table cellpadding="5" border="1">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Rating</th>
            <th>Description</th>
            <th>Publisher</th>
            <th>Authors</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.rating}</td>
                <td>${book.description}</td>
                <td>${book.publisher.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${book.authors}" var="author">
                            <li>${author.firstName} ${author.lastName}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <button><a href="/view/book/update?id=${book.id}">update</a></button>
                </td>
                <td>
                    <button><a href="/view/book/delete?id=${book.id}">delete</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
