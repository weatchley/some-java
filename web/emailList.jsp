<%@ include file="headerSetup.inc" %>
<HTML>

<HEAD>
<TITLE><%= SystemName %> Office Management System</TITLE>
<link rel="stylesheet" type="text/css" href="default.css" media="screen"/>
  <script src=javascript/utilities.js></script>
</HEAD>

<BODY leftmargin=0 topmargin=0>
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
  

<p>E-Mail List</p>
<br>

<% //System.out.println("emailList.jsp-Got Here 1"); %>
<!-- **************          **************** -->
<% cls = Clients.getItemList(myConn); %>

Select all items below and past into your e-mail program (preferably in the bcc line).
<table border=3 cellpadding=0 cellspacing=0><tr><td>
<% for (int i=0; i<cls.length; i++) { %>
    <%= ((cls[i].getEmail() !=null && !cls[i].getEmail().equals("N/A")) ? cls[i].getEmail() + "; " : "") %>
<% } %>
</td></tr></table>





<%
myConn.release();
%>
<!-- End Main Content -->
<%@ include file="imageFrameStop.inc" %>

</BODY>

</HTML>
