<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result</title>
</head>
<body>
    <table>
        <tr>
            <th>법정동</th>
            <th>지번</th>
            <th>아파트명</th>
            <th>거래금액</th>
            <th>건축년도</th>
            <th>계약년도</th>
            <th>계약월</th>
            <th>계약일</th>
            <th>전용면적</th>
            <th>층</th>
        </tr>
        <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                 <td>${data.in_cd}</td>
                 <td>${data.dong}</td>
                 <td>${data.jibun}</td>
                 <td>${data.apartment_name}</td>
                 <td>${data.deal_amount}</td>
                 <td>${data.build_year}</td>
                 <td>${data.deal_year}</td>
                 <td>${data.deal_month}</td>
                 <td>${data.deal_day}</td>
                 <td>${data.area_for_exclusive_use}</td>
                 <td>${data.flr}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
