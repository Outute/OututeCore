<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>


        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />

		
		<link href="/styles/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		<!--
		.STYLE1 {color: #006600}
		-->
		</style>        

		<script src="/scripts/outute_js.js" type="text/javascript"></script>
        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
        <decorator:head/>
    </head>
<body>

    <div id="container">
		<div class="content-box">
			<div class="top-box">
            	<jsp:include page="/common/header.jsp"/>
				<jsp:include page="/common/menu.jsp"/>
			</div>

                <%@ include file="/common/messages.jsp" %>
                <decorator:body/>

            <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
            <c:if test="${currentMenu == 'AdminMenu'}">
            <div id="sub">
                <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="AdminMenu"/>
                </menu:useMenuDisplayer>
            </div>
            </c:if>

        </div>

        <div class="footer-links">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
