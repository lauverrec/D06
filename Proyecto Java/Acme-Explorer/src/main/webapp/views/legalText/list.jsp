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
	name="legalTexts" requestURI="legalText/administrator/list.do" id="row">
	
	<!-- Action links -->

	<security:authorize access="hasRole('ADMINISTRATOR')">
		
		<display:column>
		
		<jstl:if test="${row.draftMode==true}">
		<spring:url value="legalText/administrator/edit.do" var="editURL">
		<spring:param name="legalTextId" value="${row.id}"/>
		</spring:url>
		<a href="${editURL}"><spring:message code="legalText.edit"/></a>
		</jstl:if>	
		</display:column>	
		
	
	
		<display:column>
		<spring:url value="legalText/administrator/display.do" var="displayURL">
		<spring:param name="legalTextId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="legalText.display"/></a>
		</display:column>		
	</security:authorize>
	
	<!-- Attributes -->

	<spring:message code="legalText.title" var="titleHeader" />:
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="legalText.lawsNumber" var="lawsNumber" />:
	<display:column property="lawsNumber" title="${lawsNumber}" sortable="true" />
	
	<spring:message code="legalText.moment" var="moment" />:
	<display:column property="moment" title="${moment}" sortable="true" />
	
	<spring:message code="legalText.trip.title" var="trips" />:
	<display:column property="trips" title="${trips}" sortable="true" />
	
	
	<spring:message code="legalText.draftMode" var="draftModeHeader" />:
	<display:column property="draftMode" title="${draftModeHeader}" sortable="true" />
	

</display:table>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<div>
		<a href="legalText/administrator/create.do"> <spring:message
				code="legalText.create" />
		</a>
	</div>
</security:authorize>