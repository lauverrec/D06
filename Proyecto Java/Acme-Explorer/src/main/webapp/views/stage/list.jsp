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

<display:table name="stages" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" keepStatus="true">


	<!-- Attributes -->
	<spring:message code="stage.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="stage.description" var="description" />
	<display:column property="description" title="${description}" sortable="true" />

	<spring:message code="stage.format.price" var="patternPrice "/>
	<spring:message code="stage.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" format="${patternPrice }"/>
	
	<spring:message code="stage.number" var="number" />
	<display:column property="number" title="${number}" sortable="true" />
	
	<spring:message code="stage.totalPrice" var="totalPrice" />
	<display:column property="totalPrice" title="${totalPrice}" sortable="true" />


</display:table>

<security:authorize access="hasRole('MANAGER')">
	<div>
		
		<spring:url value="stage/manager/create.do" var="stageURL">
				<spring:param name="tripId" value="${tripId}" />
			</spring:url>
		<a href="${stageURL}"><spring:message code="stage.create" /></a>	
	</div>
</security:authorize>
