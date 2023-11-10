<%-- 
    Document   : Login
    Created on : Oct 3, 2023, 3:55:08 PM
    Author     : Dac Dat
--%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">       
        <style>
            body {
                margin: 0;
                padding: 0;
                background-color: #17a2b8;
                height: 100vh;
            }
            #login .container #login-row #login-column #login-box {
                margin-top: 70px;
                max-width: 600px;
                height: 360px;
                border: 1px solid #9C9C9C;
                background-color: #EAEAEA;
            }
            form i {
                float: right;
                margin-left: -25px;
                margin-top: -25px;
                position: relative;
                z-index: 2;
            }
            #login .container #login-row #login-column #login-box #login-form {
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <div>
            <h3 class="text-center text-white pt-5">Welcome to our shop!</h3>
            <a href="signup" class="text-center text-white pt-2" style="display: block">Don't have an account?</a>
            <a href="home" class="text-center text-white pt-2" style="display: block">Back to Home Page!</a>
        </div>
        <c:set var="cookie" value="${pageContext.request.cookies}"></c:set>
        <div id="login">
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form action="login" method="post">
                                <h3 class="text-center text-info mt-3">Login</h3>
                                <c:if test="${requestScope.error!=null}"><p class="text-center text-danger">${requestScope.error}</p></c:if>
                                <div class="form-group">
                                    <label for="email" class="text-info">Email:</label><br>
                                    <input type="email" name="email" id="email" class="form-control" placeholder="Email" name="email" value="${cookie.cemail.value}">
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control" placeholder="Password" name="password" value="${cookie.cpassword.value}">
                                    <i class="fas fa-eye" id="togglePassword"></i>
                                </div>
                                <div class="form-group">
                                    <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"  ${cookie.cremember !=null?"checked":''}></span></label><br>
                                    <input type="submit" name="submit" class="btn btn-info btn-md col-md-12" value="Login">
                                </div>                          
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script >
            const togglePassword = document.querySelector("#togglePassword");
            const password = document.querySelector("#password");
            togglePassword.addEventListener("click", function () {
            // toggle the type attribute
                const type = password.getAttribute("type") === "password" ? "text" : "password";
                password.setAttribute("type", type);

            // toggle the icon
                this.classList.toggle("fa-eye-slash");
            });
        </script>
    </body>
</html>
