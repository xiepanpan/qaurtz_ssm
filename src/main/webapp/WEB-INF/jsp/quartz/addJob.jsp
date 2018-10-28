<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: xpp
  Date: 2018/10/27
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增任务</title>
</head>
<body style="background: beige; margin: 171px 600px 200px;">
<form:form action="${pageContext.request.contextPath}/quartz/add" method="post" modelAttribute="triggerEntity">
    <span>新增Trigger</span>
    <hr/>
    <table>
        <tr>
            <td>时间表达式<br>cronExpression</td>
            <td><form:input type="text" path="cronExpression" value=""/></td>
        </tr>
        <tr>
            <td>任务类名<br>Class</td>
            <td><form:input type="text" path="clazz" value=""/></td>
        </tr>
        <tr>
            <td>任务名<br>jobName</td>
            <td><form:input type="text" path="jobName" value=""/></td>
        </tr>
        <tr>
            <td>任务组名<br>JobGroupName</td>
            <td><form:input type="text" path="jobGroupName" value=""/></td>
        </tr>
        <tr>
            <td>触发器名<br>TriggerName</td>
            <td><form:input type="text" path="triggerName" value=""/></td>
        </tr>
        <tr>
            <td>触发器组名TriggerGroupName</td>
            <td><form:input type="text" path="triggerGroupName" value=""/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary" style="border: 0;background-color: #428bca;">提交</button>
                <button class="cancel btn btn-default" style="border: 0;background-color: #ffffff;">取消</button>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script>
    //上一步操作
    $(document).ready(function () {
        $(".cancel").click(function () {
            window.history.go(-1);
        });
    });
</script>
