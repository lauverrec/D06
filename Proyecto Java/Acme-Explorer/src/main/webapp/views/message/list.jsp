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
	name="messages" requestURI="${requestURI}" id="row">


	<!-- Botones para sponsor -->
	<security:authorize access="hasRole('SPONSOR')">
		<display:column>
			<spring:url value="message/sponsor/display.do"
				var="displaySponsorURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displaySponsorURL}"><spring:message
					code="message.display" /></a>
		</display:column>

	</security:authorize>

	<!-- Botones para administrator -->
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column>
			<spring:url value="message/administrator/display.do"
				var="displayAdministratorURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displayAdministratorURL}"><spring:message
					code="message.display" /></a>
		</display:column>

	</security:authorize>


	<!-- Botones para ranger -->
	<security:authorize access="hasRole('RANGER')">
		<display:column>
			<spring:url value="message/ranger/display.do"
				var="displayRangerURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displayRangerURL}"><spring:message
					code="message.display" /></a>
		</display:column>

	</security:authorize>


	<!-- Botones para explorer -->
	<security:authorize access="hasRole('EXPLORER')">
		<display:column>
			<spring:url value="message/explorer/display.do"
				var="displayExplorerURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displayExplorerURL}"><spring:message
					code="message.display" /></a>
		</display:column>

	</security:authorize>


	<!-- Botones para manager -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<spring:url value="message/manager/display.do"
				var="displayManagerURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displayManagerURL}"><spring:message
					code="message.display" /></a>
		</display:column>



	</security:authorize>

	<!-- Botones para auditor -->
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<spring:url value="message/auditor/display.do"
				var="displayAuditorURL">
				<spring:param name="messageId" value="${row.id}" />
			</spring:url>

			<a href="${displayAuditorURL}"><spring:message
					code="message.display" /></a>
		</display:column>


	</security:authorize>


	<!-- Attributes -->

	<spring:message code="message.format.date" var="pattern"></spring:message>
	<spring:message code="message.moment" var="momentHeader"  />
	<display:column property="moment" title="${momentHeader}" format="${pattern }"/>

	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" />


</display:table>

