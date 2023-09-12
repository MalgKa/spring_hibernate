<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update book</title>
</head>
<body>
<form:form method="post" modelAttribute="book">
<div>
    <label>title:
        <form:input path="title"/>
    </label>
</div>
<div>
    <label>description:
        <form:textarea path="description"/>
    </label>
</div>
<div>
    <label>rating:
        <form:input path="rating"/>
    </label>
</div>
<%--<div>--%>
<%--    authors:--%>
<%--    <form:select path="authors">--%>
<%--        <form:options items="${authorList}"/>--%>
<%--    </form:select></div>--%>
<%--<div>--%>
    <label>publisher:
        <form:select path="publisher.id" items="${publisherList}" itemLabel="name" itemValue="id"/>
    </label>
</div>
<div>
        <form:hidden path="id"/>
    <div>
        <button>save</button>
    </div>
    </form:form>
</body>
</html>
