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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applicationFor" requestURI="${requestURI}" id="row">
	
	<spring:message code="applicationfor.format.date" var="pattern"></spring:message>
	<spring:message code="applicationfor.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" format="${pattern}" />

	<spring:message code="applicationfor.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="true" />

	<spring:message code="applicationfor.reason" var="reasonWhyHeader" />
	<display:column property="reasonWhy" title="${reasonWhyHeader}" sortable="true" />

	<spring:message code="applicationfor.creditCard.holderName" var="creditCardHeader" />
	<display:column property="creditCard.holderName" title="${creditCardHeader}" sortable="true" />
	
	<spring:message code="applicationfor.trip1" var="trip1" />
	<display:column title="${trip1}">

	
		<spring:url value="trip/display.do" var="displayURL">
			<spring:param name="tripId" value="${row.trip.id}" />
		</spring:url>
		<a href="${displayURL}"><spring:message
				code="applicationfor.trip" /></a>
	</display:column>

	
	<security:authorize access="hasRole('EXPLORER')">
<%-- 	<spring:message code="applicationfor.edit1" var="edit" />
		<display:column title="${edit}">		
			<spring:url value="applicationFor/explorer/edit.do" var="editlink">
				<spring:param name="applicationForId" value="${row.id}" />
			</spring:url>
			<a href="${editlink}"><spring:message code="applicationfor.edit" /></a>
		</display:column> --%>
		
			<spring:message code="applicationfor.cancel1" var="cancel1" />
		<display:column title="${cancel1}">
		<jstl:if test="${row.trip.startDate>date && row.status=='ACCEPTED'}">
			<spring:url value="applicationFor/explorer/cancel.do" var="cancellink">
				<spring:param name="applicationForId" value="${row.id}" />
			</spring:url>
			<a href="${cancellink}"><spring:message code="applicationfor.cancel" /></a>
		</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<spring:url value="applicationFor/administrator/edit.do"
				var="editlink">
				<spring:param name="applicationForId" value="${row.id}" />
			</spring:url>
			<a href="${editlink}"><spring:message code="applicationfor.edit" /></a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('EXPLORER')">
		<spring:message code="applicationfor.display" var="Display" />
		<display:column title="${Display}" sortable="true">
		<spring:url value="applicationFor/explorer/display.do" var="displayURL">
		<spring:param name="applicationforId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="applicationfor.view"/></a>
		</display:column>	
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
		<spring:message code="applicationfor.display" var="Display" />
		<display:column title="${Display}" sortable="true">
		<spring:url value="applicationFor/manager/display.do" var="displayURL">
		<spring:param name="applicationforId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="applicationfor.view"/></a>
		</display:column>	
		
		<spring:message code="applicationfor.change" var="change"/>
		<display:column title="${change}" sortable="true">
		<jstl:if test="${row.status=='PENDING' or row.status=='DUE' or row.status=='REJECTED'}">
		<spring:url value="applicationFor/manager/change.do" var="displayURL">
		<spring:param name="applicationforId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="applicationfor.change"/></a>
		</jstl:if>
		</display:column> 
		
		
	</security:authorize>
</display:table>
