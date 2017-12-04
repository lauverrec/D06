<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Explorer Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>		
					<li><a href="sponsor/edit.do"><spring:message code="master.page.sponsor.edit" /></a></li>
          		<li><a href="sponsorship/sponsor/list.do"><spring:message code="master.page.sponsorship.sponsor.list" /></a></li>		
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" /></a>
			
				<ul>
					<li class="arrow"></li>
					<li><a href="auditRecord/auditor/list.do"><spring:message code="master.page.auditRecord.auditor.list" /></a></li>
					 <li><a href="note/auditor/list.do"><spring:message code="master.page.note.auditor.list" /></a></li>
									
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('EXPLORER')">
			<li><a class="fNiv"><spring:message	code="master.page.explorer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="trip/explorer/list-apply.do"><spring:message code="master.page.explorer.list.apply.trips" /></a></li>					
					<li><a href="trip/explorer/list-not-apply.do"><spring:message code="master.page.explorer.list.not.apply.trips" /></a></li>
									
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
									
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('RANGER')">
			<li><a class="fNiv"><spring:message	code="master.page.ranger" /></a>
				<ul>
					<li class="arrow"></li>
									
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message	code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
									
				</ul>
			</li>
		</security:authorize>

		
	
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
									
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

