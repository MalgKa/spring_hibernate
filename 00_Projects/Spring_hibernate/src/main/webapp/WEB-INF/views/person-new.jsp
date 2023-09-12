<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>dodaj osobę....</h1>
<form:form method="post" modelAttribute="person">
<div>
    <label for="login">
        login:<form:input path="login"/>
    </label>
    <label for="email">
        email: <form:input path="email"/>
    </label>
    <label for="password">
        password: <form:input path="password"/>
    </label>
    <form:button type="submit">submit</form:button>
    </form:form>
</div>
</body>
</html>
