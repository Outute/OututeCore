<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start list tutorial-->
<c:set var="newId" scope="request" value="${newTutorialId}"/>
<c:remove var="newTutorialId" scope="session"/>
<div class="hidden" script="Util.click(Util.id('tutorial_id_${newId}')||Util.id('tutorial_id_${id}')||Util.id('firstTutorial'));if(!Util.id('firstTutorial')){window.location.href=window.location.href;};"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<c:forEach items="${tutorials}" var="t" varStatus="status">
		<tr height="30px" id="tid_${t.id}">
			<td>
				<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<c:if test="${id==null||status.index==0}">
					<c:set var="listId" scope="request" value="firstTutorial"/>
				</c:if>
				<c:if test="${status.index!=0}">
					<c:set var="listId" scope="request" value="tutorial_id_${t.id}"/>
				</c:if>
				<a id="${listId}" href="javascript:;" onclick="clickTutorial('tutorialListTable','${t.id}','tutorialDetailFragment','rightTutorialPanel');return false;">${t.name}</a>
			</td>
			<td nowrap="nowrap" align="right">
				<a href="javascript:;" onclick="deleteTutorial('removeTutorial','listTutorialFragment','${t.id}','tutorialListTable');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.delete"/></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!--end list tutorial-->