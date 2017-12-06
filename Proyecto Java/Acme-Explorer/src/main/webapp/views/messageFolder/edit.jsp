
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

<form:form action="messageFolder/sponsor/edit.do" modelAttribute="messageFolder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="messages"/>

	<form:label path="name">
		<spring:message code="messageFolder.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="modifiable">
		<spring:message code="messageFolder.modifiable" />:
	</form:label>
	<form:input path="modifiable" />
	<form:errors cssClass="error" path="modifiable" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="messageFolder.save" />" />&nbsp; 
	<jstl:if test="${messageFolder.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="messageFolder.delete" />"
			onclick="javascript: return confirm('<spring:message code="messageFolder.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="messageFolder.cancel" />"
		onclick="javascript:  window.location.replace('messageFolder/sponsor/list.do');" />
	<br />
</form:form>