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

<!-- Listing trips -->

<form:form action="${requestURI}" method="get">

	<label><spring:message code="trip.search"/></label>
	<input type="text" name="keyword"/> <br />
	<br>
	<input type="submit" value="<spring:message code="trip.search" />" /> 	 
	
</form:form>

	<%-- <security:authorize access="hasRole('MANAGER')">
		<form:label path="lowprice">
			<spring:message code="trip.lowprice" />:
	</form:label>
		<form:input path="lowprice" />

		<form:label path="highprice">
			<spring:message code="trip.highprice" />:
	</form:label>
		<form:input path="highprice" />

		<form:label path="trip.startDate">
			<spring:message code="trip.startDate" />:
	</form:label>
		<form:input path="startDate" />

		<form:label path="trip.finishDate">
			<spring:message code="trip.finishDate" />:
	</form:label>
		<form:input path="finishDate" />

	</security:authorize> --%>

	<%-- <spring:url value="applicationFor/manager/edit.do" var="editlink">
		<spring:param name="search" value="search" />
		<spring:param name="lowprice" value="lowprice" />
		<spring:param name="highprice" value="highprice" />
		<spring:param name="startDate" value="startDate" />
		<spring:param name="finishDate" value="finishDate" />
	</spring:url>
	<input type="submit" name="search"
		value="<spring:message code="trip.search"/>"
		onClick=" window.location.href='${editlink}' ">/>&nbsp; --%>

<display:table name="trips" id="row" pagesize="5" class="displaytag" keepStatus="true">

<!-- Display -->
	<spring:message code="trip.display" var="Display" />
	<display:column title="${Display}" sortable="true">
		<spring:url value="trip/display.do" var="displayURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${displayURL}"><spring:message code="trip.display" /></a>
	</display:column>

<!-- Edit Para un Manager-->
	<security:authorize access="hasRole('MANAGER')">
	<spring:message code="trip.publicationDate" var="publicationDate" />	
	<spring:message code="trip.edit" var="Edit" />
		<display:column title="${Edit}" sortable="true">
			<jstl:if test="${row.publicationDate==null && row.manager==manager}">
			
				<spring:url value="trip/manager_/edit.do" var="editURL">
					<spring:param name="tripId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="trip.edit" /></a>
			
			</jstl:if>
		</display:column>		
	</security:authorize>
	
<!-- Apply Para un Explorer-->
	<security:authorize access="hasRole('EXPLORER')">
	
		<jstl:if test="${apply}">
			<display:column>
				<spring:url value="applicationFor/explorer/cancel.do" var="applyURL">
					<spring:param name="tripId" value="${row.id }" />
				</spring:url>
				<a href="${cancelURL}"><spring:message code="trip.cancel" /></a>
			</display:column>
		</jstl:if> 
		
		<jstl:if test="${!apply}">
			<display:column>
				<spring:url value="applicationFor/explorer/edit.do" var="applyURL">
					<spring:param name="tripId" value="${row.id }" />
				</spring:url>
				<a href="${applyURL}"><spring:message code="trip.apply" /></a>
			</display:column>
		</jstl:if> 
		
	</security:authorize>
	
<!-- survivalClass  Para un Explorer-->
	<security:authorize access="hasRole('EXPLORER')">
	<display:column>
		<spring:url value="survivalClass/explorer/list-enrol.do" var="displayURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${displayURL}"><spring:message code="trip.survivalClass" /></a>
	</display:column>
	</security:authorize>
<!-- Cancel  Para un Explorer-->



	<!-- Attributes -->
	<spring:message code="trip.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="trip.format.price" var="patternPrice "/>
	<spring:message code="trip.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" format="${patternPrice }"/>

	<spring:message code="trip.format.date" var="pattern"></spring:message>
	<spring:message code="trip.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" sortable="true" format="${pattern}" />

	<spring:message code="trip.format.date" var="pattern"></spring:message>
	<spring:message code="trip.finishDate" var="finishDateHeader" />
	<display:column property="finishDate" title="${finishDateHeader}" sortable="true" format="${pattern}" />
	
	<spring:message code="trip.ranger" var="rangerHeader" />
	<display:column title="${rangerHeader}" sortable="true">
		<spring:url value="curricula/display.do" var="stageURL">
			<spring:param name="rangerId" value="${row.ranger.id }" />
		</spring:url>
			<a href="${stageURL}"><spring:message code="trip.ranger" /></a>
	</display:column>
	
	<security:authorize access= "hasRole('MANAGER')">
	<spring:message code="trip.stage" var="Stages" />
	<display:column title="${Stages}" sortable="true">
		<spring:url value="stage/manager/list.do" var="stageURL">
			<spring:param name="tripId" value="${row.id }" />
		</spring:url>
			<a href="${stageURL}"><spring:message code="trip.stage" /></a>
	</display:column>
	</security:authorize>
		<security:authorize access="hasRole('MANAGER')">
	<spring:message code="trip.publicationDate" var="publicationDate" />	
	<spring:message code="trip.createStage" var="CreateStages" />
		<display:column title="${CreateStages}" sortable="true">
			<jstl:if test="${row.publicationDate==null && row.manager==manager}">
				<spring:url value="stage/manager/create.do" var="editURL">
					<spring:param name="tripId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="trip.stage.create" /></a>
			</jstl:if>
		</display:column>		
	</security:authorize>

<security:authorize access="hasRole('SPONSOR')">
	<spring:message code="sponsorship.create" var="Create" />
	<display:column title="${Create}" sortable="true">
		<spring:url value="sponsorship/sponsor/create.do" var="createURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${createURL}"><spring:message code="sponsorship.create" /></a>
	</display:column>
</security:authorize>

<security:authorize access="hasRole('AUDITOR')">
	<spring:message code="trip.auditRecord.create" var="Create" />
	<display:column title="${Create}" sortable="true">
		<spring:url value="auditRecord/auditor/create.do" var="createURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${createURL}"><spring:message code="auditRecord.create" /></a>
	</display:column>
</security:authorize>


<security:authorize access="hasRole('AUDITOR')">
<spring:message code="note.create" var="Create" />
	<display:column title="${Create}" sortable="true">
		<spring:url value="note/auditor/create.do" var="createURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${createURL}"><spring:message code="note.create" /></a>
	</display:column>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	<spring:message code="trip.survivalClass" var="SurvivalClasses" />
	<display:column title="${SurvivalClasses}" sortable="true">
	<jstl:if test="${row.manager==manager}">
		<spring:url value="survivalClass/manager/create.do" var="createURL">
			<spring:param name="tripId" value="${row.id}" />
		</spring:url>
		<a href="${createURL}"><spring:message code="survivalClass.create" /></a>
		</jstl:if>
	</display:column>
</security:authorize>




</display:table>

<security:authorize access="hasRole('MANAGER')">
	<div>
		<a href="trip/manager_/create.do"> 
			<spring:message	code="trip.create" />
		</a>
	</div>
</security:authorize>