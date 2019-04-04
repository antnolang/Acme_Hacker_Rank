
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

<display:table name="problems" id="row" requestURI="${requestURI}" class="displaytag" pagesize="5">

	<display:column>
		<a href="problem/company,hacker/display.do?problemId=${row.id}"><spring:message code="problem.display"/></a>
	</display:column>	
	
	<jstl:if test="${!problem.isFinalMode}">
		<display:column>
			<a href="problem/company/edit.do?problemId=${row.id}"><spring:message code="problem.edit"/></a>
		</display:column>
	</jstl:if>
	
	<display:column property="title" titleKey="problem.title" />
			
	<display:column property="position.title" titleKey="problem.position.title" />

</display:table>


