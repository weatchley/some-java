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
Vendor[] vnd = null;
%>
  

<p>Manage Vendors</p>
<p><a href="javascript:doNew();">New Vendor</a></p>
<br>

<% //System.out.println("clients.jsp-Got Here 1"); %>
<!-- **************          **************** -->
<h4>Patient/Client List</h4>
<% vnd = Vendor.getItemList(myConn); %>

<table border=1 cellpadding=0 cellspacing=0><tr><td>&nbsp;</td><td>ID</td><td>Name</td><td>Is Active</td><td>Web Site</td></tr>
<% for (int i=0; i<vnd.length; i++) { %>
    <tr><td><a href="javascript:doEdit(<%= vnd[i].getID() %>)">edit</a></td><td><a href="javascript:doView(<%= vnd[i].getID() %>)"><%= vnd[i].getID() %></a></td>
    <td><a href="javascript:doView(<%= vnd[i].getID() %>)"><%= vnd[i].getName() %></a></td>
    <td><%= ((vnd[i].isActive()) ? "Yes" : "No") %></td>
    <td><%= ((vnd[i].getWebsite() != null) ? vnd[i].getWebsite() : "&nbsp;") %></td><tr>
<% } %>
</table>

        <script language=javascript><!--

            function doView(id) {
                document.main.id.value = id;
                submitForm("vendorForm.jsp","view")
            }

            function doEdit(id) {
                document.main.id.value = id;
                submitForm("vendorForm.jsp","update")
            }

            function doNew() {
                submitForm("vendorForm.jsp","new")
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
