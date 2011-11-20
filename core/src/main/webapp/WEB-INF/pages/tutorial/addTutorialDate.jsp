<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<fmt:setLocale value="en_US"/>
<!--start add tutorialForm-->
<div script="Util.calender('mycalendar','tutorialDate','<fmt:formatDate value="${tutorialSchedule.startDate}" pattern="yyyyMMdd"/>');" style="display: none;"></div>
<form id="addTutorialSchedule" action="addTimeShedule" method="post" onsubmit="saveTutorialSchedule('addTimeShedule','addTutorialSchedule','tutorialDetailFragment','rightTutorialPanel');return false;">
<input type="hidden" name="tutorial.id" value="${tutorial.id}"/>
<input type="hidden" name="tutorialSchedule.id" value="${tutorialSchedule.id}"/>
<input type="hidden" name="tutorialSchedule.startDate" id="tutorialDate" value=""/>
<div class="padding10">
	<div class="addTutorialTitle"><span><fmt:message key="page.offerTutorial.AddDate"/></span></div>
	<table width="100%" height="300px" border="0" cellpadding="0" cellspacing="0" style="background-color: #EEE">
		<tr valign="top">
			<td valign="middle"><div id="mycalendar" class="w200px padding10"></div></td>
			<td>
				<table class="formContent">
					<tr valign="top">
						<td><label for="fromTime"><fmt:message key="page.offerTutorial.From"/></label></td>
						<td>
							<input type="text" class="input" id="fromTime" name="fromTime" size="10" value="<fmt:formatDate value="${tutorialSchedule.fromTime}" pattern="hh:mmaaa"/>"/>
							<div id="fromTime_maxError" style="display:none;" class="error" title="from time is over day">from time is over day time</div>
							<div id="fromTime_gtError" style="display:none;" class="error" title="from time is greater than to time">from time is greater than to time</div>
							<!-- <div id="fromam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="frompm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div> -->
						</td>
						<td><label for="toTime"><fmt:message key="page.offerTutorial.To"/></label></td>
						<td>
							<input type="text" class="input" id="toTime" name="toTime" size="10" value="<fmt:formatDate value="${tutorialSchedule.toTime}" pattern="hh:mmaaa"/>" validate="validateFromTo(Util.id('fromTime'),Util.id('toTime'),'maxError','gtError');"/>
							<div id="toTime_maxError" style="display:none;" class="error" title="from time is over day">from time is over day time</div>
							<div id="toTime_gtError" style="display:none;" class="error" title="from time is greater than to time">from time is greater than to time</div>
							<!-- <div id="toam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="topm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div>!-->
						</td>
					</tr>
					<tr>
						<td><label for="repeat"><fmt:message key="page.offerTutorial.Repeats"/></label></td>
						<td colspan="3">
							<select id="repeat" name="tutorialSchedule.durationType" class="select w200px" onchange="this.value=='0'?Util.hideId(['endstr','costtr','partr']):Util.showId(['endstr','costtr','partr']);">
								<c:set var="duration_type" scope="request" value="${tutorialSchedule.durationType}"/>
								<c:set var="duration_showtype" scope="request" value="0"/>
								<jsp:include page="/WEB-INF/pages/fregment/tutorial_schedule_duration.jsp"/>
							</select>
						</td>
					</tr>
					<tr id="endstr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
						<td valign="top"><label><fmt:message key="page.offerTutorial.Ends"/></label></td>
						<td colspan="3">
							<input type="radio" class="input" id="ends1" name="ends" value="0" <c:if test="${tutorialSchedule.endsOccurrence>0}">checked="checked"</c:if> onclick="if(this.checked){Util.id('occurrences').value='';}"/><label for="ends1"><fmt:message key="page.offerTutorial.Never"/></label><br/>
							<input type="radio" class="input" id="ends2" name="ends" value="1" <c:if test="${tutorialSchedule.endsOccurrence>0}">checked="checked"</c:if>/><label for="ends2"><fmt:message key="page.offerTutorial.After"/></label>
							<input type="text" class="input" id="occurrences" name="tutorialSchedule.endsOccurrence" size=10 value="${tutorialSchedule.endsOccurrence}" onclick="Util.id('ends2').click();"/><label for="occurrences"><fmt:message key="page.offerTutorial.occurrences"/></label>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr style="color: #CCC; margin: 0px 0px 20px 0px;"/>
						</td>
					</tr>
					<c:if test="${tutorial.type!=1}">
						<tr id="costtr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
							<td colspan="2" align="right"><label for="cost"><fmt:message key="page.offerTutorial.Cost"/></label></td>
							<td colspan="2"><input type="text" class="input" id="cost" name="tutorialSchedule.cost" size="10" value="${tutorialSchedule.cost}"/></td>
						</tr>
						<tr id="partr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
							<td colspan="2" align="right"><label for="participate"><fmt:message key="page.offerTutorial.MaxParticipates"/></label></td>
							<td colspan="2"><input type="text" class="input" id="participate" name="tutorialSchedule.maxParticipate" size="10" value="${tutorialSchedule.maxParticipate}"/></td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<br/>
				<input type="submit" value="<fmt:message key="page.button.save"/>" class="button" onclick="saveTutorialSchedule('addTimeShedule','addTutorialSchedule','tutorialDetailFragment','rightTutorialPanel');return false;"/>
				<input type="button" value="<fmt:message key="page.button.cancel"/>" class="button" onclick="try{Util.id('firstTutorial').click();}catch(err){}"/>
			</td>
		</tr>
	</table>
</div>
</form>

<!--start add tutorialForm-->