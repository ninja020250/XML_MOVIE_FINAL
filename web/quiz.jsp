<%-- 
    Document   : quiz
    Created on : Jul 19, 2019, 2:41:55 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/quiz.css"/>
    </head>
    <body>
        <header><div class="admin-info">Hello, ${sessionScope.ADMIN.name} <a href="adminPage.jsp">HOME</a>
                <form action="QuizServlet"><button type="submit" name="quizBtn" value="viewQuiz">View Quizs</button></form></div>
            <div class="logout" onclick="logOut()">LogOut</div></header>
        <h1>Quiz management</h1>
        <form class='addQuiz-container' action="QuizServlet">
            <h3>INPUT QUESTION & ANSWERS</h3>
            <div class="form-container">
                <label>Question: </label><input type="text" name="txtQuestion" style="width: 100%" value="${param.txtQuestion}">
            </div>

            <div class="answer-group">
                <div class="form-container">
                    <label>ANSWER 1: </label><input type="text" name="txtAnswer1" value="${param.txtAnswer1}">
                    <input type="text" placeholder="keywords" name="keyword1" value="${param.keyword1}">
                </div>
                <div class="form-container">
                    <label>ANSWER 2: </label><input type="text" name="txtAnswer2"  value="${param.txtAnswer2}">
                    <input type="text" placeholder="keywords" name="keyword2"  value="${param.keyword2}">
                </div>
                <div class="form-container">
                    <label>ANSWER 3: </label><input type="text" name="txtAnswer3" value="${param.txtAnswer3}">
                    <input type="text" placeholder="keywords" name="keyword3" value="${param.keyword3}">
                </div>
                <div class="form-container">
                    <label>ANSWER 4: </label><input type="text" name="txtAnswer4" value="${param.txtAnswer4}">
                    <input type="text" placeholder="keywords" name="keyword4" value="${param.keyword4}">
                </div>
            </div>
            <button name="quizBtn" value="addQuiz">Add Quiz</button>

        </form>
        <c:set var="quizs" value="${requestScope.LISTQUIZS}"/>
        <script>
            var i = '${requestScope.LISTQUIZS}';
            console.log(i);
        </script>
        <c:if test="${not empty quizs}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Question</th>
                        <th>Answers</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="quiz" items="${quizs}">
                    <form action="QuizServlet">
                        <tr>
                            <td>   ${quiz.quizID} 
                                <input type="hidden" name="txtQuizID" value="${quiz.quizID} " />
                            </td>
                            <td>   ${quiz.question}</td>
                            <td>
                                <c:forEach var="a" items="${quiz.answer}">
                                    ${a.content} -  ${a.keyword}<br/><br/>
                                </c:forEach>
                            </td>
                            <td>
                                <button type="submit" name="quizBtn" value="delQuiz">DELETE</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>

    </c:if>
</body>
</html>
