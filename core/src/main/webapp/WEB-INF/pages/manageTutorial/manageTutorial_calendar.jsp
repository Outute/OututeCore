<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- manage tutorial calendar -->
<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>
<style>
span.pagelinks{
	text-align: right;
}
</style>

<div class="main-content" style="width: 100%;">
	<h2>Select  View Options</h2>
	<input onclick="window.location.href='manageTutorial';" name="viewtype_option" type="radio" id="viewtype_option_0"/><label for="viewtype_option_0">Table View</label>
	<input checked="checked" onclick="window.location.href='manageTutorialCalendar';" name="viewtype_option" type="radio"  id="viewtype_option_1"/><label for="viewtype_option_1">Calendar View</label>
</div>
<div class="manageTutorialCalendar w100pc" style="position: relative; display: block;">
	<div class="toolBar w100pc" style="height: 25px; position: relative; display: block; padding-top: 10px;">
		<div style="display: inline;">
		<div class="button_35x21 floatl" style="border-style:none;width: 205px;"></div>
		<div class="button_35x21 pointer floatl" onclick="clickToDay();">ToDay</div>
		<div class="button_35x21 floatl" style="border-style:none; width: 10px;"></div>
		<div class="button_35x21 pointer floatl" style="border-style: none;"></div>
		<div class="button_35x21 pointer floatl" style="border-style: none;"></div>
		<div class="button_35x21 floatl" style="border-style:none; width: 10px;"></div>
		<div class="button_35x21 pointer floatl" style="border-style: none;  width: 150px;" id="manageTutorialCalendarHeader"></div>
		<div class="button_35x21 floatl" style="border-style:none; width: 10px;"></div>
		<div class="button_35x21 pointer floatl" onclick="clickDay();">Day</div>
		<div class="button_35x21 pointer floatl" onclick="clickWeek();">Week</div>
		<div class="button_35x21 pointer floatl" onclick="clickMonth();">Month</div>
		<div class="button_35x21 floatl" style="border-style:none; width: 10px;"></div>
		<div class="button_35x21 pointer floatl" style="color: gray; display: none;">Print</div>
		<div class="button_35x21 floatl" style="border-style:none; width: 10px;"></div>
		<div class="button_35x21 pointer floatl" onclick="clickRefresh();">Refresh</div>
		</div>
	</div>
	<hr class="clear"/>
	<div class="calendarContent" style="border-style:none; width: 100%; height: 500px; position: relative; display: block;">
		<div class="leftCalendar" style="position: absolute; left: 0px; top: 0px;">
			<div id="mycalendar" class="w200px padding10"></div>
		</div>
		<div class="rightCalendar" style="position: absolute; left: 210px; top: 0px; overflow: hidden;">
			<div id="mycalendar1" class="padding10" style="width: 650px;"></div>
		</div>
	</div>
</div>
<!-- end manage tutorial calendar -->
<script type="text/javascript">
Util.calender('mycalendar',{click:clickCalendar},Util.dateToStr(Util.toDay()));
clickDay(Util.dateToStr(Util.toDay()));
</script>