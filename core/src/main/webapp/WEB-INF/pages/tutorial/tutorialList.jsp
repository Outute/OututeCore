<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="tutorialList.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>

<div id="search">
<form method="get" action="${ctx}/tutorial/tutorialList" id="searchForm">
    <input type="text" size="20" name="q" id="query" value="${param.q}"
           placeholder="Enter search terms..."/>
    <input type="submit" value="<fmt:message key="button.search"/>"/>
</form>
</div>

<input type="button" style="margin-right: 5px"
    onclick="location.href='<c:url value="/editTutorial?method=Add"/>'"
    value="<fmt:message key="button.add"/>"/>

<input type="button" onclick="location.href='<c:url value="/mainMenu"/>'"
    value="<fmt:message key="button.done"/>"/>

<display:table name="tutorials" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="tutorials" pagesize="25" class="table" export="true">
    <display:column property="tutorialname" escapeXml="true" sortable="true" titleKey="tutorial.name" style="width: 25%"
        url="/editTutorial?from=list" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="tutorial.description" style="width: 34%"/>
    <display:column property="cost" sortable="true" titleKey="tutorial.cost" style="width: 25%" autolink="true" media="html"/>
    <display:column property="tutor" titleKey="tutorial.tutors.toString" sortable="true" style="width: 34%"/>
    <display:column sortProperty="enabled" sortable="true" titleKey="tutorial.enabled" style="width: 16%; padding-left: 15px" media="html">
        <input type="checkbox" disabled="disabled" <c:if test="${tutorials.enabled}">checked="checked"</c:if>/>
    </display:column>
    <display:column property="enabled" titleKey="tutorial.enabled" media="csv xml excel pdf"/>

    <display:setProperty name="paging.banner.item_name" value="tutorial"/>
    <display:setProperty name="paging.banner.items_name" value="tutorials"/>

    <display:setProperty name="export.excel.filename" value="Tutorial List.xls"/>
    <display:setProperty name="export.csv.filename" value="Tutorial List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Tutorial List.pdf"/>
</display:table>

<input type="button" style="margin-right: 5px"
    onclick="location.href='<c:url value="/editTutorial?method=Add"/>'"
    value="<fmt:message key="button.add"/>"/>

<input type="button" onclick="location.href='<c:url value="/mainMenu"/>'"
    value="<fmt:message key="button.done"/>"/>

<script type="text/javascript">
    highlightTableRows("users");
</script>
