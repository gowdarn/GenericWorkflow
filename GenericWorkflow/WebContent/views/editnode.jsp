<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
    <title>Edit Page</title>
</head>
<body>

<h2>Edit Node</h2>
<form:form method="POST" action="/GenericWorkflow/savenode">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="className">Java Class Name</form:label></td>
        <td><form:input path="className" /></td>
    </tr>
    <tr>
        <td><form:label path="workflow">Workflow</form:label></td>
        <td><select>
			    <option selected value="${command.workflow.id}">${command.workflow.name}</option>
			    <c:forEach var="item" items="${constantsBean.workflows}">
			    	<option value="${item.id}">${item.name}</option>
			    </c:forEach>
			</select>
		</td>
    </tr>
        <tr>
        <td><form:label path="previousNode">Previous Node</form:label></td>
        <td><select>
			    <option selected value="${command.previousNode.id}">${command.previousNode.name}</option>
			    <c:forEach var="item" items="${constantsBean.nodes}">
			    	<option value="${item.id}">${item.name}</option>
			    </c:forEach>
			</select>
		</td>
    </tr>
        <tr>
        <td><form:label path="nextNode">Next Node</form:label></td>
        <td><select>
			    <option selected value="${command.nextNode.id}">${command.nextNode.name}</option>
			    <c:forEach var="item" items="${constantsBean.nodes}">
			    	<option value="${item.id}">${item.name}</option>
			    </c:forEach>
			</select>
		</td>
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