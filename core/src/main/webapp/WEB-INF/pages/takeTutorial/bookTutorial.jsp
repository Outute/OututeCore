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
						<s:set value="@com.edu.webapp.action.TutorialAction@processTimeAreaTutorialSchedule(#request.tutorialSchedules)" var="mapList"/>
						<s:if test="#mapList['morning']">
							<s:set value="#mapList['morning']" var="morning"/>
							<s:set value="#mapList['afternoon']" var="afternoon"/>
							<s:set value="#mapList['evening']" var="evening"/>
							<s:iterator value="#morning" var="ts0" status="status">
								<s:set value="#afternoon[#status.index]" var="ts1"/>
								<s:set value="#evening[#status.index]" var="ts2"/>
								<tr>
									<td>
										<s:if test="#ts0">
										<div style="display: block; width:130px;">
											<input type="radio" name="time_<s:property value="#ts0['scheduleId']"/>" value="<s:property value="#ts0['scheduleId']"/>" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value,this.name);"/>
											<s:property value="#ts0['fromTime']"/>(<s:property value="#ts0['toMinute']-#ts0['fromMinute']"/> <fmt:message key="page.takeTutorial.mins"/>, <s:property value="#ts0['maxParticipate']"/> <fmt:message key="page.takeTutorial.slots"/>, $<s:property value="#ts0['cost']"/>)
										</div>
										</s:if>
									</td>
									<td>
										<s:if test="#ts1">
										<div style="display: block; width:130px;">
										<input type="radio" name="time_<s:property value="#ts1['scheduleId']"/>" value="<s:property value="#ts1['scheduleId']"/>" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value,this.name);"/>
										<s:property value="#ts1['fromTime']"/>(<s:property value="#ts1['toMinute']-#ts1['fromMinute']"/> <fmt:message key="page.takeTutorial.mins"/>, <s:property value="#ts1['maxParticipate']"/> <fmt:message key="page.takeTutorial.slots"/>, $<s:property value="#ts1['cost']"/>)
										</div>
										</s:if>
									</td>
									<td>
										<s:if test="#ts2">
										<div style="display: block; width:130px;">
										<input type="radio" name="time_<s:property value="#ts2['scheduleId']"/>" value="<s:property value="#ts2['scheduleId']"/>" onchange="clickTutorialSchedule('rightTakeTutorialPanel',this.value,this.name);"/>
										<s:property value="#ts2['fromTime']"/>(<s:property value="#ts2['toMinute']-#ts2['fromMinute']"/> <fmt:message key="page.takeTutorial.mins"/>, <s:property value="#ts2['maxParticipate']"/> <fmt:message key="page.takeTutorial.slots"/>, $<s:property value="#ts2['cost']"/>)
										</div>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</s:if>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="<fmt:message key="page.takeTutorial.BookNow"/>" class="button buttonMin" onclick="clickBookNow('bookTutorial','${tutorial.id}','rightTakeTutorialPanel');"/>
					<input type="button" value="<fmt:message key="page.button.cancel"/>" class="button buttonMin" onclick="try{Util.id('takeMoreTutorial_0').click();}catch(err){}"/>
				</td>
			</tr>
		</table>
	</div>
<!-- end view tutorial detail for book -->