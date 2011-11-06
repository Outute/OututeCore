<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- tutorial detail -->
<div class="classTutorialDetail">
	<div class="classTutorialTitle"><span>${tutorial.name}</span></div>
	<div class="classDescription"><span>${tutorial.description}</span></div>
	<hr class="tutorialHr"/>
	<div style="float:left; position:relative; width: 100%;">
		<div class="tutorialDetail">
			<div><span class="bold"><fmt:message key="page.offerTutorial.ID"/>&nbsp;${tutorial.id}</span></div>
			<div><span class="bold"><fmt:message key="page.offerTutorial.Category"/></span>
				<span>
					<c:if test="${tutorial.category==1}"><fmt:message key="page.offerTutorial.Languages"/></c:if>
					<c:if test="${tutorial.category==2}"><fmt:message key="page.offerTutorial.StudyOversea"/></c:if>
					<c:if test="${tutorial.category==3}"><fmt:message key="page.offerTutorial.AcademicStudies"/></c:if>
					<c:if test="${tutorial.category==4}"><fmt:message key="page.offerTutorial.GamesSportsAndLeisure"/></c:if>
					<c:if test="${tutorial.category==5}"><fmt:message key="page.offerTutorial.Business"/></c:if>
					<c:if test="${tutorial.category==6}"><fmt:message key="page.offerTutorial.CareerConsultationAndDevelopment"/></c:if>
					<c:if test="${tutorial.category==7}"><fmt:message key="page.offerTutorial.ArtsAndLiterature"/></c:if>
					<c:if test="${tutorial.category==8}"><fmt:message key="page.offerTutorial.HowToForDummies"/></c:if>
					<c:if test="${tutorial.category==9}"><fmt:message key="page.offerTutorial.ComputerAndInternet"/></c:if>
					<c:if test="${tutorial.category==10}"><fmt:message key="page.offerTutorial.LegalIssues"/></c:if>
					<c:if test="${tutorial.category==11}"><fmt:message key="page.offerTutorial.FinanceInsuranceAndRealEstate"/></c:if>
					<c:if test="${tutorial.category==12}"><fmt:message key="page.offerTutorial.HealthAndLivingStyle"/></c:if>
					<c:if test="${tutorial.category==13}"><fmt:message key="page.offerTutorial.Travel"/></c:if>
					<c:if test="${tutorial.category==14}"><fmt:message key="page.offerTutorial.BeautyAndFashion"/></c:if>
					<c:if test="${tutorial.category==0}"><fmt:message key="page.offerTutorial.Others"/></c:if>
				</span>
			</div>
			<div><span class="bold"><fmt:message key="page.offerTutorial.TutorialType"/></span>
				<span><c:if test="${tutorial.type==0}"><fmt:message key="page.offerTutorial.Tutorial"/></c:if><c:if test="${tutorial.type==1}"><fmt:message key="page.offerTutorial.Workshop"/></c:if></span>
			</div>
		</div>
		<div style="float: right; display:inline; text-align: right; position: absolute;bottom: 0px; right:0px; font-size: 14px;">
			<a href="javascript:;" onclick="editTutorial('addTutorialPage','${tutorial.id}','rightTutorialPanel');return false;"><span class="addIcon"></span><fmt:message key="page.lable.edit"/></a>
			<a href="javascript:;" onclick="clickAddDate('addTutorialDatePage','${tutorial.id}','rightTutorialPanel');return false;"><span class="addIcon"></span><fmt:message key="page.offerTutorial.AddDate"/></a>
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
					  				<fmt:formatDate value="${ts.startDate}" pattern="EEEEE MMMMM dd, yyyy - "/><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>&nbsp;to&nbsp;<fmt:formatDate value="${ts.toTime}" pattern="hh:mmaaa"/>
					  			</div>
					  			<div class="tutorialScheduleCostContent">
					  				<fmt:message key="page.offerTutorial.Max"/>&nbsp;<strong><c:if test="${ts.tutorial.type==1}">${ts.tutorial.maxParticipate}</c:if><c:if test="${ts.tutorial.type!=1}">${ts.maxParticipate}</c:if></strong>&nbsp;<fmt:message key="page.offerTutorial.participates"/>&nbsp;-&nbsp;<c:if test="${ts.tutorial.type==1}">$${ts.tutorial.cost}</c:if><c:if test="${ts.tutorial.type!=1}">$${ts.cost}</c:if>&nbsp;<c:if test="${(ts.tutorial.type!=1)&&(ts.durationType>0)}"><fmt:message key="page.offerTutorial.each"/></c:if>
					  			</div>
					  		</td>
					  		<td class="iconTextTd" nowrap="nowrap"><a href="javascript:;" onclick="editTutorialSchedule('addTutorialDatePage','${ts.id}','${tutorial.id}','rightTutorialPanel');return false;"><span class="addIcon"></span><fmt:message key="page.lable.edit"/></a></td>
					  		<td class="iconTextTd" nowrap="nowrap"><a href="javascript:;" onclick="deleteTutorialSchedule('removeTutorialSchedule','${ts.id}','${tutorial.id}','tutorialDetailFragment','rightTutorialPanel');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.delete"/></a></td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
<!-- end tutorial detail -->