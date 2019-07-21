<%-- 
    Document   : userLogin.jsp
    Created on : Jul 19, 2019, 10:44:54 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                background-image: url("imgs/adminLoginPage.jpg");
                font-family: "Arial";
            }
          
            form{
                background: rgba(255,255,255,0.8);
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                display: flex;
                flex-direction: column;
                border-radius: 15px;
                box-shadow: 0 0 10px 5px rgba(221, 221, 221, 1);
                padding: 35px;
            }
            button{
                padding: .5rem .5rem;
                background-color: #3498db;
                color: #ffffff;
                font-weight: bold;
            }
            .form-control{

                margin: 15px auto;
            }
            .form-control label{
                margin-right: 5px;
            }
            .form-control input{
                margin-left: 5px;
            }
            h1{
                text-align: center;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <div class="loginContainer">
            <form action="UserLoginServlet" method="POST" >
                <h1>Login</h1>

                <div class="form-control">  <label>Username: </label><input type="text" name="txtUsername" value="" /></div>
                <div class="form-control">  <label>Password: </label><input type="password" name="txtPassword" value="" /></div>
                <c:if test="${not empty requestScope.MESSAGE}"><span style="color: #e74c3c">${requestScope.MESSAGE}</span></c:if>
                <button name="btnUser" type="submit" value="userLogin">Login</button>
            </form>
        </div>
    </body>
</html>
