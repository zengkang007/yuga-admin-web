<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Register confirm</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	</script>
</head>
<body>
<ul class="breadcrumb">
    <li>
        <a href="${ctx}/">Home</a> <span class="divider">/</span>
    </li>
    <li class="active">Register</li>
</ul>
<div class="hero-unit">
    <h1>Success</h1>
    <p>You have register your message successfull, please relogin.</p>
    <p>
        <a href="${ctx}/" class="btn btn-primary btn-large">
            Back
        </a>
    </p>
</div>
</body>
</html>