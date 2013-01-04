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
Clients clnt = null;
%>
  

<%
String command = request.getParameter("command");
String idS = ((request.getParameter("id") != null && !request.getParameter("id").equals("")) ? (String) request.getParameter("id") : "0");
int id = Integer.parseInt(idS);
if (command.equals("update") || command.equals("view")) {
    clnt = new Clients(id, myConn);
} else {
    clnt = new Clients();
}

%>

<% //System.out.println("updateClient.jsp-Got Here 1"); %>
<!-- **************    Global Counts      **************** -->
<h3><% if (command.equals("view")) { %>View<% } else { %>Add/Update<% } %> Patient/Client</h3>

<table border=0>
<% if (command.equals("update") || command.equals("new") || command.equals("add")) { %>
    <tr><td>ID: </td><td><%= ((command.equals("update")) ? clnt.getID() : "New") %></td></tr>
    <tr><td>Last Name: </td><td><input type=text name=lastname value="<%= ((clnt.getLastName() != null) ? clnt.getLastName() : "") %>" size=30 maxlength=30></td></tr>
    <tr><td>First Name: </td><td><input type=text name=firstname value="<%= ((clnt.getFirstName() != null) ? clnt.getFirstName() : "") %>" size=30 maxlength=30></td></tr>
    <tr><td>MiddleName: </td><td><input type=text name=middlename value="<%= ((clnt.getMiddleName() != null) ? clnt.getMiddleName() : "") %>" size=30 maxlength=30></td></tr>
    <tr><td>Occupation: </td><td><input type=text name=occupation value="<%= ((clnt.getOccupation() != null) ? clnt.getOccupation() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>Email: </td><td><input type=text name=email value="<%= ((clnt.getEmail() != null) ? clnt.getEmail() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>Address: </td><td><input type=text name=address value="<%= ((clnt.getAddress() != null) ? clnt.getAddress() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>City: </td><td><input type=text name=city value="<%= ((clnt.getCity() != null) ? clnt.getCity() : "") %>" size=50 maxlength=50></td></tr>
    <tr><td>State: </td><td><input type=text name=state value="<%= ((clnt.getState() != null) ? clnt.getState() : "") %>" size=20 maxlength=20></td></tr>
    <tr><td>Zip: </td><td><input type=text name=zip value="<%= ((clnt.getZip() != null) ? clnt.getZip() : "") %>" size=10 maxlength=10></td></tr>
    <tr><td>Phone: </td><td><input type=text name=phone value="<%= ((clnt.getPhone() != null) ? clnt.getPhone() : "") %>" size=15 maxlength=15></td></tr>
    <tr><td>Fax: </td><td><input type=text name=fax value="<%= ((clnt.getFax() != null) ? clnt.getFax() : "") %>" size=15 maxlength=15></td></tr>
    <tr><td>Last Seen: </td><td><span id="holder1"><input type=text name=lastseen value="<%= ((clnt.getLastseen() != null) ? Utils.dateToString(clnt.getLastseen(), "MM/dd/yyyy") : "") %>" size=12 maxlength=10 onfocus="this.blur();showCalendar('',this,this,'','holder1',0,30,1);"></span></td></tr>
    <tr><td>Location: </td><td><input type=text name=location value="<%= ((clnt.getLocation() != null) ? clnt.getLocation() : "") %>" size=20 maxlength=20></td></tr>
    <tr><td valign=top>Notes: </td><td><textarea name=notes rows=4 cols=40><%= ((clnt.getNotes() != null) ? clnt.getNotes() : "") %></textarea></td></tr>
    <tr><td colspan=2 center><input type=button value='Submit' onClick="verifySubmit(document.main)"></td></tr>
    <input type=hidden name=nextscript value="clients.jsp">
<% } else if(command.equals("view")) { %>
    <tr><td>ID: </td><td><%= clnt.getID() %></td></tr>
    <tr><td>Last Name: </td><td><%= ((clnt.getLastName() != null) ? clnt.getLastName() : "&nbsp;") %></td></tr>
    <tr><td>First Name: </td><td><%= ((clnt.getFirstName() != null) ? clnt.getFirstName() : "&nbsp;") %></td></tr>
    <tr><td>MiddleName: </td><td><%= ((clnt.getMiddleName() != null) ? clnt.getMiddleName() : "&nbsp;") %></td></tr>
    <tr><td>Occupation: </td><td><%= ((clnt.getOccupation() != null) ? clnt.getOccupation() : "&nbsp;") %></td></tr>
    <tr><td>Email: </td><td><%= ((clnt.getEmail() != null) ? clnt.getEmail() : "&nbsp;") %></td></tr>
    <tr><td>Address: </td><td><%= ((clnt.getAddress() != null) ? clnt.getAddress() : "&nbsp;") %></td></tr>
    <tr><td>City: </td><td><%= ((clnt.getCity() != null) ? clnt.getCity() : "&nbsp;") %></td></tr>
    <tr><td>State: </td><td><%= ((clnt.getState() != null) ? clnt.getState() : "&nbsp;") %></td></tr>
    <tr><td>Zip: </td><td><%= ((clnt.getZip() != null) ? clnt.getZip() : "&nbsp;") %></td></tr>
    <tr><td>Phone: </td><td><%= ((clnt.getPhone() != null) ? clnt.getPhone() : "&nbsp;") %></td></tr>
    <tr><td>Fax: </td><td><%= ((clnt.getFax() != null) ? clnt.getFax() : "&nbsp;") %></td></tr>
    <tr><td>Last Seen: </td><td><%= ((clnt.getLastseen() != null) ? Utils.dateToString(clnt.getLastseen(), "MM/dd/yyyy") : "&nbsp;") %></td></tr>
    <tr><td>Location: </td><td><%= ((clnt.getLocation() != null) ? clnt.getLocation() : "&nbsp;") %></td></tr>
    <tr><td valign=top>Notes: </td><td><%= ((clnt.getNotes() != null) ? clnt.getNotes() : "&nbsp;") %></td></tr>
<% } %>

</table>

        <script language=javascript><!--

            function verifySubmit (f){
            // javascript form verification routine
                var msg = "";
                if (isblank(f.firstname.value)) {
                    msg += "First Name must be entered.\n";
                }
                if (isblank(f.lastname.value)) {
                    msg += "Last Name must be entered.\n";
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
                    submitFormResults('doClient', '<%= command %>');
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
