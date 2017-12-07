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



<display:table name="trip" class="displaytag"
  requestURI="sponsorship/sponsor/displayTrip.do" id="row">
  
  <display:column>
 <spring:message code="trip.ticker" />:
	<jstl:out value="${row.ticker}"></jstl:out>
	<p> 
	
	<spring:message code="trip.title" />:
	<jstl:out value="${row.title}"></jstl:out>
	<p> 
	
	<spring:message code="trip.starDate" />:
	<jstl:out value="${row.startDate}"></jstl:out>
	<p> 
	
	<spring:message code="trip.finishDate" />:
	<jstl:out value="${row.finishDate}"></jstl:out>
	<p> 
	
	<spring:message code="trip.description" />:
	<jstl:out value="${row.description}"></jstl:out>
	<p>
	
	<spring:message code="trip.requirementsExplorers" />:
	<jstl:out value="${row.requirementsExplorers}"></jstl:out>
	<p>
	
	<spring:message code="trip.price" />:
	<jstl:out value="${row.price}"></jstl:out>
	<p>
	
	<spring:message code="trip.cancelled" />:
	<jstl:out value="${row.cancelled}"></jstl:out>
	<p>
</display:column>
	

</display:table>
