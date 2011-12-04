<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start list tutorial-->
<div class="hidden" script="Util.click(Util.id('takedTutorial_${id}')||Util.id('takeMoreTutorial_0'));"></div>
<table class="listForTakeTutorial" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<c:forEach items="${tutorials}" var="t">
		<tr height="30px" id="tid_${t.id}">
			<td>
				<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<a id="takedTutorial_${t.id}" href="javascript:;" onclick="clickTutorial('takeTutorialList','${t.id}','tutorialDetail','rightTakeTutorialPanel');return false;">${t.name}</a>
			</td>
			<td nowrap="nowrap" align="right">
				<a href="javascript:;" onclick="unRegisterTutorial('cancelTutorial','listTakedTutorial','${t.id}','takeTutorialList');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.cancel"/></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!--end list tutorial-->