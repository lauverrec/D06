<%--
 * display.jsp
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


<display:table name="messageDisplay" class="displaytag"
	requestURI="${requestURI }" id="row">

	<!-- Attributes -->
	<display:column>

		<spring:message code="message.moment" />
		<jstl:out value=":" />
		<jstl:out value="${row.moment}"></jstl:out>

		<p>
			<spring:message code="message.subject"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.subject}"></jstl:out>
		</p>

		<p>
			<spring:message code="message.body"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.body}"></jstl:out>
		</p>

		<p>
			<spring:message code="message.priority"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.priority}"></jstl:out>
		</p>


		<p>
			<spring:message code="message.messageFolder"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.messageFolder.name}"></jstl:out>
		</p>

		<p>
			<spring:message code="message.sender"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.sender.surname }, ${row.sender.name} "></jstl:out>
		</p>

		<p>
			<spring:message code="message.recipient"></spring:message>
			<jstl:out value=":" />
			<jstl:out value="${row.sender.surname }, ${row.sender.name}"></jstl:out>
		</p>


	</display:column>
</display:table>
