<%-- 
    Document   : adminPage
    Created on : Jul 10, 2019, 1:25:12 AM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/adminpage.css"/>
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/adminpage.css"/>
    </head>
    <body>
        <script>
            var films = '${requestScope.INCOMMING}';
            // console.log(films);
        </script>
        <c:set var="report" value="${requestScope.REPORT}"/>
        <c:set var="admin" value="${sessionScope.ADMIN}"/>
        <c:if test="${empty admin}">
        <jsp:forward page="adminLogin.html"></jsp:forward>
        </c:if>
        <script type="text/javascript" src="js/adminPage.js"></script>
        <header><div class="admin-info">Hello, ${sessionScope.ADMIN.name} <a href="adminPage.jsp">HOME</a></div>
            <div class="logout" onclick="logOut()">LogOut</div></header>
        <div class="admin-content-wrapper">
            <section class="" style="width: 70%">
                <div class="button-group">
                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Make recommandation Defailt</label>
                            <button type="submit" name="adminBtn" value="recommand">START</button>
                        </form>
                    </div>
<!--                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Start Crawl phim33.com</label>
                            <button type="submit" name="adminBtn" value="phim33Crawl">START</button>
                        </form>
                    </div>-->
<!--                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Start validate phim33.com</label>
                            <button type="submit" name="adminBtn"  value="phim33validate">START</button>
                        </form>
                    </div>-->
<!--                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Start Crawl khoai.tv</label>
                            <button type="submit" name="adminBtn" value="khoaitvCrawl">START</button>
                        </form>
                    </div>-->
<!--                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Start validate khoai.tv</label>
                            <button type="submit" name="adminBtn"  value="khoaitvValidate">START</button>
                        </form>
                    </div>-->
                    <div class="btn-container ">
                        <form action="CrawlServlet">
                            <label>CRAWLER MASTER</label>
                            <button type="submit" name="adminBtn"  value="masterCrawl" class="warming">RUN</button>
                        </form>
                    </div>
                    <div class="btn-container btn-warming">
                        <form action="CrawlServlet">
                            <label>Clear Database</label>
                            <button type="submit" name="adminBtn"  value="clearDB" class="warming">CLEAR</button>
                        </form>
                    </div>
                </div>
            </section>
            <c:if test="${not empty report}">
                <section class='report-container'>

                    <h3>Crawling result</h3>
                    <ul class="ul-report">
                        <li>TOTAL CRAWLED: <span>${report.totalCrawled}</span></li>
                        <li>TOTAL VALID DATA: <span>${report.totalValid}</span></li>
                        <li>TOTAL INVALID DATA: <span>${report.totalInvalid}</span></li>
                        <li>TOTAL Insert Temp: <span>${report.totalInsetTemp}</span></li>
                    </ul>
                </section>
            </c:if>
            <c:set var="INCOMMING" value="${requestScope.INCOMMING}"/>
            <c:set var="WILLUPDATE" value="${requestScope.WILLUPDATE}"/>
            <c:set var="DISMOUNTED" value="${requestScope.DISMOUNTED}"/>
            <c:if test="${INCOMMING != null or WILLUPDATE!= null or DISMOUNTED != null}">

                <section class='report-container' style="z-index: 10">
                    <h3>Handling Crawled Data Result</h3>
                    <ul class="ul-report">
                        <li>Tổng dữ Liệu bị quá hạn: <span>${requestScope.DISMOUNTED}</span></li>
                        <li>Tổng dữ liệu Cũ trùng với dữ liệu mới: <span>${requestScope.WILLUPDATE}</span></li>
                        <li>Tổng dữ liệu mới hoàn toàn: <span>${requestScope.INCOMMING}</span></li>
                    </ul>
                </section>
            </c:if>
        </div>
        <div class="admin-content-wrapper">
            <section class="" style="width: 70%">
                <div class="button-group">
                    <div class="btn-container">
                        <form action="CrawlServlet">
                            <label>Handling Crawled Data</label>
                            <button type="submit" name="adminBtn" value="handlingNewData">START</button>
                        </form>
                    </div>
                      <div class="btn-container">
                        <form action="quiz.jsp">
                            <label>Quiz management</label>
                            <button type="submit">Navigate</button>
                        </form>
                    </div>
            </section>
         
            <c:if test="${INCOMMING != null or WILLUPDATE!= null or DISMOUNTED != null}">
                <div class="overlay"></div>
                <div class="handle-result-option" >
                    <div class="button-group">
                        <div class="btn-container" >
                            <form action="CrawlServlet">
                                <div class="form-input">  
                                    <label>Handling this Data</label>
                                    Xóa dữ liệu quá hạn: <input type="checkbox" name="handleResultData" value="del" />
                                </div>
                                <div class="form-input">  
                                    <label>Thêm Dữ Liệu Mới:</label>
                                    <input type="checkbox" name="handleResultData" value="add" />
                                </div>
                                <div class="form-input"> 
                                    <label>Chấp Nhận Update dữ liệu cũ:</label>
                                    <input type="checkbox" name="handleResultData" value="update" />
                                </div>
                                <button type="submit" name="adminBtn" value="adminHandlingResultData">START</button>
                                <button type="submit" name="" value=""><a href="adminPage.jsp">Cancel</a></button>
                            </form>
                        </div>
                    </div>
                </c:if>



            </div>

    </body>
</html>
