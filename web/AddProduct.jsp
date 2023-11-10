<%-- 
    Document   : AddProduct
    Created on : Oct 9, 2023, 11:25:30 AM
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
    if(user.getRole() != 0 && user.getRole() != 1) {
        request.getRequestDispatcher("NoPermission.jsp").forward(request, response);
        return;
    }
%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            .nav-link {
                color: white
            }
            .nav-link:hover {
                background-color: #ccc;
                color: #17a2b8
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div>
                <%@include file="SideBarAdmin.jsp" %>
            </div>

            <c:set var = "now" value = "<% = new java.util.Date()%>" />
            <div class="container mt-5 ml-3">
                <h1 style="margin-bottom: 40px">Add New Product</h1>
                <form action="add-product" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="inputName" style="font-weight: bold">Name:  </label>
                            <input type="text" class="form-control" id="inputName" placeholder="Product name"  name="name">
                            <c:if test="${requestScope.errorname!=null}"><label style="color: red;font-size: 14px">${requestScope.errorname}</label></c:if>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="description" style="font-weight: bold">Description:<label> </label> </label>
                                <input type="text" class="form-control" id="description" placeholder="Description"  name="description">                  
                            </div>
                            <div class="form-group col-md-2">
                                <label for="createat" style="font-weight: bold">Create At</label>
                                <input type="text" class="form-control" id="createat" name="creatat" readonly="" value="<%= java.time.LocalDate.now()%>">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="category" style="font-weight: bold">Category</label>
                            <select id="category" class="form-control" name="category">
                                <c:forEach var="c" items="${requestScope.listCategory}">
                                    <option value="${c.category_id}">${c.name}</option> 
                                </c:forEach>                                                          
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
