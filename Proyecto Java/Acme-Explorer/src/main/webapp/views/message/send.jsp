<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<div class="col-md-6 col-centered">
	<div class="well bs-component">
		<form:form action="message/actor/send.do" modelAttribute="m">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="sender" />
			<form:hidden path="folder" />
			<form:hidden path="moment" />
			<form:hidden path="spam" />


			<jstl:if test="${m.recipient == null}">

				<acme:select code="msg.recipient" path="recipient"
					items="${actors}" itemLabel="userAccount.username" />

				<br />

			</jstl:if>

			<form:select path="priority">
				<form:options items="${priorities}" />
			</form:select>


			<jstl:if test="${m.recipient != null}">
				<form:hidden path="recipient" />
			</jstl:if>
			<acme:textarea code="msg.subject" path="subject" />

			<acme:textarea code="msg.body" path="body" />



				<input type="submit" name="save"
					value="<spring:message code="msg.send.link" />"/>

			<input type="button" name="cancel"
				value="<spring:message code="msg.cancel.link" />"
				onclick="javascript: location.replace('welcome/index.do')" />

		</form:form>
	</div>
</div>