<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="org.archive.wayback.ResultURIConverter" %>
<%@ page import="org.archive.wayback.core.UIResults" %>
<%@ page import="org.archive.wayback.core.WaybackRequest" %>
<%@ page import="org.archive.wayback.util.StringFormatter" %>
<%
UIResults results = UIResults.extractReplay(request);
String requestDate = results.getResult().getCaptureTimestamp();
String contextPath = results.getURIConverter().makeReplayURI(requestDate,"");
String contextRoot = request.getScheme() + "://" + request.getServerName() + ":" 
  + request.getServerPort() + request.getContextPath();

String jsUrl = contextRoot + "/replay/client-rewrite.js";
%>
<script type="text/javascript">
  var sWayBackCGI = "<%= contextPath %>";
</script>
<script type="text/javascript" src="<%= jsUrl %>" ></script>