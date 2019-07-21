<%-- 
    Document   : filmDetail
    Created on : Jul 12, 2019, 2:25:08 PM
    Author     : nhatc
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Film Detail</title>
        <link rel="stylesheet" href="./css/layout.css"/>
        <link rel="stylesheet" href="./css/filmDetail.css"/>
        <style>  

        </style>
    </head>
    <body>
        <script>
            var film = '${requestScope.FILMDETAIL}';
            console.log(film);
        </script>
        <script type="text/javascript" src="./js/filmDetail.js">

        </script>
       <header><div class="admin-info"><a href="homePage.jsp" style="color:#ffffff">HOME</a></div>
            <div class="header-logo">
                 HÔM NAY XEM GÌ ?
            </div>
            <c:if test="${empty sessionScope.USER}">
                <div class="log" onclick="signIn()">Sign In</div>
            </c:if>
            <c:if test="${not empty sessionScope.USER}">
                <div class="log" onclick="logout('${sessionScope.USER.username}')">${sessionScope.USER.name},<span style="font-weight: bold">Logout</span></div>
            </c:if>
        </header>
        <c:set var="film" value="${requestScope.FILMDETAIL}"/>
        <c:if test="${not empty film}">
            <c:import var="filmDetailxsl" url="/styleSheet/filmDetail.xsl" charEncoding="utf-8"/>
            <x:transform doc="${film}"  xslt="${filmDetailxsl}" ></x:transform>  
        </c:if>
        <div class="container">  <h1 style="margin-top: 300px">Những trang web Hiện đang có sẵn Phim này, bạn có thể ghé xem</h1></div>
        <div class="compare-web">
            <c:if test="${not empty film}">
                <c:import var="filmDetailWebCompare" url="/styleSheet/filmDetailWebCompare.xsl" charEncoding="utf-8"/>
                <x:transform doc="${film}"  xslt="${filmDetailWebCompare}" ></x:transform>  
            </c:if>
        </div>
        <footer class="footer">
            <div class="footer-container">
                <h3 class="footer-title">
                    Chúng tôi cung cấp thông tin Film mà bạn muốn dựa trên tâm trạng và cũng như suy nghĩ của bạn hiện tại
                </h3>
                <ul>
                    <li>xem gợi ý phim dựa vào chỉ 5 câu hỏi của chúng tôi, cam đoan phim phù hợp với bạn lúc này</li>
                    <li>search phim muốn xem nếu đã có dự định tên phim sẵn trong đầu</li>
                    <li>dễ dàng biết phim đang được chiếu ở những trang web nào, nhiều sự lựa chọn</li>
                    <li>chọn lọc phim theo nhu cầu, xem đánh giá cũng như giới thiệu film</li>
                </ul>
            </div>
        </footer>
    </body>
</html>
