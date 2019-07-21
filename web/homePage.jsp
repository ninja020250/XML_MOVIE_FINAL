<%-- 
    Document   : homePage
    Created on : Jul 10, 2019, 4:00:57 PM
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
        <title>What movie I should watch tonight</title>
        <link rel="stylesheet" href="./css/homePage.css"/>
        <link rel="stylesheet" href="./css/layout.css"/>
    </head>
    <body>
        <script>
            var films = '${sessionScope.FILMS}';
//            var cate = '${sessionScope.CATEGORY}';
////            console.log(cate);
            var quizs = '${sessionScope.QUIZS}';
//            console.log(quizs);
        </script>
        <script>
            var films = '${sessionScope.FILMS}';
//            console.log(films);
//            var history = '${sessionScope.USER.history}'
//            console.log(history);
        </script>
        <input type="hidden" id="history" value="${sessionScope.USER.history}"/>
        <script type="text/javascript" src="./js/homePage.js"></script>
        <c:set var="filmRecommend" value="${sessionScope.RECOMMANDATION}"/>
        <c:set var="categoryXML" value="${sessionScope.CATEGORY}"/>
        <header><div class="admin-info"><a href="homePage.jsp" style="color:#ffffff">HOME</a></div>
            <div class="header-logo">
                HÔM NAY XEM GÌ ?
            </div>
            <c:if test="${empty sessionScope.USER}">
                <div class="authen-container">
                    <div class="log" onclick="signUp()">Sign up</div>
                    <div class="log" onclick="signIn()">Sign In</div>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.USER}">
                <div class="log" onclick="logout('${sessionScope.USER.username}')">${sessionScope.USER.name},<span style="font-weight: bold">Logout</span></div>
            </c:if>
        </header>
        <section class="banner">
            <button class="main-button" onclick="startQuiz()">
                HÔM NAY XEM GÌ? <br/>
                CLICK NGAY ĐỂ BIẾT
            </button>
        </section>

        <c:if test="${not empty RECOMMANDATION}">
            <div class="film-recommend">
                <div class="filter-group">
                    <c:import var="categoryXSL" url="/styleSheet/category.xsl" charEncoding="utf-8"/>
                    <x:transform doc="${categoryXML}"  xslt="${categoryXSL}" >
                    </x:transform>  
                    <div class="searchBox">
                        <label></label>
                        <div >
                            <input type="text" placeholder="SEARCH HERE.."name="txtsearchRecommendValue" id="searchBox"  onkeypress="return  onPressSearch(event)"/>
                            <button class="btn" type="button" onclick="searchData()">Search</button>
                        </div>
                    </div>  
                </div>
                <section id="searchResult">

                </section>
                <div id="pagi-container"></div>
                 <section id="specialRecommand">

                </section>
                <c:import var="xsl" url="/styleSheet/listFilm.xsl" charEncoding="utf-8"/>
                <x:transform doc="${filmRecommend}"  xslt="${xsl}" >
                    <%--<x:param name = "searchValue" value = "${searchValue}"/>--%>
                    <%--<x:param name = "brandValue" value = "${brandValue}"/>--%>
                </x:transform>  
            </div>
        </c:if>
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
        <div class="modal-container " id="quiz-modal">
            <div class="modal-overlay"></div>
            <div class="modal-dialog" id="modal-container">
                <h3 id="question">question</h3>
                <div class='answer-container'>
                    <button class="btn btn-default" id="answer1">answer1</button>
                    <button class="btn btn-default" id="answer2">answer2</button>
                    <button class="btn btn-default" id="answer3">answer3</button>
                    <button class="btn btn-default" id="answer4">answer4</button>
                </div>
            </div>
        </div>
        <div class="modal-container " id="filmResult">
            <div class="modal-overlay"></div>
            <div class="modal-dialog" id="filmResultDialog">
                <h3>sau khi phân tích, chúng tôi nghĩ bạn nên xem phim này nè. rất phù hợp với bạn</h3>
                <div class="film-container-popup">
                    <!--                    <img src="imgs/products/phephimalita-thien-than-chien-binh-2019.jpg">
                                        <div class="film-content-popup">
                                            <div >Thien than chien binh</div>
                                            <div >thời lượng:  a, b , c, d</div>
                                            <div >thể loại: a, b , c, d</div>
                                            <div >diễn viên:  a, b , c, d</div>
                                        </div>-->
                </div>
            </div>
        </div>
    </body>
</html>
