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
OfficeCal[] cal = null;

java.util.Date myDate = new java.util.Date();

%>
  

<p>Appointments/Events for <%= Utils.dateToString(myDate) %></p>
<p><a href="javascript:doNew();">New Appointment/Event</a></p>
<br>

<% //System.out.println("calDay.jsp-Got Here 1"); %>
<!-- **************          **************** -->
<h4>Appointment/Event List</h4>
<% cal = OfficeCal.getItemListForDay(myConn, myDate); %>

<table border=1 cellpadding=0 cellspacing=0><tr><td>&nbsp;</td><td>ID</td><td>Time</td><td>Client</td><td>Description</td></tr>
<% for (int i=0; i<cal.length; i++) { %>
    <tr><td><a href="javascript:doEdit(<%= cal[i].getID() %>)">edit</a></td><td><a href="javascript:doView(<%= cal[i].getID() %>)"><%= cal[i].getID() %></a></td>
    <td><a href="javascript:doView(<%= cal[i].getID() %>)"><%= ((cal[i].isAllDay()) ? "All Day" : cal[i].getBegin() + ", " + cal[i].getEnd()) %></a></td>
    <% Clients ctmp = (cal[i].getClientID() != 0) ? new Clients(cal[i].getClientID(), myConn) : null; %>
    <td><%= ((ctmp != null) ? ctmp.getLastName() + ", " + ctmp.getFirstName() : "N/A") %></td>
    <td><%= ((cal[i].getDescription() != null) ? cal[i].getDescription() : "&nbsp;") %></td><tr>
<% } %>
</table>

        <script language=javascript><!--

            function doView(id) {
                document.main.id.value = id;
                submitForm("calForm.jsp","view")
            }

            function doEdit(id) {
                document.main.id.value = id;
                submitForm("calForm.jsp","update")
            }

            function doNew() {
                submitForm("calForm.jsp","new")
            }
        //-->
        </script>





<%
myConn.release();
%>
<!-- End Main Content -->
<%@ include file="imageFrameStop.inc" %>

</BODY>

</HTML>
