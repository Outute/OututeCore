<!--start manageTutorial-->
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>

<p>
<form method="get" action="${ctx}/listTutorial" id="searchForm">
    <input type="text" size="20" name="q" id="query" value="${param.q}"
           placeholder="Enter search terms..."/>
    <input type="submit" value="<fmt:message key="button.search"/>"/>
</form>
</p>
<br/>
<table>
	<tr>
		<td>
			<input type="button" style="margin-right: 5px" class="button" onclick="location.href='<c:url value="/createTutorial"/>'" value="<fmt:message key="button.add"/>"/>
		</td>
		<td>
			<input type="button" class="button" onclick="location.href='<c:url value="/mainMenu"/>'" value="<fmt:message key="button.done"/>"/>
		</td>
	</tr>
</table>
<br/>
<display:table name="tutorials" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="tutorials" pagesize="25" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="tutorial.name" style="width: 25%"
        url="/editTutorial?from=list" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="tutorial.description" style="width: 34%"/>
    <display:column property="cost" sortable="true" titleKey="tutorial.cost" style="width: 25%" autolink="true" media="html"/>
    <display:column titleKey="tutorial.tutor" sortable="true" style="width: 34%">
      <c:forEach var="tutor" items="${tutorials.tutors}">
        <c:out value="${tutor.firstName} ${tutor.lastName}"/>
      </c:forEach>
   </display:column>
    <display:column sortProperty="enabled" sortable="true" titleKey="tutorial.enabled" style="width: 16%; padding-left: 15px" media="html">
        <input type="checkbox" disabled="disabled" <c:if test="${tutorials.enabled}">checked="checked"</c:if>/>
    </display:column>

    <display:column property="enabled" titleKey="tutorial.status" media="csv xml excel pdf"/>

    <display:setProperty name="paging.banner.item_name" value="tutorial"/>
    <display:setProperty name="paging.banner.items_name" value="tutorials"/>

    <display:setProperty name="export.excel.filename" value="Tutorial List.xls"/>
    <display:setProperty name="export.csv.filename" value="Tutorial List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Tutorial List.pdf"/>
</display:table>
<br/>
<table>
	<tr>
		<td>
			<input type="button" style="margin-right: 5px" class="button" onclick="location.href='<c:url value="/saveTutorial?method=Add&from=list"/>'" value="<fmt:message key="button.add"/>"/>
		</td>
		<td>
			<input type="button" class="button" onclick="location.href='<c:url value="/mainMenu"/>'" value="<fmt:message key="button.done"/>"/>
		</td>
	</tr>
</table>
<script type="text/javascript">
    highlightTableRows("tutorials");
</script>


<!--end manageTutorial-->