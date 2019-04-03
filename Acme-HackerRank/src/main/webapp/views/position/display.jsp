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

	<strong><spring:message code="position.company"/>:</strong>
		<jstl:out value="${position.company.commercialName}"/>
	<br/>
	
	<strong><spring:message code="position.ticker"/>:</strong>
		<jstl:out value="${position.ticker}"/>
	<br/>

	<strong><spring:message code="position.title"/>:</strong>
		<jstl:out value="${position.title}"/>
	<br/>
	
	<strong><spring:message code="position.description"/>:</strong>
		<jstl:out value="${position.description}"/>
	<br/>
	
	<security:authorize access="hasRole('COMPANY')">
	<jstl:if test="${principal == position.commpany}">
		<strong><spring:message code="position.finalMode"/>:</strong>
			<jstl:out value="${position.finalMode}"/>
		<br/>
		
		<strong><spring:message code="position.isCancelled"/>:</strong>
			<jstl:out value="${position.isCancelled}"/>
		<br/>
	</jstl:if>
	</security:authorize>
	<strong><spring:message code="position.deadline"/>:</strong>
		<jstl:out value="${position.deadline}"/>
	<br/>
	
	<strong><spring:message code="position.profile"/>:</strong>
		<jstl:out value="${position.profile}"/>
	<br/>
	
	<strong><spring:message code="position.skills"/>:</strong>
		<jstl:out value="${position.skills}"/>
	<br/>
	
		<strong><spring:message code="position.technologies"/>:</strong>
		<jstl:out value="${position.technologies}"/>
	<br/>
	
	<strong><spring:message code="position.salary"/>:</strong>
		<jstl:out value="${position.salary}"/>
	<br/>
	
	
	<!-- Links -->	
	
	<a href="position/list.do?companyId=${position.company.id}">
		<spring:message	code="position.back" />			
	</a>

