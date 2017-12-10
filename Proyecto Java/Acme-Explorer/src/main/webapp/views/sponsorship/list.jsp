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


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="sponsorships" requestURI="sponsorship/sponsor/list.do" id="row">
	
	<!-- Action links -->

	<security:authorize access="hasRole('SPONSOR')">
		<display:column>
		<spring:url value="sponsorship/sponsor/edit.do" var="editURL">
		<spring:param name="sponsorshipId" value="${row.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="sponsorship.edit"/></a>
		</display:column>		
	
	
		<display:column>
		<spring:url value="sponsorship/sponsor/display.do" var="displayURL">
		<spring:param name="sponsorshipId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="sponsorship.display"/></a>
		</display:column>		
	</security:authorize>
	
	<display:column property="bannerURL" titleKey="sponsorship.bannerURL" sortable="true" />
	<display:column property="trip.title" titleKey="sponsorship.trip" sortable="true" />	
</display:table>

