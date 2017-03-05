<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>Bidding Manager</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('submitting ...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("error input ");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active">Bidding Confirm</li>
</ul><br/>
<form:form id="inputForm" modelAttribute="bidding" action="${ctx}/cst/bidding/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="consultantId"/>
    <form:hidden path="currentBid"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">Current Bid：</label>
        <div class="controls">
            <label><font size="4"><b>$ ${bidding.currentBid}</b></font></label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">Max Bid：</label>
        <div class="controls">
            <form:input path="maxBid" min="${bidding.currentBid+1}" htmlEscape="false" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
            <span class="help-inline">Enter US $ ${bidding.currentBid} more </span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnConfirm" class="btn" type="submit" value="Confirm"/>
    </div>
</form:form>
</body>
</html>