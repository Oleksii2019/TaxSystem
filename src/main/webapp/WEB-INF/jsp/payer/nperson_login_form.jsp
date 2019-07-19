<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Login form's Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <style>
        table.text  {
            height: 35px;
            width:  100%; /* Ширина таблицы */
            border-spacing: 0; /* Расстояние между ячейками */
            background:#ccc;
            padding:5px;
            margin:5px 0px
        }
        table.text td {
            width: 50%; /* Ширина ячеек */
            vertical-align: middle; /* Выравнивание по верхнему краю */
        }
        td.rightcol { /* Правая ячейка */
            text-align: right; /* Выравнивание по правому краю */
        }
    </style>
</head>
<body ng-app="login_form" ng-controller="AppCtrl">
<table class="text">
    <tr>
        <td>
            <a href="/tax_system" th:text="#{home.menu}">Home</a>
            &nbsp;|&nbsp;
            <a href="/reg_form" th:text="#{reg.menu}">Registration</a>
        </td>
        <td class="rightcol">
            <a th:href="@{/login_natural_person?lang=en}">English</a>
            &nbsp;|&nbsp;
            <a th:href="@{/login_natural_person?lang=uk}">Ukrainian</a>
        </td>
    </tr>
</table>
<div class="col-md-8 col-md-offset-2">
    <h1 th:text="#{nperson.login.form}">Login form's Main</h1>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h3 id="resultMessage">{{message}}</h3>
            <h1 class="page-header" th:text="#{login.title}">Login</h1>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">
                <div class="form-group">
                    <label id="exampleInputLoginLabel" for="exampleInputLogin1" th:text="#{login.lable}">Login</label>
                    <input type="login"
                           class="form-control"
                           id="exampleInputLogin1"
                           placeholder="Login"
                           required
                           ng-model="auth.login" th:attr="placeholder=#{login.lable}">
                </div>
                <div class="form-group">
                    <label id="exampleInputPasswordLabel" class="control-label" for="exampleInputPassword1" th:text="#{password.lable}">Password</label>
                    <input type="password"
                           class="form-control"
                           id="exampleInputPassword1"
                           placeholder="Password"
                           required
                           ng-model="auth.password" th:attr="placeholder=#{password.lable}">
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" ng-disabled="form.$invalid" th:text="#{login.submit.lable}">
                    Submit
                </button>
            </form>
            <form action="/payer_report_list">
                <button id="goBtn" class="btn btn-success" style="margin-top:30px" th:text="#{forward.button.lable}">
                    Next
                </button>
            </form>
        </div>
    </div>
</div>
<div>
    <h1 hidden id="messageLoginOK" th:text="#{message.login.ok}">Ok</h1>
    <h1 hidden id="messageLoginErr" th:text="#{message.login.error}">Error</h1>
</div>
<script type="text/javascript" src="/js/np_log_form.js"></script>
</body>
</html>