<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${duration_showtype==0}">
<option value="<s:property value="@com.edu.util.DateUtil@DURATION_NO_REPEAT"/>" <c:if test="${duration_type==0}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.NoRepeat"/></option>
<option value="<s:property value="@com.edu.util.DateUtil@DURATION_DAYLY"/>" <c:if test="${duration_type==1}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Daily"/></option>
<option value="<s:property value="@com.edu.util.DateUtil@DURATION_WEEKLY"/>" <c:if test="${duration_type==2}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Weekly"/></option>
<option value="<s:property value="@com.edu.util.DateUtil@DURATION_BI_WEEKLY"/>" <c:if test="${duration_type==3}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.BiWeekly"/></option>
<option value="<s:property value="@com.edu.util.DateUtil@DURATION_MONTHLY"/>" <c:if test="${duration_type==4}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Monthly"/></option>
</c:if>