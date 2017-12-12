
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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="${requestURI }" modelAttribute="mess">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sender"/>
	<form:hidden path="messageFolder"/>

	<form:label path="moment">
		<spring:message code="message.moment" />:
	</form:label>
	<form:input path="moment" readonly="true" />
	<form:errors cssClass="error" path="moment" />
	<br />

	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
	<form:input path="subject"/>
	<form:errors cssClass="error" path="subject" />
	<br />

	<form:label path="body">
		<spring:message code="message.body" />:
	</form:label>
	<form:input path="body"/>
	<form:errors cssClass="error" path="body" />
	<br />

	<form:label path="priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:input path="priority" />
	<form:errors cssClass="error" path="priority" />
	<br />


<form:label path="recipient">
		<spring:message code="message.sendTo" />:
	</form:label>
	<form:select id="actors" path="recipient">
		<form:option value="0" label="----" />
		<form:options items="${actors}" itemValue="id"
			itemLabel="surname" />
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br />


<!-- Boton save y deletes -->
	<input type="submit" name="send"
		value="<spring:message code="message.send" />" />&nbsp; 
	

</form:form>
