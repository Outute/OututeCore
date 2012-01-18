<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- month tutorial schedule -->
<div style="display: none;" script="setCalendarHeader('manageTutorialCalendarHeader','<fmt:formatDate value="${startDate}" pattern="MMMM yyyy" timeZone="${timeZone}"/>');highLightCalendar('${highLight}','mycalendar');"></div>
<s:iterator value="@com.edu.webapp.action.TutorialAction@processWeekSchedule(#request.tutorialSchedules,#request.timeZone)" status="status" var="entry">
<div class="week_schedule_<s:property value="#entry.key"/>">
	<s:iterator value="#entry.value" status="s" var="l">
		<div id="week_ts_<s:property value="#l['scheduleId']"/>" style="vertical-align:middle;font-size:10px;background-color:<s:if test="#l['id'] in #request.hasTakenTutorialIds">#F691B2</s:if><s:else>#4F85BB</s:else>;position:absolute;top:<s:property value="#l['fromMinute']*38/120"/>px;width:80px;height:<s:property value="(#l['toMinute']-#l['fromMinute'])*38/120"/>px; left:1px;overflow:hidden;" 
			title="<s:property value="#l['name']+'('+#l['fromTime']+' to '+#l['toTime']+')'" escape="true"/>">
			<a href="<s:url value="/takeTutorial?id=" includeContext="true"/><s:property value="#l['id']"/>" style="text-decoration:none;">
				<div><s:property value="#l['name']" escape="true"/></div>
				<div><s:property value="#l['fromTime']"/> to <s:property value="#l['toTime']"/></div>
			</a>
		</div>
	</s:iterator>
</div>
</s:iterator>
<!-- end month tutorial schedule -->
