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
<form:form id="inputForm" modelAttribute="bidding" action="${ctx}/cst/bidding/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="consultantId"/>
    <form:hidden path="currentBid"/>
    <br><br>
    <div class="control-group">
        <label class="control-label">Your Bid：</label>
        <div class="controls">
            <form:input path="maxBid" min="${bidding.floorPrice}" htmlEscape="false" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
            <span class="help-inline">Enter US $ ${bidding.floorPrice} more </span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnConfirm" class="btn" type="submit" value="Confirm"/>
    </div>
</form:form>
</body>
</html>