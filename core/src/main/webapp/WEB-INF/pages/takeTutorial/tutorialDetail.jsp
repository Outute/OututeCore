<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- tutorial detail -->

<div class="classTutorialDetail">
	<div class="classTutorialTitle"><span>${tutorial.name}</span></div>
	<div class="classDescription"><span>${tutorial.description}</span></div>
	<hr class="tutorialHr"/>
	<div style="float:left; position:relative; width: 100%;">
		<div class="tutorialDetail">
			<div><span class="bold"><fmt:message key="page.takeTutorial.ID"/></span><span>${tutorial.id}</span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.Category"/></span>
					<span><c:set var="tutorial_category" scope="request" value="${tutorial.category}"/>
			  		<c:set var="category_type" scope="request" value="0"/>
			  		<jsp:include page="/WEB-INF/pages/fregment/tutorial_category.jsp"/></span>
			</div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.TutorialType"/></span>
					<span><c:set var="tutorial_type" scope="request" value="${tutorial.type}"/>
					<c:set var="tutorial_showtype" scope="request" value="0"/>
					<jsp:include page="/WEB-INF/pages/fregment/tutorial_type.jsp"/></span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.TotalCost"/></span>
				<span id="takeTutorialTotalCost">${totalCost}</span></div>
		</div>
		<div style="float: right; width:25%; display:inline; text-align: right; position: absolute;bottom: 10px;">
			<span class="takeMoreTutorialIcon"></span>
			<a href="javascript:;" onclick="clickTakeTutorial('rightTakeTutorialPanel','searchTutorialPage');return false;"><fmt:message key="page.takeTutorial.TakeMoreTutorial"/></a>
		</div>
	</div>
</div>
	<div class="listTutorialSchedule" style="width: 100%; background-color: white; overflow-y: scroll; height: 330px;">
		<table class="outertable">
			<c:forEach items="${tutorialSchedules}" var="ts">
			<tr>
				<td>
					<table class=innertable>
						<tr>
							<td class="iconTd"><span class="calendarIcon"></span></td>
					  		<td>
					  			<div class="tutorialScheduleTimeContent">
					  				<fmt:formatDate value="${ts.fromTime}" pattern="EEEEE MMMMM dd, yyyy - "/><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>&nbsp;<fmt:message key="page.takeTutorial.to"/>&nbsp;<fmt:formatDate value="${ts.toTime}" pattern="hh:mmaaa"/>
					  			</div>
					  			<div class="tutorialScheduleCostContent">
					  				<fmt:message key="page.takeTutorial.Max1"/>&nbsp;<strong><c:if test="${ts.tutorial.type==1}">${ts.tutorial.maxParticipate}</c:if><c:if test="${ts.tutorial.type!=1}">${ts.maxParticipate}</c:if></strong>&nbsp;<fmt:message key="page.takeTutorial.participates"/>&nbsp;-&nbsp;<c:if test="${ts.tutorial.type==1}">$${ts.tutorial.cost}</c:if><c:if test="${ts.tutorial.type!=1}">$${ts.cost}</c:if>&nbsp;<c:if test="${(ts.tutorial.type!=1)&&(ts.durationType>0)}"><fmt:message key="page.takeTutorial.each"/></c:if>
					  			</div>
					  		</td>
					  		<td class="iconTextTd" nowrap="nowrap">
								<a href="javascript:;" onclick="unRegisterTutorialSchedule('cancelTutorialSchedule','listTakedTutorial','${ts.id}','<fmt:formatDate value="${ts.fromTime}" pattern="MM/dd/yyyy"/>','${tutorial.id}','takeTutorialList');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.cancel"/></a>
					  		</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
<!-- end tutorial detail -->