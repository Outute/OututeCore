<!--start tutorialForm-->
<%@ include file="/common/taglibs.jsp"%><head>
    <title><fmt:message key="tutorial.profile.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorial.profile.heading'/>"/>
    <meta name="menu" content="TutorialMenu"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
</head>

<s:form name="tutorialForm" action="saveTutorial" method="post" validate="true">
    
<table>
<tr>
	<td>
        <s:hidden key="tutorial.id"/>
        <s:hidden key="tutorial.version"/>
        <input type="hidden" name="from" value="${param.from}"/>

        <s:if test="tutorial.version == null">
            <input type="hidden" name="encryptPass" value="true" />
        </s:if>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.name" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.description" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.type" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.category" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.cost" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.method" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
<tr>
	<td>
    	<s:textfield key="tutorial.lengthInMins" required="true" theme="xhtml" cssClass="text large"/>
    </td>
</tr>
</table>
<table>
<tr>
	<td>
    	<s:submit key="button.save" method="save" onclick="onFormSubmit(this.form)"/>
    </td>
    <td>
<c:if test="${param.from == 'list' and not empty tutorial.id}">
    	<s:submit key="button.delete" method="delete" onclick="return confirmDelete('tutorial')"/>
</c:if>
    </td>
    <td>
    	 <s:submit key="button.cancel" method="cancel"/>
    </td
</tr>
</table>
</s:form>

<!--end tutorialForm-->