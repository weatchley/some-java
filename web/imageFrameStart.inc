<!-- Begin Image Frame Start ************************************************************************************************ -->
<script language=javascript><!--
   function submitForm(script,command) {
       document.main.command.value = command;
       document.main.target = '_self';
       document.main.action = script;
       document.main.submit();
   }
   function submitFormResults(script,command) {
       document.main.command.value = command;
       document.main.action = script;
       document.main.target = 'results';
       document.main.submit();
   }
   function submitFormNewWindow(script,command) {
       document.main.command.value = command;
       document.main.action = script;
       var saveTarget = document.main.target;
       document.main.target = '_blank';
       document.main.submit();
       document.main.target = saveTarget;
   }
   function popupNewWindow(script) {
       popup = window.open(script, "name");
   }
//-->
</script>
<script src=/common/javascript/widgets.js></script>
<script src=/common/javascript/utilities.js></script>
<script language=javascript><!--
    document.body.style.cursor = 'default';
//-->
</script>

<% if (!request.getScheme().equals("https")) { %>
<!-- Force the use of https -->
<script language=javascript><!--
    //document.location= 'https://<%= request.getServerName() %>:8443/<%= getServletContext().getServletContextName()  %>/home.jsp';
//-->
</script>
<% } else {
      if (session.getAttribute("user.name")  != null && !((String) session.getAttribute("user.name")).toLowerCase().equals("guest")) {
           // get information about user: name, privs, etc...
           isAuthenticated = true;
           //sUsr = new Person((String) session.getAttribute("user.name"));
           //usr = (Person) session.getAttribute("user.person");
           //pos = (Position) session.getAttribute("user.position");
           //posID = (long) new Long((String) session.getAttribute("user.positionid")).longValue();
           //perMap = (HashMap) session.getAttribute("user.permissionmap");
           //if (pos.belongsTo(((Long) perMap.get("8-admin")).longValue())) {
                   isAdmin = true;
           //}
      }
   } %>

<div class="outer-container">

<div class="inner-container">

	<div class="header">

		<div class="title">

			<!--span class="sitename"><span class="left><a href="index.html">Pauline Alwes, NMD</a></span></span-->

			<div class="slogan" align=center><br>Office Management System</div>
			<br><br>

			<span class="right"><a href=home.jsp>Home</a> |  <a href=clients.jsp>Clients</a> |  <a href=invoicing.jsp>Invoicing</a> |  <a href=calDay.jsp>Calendar</a> |  <a href=inventory.jsp>Inventory</a> |  <a href=http://www.paulinealwesnmd.net target=_blank>Website</a></span>

		</div>

	</div>

	<div class="main">

		<div class="content">



<!-- Setup form for page -->
<% String formName = "main"; %>
<form <%= ((useFileUpload) ? "enctype=\"multipart/form-data\" " : "") %>name='<%= formName %>' METHOD='POST' action=>

<input type=hidden name=id value='0'>
<input type=hidden name=command value='test'>




<!-- End Image Frame Start ************************************************************************************************ -->
