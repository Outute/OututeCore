<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!--start tutorialForm-->
<%@ include file="/common/taglibs.jsp"%><head>
<title><fmt:message key="tutorial.profile.title" /></title>
<meta name="heading"
	content="<fmt:message key='tutorial.profile.heading'/>" />
<meta name="menu" content="TutorialMenu" />
</head>
<!-- Offer Tutorials -->
<button onclick="Util.ajax('createTutorial?ajax=true',null,[function(a,b,c){document.getElementById('aaa').innerHTML=c.text;},function(a,b,c){alert(c.text);}],true,1000);">adf</button>
<button onclick="alert(Util.serialize('addTutorial'))">aaaa</button>
<button onclick="Util.ajax('addTutorialDatePage?ajax=true',null,[function(a,b,c){Util.html('rightTutorialPanel',c.text);},function(a,b,c){alert(c.text);}]);">date</button>
<div id="aaa"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<tr valign="top">
			<td width="30%">
			<!-- Tutorials list -->
				<div class="addTutorial" style="text-align: right;background-color: #ddd; height: 30px; line-height: 30px; padding-right: 5px;">
					<span class="addTutorialIcon">+</span><a href="javascript:;" onclick="clickAddTutorial('rightTutorialPanel','addTutorialPage?ajax=true');return false;">Add Tutorial</a>
				</div>
				<div class="tutorialList"  style="overflow-y: scroll; height: 400px; background-color: white;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<c:forEach items="[1,2,3,4,5,6]">
							<tr height="30px;">
								<td width="80%">
									<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<a href="">tutorial name</a>
								</td>
								<td>
									<span class="deleteTutorialIcon">X</span>
									<a href="">Delete</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			<!-- end Tutorials list -->
			</td>
			<td>
			<div id="rightTutorialPanel" style="padding: 10px; background-color: teal;">
			<!-- Class list -->
				<div style="width: 100%;">
					<div class="classTutorialTitle"><span>Tutorial Title</span></div>
					<div class="classDescription"><span>class Description</span></div>
				</div>
				<hr/>
				<div style="float:left; position:relative; width: 100%;">
					<div class="tutorialDetail" style="float:left; width:70%; display:inline;">
						<div><span>ID:</span><span>12345</span></div>
						<div><span>Category:</span><span>Language</span></div>
						<div><span>Tutorial Type:</span><span>Classes</span></div>
					</div>
					<div style="float: right; width:25%; display:inline; text-align: right; position: absolute;bottom: 10px;">
						<span class="tutorialEditIcon"></span>
						<a href="">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="tutorialAddDateIcon"></span>
						<a href="javascript:;" onclick="Util.ajax('addTutorialDatePage?ajax=true',null,[function(a,b,c){Util.html('rightTutorialPanel',c.text);},function(a,b,c){alert(c.text);}]);">Add Date</a>
					</div>
				</div>
				<div style="width: 100%; background-color: white; overflow-y: scroll; height: 330px;">
					<table width="100%" height="100%" border="0" cellspacing="5px" cellpadding="0">
						<tbody>
							<c:forEach items="[1,2,3,4,5,6,7,8]">
							<tr height="40px;" style="background-color: #eee;">
								<td width="80%">
									<span class="tutorialIcon">&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<a href="">tutorial name</a>
								</td>
								<td>
									<span class="deleteTutorialIcon">+</span>
									<a href="">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="deleteTutorialIcon">-</span>
									<a href="">Delete</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- end Class list -->
			</td>
		</tr>
	</tbody>
</table>
<!-- end Offer Tutorials -->