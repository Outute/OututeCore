<!--start userForm-->
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="heading" content="<fmt:message key='userProfile.heading'/>"/>
    <meta name="menu" content="UserMenu"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
</head>

<s:form name="userForm" action="saveUser" method="post" validate="true">
        <s:hidden key="user.id"/>
        <s:hidden key="user.version"/>
        <input type="hidden" name="from" value="${param.from}"/>

        <c:if test="${cookieLogin == 'true'}">
            <s:hidden key="user.password"/>
            <s:hidden key="user.confirmPassword"/>
        </c:if>

        <s:if test="user.version == null">
            <input type="hidden" name="encryptPass" value="true" />
        </s:if>
    <table>
    	<tr>
    		<td>
    			<s:submit key="button.save" method="save" cssClass="button" onclick="onFormSubmit(this.form)"/>
    		</td>
    		<td>
    			<c:if test="${param.from == 'list' and not empty user.id}">
    				<s:submit key="button.delete" method="delete" cssClass="button" onclick="return confirmDelete('user')"/>
    			</c:if>
    		</td>
    		<td>
    			<s:submit key="button.cancel" cssClass="button" method="cancel"/>
    		</td>
    	</tr>
    </table>
    <br/>
        <c:choose>
            <c:when test="${param.from == 'list'}">
                <p><fmt:message key="userProfile.admin.message"/></p>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="userProfile.message"/></p>
            </c:otherwise>
        </c:choose>
    <table>
    	<tr>
    		<td>
    			<s:textfield key="user.username" cssClass="text large" theme="xhtml" required="true"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<c:if test="${cookieLogin != 'true'}">
                <s:password key="user.password" showPassword="true" theme="xhtml" required="true" 
                    cssClass="text medium" onchange="passwordChanged(this)"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:password key="user.confirmPassword" theme="xhtml" required="true" 
                    showPassword="true" cssClass="text medium" onchange="passwordChanged(this)"/>
                </c:if>
            </td>
        </tr>
        <tr>
        	<td>
        		<s:textfield key="user.passwordHint" required="true" cssClass="text large" theme="xhtml" />
        	</td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.firstName" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.lastName" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.email" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.phoneNumber" theme="xhtml" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
        		<s:textfield key="user.website" required="true" theme="xhtml" cssClass="text large"/>
        	</td>
        </tr>
        <tr>
        	<td>
        		<label class="desc"><fmt:message key="user.address.address"/></label>
        	</td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.address.address" theme="xhtml" cssClass="text large" />
            </td>
        </tr>
        <tr>
            <td>
                <s:textfield key="user.address.city" theme="xhtml" required="true" cssClass="text medium" />
            </td>
        </tr>
        <tr>
                
            <td>
                <s:textfield key="user.address.province" theme="xhtml" required="true" cssClass="text state" />
            </td>
        </tr>
        <tr>
                
            <td>
                <s:textfield key="user.address.postalCode" theme="xhtml" required="true" cssClass="text medium" />
            </td>
        </tr>
        <tr>
            <td>
            	<p>
            		<label for="user.address.country">
            			<fmt:message key="user.address.country"/> <span class="req">*</span>
            		</label>
            	</p>
            </td>
        <tr>
        	<td>
                <s:set name="country" value="user.address.country" scope="page"/>
                <appfuse:country name="user.address.country" prompt="" default="${country}"/>
            </td>
        </tr>
    </table>
    <br/>
    <c:choose>
    	<c:when test="${param.from == 'list'}">
		    <legend><fmt:message key="userProfile.accountSettings"/></legend>
		    <s:checkbox key="user.enabled" id="user.enabled" fieldValue="true" theme="simple"/>
		    <label for="user.enabled" class="choice"><fmt:message key="user.enabled"/></label>
		
		    <s:checkbox key="user.accountExpired" id="user.accountExpired" fieldValue="true" theme="simple"/>
		    <label for="user.accountExpired" class="choice"><fmt:message key="user.accountExpired"/></label>
			
		    <s:checkbox key="user.accountLocked" id="user.accountLocked" fieldValue="true" theme="simple"/>
		    <label for="user.accountLocked" class="choice"><fmt:message key="user.accountLocked"/></label>
			
		    <s:checkbox key="user.credentialsExpired" id="user.credentialsExpired" fieldValue="true" theme="simple"/>
    	    <label for="user.credentialsExpired" class="choice"><fmt:message key="user.credentialsExpired"/></label>

            <legend><fmt:message key="userProfile.assignRoles"/></legend>
            <table class="pickList">
                <tr>
                    <th class="pickLabel">
                        <label class="required"><fmt:message key="user.availableRoles"/></label>
                    </th>
                    <td></td>
                    <th class="pickLabel">
                        <label class="required"><fmt:message key="user.roles"/></label>
                    </th>
                </tr>
                <c:set var="leftList" value="${availableRoles}" scope="request"/>
                <s:set name="rightList" value="user.roleList" scope="request"/>
                <c:import url="/WEB-INF/pages/pickList.jsp">
                    <c:param name="listCount" value="1"/>
                    <c:param name="leftId" value="availableRoles"/>
                    <c:param name="rightId" value="userRoles"/>
                </c:import>
            </table>
	    </c:when>
	    <c:otherwise>
	        <strong><fmt:message key="user.roles"/>:</strong>
	        <s:iterator value="user.roleList" status="status">
	          <s:property value="label"/><s:if test="!#status.last">,</s:if>
	          <input type="hidden" name="userRoles" value="<s:property value="value"/>"/>
	        </s:iterator>
	        <s:hidden name="user.enabled" value="%{user.enabled}"/>
	        <s:hidden name="user.accountExpired" value="%{user.accountExpired}"/>
	        <s:hidden name="user.accountLocked" value="%{user.accountLocked}"/>
	        <s:hidden name="user.credentialsExpired" value="%{user.credentialsExpired}"/>
	    </c:otherwise>
    </c:choose>
    <br/>
    <table>
    	<tr>
    		<td>
    			<s:submit key="button.save" method="save" cssClass="button" onclick="onFormSubmit(this.form)"/>
    		</td>
    		<td>
    			<c:if test="${param.from == 'list' and not empty user.id}">
    				<s:submit key="button.delete" method="delete" cssClass="button" onclick="return confirmDelete('user')"/>
    			</c:if>
    		</td>
    		<td>
    			<s:submit key="button.cancel" cssClass="button" method="cancel"/>
    		</td>
    	</tr>
    </table>
</s:form>

<script type="text/javascript">
    /*Form.focusFirstElement(document.forms["userForm"]);*/
    highlightFormElements();

    function passwordChanged(passwordField) {
        if (passwordField.name == "user.password") {
            var origPassword = "<s:property value="user.password"/>";
        } else if (passwordField.name == "user.confirmPassword") {
            var origPassword = "<s:property value="user.confirmPassword"/>";
        }
        
        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden",  "encryptPass", "encryptPass",
                              "true", passwordField.form);
        }
    }

<!-- This is here so we can exclude the selectAll call when roles is hidden -->
function onFormSubmit(theForm) {
<c:if test="${param.from == 'list'}">
    selectAll('userRoles');
</c:if>
}
</script>

<!--end userForm-->