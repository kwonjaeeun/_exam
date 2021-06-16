<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>main</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>선택</h1>
    <form action="/result" method="post">
        year:
        <select name="deal_year">
            <c:forEach begin="2000" end="2020" var="year">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>
        month:
        <select name="deal_month">
            <c:forEach begin="1" end="12" var="month">
                <option value="${month}"> ${month}</option>
            </c:forEach>
        </select>
        <hr>
        Daegu:
        <select name="ex_cd">
            <c:forEach var="item" items="${locationList}">
                <option value="${item.ex_cd}">${item.nm}</option>
            </c:forEach>
        </select>

        <input type="submit" value="검색">
    </form>
</body>
</html>
