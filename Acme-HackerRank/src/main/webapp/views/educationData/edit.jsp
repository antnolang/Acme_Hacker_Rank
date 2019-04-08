<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="educationData/hacker/edit.do" modelAttribute="educationData">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<fieldset>
		<legend><spring:message code="educationData.fieldset"/></legend>
		
		<acme:textbox code="educationData.degree" path="degree"/>
		<acme:textbox code="educationData.institution" path="institution"/>
		<acme:textbox code="educationData.mark" path="mark"/>
		<acme:textbox code="educationData.startDate" path="startDate" placeholder="dd/mm/yyyy"/>
		<acme:textbox code="educationData.endDate" path="endDate" placeholder="dd/mm/yyyy"/>
	</fieldset>
	
	<!-- Buttons -->
	<acme:submit name="save" code="educationData.save"/>
	&nbsp;
	<acme:submit name="delete" code="educationData.delete"/>
	&nbsp;
	<acme:cancel code="educationData.cancel" url="educationData/hacker/backCurriculum.do?educationDataId=${educationData.id}"/>
</form:form>