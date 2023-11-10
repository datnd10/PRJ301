<%-- 
    Document   : AddReview
    Created on : Oct 23, 2023, 10:43:56 PM
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

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">    
        <style>
            .rating {
                display: flex;
                margin-top: -10px;
                flex-direction: row-reverse;
                margin-left: -4px;
                float: left;
            }

            .rating>input {
                display: none
            }

            .rating>label {
                position: relative;
                width: 19px;
                font-size: 25px;
                color: #ffce3d;
                cursor: pointer;

            }

            .rating>label::before {
                content: "\2605";
                position: absolute;
                opacity: 0
            }

            .rating>label:hover:before,
            .rating>label:hover~label:before {
                opacity: 1 !important
            }

            .rating>input:checked~label:before {
                opacity: 1
            }

            .rating:hover>input:checked~label:before {
                opacity: 0.4
            }
        </style>
    </head>
    <body style="background-color: #e8e8e8">
        <%@include file="Header.jsp" %>
        <c:set var="prod" value="${requestScope.product}"></c:set>
        <c:set var="prodColor" value="${requestScope.productColor}"></c:set>
            <div class="row">
                <div class="container bg-white mt-4">     
                    <div class="col-md-6 mt-5 mb-5 mx-auto">
                        <div class="card">
                            <div class="card-header d-flex align-items-center justify-content-between" >
                                <div style="font-size: 22px;color: chocolate;font-weight: bold; font-style: italic"">Review Product Color #${requestScope.productColorId}</div>
                        </div>
                        <div class="card-body">
                            <form id="reviewForm">
                                <input type="number" class="form-control productId" id="productColorId" name="productColorId" hidden="" value="${prodColor.product_color_id}">
                                <div class="form-group row">
                                    <div class="col-sm-3 col-3">
                                        <img class="img-fluid" src="images/${prodColor.image}"/>
                                    </div>

                                    <div class="col-sm-6 col-6 text-left">                                                             
                                        <div class="col-12" style="font-weight: bold;font-size: 18px">${prod.name}</div>
                                        <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Color: <span style="font-weight: lighter ;font-style: normal">${prodColor.color}</span></small></div>
                                        <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Price: <span style="font-weight: lighter ;font-style: normal">$${prodColor.price}</span></small></div>
                                    </div>

                                </div> 
                                <div class="form-group col-md-6 text-left mb-5">
                                    <label for="star" style="font-weight: bold; display: block">Star</label>
                                    <div class="rating"> 
                                        <input type="radio" name="rating" value="5" id="5" class="ratingStar"><label for="5">☆</label>
                                        <input type="radio" name="rating" value="4" id="4" class="ratingStar"><label for="4">☆</label> 
                                        <input type="radio" name="rating" value="3" id="3" class="ratingStar"><label for="3">☆</label>
                                        <input type="radio" name="rating" value="2" id="2" class="ratingStar"><label for="2">☆</label>
                                        <input type="radio" name="rating" value="1" id="1" class="ratingStar"><label for="1">☆</label>
                                    </div>
                                </div>
                                <div class="form-group col-md-12 text-left">
                                    <label for="description" style="font-weight: bold">Description</label>
                                    <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                                </div>
                                <div class="form-group col-md-12 text-left">
                                    <label for="imageFeedback" style="font-weight: bold">Image</label>
                                    <input type="file" class="form-control-file" accept="image/*" onchange="loadFile(event)" id="imageFeedback" name="avatar">
                                    <img id="output" style="width: 100px;height: 100px;object-fit: cover"/>
                                </div>
                                <input type="text" class="form-control" id="image" name="image" hidden="">
                                <input type="text" class="form-control" id="createat" name="creatat" hidden="" value="<%= java.time.LocalDate.now()%>">
                                <div class="form-group col-md-12 text-left">
                                    <button type="button" class="btn btn-primary submitBtn">Submit</button>
                                </div>
                                <input type="text" value="${product.product_id}" id="productId" hidden="">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
                                        const submitBtn = document.querySelector(".submitBtn");
                                        submitBtn.onclick = () => {
                                            const checkOutForm = document.querySelector("#reviewForm");
                                            const data = new FormData(checkOutForm);
                                            $.ajax({
                                                type: "post",
                                                url: "/PhoneStore/add-review",
                                                processData: false,
                                                contentType: false,
                                                enctype: "multipart/form-data",
                                                data: data,
                                                cache: false,
                                                success: function (response)
                                                {
                                                    switch (response) {
                                                        case 'Invalid':
                                                            Swal.fire({
                                                                title: 'You have already rated',
                                                                icon: 'error',
                                                                confirmButtonText: 'OK',
                                                            })
                                                            break;
                                                        default :
                                                            Swal.fire({
                                                                title: 'Review successfully',
                                                                icon: 'success',
                                                                confirmButtonText: 'See your rate'
                                                            }).then((result) => {
                                                                const productId = document.querySelector('#productId');
                                                                if (result.isConfirmed) {
                                                                    window.open(`detail-product?id=\${productId.value}`, "_self");
                                                                }
                                                            })
                                                    }
                                                }
                                            });
                                        };
        </script>
    </body>
</html>
