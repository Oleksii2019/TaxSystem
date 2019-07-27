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
<div>
    <h1 style="margin-left: 40px"><fmt:message key="payers.report.list.form"/></h1>
    <form action="${pageContext.request.requestURL.toString()}/make-report" method="post">
        <select id="listofreport" style="margin-left: 40px" name="selReport" size="5" >
            <c:forEach items="${report}" var="i">
                <c:if test="${i.assessed eq true}">
                    <option value="${i.payerID}S${i.creationTime}"><fmt:message key="report.from.words"/> ${i.payerName} ${i.creationTime} <fmt:message key="assessed.word"/></option>
                </c:if>
                <c:if test="${i.assessed eq false}">
                    <option value="${i.payerID}S${i.creationTime}"><fmt:message key="report.from.words"/> ${i.payerName} ${i.creationTime} <fmt:message key="not.assessed.word"/></option>
                </c:if>
            </c:forEach>
        </select>
        <p>
            <button id="accBtn" class="btn btn-success" name="accBtn" style="margin-top:10px; margin-left: 40px" disabled>
                <fmt:message key="accept.report.button.lable"/>
<%--                Accept report--%>
            </button>
        </p>
        <textarea rows="3" cols="45" style="margin-top:20px; margin-left: 40px" name="reclText" placeholder="<fmt:message key="reclamation.text.lable"/>"></textarea>
        <p>
            <button id="reclBtn" class="btn btn-success" name="reclBtn" style="margin-top:10px; margin-left: 40px" disabled>
                <fmt:message key="send.reclamation.button.lable"/>
<%--                Send reclamation--%>
            </button>
        </p>
    </form>
</div>
<script type="text/javascript">

    var select = document.getElementById("listofreport");
    var button1 = document.getElementById("accBtn");
    var button2 = document.getElementById("reclBtn");

    (function() {
        select.addEventListener("change", function(e) {
            button1.disabled = false;
            button2.disabled = false;
        });
    })();

</script>
</body>
</html>