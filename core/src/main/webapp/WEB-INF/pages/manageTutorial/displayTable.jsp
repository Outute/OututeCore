<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start display tutorials-->
<c:if test="${isCurrent}">
<form name="currentTutorials" action="currentTutorials" method="get" onsubmit="return clickTable(this);">
<display:table name="tutorials" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" pagesize="25" class="table" export="false" form="currentTutorials" excludedParams="ajax">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="tutorial.name" style="width: 25%"
        url="/clickTutorial" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="tutorial.description" style="width: 34%"/>
    <display:column property="cost" sortable="true" titleKey="tutorial.cost" style="width: 25%" autolink="true" media="html"/>
    <display:column titleKey="tutorial.tutor" sortable="true" style="width: 34%">
      <c:forEach var="tutor" items="${tutorial.tutor}">
        <c:out value="${tutor.firstName} ${tutor.lastName}"/>
      </c:forEach>
   </display:column>
    <display:column sortProperty="enabled" sortable="true" titleKey="tutorial.enabled" style="width: 16%; padding-left: 15px" media="html">
        <input type="checkbox" disabled="disabled" <c:if test="${tutorial.enabled!=true}">checked="checked"</c:if>/>
    </display:column>

    <display:column property="enabled" titleKey="tutorial.status" media="csv xml excel pdf"/>
    <display:setProperty name="paging.banner.item_name" value="tutorial"/>
    <display:setProperty name="paging.banner.items_name" value="tutorials"/>
    <display:setProperty name="export.excel.filename" value="Tutorial List.xls"/>
    <display:setProperty name="export.csv.filename" value="Tutorial List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Tutorial List.pdf"/>
</display:table>
</form>
</c:if>
<c:if test="${isHistory}">
<form name="historyTutorials" action="historyTutorials" method="get" onsubmit="return clickTable(this);">
<display:table name="tutorials" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" pagesize="25" class="table" export="false" form="historyTutorials" excludedParams="ajax">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="tutorial.name" style="width: 25%"
        url="/clickTutorial" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="tutorial.description" style="width: 34%"/>
    <display:column property="cost" sortable="true" titleKey="tutorial.cost" style="width: 25%" autolink="true" media="html"/>
    <display:column titleKey="tutorial.tutor" sortable="true" style="width: 34%">
      <c:forEach var="tutor" items="${tutorial.tutor}">
        <c:out value="${tutor.firstName} ${tutor.lastName}"/>
      </c:forEach>
   </display:column>
    <display:column sortProperty="enabled" sortable="true" titleKey="tutorial.enabled" style="width: 16%; padding-left: 15px" media="html">
        <input type="checkbox" disabled="disabled" <c:if test="${tutorial.enabled}">checked="checked"</c:if>/>
    </display:column>

    <display:column property="enabled" titleKey="tutorial.status" media="csv xml excel pdf"/>
    <display:setProperty name="paging.banner.item_name" value="tutorial"/>
    <display:setProperty name="paging.banner.items_name" value="tutorials"/>
    <display:setProperty name="export.excel.filename" value="Tutorial List.xls"/>
    <display:setProperty name="export.csv.filename" value="Tutorial List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Tutorial List.pdf"/>
</display:table>
</form>
</c:if>
<!--end display tutorials-->