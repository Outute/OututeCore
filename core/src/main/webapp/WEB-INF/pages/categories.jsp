<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- Categories -->
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
<h2><strong>Outute Categories</strong></h2>
   <table width="100%" border="0" cellspacing="10" cellpadding="10">
     <tr>
       <td width="33%"><a href="#" onclick="updateCategory('language')"><img src="images/language.jpg" id="languageImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('academic')"><img src="images/academic.jpg" id="academicImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('oversea')"><img src="images/oversea.jpg" id="overseaImg"/></a></td>
     </tr>
     <tr>
       <td width="33%"><a href="#" onclick="updateCategory('dummy')"><img src="images/dummy.jpg" id="dummyImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('computer')"><img src="images/computer.jpg" id="computerImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('other')"><img src="images/otherTutotial.jpg" id="otherImg"/></a></td>
     </tr>
  	</table>
   <div class="servicecolumnzone">
   <div align="center">
   	<select id="categoryType" style="width:auto">
          <option>Languages</option>
          <option>Academic Studies</option>
          <option>Study Oversea</option>
          <option>How to for Dummies</option>
          <option>Computer and Internet</option>
          <option>Other Categories</option>
        </select>
        <input name="Submit" type="button" value="Search" />
    </div>
    <div align="center">
    <table class='sortable'>
        <thead>
            <tr>
                <th>Start Date time</th>
                <th>Tutorial name</th>
                <th>Instructor</th>
                <th>Course Number</th>
                <th>Length (mins)</th>
                <th>Course type</th>
                <th>Course status</th>
                <th>User role</th>
                <th>Cost (USD)</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </tfoot>
        <tbody>
            <tr>
                <td>21/11/2011 16:30</td>
                <td>ESL 1 tutorial</td>
                <td>Crosby</td>
                <td class='numeric'>012311</td>
                <td class='numeric'>30</td>
                <td>Workshop</td>
                <td>Registered</td>
                <td>Student</td>
                <td class='numeric'>12.00</td>
            </tr>
            <tr class='odd'>
                <td>22/11/2011 16:30</td>
                <td>ESL 2 tutorial</td>
                <td>Crosby</td>
                <td class='numeric'>012312</td>
                <td class='numeric'>30</td>
                <td>Workshop</td>
                <td>Registered</td>
                <td>Student</td>
                <td class='numeric'>12.00</td>
            </tr>
            <tr>
                <td>23/11/2011 14:30</td>
                <td>ESL 3 tutorial</td>
                <td>Crosby</td>
                <td class='numeric'>012313</td>
                <td class='numeric'>40</td>
                <td>Workshop</td>
                <td>Registered</td>
                <td>Student</td>
                <td class='numeric'>15.00</td>
            </tr>
            <tr class='odd'>
                <td>02/12/2011 12:30</td>
                <td>French tutorial</td>
                <td>Ada</td>
                <td class='numeric'>012306</td>
                <td class='numeric'>10</td>
                <td>Tutorial</td>
                <td>Open</td>
                <td>Tutor</td>
                <td class='numeric'>8.00</td>
            </tr>
            <tr>
                <td>04/12/2011 10:50</td>
                <td>Chinese tutorial</td>
                <td>Ada</td>
                <td class='numeric'>012319</td>
                <td class='numeric'>15</td>
                <td>Tutorial</td>
                <td>Confirmed</td>
                <td>Tutor</td>
                <td class='numeric'>5.00</td>
            </tr>
        </tbody>
    </table>
    </div>
</div>