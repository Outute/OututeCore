<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- day tutorial schedule -->
<div style="display: none;" script="setCalendarHeader('manageTutorialCalendarHeader','<fmt:formatDate value="${startDate}" pattern="MMMM yyyy" timeZone="${timeZone}"/>');highLightCalendar('${highLight}','mycalendar');"></div>
<s:iterator value="@com.edu.webapp.action.TutorialAction@processDaySchedule(#request.tutorialSchedules,#request.tutorialScheduleStudent,#request.timeZone)" status="status" var="entry">
<div class="day_schedule_<s:property value="#entry.key"/>">
	<s:iterator value="#entry.value" status="s" var="l">
		<div id="day_ts_<s:property value="#l['scheduleId']"/>" style="cursor:pointer;vertical-align:middle;font-size:10px;background-color:<s:if test="#l['isOwned']">#F691B2</s:if><s:else>#B3BEAD</s:else>;position:absolute;top:<s:property value="#l['fromMinute']*38/120"/>px;width:80px;height:<s:property value="(#l['toMinute']-#l['fromMinute'])*38/120"/>px; left:<s:property value="#s.index*90"/>px;overflow:hidden;" 
			title="<s:property value="#l['name']+'('+#l['fromTime']+' to '+#l['toTime']+')'" escape="true"/>">
			<s:if test="1==2"><!-- #l['isOwned']==true -->
				<a href="<s:url value="/takeTutorial?id=" includeContext="true"/><s:property value="#l['id']"/>" style="text-decoration:none;">
			</s:if>
			<s:else>
				<a href="<s:url value="/createTutorial?id=" includeContext="true"/><s:property value="#l['id']"/>" style="text-decoration:none;">
			</s:else>
				<div><s:property value="#l['name']" escape="true"/></div>
				<div><s:property value="#l['fromTime']"/> to <s:property value="#l['toTime']"/></div>
			</a>
		</div>
	</s:iterator>
</div>
</s:iterator>
<!-- end day tutorial schedule -->
