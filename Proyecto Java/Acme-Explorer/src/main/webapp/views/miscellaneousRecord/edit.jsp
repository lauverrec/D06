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

<form:form action="${RequestURI }" modelAttribute="miscellaneousRecord">

  	HOLA

<%-- <display:table name="miscellaneousRecord" class="displaytag" id="row">

	<!-- Attributes -->
	<spring:message code="curricula.miscellaneousRecord.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />
	
	<spring:message code="curricula.miscellaneousRecord.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}"
		sortable="true" />
		
	<spring:message code="curricula.miscellaneousRecord.comments" var="commentsHeader" />
	<display:column property="comments" title="${commentsHeader}"
		sortable="true" />
		
	<security:authorize access="hasRole('RANGER')">
		<display:column>
			<spring:url value="miscellaneousRecord/ranger/edit.do" var="editlink">
				<spring:param name="miscellaneousRecordId" value="${row.id}" />
			</spring:url>
			<a href="${editlink}"><spring:message code="curricula.edit" /></a>
		</display:column>
	</security:authorize>
	
	
	
</display:table> --%>

</form:form>