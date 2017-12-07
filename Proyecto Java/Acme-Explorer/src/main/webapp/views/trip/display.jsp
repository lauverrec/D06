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


<form:form action="trip/manager_/edit.do" modelAttribute="trip">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="publicationDate" />
		
	

	<form:hidden path="price"/>
	<form:hidden path="reasonWhy"/>
	<form:hidden path="cancelled"/>
	<form:hidden path="manager"/>
	<form:hidden path="stages"/>
	<!--<form:hidden path="tags"/>-->
	<form:hidden path="applicationsFor"/>
	<form:hidden path="notes"/>
	<form:hidden path="auditRecords"/>
	
	
	<!--  <input id="cancelled" name="cancelled" type="hidden" value="0"/>-->


	<p><spring:message code="trip.ticker" />: <jstl:out value="${trip.ticker}"></jstl:out></p>
	<p><spring:message code="trip.title" />:  <jstl:out value="${trip.title}"></jstl:out></p>
	<p><spring:message code="trip.description" />:  <jstl:out value="${trip.description}"></jstl:out></p>
	
	<form:label path="requirementsExplorers"><spring:message code="trip.requirementsExplorers" />:</form:label>
	<form:input path="requirementsExplorers" readOnly="true"/>
	<form:errors cssClass="error" path="requirementsExplorers" />
	<br />


	
	<p><spring:message code="trip.startDate" />:  <jstl:out value="${trip.startDate}"></jstl:out></p>
	<p><spring:message code="trip.finishDate" />:  <jstl:out value="${trip.finishDate}"></jstl:out></p>
	
	<p><spring:message code="trip.manager.name" />:  <jstl:out value="${trip.manager.name}"></jstl:out></p>
		
	<br />

	
	
	
	<h1><spring:message code="trip.tags.name" /></h1>	
	<display:table name="tags" id="row" class="displaytag">
		<spring:message code="trip.title.name" var="titleHeader" />
		<display:column property="name" title="${titleHeader}" sortable="true" >
		<jstl:out value="${row.name}"></jstl:out>
	</display:column>
	</display:table>
	
	

	
	
<%-- 	<form:label path="ranger">
		<spring:message code="trip.ranger" />:
	</form:label>
	<form:select id="rangers" path="ranger" >
		<form:option value="0" label="----" />		
		<form:options items="${rangers}" itemValue="id"
			itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="ranger" />
	<br /> --%>
	
	
	
<%--	
	 <form:label path="legalText">
		<spring:message code="trip.legalText" />:
	</form:label>
	<form:select id="legalTexts" path="legalText" >
		<form:option value="0" label="----" />		
		<form:options items="${legalTexts}" itemValue="id"
			itemLabel="title" />
	</form:select>
	<form:errors cssClass="error" path="legalText" />
	<br />
	
	--%>
	

	


	<input type="submit" name="save" value="<spring:message code="trip.save" />" />&nbsp; 
	<jstl:if test="${trip.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="trip.delete" />"
			onclick="javascript: return confirm('<spring:message code="trip.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="trip.cancel" />"
		onclick="javascript:  window.location.replace('trip/manager_/list.do');" />
	<br />
</form:form>
