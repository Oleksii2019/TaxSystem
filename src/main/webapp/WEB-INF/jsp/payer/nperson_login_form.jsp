<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="i18n/messages"/>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-3.4.1.js"></script>
    <style>
        table.text  {
            height: 35px;
            width:  100%;
            border-spacing: 0;
            background:#ccc;
            padding:5px;
            margin:5px 0px;
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
            &nbsp;|&nbsp;
            <a href="payer-registration"><fmt:message key="reg.menu"/></a>
        </td>
        <td class="rightcol">
            <a href="${pageContext.request.requestURL.toString()}?lang=en">English</a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.requestURL.toString()}?lang=uk">Ukrainian</a>
        </td>
    </tr>
</table>
<div class="col-md-8 col-md-offset-2">
    <h1><fmt:message key="nperson.login.form"/></h1>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${inputMistake ne null}">
                <h3 id="message-title" style="color:#ff0000"><fmt:message key="message.login.error"/></h3>
            </c:if>
<%--            <h3 id="message-title"><c:out value="${sessionScope.err}" default="DDDDD"/></h3>        ${pageContext.request.contextPath}            --%>
            <h1 class="page-header"><fmt:message key="login.title"/></h1>
            <form style="margin-bottom: 30px" id="main-form" method="get" action=" ${pageContext.request.requestURL.toString()}/login" autocomplete="off" novalidate>
                <div class="form-group">
                    <label  class="control-label" for="exampleInputLogin"><fmt:message key="login.lable"/></label>
                        <input type = "text"
                               name = "login"
                               class="form-control"
                               id="exampleInputLogin"
                               placeholder=<fmt:message key="login.lable"/>
                               required>
                </div>
                <div class="form-group">
                    <label class="control-label" for="exampleInputPassword1"><fmt:message key="password.lable"/></label>
                    <input type = "password"
                           name="password"
                           class="form-control"
                           id="exampleInputPassword1"
                           placeholder=<fmt:message key="password.lable"/>
                           required>
                    <button type="submit" id="sbmBtn" class="btn btn-success" style="margin-top:30px" disabled="disabled" ${ condition ? 'disabled="disabled"' : ''}>
                        <fmt:message key="login.submit.lable"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div>
    <h1 hidden id="messageLoginOK" th:text="#{message.login.ok}">Ok</h1>
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