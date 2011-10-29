<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- view tutorial detail for book -->
<div script="Util.calender('takeTutorialScheduleCalendar',{input:'tutorialScheduleDate',click:selectCalendar4Schedule,funcParams:{url:'bookTutorialPage',tutorialId:'${tutorial.id}',id:'rightTakeTutorialPanel'}},'<fmt:formatDate value="${startDate}" pattern="yyyyMMdd"/>');" style="display: none;"></div>
<div class="classTutorialDetail">
	<div class="classTutorialTitle"><span>${tutorial.name}</span></div>
	<div class="classDescription"><span>${tutorial.description}</span></div>
	<hr class="tutorialHr"/>
	<div style="float:left; position:relative; width: 100%;">
		<div class="tutorialDetail">
			<div><span class="bold"><fmt:message key="page.takeTutorial.ID"/></span><span>${tutorial.id}</span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.Category"/></span><span>${tutorial.category}</span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.TutorialType"/></span><span>${tutorial.type}</span></div>
		</div>
		<div style="float: right; width:25%; display:inline; text-align: right; position: absolute;bottom: 10px;">
			<span class="takeMoreTutorialIcon"></span>
			<a href="javascript:;" onclick="clickAddDate('addTutorialDatePage','${tutorial.id}','rightTutorialPanel');return false;"><fmt:message key="page.takeTutorial.TakeMoreTutorial"/></a>
		</div>
	</div>
</div>
	<div class="listTutorialSchedule" style="width: 100%; background-color: white; overflow-y: scroll; height: 330px;">
		<input type="hidden" id="tutorialScheduleDate"/>
		<table class="w100pc">
			<tr>
				<td valign="middle"><div id="takeTutorialScheduleCalendar" class="w200px padding10"></div></td>
				<td valign="top">
					<table class="w100pc">
						<tr>
							<td colspan="3"><fmt:message key="page.takeTutorial.AvailableTimes"/> <strong>date</strong></td>
						</tr>
						<tr>
							<th><fmt:message key="page.takeTutorial.Morning"/></th>
							<th><fmt:message key="page.takeTutorial.Afternoon"/></th>
							<th><fmt:message key="page.takeTutorial.Evening"/></th>
						</tr>
						<c:forEach items="${tutorialSchedules}" var="ts">
						<tr>
							<td><input type="radio" name="time" value="${ts.id}" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value);"/><span><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>(50 <fmt:message key="page.takeTutorial.mins"/>, 10 <fmt:message key="page.takeTutorial.slots"/>, $${ts.cost})</span></td>
							<td><input type="radio" name="time" value="${ts.id}" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value);"/><span><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>(50 <fmt:message key="page.takeTutorial.mins"/>, 10 <fmt:message key="page.takeTutorial.slots"/>, $${ts.cost})</span></td>
							<td><input type="radio" name="time" value="${ts.id}" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value);"/><span><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>(50 <fmt:message key="page.takeTutorial.mins"/>, 10 <fmt:message key="page.takeTutorial.slots"/>, $${ts.cost})</span></td>
						</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="<fmt:message key="page.takeTutorial.BookNow"/>" class="button buttonMin" onclick="clickBookNow('bookTutorial','${tutorial.id}','rightTakeTutorialPanel');"/>
					<input type="button" value="<fmt:message key="page.button.cancel"/>" class="button buttonMin"/>
				</td>
			</tr>
		</table>
	</div>
<!-- end view tutorial detail for book -->