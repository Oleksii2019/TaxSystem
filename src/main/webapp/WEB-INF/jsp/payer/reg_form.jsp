<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="i18n/messages"/>
<head>
    <meta charset="UTF-8">
    <title>registration form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
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
            <a href="home"><fmt:message key="home.menu"/></a>
        </td>
        <td class="rightcol">
            <a href="${pageContext.request.requestURL.toString()}?lang=en">English</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.requestURL.toString()}?lang=uk">Ukrainian</a>
        </td>
    </tr>
</table>
<div class="col-md-8 col-md-offset-2">
    <h1><fmt:message key="registration.form"/></h1>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${inputMistake ne null}">
                <h3 id="message-title" style="color:#ff0000"><fmt:message key="message.registration.error"/></h3>
            </c:if>
            <h2 class="page-header"><fmt:message key="registration.inscription"/></h2>
            <form style="margin-bottom: 30px" id="main-form" method="get" action=" ${pageContext.request.requestURL.toString()}/login" autocomplete="off" novalidate>
                <div class="form-group">
                    <label id="inputNameLabel" for="exampleInputName"><fmt:message key="name.lable"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputName"
                           placeholder="Name" <fmt:message key="name.lable"/>
                           required>
                </div>
                <div class="form-group">
                    <label id="inputLoginLabel" for="exampleInputLogin"><fmt:message key="login.lable"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputLogin"
                           placeholder="Login" <fmt:message key="login.lable"/>
                           required>
                </div>
                <div class="form-group">
                    <label id="inputPasswordLabel" for="exampleInputPassword"><fmt:message key="password.lable"/></label>
                    <input type="text"
                           class="form-control"
                           id="exampleInputPassword"
                           placeholder="Password" <fmt:message key="password.lable"/>
                           required>
                </div>
                <button type="submit" id="sbmBtn" class="btn btn-success" style="margin-top:30px" disabled="disabled" ${ condition ? 'disabled="disabled"' : ''}>
                    <fmt:message key="submit.lable"/>
<%--                    Submit--%>
                </button>
            </form>
        </div>
    </div>
</div>
<div>
    <h1 hidden id="messageOK" th:text="#{message.registration.ok}">Ok</h1>
    <h1 hidden id="messageDbErr" th:text="#{message.registration.db.error}">Error</h1>
</div>
<script type="text/javascript">
    var select = document.getElementById("main-form");

    (function() {
        var button = document.getElementById("sbmBtn");
        var mess = document.getElementById("message-title");
        select.addEventListener("change", function(e) {
            button.disabled = false;
            mess.hidden = true;

        });
    })();
</script>
</body>
</html>