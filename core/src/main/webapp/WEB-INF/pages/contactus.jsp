<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- Contact Us -->
<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>
<style>
span.pagelinks{
	text-align: right;
}
</style>

<div class="main-content">
 	<h2>Contact Us</h2>
	  <p>We would love to hear your voice no matter they are complains, suggestion, or complements. Please contact either through email: <a href="mailto:Outue@hotmail.com"><strong>Outue@hotmail.com</strong></a></p>
	  <p>Or</p>
   <div style="width:500px">
   <h2>Send Us Directly:</h2>
   <form action="form.php" method="post">
     <table width="97%">
       <tr>
         <td width="145" align="left" valign="top" class="body" id="Contact"><strong>Full Name:</strong></td>
         <td width="280" align="left" valign="top"><input name="Name" type="text" size="30" /></td>
       </tr>
       <tr>
         <td align="left" valign="top" class="body" id="Email"><strong> Email: </strong></td>
         <td align="left" valign="top"><input name="Email" type="text" size="30" /></td>
       </tr>
       <tr>
         <td align="left" valign="top" class="body" id="Comments"><strong> Questions / Comments: </strong></td>
         <td align="left" valign="top"><textarea name="comments" cols="25" rows="6"></textarea></td>
       </tr>
       <tr>
         <td></td>
         <td><input type="submit" name="submit" class="button" value="Send Now" /></td>
       </tr>
     </table>
   </form>
   </div>
 </div>