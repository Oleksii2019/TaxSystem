<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="i18n/messages"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>Lists</title>
    <style>
        table.text  {
            height: 35px;
            width:  100%;
            border-spacing: 0;
            background:#ccc;
            padding:5px;
            margin:5px 0px
        }
        table.text td {
            width: 50%;
            vertical-align: middle;
        }
        td.rightcol {
            text-align: right;
        }
    </style>
</head>
<body>
<table class="text" >
    <tr>
        <td>
            <c:out value="${sessionScope.userName}" default=""/>
            &nbsp;|&nbsp;
            <a href="/tax_system/logout"><fmt:message key="logout.menu"/></a>
        </td>
        <td class="rightcol">
            <a href="${pageContext.request.requestURL.toString()}?lang=en">English</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.requestURL.toString()}?lang=uk">Ukrainian</a>
        </td>
    </tr>
</table>
<div ng-app="users_form" ng-controller="UserCtrl">
    <h1 data-ng-init="getReports()" style="margin-left: 40px"><fmt:message key="payer.report.list.form"/></h1>
    <form action="/payer_report_list/creation" method="post">
        <select id="listofreport" style="margin-left: 40px" name="report" size="5"></select>
        <p>
            <button id="chBtn" name="editBtn" class="btn btn-success" style="margin-top:10px; margin-left: 40px" disabled>
                <fmt:message key="edit.report.button.lable"/>
<%--                Edit report--%>
            </button>
        </p>
    </form>
</div>
<form action="/payer_report_list/creation" method="post">
    <button class="btn btn-success" name="createBtn" style="margin-top:20px; margin-left: 40px">
        <fmt:message key="create.report.button.lable"/>
<%--        Create new report--%>
    </button>
    <button class="btn btn-success" name="complaintBtn" style="margin-top:20px; margin-left: 40px">
        <fmt:message key="make.complaint.button.lable"/>
<%--        Make complaint--%>
    </button>
</form>

<%--<script type="text/javascript" src="/js/payer_rep_list_form.js"></script>--%>
</body>
</html>