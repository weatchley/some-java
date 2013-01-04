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
  

<p>Manage Clients</p>
<p><a href="javascript:doNew();">New Client</a></p>
<br>

<% //System.out.println("clients.jsp-Got Here 1"); %>
<!-- **************          **************** -->
<h4>Patient/Client List</h4>
<% cls = Clients.getItemList(myConn); %>

<table border=1 cellpadding=5 cellspacing=0><tr><td>&nbsp;</td><td>ID</td><td>Name</td><td>Last Seen</td><td>Occupation</td></tr>
<% for (int i=0; i<cls.length; i++) { %>
    <tr><td><a href="javascript:doEdit(<%= cls[i].getID() %>)">edit</a>&nbsp;</td><td><a href="javascript:doView(<%= cls[i].getID() %>)"><%= cls[i].getID() %></a>&nbsp;</td>
    <td><a href="javascript:doView(<%= cls[i].getID() %>)"><%= cls[i].getLastName() + ", " + cls[i].getFirstName() %></a>&nbsp;</td>
    <td><%= ((cls[i].getLastseen() != null) ? Utils.dateToString(cls[i].getLastseen(), "MM/dd/yyyy") : "&nbsp;") %>&nbsp;</td>
    <td><%= ((cls[i].getOccupation() != null) ? cls[i].getOccupation() : "&nbsp;") %></td><tr>
<% } %>
</table>

        <script language=javascript><!--

            function doView(id) {
                document.main.id.value = id;
                submitForm("clientForm.jsp","view")
            }

            function doEdit(id) {
                document.main.id.value = id;
                submitForm("clientForm.jsp","update")
            }

            function doNew() {
                submitForm("clientForm.jsp","new")
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
