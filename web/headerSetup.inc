<!-- Header file for csi JSP files -->
<!-- Import required libraries -->
<%@ page import="java.lang.*,java.io.*,java.util.*,javax.xml.parsers.DocumentBuilder,javax.xml.parsers.DocumentBuilderFactory,org.w3c.dom.*" %>
<%@ page import="org.xml.sax.SAXException,org.xml.sax.SAXParseException,javax.naming.*,javax.naming.*,com.goldenop.util.misc.*,com.goldenop.util.db.*" %>
<%@ page import="net.paulinealwesnmd.util.db.*,net.paulinealwesnmd.misc.*,net.paulinealwesnmd.people.*" %>
<%@ page import="net.paulinealwesnmd.auth.*,net.paulinealwesnmd.items.*,org.json.*,net.paulinealwesnmd.model.*" %>
<!--%@ taglib uri="gov.ymp.rdc.tags" prefix="rdc" %-->

<%
// set up initial variables
boolean useFileUpload = false;
boolean isAuthenticated = false;
boolean isAdmin = true; 
//Person sUsr = null;
//Person usr = null;
//Position pos = null;
//long posID = 0;
//HashMap perMap = null;
%>

<%
// set up initial environment
Context initCtx = new InitialContext();
String SystemName = (String) initCtx.lookup("java:comp/env/SystemName");
String acronym = (String) initCtx.lookup("java:comp/env/SystemAcronym");
String ProductionStatus = (String) initCtx.lookup("java:comp/env/ProductionStatus");
//String csiSchema = (String) initCtx.lookup("java:comp/env/csi-schema-path");
String PathToRoot = (String) initCtx.lookup("java:comp/env/PathToRoot");
DbConn tmpConn = new DbConn("dummy");
String csSchema = tmpConn.getSchemaPath();
String appSchema = (String) initCtx.lookup("java:comp/env/AppSchema");
//Context envContext = (Context) initCtx.lookup("java:comp/env");
//String DeploymentStatus = (String) envContext.lookup("DeploymentStatus");
//String DeploymentStatus = (String) initCtx.lookup("java:comp/env/DeploymentStatus");

%>
