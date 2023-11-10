<%-- 
    Document   : Information
    Created on : Oct 5, 2023, 10:34:31 AM
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
        <c:set var="user" value="${sessionScope.account}"/>
        <div class="container mt-5 bg-white p-3">
            <h1 style="margin-bottom: 40px" class="text-primary">Information</h1>
            <form action="information" method="post">
                <input value="${sessionScope.account.user_id}" name="id" hidden="">
                <input value="${sessionScope.account.password}" name="password" hidden="">
                <input value="${sessionScope.account.role}" name="role" hidden="">
                <input value="${sessionScope.account.create_at}" name="creatat" hidden="">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4" style="font-weight: bold">Email: <c:if test="${requestScope.erroremail!=null}">${requestScope.erroremail}</c:if></label>
                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email" value="${user.email}" name="email">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputUsername4" style="font-weight: bold">Username: <c:if test="${requestScope.errorusername!=null}">${requestScope.errorusername}</c:if></label>
                        <input type="text" class="form-control" id="inputUsername4" placeholder="Username" value="${user.username}"  disabled="">
                        <input type="text" class="form-control" value="${user.username}" name="username" hidden="">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputFullname4" style="font-weight: bold">Fullname</label>
                        <input type="text" class="form-control" id="inputFullname4" placeholder="FullName" value="${user.fullname}" name="fullname">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPhone4" style="font-weight: bold">Phone:<c:if test="${requestScope.errorphone!=null}">${requestScope.errorphone}</c:if></label>
                        <input type="text" class="form-control" id="inputPhone4" placeholder="Phone" value="${user.phone}" name="phone">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputAddress" style="font-weight: bold">Address</label>
                        <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" value="${user.address}" name="address">
                    </div>               
                </div>
                <div class="form-group">
                    <label for="exampleFormControlFile1" style="font-weight: bold">Avatar</label>
                    <input type="file" class="form-control-file" id="exampleFormControlFile1" accept="image/*" onchange="loadFile(event)" name="avatar"  >
                    <img src="images/${user.avatar}" id="output" style="width: 100px;height: 100px;object-fit: cover"/>                   
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
                        var loadFile = function (event) {
                            var output = document.getElementById('output');
                            output.src = URL.createObjectURL(event.target.files[0]);
                            output.onload = function () {
                                URL.revokeObjectURL(output.src);
                            }
                        };
        </script>           
    </body>
</html>
