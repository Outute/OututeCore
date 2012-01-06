<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- day tutorial schedule -->
<c:if test="${not empty tutorialSchedule}">
<div style="display: none;" script="showNotificationDialog('${tutorialSchedule.id}','Tutorial Class Notification','Tutorial: <c:out value="${tutorialSchedule.tutorial.name}" escapeXml="true"/> will start at time:<br/> <fmt:formatDate value="${tutorialSchedule.fromTime}" pattern="HH:mm" timeZone="${timeZone}"/> <fmt:formatDate value="${tutorialSchedule.startDate}" pattern="MMMM dd yyyy" timeZone="${timeZone}"/>');">
</c:if>
<div></div>
<!-- end day tutorial schedule -->
