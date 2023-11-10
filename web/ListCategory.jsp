<%-- 
    Document   : ListCategory
    Created on : Oct 6, 2023, 11:26:09 PM
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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <style>
            .nav-link {
                color: white
            }
            .nav-link:hover {
                background-color: #ccc;
                color: #17a2b8
            }
        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("are you sure to delete category id = " + id)) {
                    window.location = "delete-category?id=" + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="d-flex">
            <div>
                <%@include file="SideBarAdmin.jsp" %>
            </div>
            <div class="container">               
                <div>
                    <div class="container">
                        <a href="list-product-color" class="btn btn-warning p-2" style="margin-bottom: 45px;margin-top: 30px">List Product Color</a>
                        <a href="add-category" class="btn btn-success p-2" style="margin-bottom: 45px;margin-top: 30px">Add New Category</a>
                    </div>
                </div>
                <div class="container" style="margin-top: -30px">
                    <div class="d-flex align-items-center justify-content-between">
                        <form action="list-category">
                            <input type="text" name="search" class="col-md-8" placeholder="Enter category name" value="${requestScope.categorySearch != null ? requestScope.categorySearch : ''}"  style="padding: 5px;border-radius: 30px;width: 73vw"> 
                            <button class="btn btn-primary">Search</button>
                        </form>
                    </div>
                    <a href="list-category" style="text-decoration: none; color: inherit"><h1 style="color: chocolate">List Category</h1></a>
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">CategoryID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Create At</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>                     
                            <c:if test="${requestScope.list == null}">
                            <h2>${requestScope.list}</h2>
                            <h2>No Category was found</h2>
                        </c:if>
                        <c:if test="${requestScope.list != null}">
                            <c:forEach items = "${requestScope.list}" var="c">
                                <c:set var="id" value="${c.category_id}"/> 
                                <tr>
                                    <td>${id}</td>
                                    <td>${c.name}</td>
                                    <td>${c.description}</td>
                                    <td>${c.creat_at}</td>
                                    <td>
                                        <a href="update-category?id=${id}" class="btn btn-warning mr-2">Update</a>
                                        <a href="#" onclick="doDelete('${id}')" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>   
                        </tbody>
                    </table>
                    <c:if test="${requestScope.show != false && requestScope.list != null}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination  justify-content-end">
                                <li class="page-item ${requestScope.page == 1 ? "disabled" :""}"><a class="page-link" href="list-category?page=${requestScope.page-1}&search=${requestScope.categorySearch}">Previous</a></li>
                                    <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                    <li class="page-item ${requestScope.page ==i ?"active" :""}" ><a href="list-category?page=${i}&search=${requestScope.categorySearch}" class="page-link" >${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${requestScope.page == requestScope.endPage || requestScope.endPage ==0 ? "disabled" :""}"><a class="page-link" href="list-user?page=${requestScope.page+1}&search=${requestScope.categorySearch}">Next</a></li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
