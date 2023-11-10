<%-- 
    Document   : UpdateUser.jsp
    Created on : Oct 2, 2023, 11:05:40 PM
    Author     : Dac Dat
--%>
<%@page import="model.User"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User user = (User) session.getAttribute("account");
        if(user == null) {
        response.sendRedirect("/login");
        return;
    }
    if(user.getRole() != 0) {
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
    </head>
    <body>
        <div class="d-flex">
            <div>
                <%@include file="SideBarAdmin.jsp" %>
            </div>
            <c:set var="user" value="${requestScope.data}"/>
            <div class="container mt-5">
                <h1 style="margin-bottom: 40px">Update User</h1>
                <form id="updateForm">
                    <input value="${user.user_id}" name="id" hidden="">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail4" style="font-weight: bold">Email: <span id="errorEmail" class="text-danger d-none">The Email Already exists </span></label>
                            <input type="email" class="form-control" id="inputEmail4" placeholder="Email" value="${user.email}" name="email">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputPassword4" style="font-weight: bold">Password</label>
                            <input type="password" class="form-control" id="inputPassword4" placeholder="Password" value="${user.password}" readonly="" name="password">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="inputUsername4" style="font-weight: bold">Username: <span id="errorUsername" class="text-danger d-none">The Username Already exists</span></label>
                            <input type="text" class="form-control" id="inputUsername4" placeholder="Username" value="${user.username}" name="username">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputFullname4" style="font-weight: bold">Fullname</label>
                            <input type="text" class="form-control" id="inputFullname4" placeholder="FullName" value="${user.fullname}" name="fullname">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputPhone4" style="font-weight: bold">Phone:<span id="errorPhone" class="text-danger d-none">Phone Already exists</span></label>
                            <input type="text" class="form-control" id="inputPhone4" placeholder="Phone" value="${user.phone}" name="phone">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress" style="font-weight: bold">Address</label>
                            <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" value="${user.address}" name="address">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="role" style="font-weight: bold">Role</label>
                            <select id="role" class="form-control" name="role">
                                <option ${user.role == 0 ? "selected" : ""}>0</option>
                                <option ${user.role == 1 ? "selected" : ""}>1</option>
                                <option ${user.role == 2 ? "selected" : ""}>2</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="createat" style="font-weight: bold">Create At</label>
                            <input type="text" class="form-control" id="createat" value="${user.create_at}" readonly="" name="creatat">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlFile1" style="font-weight: bold">Avatar</label>
                        <input type="file" class="form-control-file" id="exampleFormControlFile1" accept="image/*" onchange="loadFile(event)" name="avatar">
                        <img src="images/${user.avatar}" id="output" style="width: 100px;height: 100px;object-fit: cover"/>
                    </div>
                    <input type="text" class="form-control" id="image" name="image" hidden="">
                    <input type="text" value="${user.avatar}" name="defaultAvatar" hidden="">
                    <button type="button" class="btn btn-primary updateBtn">Update</button>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                            var loadFile = function (event) {
                                var output = document.getElementById('output');
                                output.src = URL.createObjectURL(event.target.files[0]);
                                output.onload = function () {
                                    URL.revokeObjectURL(output.src);
                                }
                                const image = document.querySelector("#image");
                                image.value = event.target.files[0].name;
                            };

                            const updateBtn = document.querySelector('.updateBtn');
                            updateBtn.onclick = () => {
                                const errorEmail = document.querySelector('#errorEmail');
                                const errorUsername = document.querySelector('#errorUsername');
                                const errorPhone = document.querySelector('#errorPhone');
                                const updateForm = document.querySelector("#updateForm");
                                const data = new FormData(updateForm);
                                $.ajax({
                                    type: "post",
                                    url: "update-user",
                                    processData: false,
                                    contentType: false,
                                    enctype: "multipart/form-data",
                                    data: data,
                                    cache: false,
                                    success: function (response)
                                    {
                                        switch (response) {
                                            case 'OK' :
                                                errorEmail.classList.add('d-none');
                                                errorPhone.classList.add('d-none');
                                                errorUsername.classList.add('d-none');
                                                Swal.fire({
                                                    title: 'Check out successfully',
                                                    icon: 'success',
                                                    showCancelButton: true,
                                                    confirmButtonText: 'Go to list user',
                                                    cancelButtonText: 'Ok'
                                                }).then((result) => {
                                                    if (result.isConfirmed) {
                                                        window.open("list-user", "_self");
                                                    }
                                                })
                                                break;
                                            default :
                                                if (response.includes("erroremail")) {
                                                    errorEmail.classList.remove('d-none');
                                                } else {
                                                    errorEmail.classList.add('d-none');
                                                }

                                                if (response.includes("errorphone")) {
                                                    errorPhone.classList.remove('d-none');
                                                } else {
                                                    errorPhone.classList.add('d-none');
                                                }
                                                if (response.includes("errorusername")) {
                                                    errorUsername.classList.remove('d-none');
                                                } else {
                                                    errorUsername.classList.add('d-none');
                                                }
                                                Swal.fire({
                                                    title: 'Something duplicate',
                                                    icon: 'error',
                                                    confirmButtonText: 'OK',
                                                })
                                                break;
                                        }
                                    }
                                });
                            }
        </script>           
    </body>
</html>
