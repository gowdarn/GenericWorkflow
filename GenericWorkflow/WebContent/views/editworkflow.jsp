<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Edit Page</title>
</head>
<body>

<h2>Edit Workflow</h2>
<form:form method="POST" action="/GenericWorkflow/saveworkflow">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="description">description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
    <tr>
        <td><form:label path="createdBy">createdBy</form:label></td>
        <td><form:input path="createdBy" /></td>
    </tr>
    <tr>
        <td colspan="2">
        	<form:hidden path="id" />
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>