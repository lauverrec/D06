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



<display:table name="sponsorship" class="displaytag"
  requestURI="sponsorship/sponsor/display.do" id="row">
  
  <display:column>
  <spring:message code="sponsorship.link" />:
	<jstl:out value="${row.link}"></jstl:out>
	<p>
	<spring:message code="sponsorship.bannerURL" />:
		<jstl:out value="${row.bannerURL}"></jstl:out>
	</p>
	<spring:message code="sponsorship.creditCard" />:
		<jstl:out value="${row.creditCard.holderName}"></jstl:out>
	<p>
	<%-- <spring:message code="sponsorship.trip" />:
		<jstl:out value="${row.trip.ticker}"></jstl:out>
	<p> --%>
	
	 <security:authorize access="hasRole('SPONSOR')">
		<spring:url value="sponsorship/sponsor/displayTrip.do" var="displayURL">
		<spring:param name="sponsorshipId" value="${row.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="sponsorship.trip.display"/></a>
		
	</security:authorize> 
</display:column>
	

</display:table>
