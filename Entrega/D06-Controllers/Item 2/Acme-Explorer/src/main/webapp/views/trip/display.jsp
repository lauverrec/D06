<%--
 * edit.jsp
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


<form:form action="${resquestURI}" modelAttribute="trip">
	
	<p><spring:message code="trip.ticker" />: <jstl:out value="${trip.ticker}"></jstl:out></p>
	<p><spring:message code="trip.title" />:  <jstl:out value="${trip.title}"></jstl:out></p>
	<p><spring:message code="trip.description" />:  <jstl:out value="${trip.description}"></jstl:out></p>
	<p><spring:message code="trip.startDate" />:  <jstl:out value="${trip.startDate}"></jstl:out></p>
	<p><spring:message code="trip.finishDate" />:  <jstl:out value="${trip.finishDate}"></jstl:out></p>	
	<p><spring:message code="trip.manager.name" />:  <jstl:out value="${trip.manager.name}"></jstl:out></p>
		
	
	<h2><spring:message code="trip.tags.name.table" /></h2>	
	<display:table name="tags" id="row" class="displaytag">
		<spring:message code="trip.tags.title" var="titleHeader" />
		<display:column property="name" title="${titleHeader}" sortable="false" >
			<jstl:out value="${row.name}"></jstl:out>
		</display:column>
	</display:table>
	
	<h2><spring:message code="trip.requirementsExplorers.name.table" /></h2>	
	<display:table name="requirements" id="row" class="displaytag">
		<spring:message code="trip.requirementsExplorers.title" var="titleHeader" />
		<display:column title="${titleHeader}" sortable="false" >
			<jstl:out value="${row}"></jstl:out>
		</display:column>
	</display:table>
	
	<h2><spring:message code="trip.stages.name.table" /></h2>	
	<display:table name="stages" id="row" class="displaytag">
		<spring:message code="trip.stages.title" />
		<display:column property="title" title="${titleHeader}" sortable="false" >
			<jstl:out value="${row.title}"></jstl:out>
		</display:column>
		<spring:message code="trip.stages.description" var="titleHeader2" />
		<display:column property="description" title="${titleHeader2}" sortable="false" >
			<jstl:out value="${row.description}"></jstl:out>
		</display:column>
	</display:table>
	
	<h2><spring:message code="trip.auditRecords.name.table" /></h2>	
	<display:table name="auditRecords" id="row" class="displaytag">
		<spring:message code="trip.auditRecords.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" sortable="false" >
			<jstl:out value="${row.title}"></jstl:out>
		</display:column>
		<spring:message code="trip.auditRecords.description" var="titleHeader2" />
		<display:column property="description" title="${titleHeader2}" sortable="false" >
			<jstl:out value="${row.description}"></jstl:out>
		</display:column>
	</display:table>

	
	<h2><spring:message code="trip.notes.name.table" /></h2>	
	<display:table name="notes" id="row" class="displaytag">
	
		<spring:message code="trip.notes.createdMoment" var="titleHeader" />
		<display:column property="createdMoment" title="${titleHeader}" sortable="false" >
			<jstl:out value="${row.createdMoment}"></jstl:out>
		</display:column>
		
		<spring:message code="trip.notes.body" var="titleHeader2" />
		<display:column property="body" title="${titleHeader2}" sortable="false" >
			<jstl:out value="${row.body}"></jstl:out>
		</display:column>
		
		<spring:message code="trip.notes.remark" var="titleHeader3" />
		<display:column property="remark" title="${titleHeader3}" sortable="false" >
			<jstl:out value="${row.remark}"></jstl:out>
		</display:column>
		
		<spring:message code="trip.notes.reply" var="titleHeader4" />
		<display:column property="reply" title="${titleHeader4}" sortable="false" >
			<jstl:out value="${row.reply}"></jstl:out>
		</display:column>
		
		<spring:message code="trip.notes.replyMoment" var="titleHeader5" />
		<display:column property="replyMoment" title="${titleHeader5}" sortable="false" >
			<jstl:out value="${row.replyMoment}"></jstl:out>
		</display:column>
		
	</display:table>
	
	
<h2><spring:message code="sponsorship.bannerURL" /></h2>
<display:table name="sponsorshiprandom" id="row" class="displaytag">
		<jstl:if test="${row!='nothing to show'}">
<spring:message code="sponsorship.bannerURL" var="bannerURLHeader" />
		 <display:column >
		 <div
  style="position: relative; width: 500px; height: 300px; margin-left: auto; margin-right: auto;">
  			
		  <img src="${row}"width= "500" height="300">
		  </div>
		 </display:column> 
	</jstl:if>
</display:table>
	
	
<security:authorize access="hasRole('MAMANGER')">
	<input type="button" name="back" value="<spring:message code="trip.back" />"
		onclick="javascript:  window.location.replace('trip/manager_/list.do');" />
	<br />
</security:authorize>

<security:authorize access="hasRole('EXPLORER')">
	<input type="button" name="back" value="<spring:message code="trip.back" />"
		onclick="javascript:  window.location.replace('applicationFor/explorer/list.do');" />
	<br />
</security:authorize>


</form:form>
