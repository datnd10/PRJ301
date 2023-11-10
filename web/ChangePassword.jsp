<%-- 
    Document   : ChangePassword
    Created on : Oct 5, 2023, 11:00:29 AM
    Author     : Dac Dat
--%>
<%@page import="model.User"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User user = (User) session.getAttribute("account");
        if(user == null) {
        response.sendRedirect("login");
        return;
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            form i {
                float: right;
                margin-left: -25px;
                margin-top: -25px;
                position: relative;
                z-index: 2;
            }
            .dropdown-toggle::after {
                content: none;
            }
            .dropdown-toggle {
                background-color: transparent;
            }
        </style>
    </head>
    <body style="background-color: #e8e8e8">
        <%@include file="Header.jsp" %>   
        <div class="container mt-5 bg-white p-4" >
            <h1 style="margin-bottom: 40px" class="text-warning">Change Password</h1>
            <form action="change-password" method="post">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputCurrentPassword4" style="font-weight: bold">Current Password: <c:if test="${requestScope.errorcurrentpass!=null}"><label class="text-danger">${requestScope.errorcurrentpass}</label></c:if></label>
                            <input type="password" class="form-control password" id="inputCurrentPassword4" placeholder="Current Password"  name="currentPassword">
                            <i class="fas fa-eye" id="togglePassword"></i>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="inputNewPassword4" style="font-weight: bold">New Password: <c:if test="${requestScope.errornewpass!=null}"><label class="text-danger">${requestScope.errornewpass}</label></c:if></label>
                            <input type="password" class="form-control password1" id="inputNewPassword4" placeholder="New Password" " name="newPassword">
                            <i class="fas fa-eye" id="togglePassword1"></i>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="inputConfirmPassword4" style="font-weight: bold">Confirm New Password: <c:if test="${requestScope.errorconfirmnewpass!=null}"><label class="text-danger">${requestScope.errorconfirmnewpass}</label></c:if></label>
                            <input type="password" class="form-control password2" id="inputConfirmPassword4" placeholder="Confirm New Password "  name="confirmPassword">
                            <i class="fas fa-eye" id="togglePassword2"></i>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            function togglePasswordVisibility(toggleButton, passwordInput) {
                toggleButton.addEventListener("click", function () {
                    // Toggle the type attribute
                    const type = passwordInput.getAttribute("type") === "password" ? "text" : "password";
                    passwordInput.setAttribute("type", type);
                    // Toggle the icon
                    toggleButton.classList.toggle("fa-eye-slash");
                });
            }

            const togglePassword = document.querySelector("#togglePassword");
            const password = document.querySelector(".password");
            togglePasswordVisibility(togglePassword, password);

            const togglePassword1 = document.querySelector("#togglePassword1");
            const password1 = document.querySelector(".password1");
            togglePasswordVisibility(togglePassword1, password1);

            const togglePassword2 = document.querySelector("#togglePassword2");
            const password2 = document.querySelector(".password2");
            togglePasswordVisibility(togglePassword2, password2);

        </script>
    </body>
</html>
