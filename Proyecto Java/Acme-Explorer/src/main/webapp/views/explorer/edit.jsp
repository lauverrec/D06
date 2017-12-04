<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="explorer/edit.do" modelAttribute="explorer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="socialIdentities"/>
	<form:hidden path="messagesFolders"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="stories"/>
	<form:hidden path="applicationsFor"/>
	<form:hidden path="contactsEmergency"/>
	
	<security:authorize access="hasRole('EXPLORER')">
	
	<form:label path="name">
		<spring:message code="explorer.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="explorer.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<spring:message code="explorer.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="address">
		<spring:message code="explorer.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="phone">
		<spring:message code="explorer.phone" />:
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />
	
	
	
	<input type="submit" name="save"
		value="<spring:message code="explorer.save" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="explorer.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
	</security:authorize>
	
</form:form>