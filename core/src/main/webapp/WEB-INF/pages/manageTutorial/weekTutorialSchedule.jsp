<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- month tutorial schedule -->
<s:iterator value="@com.edu.webapp.action.TutorialAction@processWeekSchedule(#request.tutorialSchedules)" status="status" var="entry">
<div class="week_schedule_<s:property value="#entry.key"/>">
	<s:iterator value="#entry.value" status="s" var="l">
	<div id="week_ts_<s:property value="#l['scheduleId']"/>" style="vertical-align:middle;font-size:10px;background-color:#F691B2;position:absolute;top:<s:property value="#l['fromMinute']*38/120"/>px;width:120px;height:<s:property value="(#l['toMinute']-#l['fromMinute'])*38/120"/>px; left:<s:property value="#s.index*50"/>px;overflow:hidden;" 
		title="<s:property value="#l['name']+'('+#l['fromTime']+' to '+#l['toTime']+')'" escape="true"/>">
		<div><s:property value="#l['name']" escape="true"/></div>
		<div><s:property value="#l['fromTime']"/> to <s:property value="#l['toTime']"/></div>
	</div>
	</s:iterator>
</div>
</s:iterator>
<!-- end month tutorial schedule -->
