<!-- Begin Image Frame Stop *********************************************************** -->

		</div>

		<div class="navigation">

			<h2>Clients</h2>

			<ul>
			    <li><a href=clients.jsp>List/Update</a></li>
			    <li><a href="javascript:doNewClient();">New</a></li>
			    <li><a href="javascript:doMailingLabels();">Mailing Labels</a></li>
			    <li><a href=emailList.jsp>E-Mail List</a></li>

			</ul>

			<h2>Calendar</h2>

			<ul>
			    <li><a href=calDay.jsp>View/Update</a></li>
			    <li><a href=#>New</a></li>

			</ul>

			<h2>Invoicing</h2>

			<ul>
			    <li><a href=#>List/Update</a></li>
			    <li><a href=#>New</a></li>

			</ul>

			<h2>Inventory</h2>

			<ul>
			    <li><a href=#>List/Update</a></li>
			    <li><a href=#>New</a></li>
			    <li><a href=#>Update Website</a></li>

			</ul>

			<h2>Vendors</h2>

			<ul>
			    <li><a href=vendors.jsp>List/Update</a></li>
			    <li><a href=#>New</a></li>

			</ul>



		</div>

		<div class="clearer">&nbsp;</div>

	</div>

	<div class="footer"><a href="http://www.golden-op.com" target="_blank"><img src="images/GO_button.GIF" alt="Website design and hosting services by by Golden Opportunities" title="Website design and hosting services by by Golden Opportunities" width="90" height="33" border="0" align="left" /></a>

				<span class="right"><a href="home.jsp">Home</a> | </a><a href=http://www.paulinealwesnmd.net target=_blank>Website</a></span>

	</div>

	<div class="clearer"></div>

	</div>

</div>

</div>


        <script language=javascript><!--

            function doNewClient() {
                submitForm("clientForm.jsp","new")
            }

            function doMailingLabels() {
                popupNewWindow("MailingLabels")
            }

        //-->
        </script>



<% //if (!ProductionStatus.equals("prod")) { %>
    <!-- Show environment information when not on production -->
    <br>
    <hr width=80% >
    <br><br><%@ include file="envDisp.inc" %>
<% //} %>
			<!--IMG SRC="/images/spacer.gif" WIDTH=1 HEIGHT=383-->
</form>
<!-- set up iframe for submitting changes to the database -->
<% //if (ProductionStatus.equals("prod")) { %>
    <!--iframe src="blank.jsp" width=1 height=1 align=left name=results border=0 frameborder=0></iframe-->
<% //} else { %>
    <iframe src="blank.jsp" width=60 height=60 align=left name=results border=0 frameborder=0></iframe>
<% //} %>

<%!
private String testNull(String text, String def) {
    if (text != null) {
        return text;
    } else {
        return def;
    }
}
%>

<%!
private String lPad(String text, String pad, int len) {
    while (text.length() < len) {
        text = pad + text;
    }
    return text;
}
%>
<form name='footerform' METHOD='POST' action=>
<input type=hidden name=command value="test">
</form>
<!-- End Image Frame Stop *********************************************************** -->
