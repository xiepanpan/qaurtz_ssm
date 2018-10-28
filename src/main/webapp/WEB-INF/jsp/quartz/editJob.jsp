<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: xpp
  Date: 2018/10/27
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑任务</title>
</head>
<body style="background: beige;margin: 171px 600px 200px;">
<from:form action="${pageContext.request.contextPath}/quartz.edit" modelAttribute="triggerEntity" method="post">
    <span>编辑触发器Trigger</span>
    <hr/>
    <table>
        <tr>
            <td>时间表达式<br>cronExpression</td>
            <td><form:input type="text" path="cronExpression" value="${triggerEntity.cronExpression}"/></td>
        </tr>
        <tr>
            <td>任务类名<br>Class</td>
            <td><form:input type="text" path="clazz" value="${triggerEntity.clazz}"/></td>
        </tr>
        <tr>
            <td>任务名<br>jobName</td>
            <td><form:input type="text" path="jobName" value="${triggerEntity.jobName}"/></td>
        </tr>
        <tr>
            <td>任务组名<br>JobGroupName</td>
            <td><form:input type="text" path="jobGroupName" value="${triggerEntity.jobGroupName}"/></td>
        </tr>
        <tr>
            <td>触发器名<br>TriggerName</td>
            <td><form:input type="text" path="triggerName" value="${triggerEntity.triggerName}"/></td>
        </tr>
        <tr>
            <td>触发器组名TriggerGroupName</td>
            <td><form:input type="text" path="triggerGroupName" value="${triggerEntity.triggerGroupName}"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary" style="border: 0;background-color: #428bca;">提交</button>
                <button class="cancel btn btn-default" style="border: 0;background-color: #ffffff;">取消</button>
            </td>
        </tr>
    </table>
</from:form>
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
            window.history.back();
        });
    });
</script>