<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start list tutorial-->
<div class="hidden" script="try{(Util.id('tutorial_id_${id}')||Util.id('firstTutorial')).click();}catch(err){}"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<c:forEach items="${tutorials}" var="t" varStatus="status">
		<tr height="30px" id="tid_${t.id}">
			<td width="80%">
				<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<a id="<c:if test="${id==null||status.index==0}">firstTutorial</c:if><c:if test="${id!=null&&status.index!=0}">tutorial_id_${id}</c:if>" href="javascript:;" onclick="clickTutorial('tutorialListTable','${t.id}','tutorialDetailFragment','rightTutorialPanel');return false;">${t.name}</a>
			</td>
			<td>
				<a href="javascript:;" onclick="deleteTutorial('removeTutorial','listTutorialFragment','${t.id}','tutorialListTable');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.delete"/></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!--end list tutorial-->