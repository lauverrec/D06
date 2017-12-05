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

<!-- Listing messageFodler -->
<display:table name="messageFolders" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag" keepStatus="true">

	<!-- Display -->
	<display:column>
		<spring:url value="messageFolder/sponsor/display.do" var="displayURL">
			<spring:param name="messageFolderId" value="${row.id}" />
		</spring:url>
		<a href="${displayURL}"><spring:message
				code="messageFolder.display" /></a>
	</display:column>
	
	
	<!-- Edit -->
	<display:column>
		<spring:url value="messageFolder/sponsor/edit.do" var="editURL">
			<spring:param name="messageFolderId" value="${row.id }"></spring:param>
		</spring:url>
		<a href="${editURL }"><spring:message code="messageFolder.edit" /></a>
	</display:column>


	<!-- Attributes -->
	<spring:message code="messageFolder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />


</display:table>
<div>
	<a href="messageFolder/sponsor/create.do"> <spring:message
			code="messageFolder.create" />
	</a>
</div>
