<!--start signup-->
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="signup.title"/></title>
    <meta name="heading" content="<fmt:message key='signup.heading'/>"/>
</head>

<body id="signup"/>

<s:form name="signupForm" action="signup" method="post" validate="true">

    <p class="signup">
        <fmt:message key="signup.message"/>
    </p>
    <table>
    	<tr>
    		<td>
    			<s:textfield key="user.username" theme="xhtml" cssClass="text medium" required="true"/>
    		</td>
    	</tr>
    	<tr>
    		<td>
                <s:password key="user.password" showPassword="true" theme="xhtml" required="true" 
                    cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:password key="user.confirmPassword" theme="xhtml" required="true" 
                    showPassword="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
        	<td>
        		<s:textfield key="user.passwordHint" theme="xhtml" required="true" cssClass="text large"/>
        	</td>
        </tr>
        <tr>
            <td>
                <s:textfield key="user.firstName" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
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
        		<s:textfield key="user.website" theme="xhtml" required="true" cssClass="text large"/>
        	</td>
        </tr>
        	<td>
        		<label class="desc"><fmt:message key="user.address.address"/></label>
        	</td>
        </tr>
        <tr>
            <td>
                <s:textfield key="user.address.address" theme="xhtml" cssClass="text large"/>
            </td>
        </tr>
        <tr>
        	<td>
                <s:textfield key="user.address.city" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
           	<td>
                <s:textfield key="user.address.province" theme="xhtml" required="true" cssClass="text state"/>
            </td>
        </tr>
        <tr>
           	<td>
                <s:textfield key="user.address.postalCode" theme="xhtml" required="true" cssClass="text medium"/>
            </td>
        </tr>
        <tr>
    		<td>
            	<label for="user.address.country">
                	<fmt:message key="user.address.country"/> <span class="req">*</span>
                </label>
            </td>
        </tr>
        <tr>
           	<td>
                <s:set name="country" value="user.address.country" scope="page"/>
                <appfuse:country name="user.address.country" prompt="" default="${country}"/>
            </td>
        </tr>
        <tr>
        	<td>
        		<s:submit key="button.register" cssClass="button"/>
        	</td>
        	<td>
        		<s:submit key="button.cancel" name="cancel" cssClass="button"/>
        	</td>
        </tr>
    </table>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["signupForm"]);
</script>


<!--end signup-->