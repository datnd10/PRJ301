<%-- 
    Document   : Register
    Created on : Oct 3, 2023, 3:55:18 PM
    Author     : Dac Dat
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div style="background-color: antiquewhite;height: 100vh; clear: both">
            <div class="container pt-3">
                <div class="row d-flex justify-content-center">
                    <h1>Sign Up</h1>
                </div>
            </div>
            <div class="container p-1">
                <div class="row">
                    <div class="col-md-6">
                        <form id="signUpForm">
                            <div class="form-group">
                                <label for="Email" style="font-weight: bold">Email: 
                                    <span id="wrongEmail" class="text-danger d-none">The Email not correct form </span>
                                    <span id="existEmail" class="text-danger d-none">The Email Already exists </span>
                                </label>
                                <input type="email" class="form-control" id="Email" placeholder="Email" name="email">
                            </div>
                            <div class="form-group">
                                <label for="Password" style="font-weight: bold">Password: 
                                    <span id="wrongPassword" class="text-danger d-none">Must be at least 6 characters, contain number and character</span>
                                </label>
                                <input type="password" class="form-control" id="Password" placeholder="Password" name="password">
                            </div>
                            <div class="form-group">
                                <label for="ConfirmPassword" style="font-weight: bold">Confirm Password: <span id="wrongConfirmPassword" class="text-danger d-none">Confirm password doesn't same with password</span></label>
                                <input type="password" class="form-control" id="ConfirmPassword" placeholder="Confirm Password" name="confirmpassword">
                            </div>
                            <div class="form-group">
                                <label for="Username" style="font-weight: bold">Username:
                                    <span id="existUsername" class="text-danger d-none">The Username Already exists </span>
                                </label>
                                <input type="text" class="form-control" id="Username" placeholder="Username" name="username">
                            </div>
                            <div class="form-group">
                                <label for="Phone" style="font-weight: bold">Phone: 
                                    <span id="wrongPhone" class="text-danger d-none">The Phone must be 10 numbers </span>
                                    <span id="existPhone" class="text-danger d-none">The Phone Already exists </span>
                                </label>
                                <input type="text" class="form-control" id="Phone" placeholder="Phone" name="phone">
                            </div>
                            <div class="form-group">
                                <label for="Address" style="font-weight: bold">Address: </label>
                                <input type="text" class="form-control" id="Address" placeholder="Address" name="address">
                            </div>
                            <button type="button" class="btn btn-primary signUpBtn col-md-12">Sign Up</button>
                        </form>
                    </div>
                    <img src="images/signup.jpg" style="height: 74vh;margin-left: 80px; border-radius: 30px;margin-top: 10px "/> 
                </div>


            </div>
            <div class="mt-2 d-flex justify-content-center">
                <a href="login" style="text-decoration: none;color: darkcyan; font-size: 20px">Already have an account?</a>
            </div>
            <div class="p-1 d-flex justify-content-center">
                <a href="home" style="text-decoration: none;color: goldenrod; font-size: 20px">Back to Home Page!</a>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        const signUpBtn = document.querySelector('.signUpBtn');
        signUpBtn.onclick = () => {
            const wrongEmail = document.querySelector('#wrongEmail');
            const existEmail = document.querySelector('#existEmail');
            const wrongPassword = document.querySelector('#wrongPassword');
            const wrongPhone = document.querySelector('#wrongPhone');
            const existPhone = document.querySelector('#existPhone');
            const existUsername = document.querySelector('#existUsername');
            const wrongConfirmPassword = document.querySelector('#wrongConfirmPassword');
            console.log(wrongEmail);
            console.log(existEmail);
            console.log(wrongPassword);
            console.log(wrongPhone);
            console.log(existPhone);
            console.log(existUsername);
            console.log(wrongConfirmPassword);
            
            const signUpForm = document.querySelector("#signUpForm");
            const data = new FormData(signUpForm);
            $.ajax({
                type: "post",
                url: "signup",
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
                            wrongConfirmPassword.classList.add('d-none');
                            Swal.fire({
                                title: 'Sign Up successfully',
                                icon: 'success',
                                confirmButtonText: 'Go to login page'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.open("login", "_self");
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
                            if (response.includes("wrongconfirm")) {
                                wrongConfirmPassword.classList.remove('d-none');
                            } else {
                                wrongConfirmPassword.classList.add('d-none');
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
