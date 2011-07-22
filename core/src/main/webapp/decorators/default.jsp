<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><fmt:message key="webapp.name"/></title>
    <link rel="Bookmark" href="/images/outute_icon.ico">
    <link rel="Shortcut Icon" href="/images/outute_icon.ico">
    <link href="css/outute_style.css" rel="stylesheet" type="text/css" />
    <script src="js/outute_js.js" type="text/javascript"></script>
    <style type="text/css">
    <!--
    .STYLE1 {color: #006600}
    -->
  </style>

  <decorator:head/>
  </head>

  <body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
    <div id="container">
      <div class="content-box">
        <jsp:include page="/common/header.jsp"/>
        <%@ include file="/common/messages.jsp" %>
        <decorator:body/>
      </div>
      <div class="footer-links floatl">
        <jsp:include page="/common/footer.jsp"/>
      </div>
    </div>
  </body>
</html>
