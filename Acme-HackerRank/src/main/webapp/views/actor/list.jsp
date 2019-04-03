<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="actors" id="row" requestURI="${requestURI}" class="displaytag" pagesize="5">
		

	
	<display:column>
		<a href="actor/display.do?actorId=${row.id}"><spring:message code="actor.table.display.profile"/></a>
	</display:column>		

	<display:column property="fullname" titleKey="table.fullname" />
	
	<display:column property="email" titleKey="table.email" />
	
	<display:column property="phoneNumber" titleKey="table.phoneNumber" />
	
	<display:column property="address" titleKey="table.address"/>
	
	<display:column property="commercialName" titleKey="table.commercialName"/>
		

</display:table>

 	
