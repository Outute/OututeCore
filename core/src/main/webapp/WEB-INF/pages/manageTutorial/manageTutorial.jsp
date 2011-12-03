<!--start manageTutorial-->
<%@ include file="/common/taglibs.jsp"%>

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
	<h2>Select View Options</h2>
	<input checked="checked" onclick="window.location.href='manageTutorial';" name="viewtype_option" type="radio" id="viewtype_option_0"/><label for="viewtype_option_0">Table View</label>
	<input onclick="window.location.href='manageTutorialCalendar';" name="viewtype_option" type="radio"  id="viewtype_option_1"/><label for="viewtype_option_1">Calendar View</label>
	<form method="get" action="${ctx}/listTutorial" id="searchForm" style="display:none;">
	    <input type="text" size="20" name="q" id="query" value="${param.q}" placeholder="Enter search terms..."/>
	    <input type="submit" value="<fmt:message key="button.search"/>"/>
	</form>
	<div>
		<h2>Tutorial Appointments</h2>
		<p>
		<div class="display-table" id="tableTutorialAppointments">
			<jsp:include page="/WEB-INF/pages/manageTutorial/displayTable.jsp"></jsp:include>
		</div>
	</div>
</div>
<div class="main-content" style="width: 100%;">
	<div>
		<h2>Tutorial History</h2>
		<div class="display-table" id="tableTutorialHistory">
		</div>
	</div>
</div>
<script type="text/javascript">
function displaytagform(formname, fields){
    var objfrm = document.forms[formname];
    for (j=fields.length-1;j>=0;j--){var f= objfrm.elements[fields[j].f];if (f){f.value=fields[j].v};}
    objfrm.onsubmit();
}
</script>
<script type="text/javascript">
	setTimeout(function(){try{loadPage("historyTutorials",null,Util.id('tableTutorialHistory'));}catch(err){}},100);
    //try{highlightTableRows("tutorials");}catch(err){}
</script>
<!--end manageTutorial-->