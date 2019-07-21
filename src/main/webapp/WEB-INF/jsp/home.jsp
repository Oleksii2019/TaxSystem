<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="i18n/messages"/>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
<table class="text">
    <tr>
        <td>
        </td>
        <td class="rightcol">
            <a href="${pageContext.request.requestURL.toString()}?lang=en">English</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.requestURL.toString()}?lang=uk">Ukrainian</a>
        </td>
    </tr>
</table>
<div class="col-md-8 col-md-offset-2">
    <h1 style="margin-right:0px" th:text="#{task.name}"> <fmt:message key="task.name"/></h1>
</div>
<form action="${pageContext.request.contextPath}/juridical-person-login">
    <button class="btn btn-success" style="margin-top:30px; margin-left:40px" th:text="#{to.jpersonlogin.button.lable}">
        <fmt:message key="to.jpersonlogin.button.lable"/>
<%--        Juridical person login--%>
    </button>
</form>
<form action="${pageContext.request.contextPath}/person-login">
    <button class="btn btn-success" style="margin-top:30px; margin-left:40px" th:text="#{to.npersonlogin.button.lable}">
        <fmt:message key="to.npersonlogin.button.lable"/>
<%--        Natural person login--%>
    </button>
</form>
<form action="${pageContext.request.contextPath}/officer-login">
    <button class="btn btn-success" style="margin-top:30px; margin-left:40px" th:text="#{to.officerlogin.button.lable}">
        <fmt:message key="to.officerlogin.button.lable"/>
<%--        Tax officer login--%>
    </button>
</form>
</body>
</html>