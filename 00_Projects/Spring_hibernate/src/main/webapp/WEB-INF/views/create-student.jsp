<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        form {
            display: flex;
            flex-direction: column;
            row-gap: 2rem;
        }

        .row {
            display: flex;
            flex-direction: row;
        }

        .centered-label {
            text-align: center;
            padding-right: 0.3rem;
        }

        button {
            width: fit-content;
            background-color: orange;
            padding-inline: 0.8rem;
        }
    </style>
</head>
<body>
<form:form method="post" modelAttribute="student">
    <div>
        <label for="firstName">
            firstName: <form:input path="firstName"/>
        </label>
    </div>
    <div>
        <label for="lastName">
            firstName: <form:input path="lastName"/>
        </label>
    </div>
    <div>
        gender:
        <label>
            Female:<form:radiobutton path="gender" value="F"/>
            Male:<form:radiobutton path="gender" value="M"/>
        </label>
    </div>
    <div>
        <label>
            country:
            <form:select path="country">
                <form:option value="-" label="--Please select country--"/>
                <form:options items="${countries}"/>
            </form:select>
        </label>
    </div>
    <div class="row">
        <div class="centered-label"><p>notes:</p></div>
        <div>
            <form:textarea path="notes"/>
        </div>
    </div>
    <div>
        <label>
            mailingList: <form:checkbox path="mailingList"/>
        </label>
    </div>
    <div class="row">
        <div class="centered-label"><p>ProgrammingSkills: </p></div>
        <div>
            <form:select path="programmingSkills" multiple="true">
                <form:options items="${programmingSkills}"/>
            </form:select>
        </div>
    </div>
    <div>
        <label>
            hobbies: <form:checkboxes path="hobbies" items="${hobbies}"/>
        </label>
    </div>
    <button type="submit">send</button>
</form:form>
</body>
</html>
