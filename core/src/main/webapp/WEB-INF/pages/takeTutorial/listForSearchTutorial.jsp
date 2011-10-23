<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start list tutorial-->
<div class="hidden" script="Util.id('firstTutorial').click();"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<c:forEach items="${tutorials}" var="t">
		<tr height="30px" id="tid_${t.id}">
			<td width="80%">
				<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<a id="firstTutorial" href="javascript:;" onclick="clickTutorial('tutorialListTable','${t.id}','tutorialDetailFragment','rightTutorialPanel');return false;">${t.name}</a>
			</td>
			<td>
				<span class="deleteTutorialIcon">X</span>
				<a href="javascript:;" onclick="deleteTutorial('removeTutorial','${t.id}','listTutorialFregmaent','tutorialListTable');return false;">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!--end list tutorial-->