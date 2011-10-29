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
				<a href="javascript:;" onclick="clickTutorial('takeTutorialList','${t.id}','tutorialDetail','rightTakeTutorialPanel');return false;">${t.name}</a>
			</td>
			<td>
				<a href="javascript:;" onclick="cancelTutorial('removeTutorial','${t.id}','listTutorialFregmaent','tutorialListTable');return false;"><span class="removeIcon"></span>Cancel</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!--end list tutorial-->