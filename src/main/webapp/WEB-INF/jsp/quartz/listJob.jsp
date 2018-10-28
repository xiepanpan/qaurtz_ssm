<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: xpp
  Date: 2018/10/26
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任务列表</title>
    <style type="text/css">
        table {
            width: 90%;
            margin: 0 auto;
            text-align: center;
            border-right: 1px solid #999;
            border-bottom: 1px solid #999;
        }

        table tr {
        }

        table td, table th {
            border-left: 1px solid #999;
            border-top: 1px solid #999;
        }

        .btn {
            border: 0;
            border-radius: 4px;
            background-color: #428bca;
            color: #fff;
            padding: 3px 5px;
            cursor: pointer;
        }
    </style>
</head>
<body style="background: beige;">
<div style="text-align: center;">
    <span>欢迎${username}登录！</span>
    <span><a href="${pageContext.request.contextPath}/user/logout">退出</a></span>
</div>
<h2 style="text-align: center">定时任务后台管理系统</h2>
<table id="table_report" class="table " cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th class="center">任务组名称</th>
        <th class="center">定时任务名称</th>
        <th class="center">触发器组名称</th>
        <th class="center">触发器名称</th>
        <th class="center">时间表达式</th>
        <th class="center">上次运行时间</th>
        <th class="center">下次运行时间</th>
        <th class="center">任务状态</th>
        <th class="center">开始时间</th>
        <th class="center">结束时间</th>
        <th class="center">任务类名</th>
        <th class="center" width="15%">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty jobEntities && jobEntities.size()>0}">
            <c:forEach items="${jobEntities}" var="jobEntity">
                <tr>
                    <td class="center" style="width: auto;">${jobEntity.jobGroup}</td>
                    <td class="center" style="width: auto;">${jobEntity.jobName}</td>
                    <td class="center" style="width: auto;">${jobEntity.triggerGroupName}</td>
                    <td class="center" style="width: auto;">${jobEntity.triggerName}</td>
                    <td class="center" style="width: auto;">${jobEntity.cronExpression}</td>
                    <td class="center" style="width: auto;">
                        <fmt:formatDate value="${jobEntity.previousFireTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td class="center" style="width: auto;">
                        <fmt:formatDate value="${jobEntity.nextFireTime}" pattern="yyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td class="center" style="width: auto;">
                        <c:if test="${jobEntity.jobStatus=='NONE'}">
                            <span class="lable">未知</span>
                        </c:if>
                        <c:if test="${jobEntity.jobStatus=='NORMAL'}">
                            <span class="lable label-success arrowed">正常</span>
                        </c:if>
                        <c:if test="${jobEntity.jobStatus=='PAUSED'}">
                            <span class="lable label-warning">暂停</span>
                        </c:if>
                        <c:if test="${jobEntity.jobStatus=='COMPLETE'}">
                            <span class="lable label-important arrowed-in">完成</span>
                        </c:if>
                        <c:if test="${jobEntity.jobStatus=='ERROR'}">
                            <span class="lable label-info arrowed-in-right arrowed">错误</span>
                        </c:if>
                        <c:if test="${jobEntity.jobStatus=='BLOCKED'}">
                            <span class="lable label-inverse">锁定</span>
                        </c:if>
                    </td>
                    <td class="center" style="width: auto;">
                        <fmt:formatDate value="${jobEntity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td class="center" style="width: auto;">
                         <fmt:formatDate value="${jobEntity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td class="center" style="width: auto;">${jobEntity.jobClass}</td>
                    <td class="center" style="width: auto;">
                        <a class="btn btn-minier btn-success" onclick="edit(${jobEntity.jobName},${jobEntity.jobGroup})">编辑</a>
                        <a class="btn btn-minier btn-warning">暂停</a>
                        <a class="btn btn-minier btn-purple">恢复</a>
                        <a class="btn btn-minier btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr><td colspan="100" class="center">没有相关数据</td></tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
<div style="width: 90%;margin: 0 auto;text-align: center;margin-top: 25px;">
    <button type="button" onclick="add();" class="btn">新增任务</button>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var url = "${pageContext.request.contextPath}";

    function add() {
        window.location.href = url + "/quartz/toAdd";
    }
    function edit(jobName,jobGroup) {
        window.location.href = url + "/quartz/toEdit?jobName="+jobName+"&jobGroup="+jobGroup;
    }
</script>
</body>
</html>
