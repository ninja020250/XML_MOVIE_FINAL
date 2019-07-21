<%-- 
    Document   : userSignUp
    Created on : Jul 20, 2019, 4:12:09 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{
                background-image: url("imgs/signUp.jpg");
                font-family: "Arial";
                background-size: cover;
                background-repeat: no-repeat;
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
                justify-content: space-between;
                margin-top: 5px;
                margin-bottom: 5px;
                display: flex;
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
            .error-container{
                color: red;
                display: flex;
                flex-direction: column;
            }
        </style>
    </head>
    <body>
        <script type="text/javascript" src="js/user.js"></script>
        <div class="loginContainer">
            <form action="UserLoginServlet" method="POST" id="signUpForm" >
                <h1>SIGN UP</h1>
                <div class='error-container'>
                    <span>${requestScope.SIGNUP_ERROR}</span>
                    <span id="usernameError"></span>
                    <span id="nameError"></span>
                    <span id="passwordError"></span>
                    <span id="mainError"></span>
                    <span id="rePasswordError"></span>
                </div>
                <div class="form-control">  <label>Username: </label><input type="text" name="txtUsername" value="" id="txtUsername"/></div>
                <div class="form-control">  <label>Name: </label><input type="text" name="txtName" value="" id="txtName"/></div>
                <div class="form-control">  <label>Password: </label><input type="password" name="txtPassword" value="" id="txtPassword"/></div>
                <div class="form-control">  <label>Re-Password: </label><input type="password" name="txtRePassword" value="" id="txtRePassword"/></div>
                <button  type="button" onclick="submitSignUp()">Sign Up</button>
            </form>
        </div>
    </body>
</html>
