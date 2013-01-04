<%@ include file="headerSetup.inc" %>
<HTML>

<HEAD>
<TITLE><%= SystemName %>Office Management System</TITLE>
<link rel="stylesheet" type="text/css" href="default.css" media="screen"/>
  <script src=javascript/utilities.js></script>
  <link rel=stylesheet href="javascript/xc2/css/xc2_default.css" type="text/css">
  <script language="javascript" src="javascript/xc2/config/xc2_default.js"></script>
  <script language="javascript" src="javascript/xc2/script/xc2_inpage.js"></script>
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
Vendor vnd = null;
%>
  

<%
String command = request.getParameter("command");
String idS = ((request.getParameter("id") != null && !request.getParameter("id").equals("")) ? (String) request.getParameter("id") : "0");
int id = Integer.parseInt(idS);
if (command.equals("update") || command.equals("view")) {
    vnd = new Vendor(id, myConn);
} else {
    vnd = new Vendor();
}

%>

<% //System.out.println("updateClient.jsp-Got Here 1"); %>
<!-- **************    Global Counts      **************** -->
<h3>Update Patient/Client</h3>

<table border=0>
<% if (command.equals("update") || command.equals("new") || command.equals("add")) { %>
    <tr><td>ID: </td><td><%= ((command.equals("update")) ? vnd.getID() : "New") %></td></tr>
    <tr><td>Name: </td><td><input type=text name=name value="<%= ((vnd.getName() != null) ? vnd.getName() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>Email: </td><td><input type=text name=email value="<%= ((vnd.getEmail() != null) ? vnd.getEmail() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>Address: </td><td><input type=text name=address value="<%= ((vnd.getAddress() != null) ? vnd.getAddress() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>City: </td><td><input type=text name=city value="<%= ((vnd.getCity() != null) ? vnd.getCity() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>State: </td><td><input type=text name=state value="<%= ((vnd.getState() != null) ? vnd.getState() : "") %>" size=20 maxlength=20></td></tr>
    <tr><td>Zip: </td><td><input type=text name=zip value="<%= ((vnd.getZip() != null) ? vnd.getZip() : "") %>" size=10 maxlength=10></td></tr>
    <tr><td>Phone: </td><td><input type=text name=phone value="<%= ((vnd.getPhone() != null) ? vnd.getPhone() : "") %>" size=15 maxlength=15></td></tr>
    <tr><td>Fax: </td><td><input type=text name=fax value="<%= ((vnd.getFax() != null) ? vnd.getFax() : "") %>" size=15 maxlength=15></td></tr>
    <tr><td>Web Site: </td><td><input type=text name=website value="<%= ((vnd.getWebsite() != null) ? vnd.getWebsite() : "") %>" size=60 maxlength=200></td></tr>
    <tr><td>Is Active: </td><td><input type=checkbox name=isactive value="T"<%= ((vnd.isActive()) ? " checked" : "") %>></td></tr>
    <tr><td valign=top>Notes: </td><td><textarea name=notes rows=4 cols=40><%= ((vnd.getNotes() != null) ? vnd.getNotes() : "") %></textarea></td></tr>
    <tr><td colspan=2 center><input type=button value='Submit' onClick="verifySubmit(document.main)"></td></tr>
    <input type=hidden name=nextscript value="vendors.jsp">
<% } else if(command.equals("view")) { %>
    <tr><td>ID: </td><td><%= vnd.getID() %></td></tr>
    <tr><td>Name: </td><td><%= ((vnd.getName() != null) ? vnd.getName() : "&nbsp;") %></td></tr>
    <tr><td>Email: </td><td><%= ((vnd.getEmail() != null) ? vnd.getEmail() : "&nbsp;") %></td></tr>
    <tr><td>Address: </td><td><%= ((vnd.getAddress() != null) ? vnd.getAddress() : "&nbsp;") %></td></tr>
    <tr><td>City: </td><td><%= ((vnd.getCity() != null) ? vnd.getCity() : "&nbsp;") %></td></tr>
    <tr><td>State: </td><td><%= ((vnd.getState() != null) ? vnd.getState() : "&nbsp;") %></td></tr>
    <tr><td>Zip: </td><td><%= ((vnd.getZip() != null) ? vnd.getZip() : "&nbsp;") %></td></tr>
    <tr><td>Phone: </td><td><%= ((vnd.getPhone() != null) ? vnd.getPhone() : "&nbsp;") %></td></tr>
    <tr><td>Fax: </td><td><%= ((vnd.getFax() != null) ? vnd.getFax() : "&nbsp;") %></td></tr>
    <tr><td>Web Site: </td><td><%= ((vnd.getWebsite() != null) ? "<a href=http://<%= vnd.getWebsite() %> target=_blank>" + vnd.getWebsite() + "</a>" : "&nbsp;") %></td></tr>
    <tr><td>Is Active: </td><td><%= ((vnd.isActive()) ? "Yes" : "No") %></td></tr>
    <tr><td valign=top>Notes: </td><td><%= ((vnd.getNotes() != null) ? vnd.getNotes() : "&nbsp;") %></td></tr>
<% } %>

</table>

        <script language=javascript><!--

            function verifySubmit (f){
            // javascript form verification routine
                var msg = "";
                if (isblank(f.name.value)) {
                    msg += "Name must be entered.\n";
                }
                if (isblank(f.email.value)) {
                    msg += "E-Mail must be entered.\n";
                }
                if (isblank(f.phone.value)) {
                    msg += "Phone number must be entered.\n";
                }
                if (msg != "") {
                  alert (msg);
                } else {
                    f.id.value = <%= id %>;
                    submitFormResults('doVendor', '<%= command %>');
                }
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
