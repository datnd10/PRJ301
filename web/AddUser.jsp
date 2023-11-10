<%-- 
    Document   : AddUser.jsp
    Created on : Oct 3, 2023, 12:03:45 PM
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
    if(user.getRole() != 0 ) {
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
                <h1 style="margin-bottom: 40px">Add New User</h1>
                <form id="addForm">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail4" style="font-weight: bold">Email:  
                                <span id="wrongEmail" class="text-danger d-none">The Email not correct form </span>
                                <span id="existEmail" class="text-danger d-none">The Email Already exists </span>
                            </label>
                            <input type="email" class="form-control" id="inputEmail4" placeholder="Email"  name="email">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputPassword4" style="font-weight: bold">Password: 
                                <span id="wrongPassword" class="text-danger d-none">Must be at least 6 characters, contain number and character</span>
                            </label>
                            <input type="password" class="form-control" id="inputPassword4" placeholder="Password"  name="password">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="inputUsername4" style="font-weight: bold">Username:
                                <span id="existUsername" class="text-danger d-none">The Username Already exists </span>
                            </label>
                            <input type="text" class="form-control" id="inputUsername4" placeholder="Username" name="username">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputFullname4" style="font-weight: bold">Fullname:</label>
                            <input type="text" class="form-control" id="inputFullname4" placeholder="FullName" name="fullname">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputPhone4" style="font-weight: bold">Phone:  
                                <span id="wrongPhone" class="text-danger d-none">The Phone must be 10 numbers </span>
                                <span id="existPhone" class="text-danger d-none">The Phone Already exists </span>
                            </label>
                            <input type="text" class="form-control" id="inputPhone4" placeholder="Phone" name="phone">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress" style="font-weight: bold">Address:</label>
                            <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" name="address">
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
                            <input type="text" class="form-control" id="createat" name="creatat" readonly="" value="<%= java.time.LocalDate.now()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlFile1" style="font-weight: bold">Avatar</label>
                        <input type="file" class="form-control-file" accept="image/*" onchange="loadFile(event)" id="exampleFormControlFile1" name="avatar">
                        <img id="output" style="width: 100px;height: 100px;object-fit: cover"/>
                    </div>
                    <input type="text" class="form-control" id="image" name="image" hidden="">   
                    <button type="button" class="btn btn-primary addBtn">Add</button>
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
                            const addBtn = document.querySelector('.addBtn');
                            addBtn.onclick = () => {
                                const wrongEmail = document.querySelector('#wrongEmail');
                                const existEmail = document.querySelector('#existEmail');
                                const wrongPassword = document.querySelector('#wrongPassword');
                                const wrongPhone = document.querySelector('#wrongPhone');
                                const existPhone = document.querySelector('#existPhone');
                                const existUsername = document.querySelector('#existUsername');
                                const updateForm = document.querySelector("#addForm");
                                const data = new FormData(updateForm);
                                $.ajax({
                                    type: "post",
                                    url: "add-user",
                                    processData: false,
                                    contentType: false,
                                    enctype: "multipart/form-data",
                                    data: data,
                                    cache: false,
                                    success: function (response)
                                    {
                                        console.log(response);
                                        switch (response) {
                                            case 'OK' :
                                                wrongEmail.classList.add('d-none');
                                                existEmail.classList.add('d-none');
                                                wrongPassword.classList.add('d-none');
                                                wrongPhone.classList.add('d-none');
                                                existPhone.classList.add('d-none');
                                                existUsername.classList.add('d-none');
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
                                                if (response.includes("wrongemail")) {
                                                    wrongEmail.classList.remove('d-none');
                                                } else {
                                                    wrongEmail.classList.add('d-none');
                                                }

                                                if (response.includes("wrongpassword")) {
                                                    wrongPassword.classList.remove('d-none');
                                                } else {
                                                    wrongPassword.classList.add('d-none');
                                                }
                                                if (response.includes("wrongphone")) {
                                                    wrongPhone.classList.remove('d-none');
                                                } else {
                                                    wrongPhone.classList.add('d-none');
                                                }
                                                
                                                if (response.includes("existemail")) {
                                                    existEmail.classList.remove('d-none');
                                                } else {
                                                    existEmail.classList.add('d-none');
                                                }

                                                if (response.includes("existusername")) {
                                                    existUsername.classList.remove('d-none');
                                                } else {
                                                    existUsername.classList.add('d-none');
                                                }
                                                if (response.includes("existphone")) {
                                                    existPhone.classList.remove('d-none');
                                                } else {
                                                    existPhone.classList.add('d-none');
                                                }
                                                Swal.fire({
                                                    title: 'Something Wrong',
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
