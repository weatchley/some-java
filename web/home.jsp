<%@ include file="headerSetup.inc" %>
<HTML>

<HEAD>
<TITLE><%= SystemName %></TITLE>
<link rel="stylesheet" type="text/css" href="default.css" media="screen"/>
</HEAD>

<BODY>
<%@ include file="imageFrameStart.inc" %>
<!-- Begin Main Content -->
<p>
    <% 
String docRoot = getServletContext().getRealPath("/");
DbConn myConn = new DbConn("pka");
String tempText = null;
String[] bgcolors = new String [] {"#ffffff", "#dddddd"};
Clients[] cls = null;
%>
  

<p>Welcome to the Dr Alwes Office Management System.</p>
<p>This system performs the following functions:</p>
<ul>
<li><a href=clients.jsp>Manage Clients.</a></li>
<li>Print Mailing Lists.</li>
<li>Manage Inventory.</li>
<li><a href=vendors.jsp>Manage Vendors.</a></li>
<li>Manage Patient invoices and history.</li>
</ul>
<p>&nbsp; </p>
<br>

<% //System.out.println("home.jsp-Got Here 1"); %>
<!-- **************    --      **************** -->
<p>Calendar</p>




<%
myConn.release();
%>
<!-- End Main Content -->
<%@ include file="imageFrameStop.inc" %>

</BODY>

</HTML>
