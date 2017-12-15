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


<table>
	<caption>findAvgMinMaxStddevOfTheNumOfApplicationsPerTrip</caption>
	<tr>
		<th>AVG</th>
		<th>Min</th>
		<th>Max</th>
		<th>STDDEV</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findAvgMinMaxStddevOfTheNumOfApplicationsPerTrip }">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption>findAvgMinMaxStddevOfTheNumOfTripsPerManager</caption>
	<tr>
		<th>AVG</th>
		<th>Min</th>
		<th>Max</th>
		<th>STDDEV</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findAvgMinMaxStddevOfTheNumOfTripsPerManager }">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption>findAvgMinMaxStddevOfThePriceOfTheTrips</caption>
	<tr>
		<th>AVG</th>
		<th>Min</th>
		<th>Max</th>
		<th>STDDEV</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findAvgMinMaxStddevOfThePriceOfTheTrips }">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption>findAvgMinMaxStddevOfTheNumTripsPerRanger</caption>
	<tr>
		<th>AVG</th>
		<th>Min</th>
		<th>Max</th>
		<th>STDDEV</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findAvgMinMaxStddevOfTheNumTripsPerRanger }">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption><spring:message code="dashboard.statistics" var="statistics"/>
	<jstl:out value="${statistics }"></jstl:out>
	</caption>
	<tr>
		<th>findRatOfApplicationsPending</th>
		<th>findRatioOfApplicationsDue</th>
		<th>findRatOfApplicationsAccepted</th>
		<th>findRatOfApplicationsCancelled</th>
		<th>findRatOfTheTripsCancelledvsTripsOrganised</th>
	</tr>
	<tr>
		<td><jstl:out value="${findRatOfApplicationsPending }"></jstl:out>
		</td>
		<td><jstl:out value="${findRatioOfApplicationsDue }"></jstl:out>
		</td>
		<td><jstl:out value="${findRatOfApplicationsAccepted }"></jstl:out>
		</td>
		<td><jstl:out value="${findRatOfApplicationsCancelled }"></jstl:out>
		</td>
		<td><jstl:out
				value="${findRatOfTheTripsCancelledvsTripsOrganised }"></jstl:out>
	</tr>
</table>

<display:table name="findTrips10porcentMoreApplicationsThanAvg" id="row"
	class="displaytag">
	<spring:message code="trip.ticker" var="titleHeader" />
	<display:column property="ticker" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.ticker}"></jstl:out>
	</display:column>

	<spring:message code="trip.description" var="titleHeader" />
	<display:column property="description" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.description}"></jstl:out>
	</display:column>

	<spring:message code="trip.price" var="titleHeader" />
	<display:column property="price" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.price}"></jstl:out>
	</display:column>

	<spring:message code="trip.requirementsExplorers" var="titleHeader" />
	<display:column property="requirementsExplorers" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.requirementsExplorers}"></jstl:out>
	</display:column>

	<spring:message code="trip.publicationDate" var="titleHeader" />
	<display:column property="publicationDate" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.publicationDate}"></jstl:out>
	</display:column>

	<spring:message code="trip.startDate" var="titleHeader" />
	<display:column property="startDate" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.startDate}"></jstl:out>
	</display:column>

	<spring:message code="trip.finishDate" var="titleHeader" />
	<display:column property="finishDate" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.finishDate}"></jstl:out>
	</display:column>

	<spring:message code="trip.reasonWhy" var="titleHeader" />
	<display:column property="reasonWhy" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.reasonWhy}"></jstl:out>
	</display:column>

	<spring:message code="trip.cancelled" var="titleHeader" />
	<display:column property="cancelled" title="${titleHeader}"
		sortable="false">
		<jstl:out value="${row.cancelled}"></jstl:out>
	</display:column>
</display:table>



<display:table name="findNumOfTimesALegalTextIsReferenced" id="row"
	class="displaytag">
	<jstl:forEach var="medidas"
			items="${findNumOfTimesALegalTextIsReferenced[1] }">
			<td><jstl:out value="${medidas}"></jstl:out></td>
		</jstl:forEach>
	

</display:table>


<table>
	<caption>findMinMaxAvgStddevOfTheNumOfNotesPerTrip</caption>
	<tr>
		<th>Min</th>
		<th>Max</th>
		<th>AVG</th>
		<th>sttdev</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findMinMaxAvgStddevOfTheNumOfNotesPerTrip }">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption>findMinMaxAvgStddevOfTheNumOfAuditRecordsPerTrip</caption>
	<tr>
		<th>Min</th>
		<th>Max</th>
		<th>AVG</th>
		<th>sttdev</th>
	</tr>
	<tr>
		<jstl:forEach var="medidas"
			items="${findMinMaxAvgStddevOfTheNumOfAuditRecordsPerTrip}">
			<td><jstl:out value="${ medidas}"></jstl:out></td>
		</jstl:forEach>
	</tr>
</table>

<table>
	<caption><spring:message code="dashboard.statistics" var="statistics"/>
	<jstl:out value="${statistics }"></jstl:out></caption>
	<tr>
		<th>findTheRatOfRangersWhoHaveRegisteredCurricula</th>
		<th>findTheRatOfRangersWhoseCurrIsEndorsed</th>
		<th>findTheRatOFSuspiciousManagers</th>
		<th>findTheRatOFSuspiciousRangers</th>

	</tr>
	<tr>
		<td><jstl:out
				value="${findTheRatOfRangersWhoHaveRegisteredCurricula}"></jstl:out>
		</td>
		<td><jstl:out value="${findTheRatOfRangersWhoseCurrIsEndorsed}"></jstl:out>
		</td>
		<td><jstl:out value="${findTheRatOFSuspiciousManagers}"></jstl:out>
		</td>
		<td><jstl:out value="${findTheRatOFSuspiciousRangers}"></jstl:out>
		</td>

	</tr>
</table>



