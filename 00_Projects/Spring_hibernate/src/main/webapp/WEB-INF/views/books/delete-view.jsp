<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>usuń książkę</title>
</head>
<body>

<h3 style="color:red">Are you sure you want to delete the book: "${book.title}" ?</h3>


<form:form method="post" modelAttribute="book">
    <div>
        <input type="hidden" name="id" value="${book.id}"/>
    </div>
    <div>
        <button>yes</button>
        <button><a style="text-decoration: none" href="/view/book/list">no</a></button>
    </div>
</form:form>
</body>
</html>
