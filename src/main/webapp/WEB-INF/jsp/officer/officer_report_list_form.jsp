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
            <a href="/logout" th:text="#{logout.menu}">Logout</a>
        </td>
        <td class="rightcol">
            <a href="${pageContext.request.requestURL.toString()}?lang=en">English</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.requestURL.toString()}?lang=uk">Ukrainian</a>
        </td>
    </tr>
</table>
<div>
    <h1 style="margin-left: 40px"><fmt:message key="payers.report.list.form"/></h1>
    <form action="/officer_report_list/create" method="post">
        <select id="listofreport" style="margin-left: 40px" name="report" size="5"></select>
        <p>
            <button id="accBtn" class="btn btn-success" name="accBtn" style="margin-top:10px; margin-left: 40px" disabled>
                <fmt:message key="accept.report.button.lable"/>
<%--                Accept report--%>
            </button>
        </p>
        <textarea style="margin-top:20px; margin-left: 40px" name="reclText" th:attr="placeholder=#{reclamation.text.lable}"></textarea>
        <p>
            <button id="reclBtn" class="btn btn-success" name="reclBtn" style="margin-top:10px; margin-left: 40px" disabled>
                <fmt:message key="send.reclamation.button.lable"/>
<%--                Send reclamation--%>
            </button>
        </p>
    </form>
</div>
<script type="text/javascript" src="/js/officer_rep_list_form.js"></script>
</body>
</html>