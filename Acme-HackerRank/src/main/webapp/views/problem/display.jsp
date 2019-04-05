<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<security:authorize access="hasRole('HACKER')">
		<strong><spring:message code="problem.company"/>:</strong>
			<a href="actor/display.do?actorId=${problem.company.id}"><jstl:out value="${problem.company.commercialName}"/></a>
		<br/>
	</security:authorize>


	<strong><spring:message code="problem.position"/>:</strong>
		<a href="position/display.do?positionId=${problem.position.id}"><jstl:out value="${problem.position.title}"/></a>
	<br/>
	
	<strong><spring:message code="problem.title"/>:</strong>
		<jstl:out value="${problem.title}"/>
	<br/>
	
	<strong><spring:message code="problem.statement"/>:</strong>
		<jstl:out value="${problem.statement}"/>
	<br/>
	
	<strong><spring:message code="problem.hint"/>:</strong>
		<jstl:out value="${problem.hint}"/>
	<br/>
	
	<strong><spring:message code="problem.attachments"/>:</strong>
		<jstl:out value="${problem.attachments}"/>
	<br/>
	
	<security:authorize access="hasRole('COMPANY')">
		<strong><spring:message code="problem.finalMode"/>:</strong>
			<jstl:out value="${problem.isFinalMode}"/>
		<br/>
	</security:authorize>
	
	
	<!-- Links -->
		
	<security:authorize access="hasRole('COMPANY')">
		<a href="position/list.do?companyId=${problem.company.id}">
			<spring:message	code="position.back" />			
		</a>
	</security:authorize>
	
	<security:authorize access="hasRole('HACKER')">
		<a href="position/display.positionId=${problem.position.id}">
			<spring:message	code="position.back" />			
		</a>
	</security:authorize>
	

