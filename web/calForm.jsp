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
OfficeCal item = null;
%>
  

<%
String command = request.getParameter("command");
String idS = ((request.getParameter("id") != null && !request.getParameter("id").equals("")) ? (String) request.getParameter("id") : "0");
int id = Integer.parseInt(idS);
if (command.equals("update") || command.equals("view")) {
    item = new OfficeCal(id, myConn);
} else {
    item = new OfficeCal();
}

%>

<% //System.out.println("calForm.jsp-Got Here 1"); %>
<!-- **************          **************** -->
<h3>Update Event/Appointment</h3>

<table border=0>
<% if (command.equals("update") || command.equals("new") || command.equals("add")) { %>
    <tr><td>ID: </td><td><%= ((command.equals("update")) ? item.getID() : "New") %></td></tr>
    <tr><td>Date: </td><td><span id="holder1"><input type=text name=eventdate value="<%= ((item.getEventDate() != null) ? Utils.dateToString(item.getEventDate(), "MM/dd/yyyy") : "") %>" size=12 maxlength=10 onfocus="this.blur();showCalendar('',this,this,'','holder1',0,30,1);"></span></td></tr>
    <tr><td>All Day: </td><td><input type=checkbox name=allday value="T" <%= ((item.isAllDay()) ? "checked" : "") %>></td></tr>
    <tr><td>Begin: </td><td><input type=text name=begin value="<%= ((item.getBegin() != null) ? item.getBegin() : "") %>" size=10 maxlength=10></td></tr>
    <tr><td>End: </td><td><input type=text name=end value="<%= ((item.getEnd() != null) ? item.getEnd() : "") %>" size=10 maxlength=10></td></tr>
    <tr><td>Client Appointment: </td><td><input type=checkbox name=isclientapp value="T" <%= ((item.isClientApp()) ? "checked" : "") %>></td></tr>
    <% Clients [] clnts = Clients.getItemList(myConn); %>
    <tr><td>Client: </td><td><select name=client><option value=0> </option>
    <% for (int i=0; i<clnts.length; i++) { %>
          <option value=<%= clnts[i].getID() %>><%= clnts[i].getFullName() %>
    <% } %>
    </select></td></tr>
    <tr><td>Send Reminder: </td><td><input type=checkbox name=sendreminder value="T" <%= ((item.sendReminder()) ? "checked" : "") %>></td></tr>
    <tr><td>Public: </td><td><input type=checkbox name=ispublic value="T" <%= ((item.isPublic()) ? "checked" : "") %>></td></tr>
    <tr><td>Showed up: </td><td><input type=checkbox name=showedup value="T" <%= ((item.showedUp()) ? "checked" : "") %>></td></tr>
    <tr><td valign=top>Notes/Description: </td><td><textarea name=description rows=4 cols=40><%= ((item.getDescription() != null) ? item.getDescription() : "") %></textarea></td></tr>
    <tr><td colspan=2 center><input type=button value='Submit' onClick="verifySubmit(document.main)"></td></tr>
    <input type=hidden name=nextscript value="calDay.jsp">
<% } else if(command.equals("view")) { %>
    <tr><td>ID: </td><td><%= item.getID() %></td></tr>
    <tr><td>Date: </td><td><%= Utils.dateToString(item.getEventDate(), "MM/dd/yyyy") %></td></tr>
    <tr><td>All Day: </td><td><%= ((item.isAllDay()) ? "Yes" : "No") %></td></tr>
    <tr><td>Begin: </td><td><%= ((item.getBegin() != null) ? item.getBegin() : "") %></td></tr>
    <tr><td>End: </td><td><%= ((item.getEnd() != null) ? item.getEnd() : "") %></td></tr>
    <tr><td>Client Appointment: </td><td><%= ((item.isClientApp()) ? "Yes" : "No") %></td></tr>
    <tr><td>Client: </td><td><%= ((item.getClient() != null) ? item.getClient().getFullName() : "") %></td></tr>
    <tr><td>Send Reminder: </td><td><%= ((item.sendReminder()) ? "Yes" : "No") %></td></tr>
    <tr><td>Public: </td><td><%= ((item.isPublic()) ? "Yes" : "No") %></td></tr>
    <tr><td>Showed up: </td><td><%= ((item.showedUp()) ? "Yes" : "") %>></td></tr>
    <tr><td valign=top>Notes/Description: </td><td><%= ((item.getDescription() != null) ? item.getDescription() : "") %></td></tr>
<% } %>

</table>

        <script language=javascript><!--

            function verifySubmit (f){
            // javascript form verification routine
                var msg = "";
                if (isblank(f.description.value)) {
                    msg += "Notes/Description must be entered.\n";
                }
                if (isblank(f.eventdate.value)) {
                    msg += "Date must be entered.\n";
                }
                if (msg != "") {
                  alert (msg);
                } else {
                    f.id.value = <%= id %>;
                    submitFormResults('doCal', '<%= command %>');
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
