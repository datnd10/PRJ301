<%-- 
    Document   : DetailProduct
    Created on : Oct 16, 2023, 10:38:09 AM
    Author     : Dac Dat
--%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" />
        <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <style>
            img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center;
            }
            .main {
                height: 85%;
                position: relative;
            }

            .control {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                font-size: 80px;
                color: white;
                cursor: pointer;
            }

            .prev {
                left: -10px;
                color: black
            }

            .next {
                right: -10px;
                color: black
            }

            .img-wrap {
                width: 100%;
                height: 100%;
            }

            .list-img {
                display: flex;
            }

            .list-img span {
                cursor: pointer;
                padding: 5px;
                background-color: #bbb;
            }

            .list-img span.active {
                background-color: rgb(220, 86, 86);
            }
            .list-color .active {
                background-color: white;
                color: blue;
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
        <c:set var="account" value="${sessionScope.account}"></c:set>
        <c:set var="product" value="${requestScope.product}"></c:set>
        <c:set var="listprodcolor" value="${requestScope.listProductColor}"></c:set>
            <div class="container">
                <nav aria-label="breadcrumb" >
                    <ol class="breadcrumb" style="background-color: #e8e8e8">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
                </ol>
            </nav>
        </div>
        <form id="productForm">
            <div class="row" >
                <div class="container d-flex p-5"" style="background-color: white">
                    <div class="col-md-6">
                        <div class="container mt-3">
                            <div class="main border">
                                <span class="control prev">
                                    <i class="bx bx-chevron-left"></i>
                                </span>
                                <span class="control next">
                                    <i class="bx bx-chevron-right"></i>
                                </span>
                                <div class="img-wrap" style="height: 500px;object-fit: cover">
                                    <c:set var="count" value="0"></c:set>
                                    <c:forEach var="pc" items="${listprodcolor}">
                                        <c:if test="${count == 0}">
                                            <img src="images/${pc.image}" alt="${pc.image}" />
                                            <c:set var="count" value="1"></c:set>
                                        </c:if>
                                    </c:forEach>                               
                                </div>
                            </div>
                            <c:set var="check" value="0"></c:set>
                                <div class="list-img">
                                <c:forEach var="pc" items="${listprodcolor}" begin="0" end="${listprodcolor.size()-1}">
                                    <span class="${check == 0 ? "active" :""}">  
                                        <img src="images/${pc.image}" alt="${pc.image}" style="width: 80px;height: 80px; object-fit: cover" class="image${check+1}"/>
                                        <c:set var="check" value="${check+1}"></c:set>
                                        </span> 
                                </c:forEach>                     
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="mt-2">
                            <h2 style="font-weight: bold; font-size: 35px">${product.name}</h2>
                            <div class="d-flex mt-3">
                                <span style="padding-right: 30px; border-right: 1px solid black">
                                    <c:forEach begin="1" end="5" var="c">
                                        <c:if test="${c <= product.star}">
                                            <i class="fas fa-star" style="color: #ee4d2d;font-size: 14px"></i>
                                        </c:if>
                                        <c:if test="${c > product.star}">
                                            <i class="far fa-star" style="font-size: 14px"></i>
                                        </c:if>     
                                    </c:forEach>  
                                </span>
                                <span style="margin-left: 30px;padding-right: 30px; border-right: 1px solid black;font-size: 14px;font-weight: bold" >${requestScope.totalRate == null ? "0" : requestScope.totalRate} reviews</span>
                                <span style="margin-left: 30px;padding-right: 30px; border-right: 1px solid black;font-size: 14px;font-weight: bold" >
                                    ${requestScope.totalSold} Sold
                                </span>
                            </div>
                        </div>
                        <div class="list-price mt-3">
                            <c:set var="check" value="1"></c:set>
                            <c:forEach var="pc" items="${listprodcolor}">
                                <h2 style="color: #ee4d2d; font-weight: 500; font-size: 30px; font-style: italic" class="price${check} ${check == 1 ? "" : "d-none"}">$${pc.price}</h2>
                                <c:set var="check" value="${check+1}"></c:set>
                            </c:forEach>
                        </div>

                        <div class="d-flex align-items-center mt-4">
                            <div style="font-size: 20px;font-weight: bold; margin-right: 50px">Color:</div>
                            <div class="list-color">
                                <c:set var="check" value="1"></c:set>
                                <c:forEach var="pc" items="${listprodcolor}">
                                    <input type="button" class="btn btn-primary${check == 1 ? " active" : ""} color" name="color" value="${pc.color}"></input>
                                    <c:set var="check" value="${check+1}"></c:set>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="text" hidden="" name="color" class="inputColor">
                        <input type="text" value="${product.product_id}" hidden="" name="productID">
                        <div class="d-flex mt-4">
                            <div style="font-size: 20px;font-weight: bold; margin-right: 50px">Quantity:</div>
                            <div>
                                <input type="number" id="quantity" name="quantity" min="1" value="1">
                                <input type="text" id="inventory" name="inventory" hidden="">
                            </div>
                            <div class="list-quantity">
                                <c:set var="check" value="1"></c:set>
                                <c:forEach var="pc" items="${listprodcolor}">
                                    <c:if test="${pc.quantity > 0}">
                                        <p style="color: #757575; font-style: italic" class="quantiy${check} ${check == 1 ? "" : "d-none"} ml-4" id="${pc.quantity}">${pc.quantity} quantities</p>
                                    </c:if>
                                    <c:if test="${pc.quantity == 0}">
                                        <p style="color: red;font-weight: bold; font-style: italic" class="quantiy${check} ${check == 1 ? "" : "d-none"} ml-4" id="${pc.quantity}">SOLD OUT</p>
                                    </c:if>
                                    <c:set var="check" value="${check+1}"></c:set>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="d-flex mt-3" style="gap: 30px">                            
                            <button id="submitBtn" type="button"  style="text-decoration: none; color: #ee4d2d; background-color: rgba(255,87,34,0.1); border: 1px solid #ee4d2d; padding: 15px 40px">Add to cart</button>
                            <button id="buyNow" type="button" style="background-color: rgb(238, 77, 45); color: rgb(255, 255, 255);border: 1px solid #ee4d2d; padding: 15px 40px">
                                <i class="fa-solid fa-money-bills"></i>
                                BUY NOW
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <c:set var="reviews" value="${requestScope.listReview}"></c:set>
        <c:set var="users" value="${requestScope.listUser}"></c:set>
        <c:set var="products" value="${requestScope.listProduct}"></c:set>
            <div class="container mt-5 bg-white p-5">
                <h2 style="color: #ee4d2d; font-style: italic">Product Reviews</h2>
                <hr>
            <c:forEach var="review" items="${reviews}"> 
                <div class="row">
                    <div class="d-flex justify-content-between order-${review.review_id}">
                        <div class="container d-flex gap-4" >
                            <div class="avatar">
                                <c:forEach var="user" items="${users}">
                                    <c:if test="${review.user_id == user.user_id}">
                                        <img src="images/${user.avatar}" style="width: 50px; height: 50px; border-radius: 50%"  alt="alt"/>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="review">
                                <div class="review-header">
                                    <div class="review-info">
                                        <c:forEach var="user" items="${users}">
                                            <c:if test="${review.user_id == user.user_id}">
                                                <p class="reviewer-name mb-0" style="font-style: italic; color:rgba(0,0,0,.87);font-size: 18px">${user.username}</p>
                                            </c:if>
                                        </c:forEach>
                                        <div class="review-rating">
                                            <c:forEach begin="1" end="5" var="c">
                                                <c:if test="${c <= review.star}">
                                                    <i class="fas fa-star" style="color: #ffce3d;font-size: 12px"></i>
                                                </c:if>
                                                <c:if test="${c > review.star}">
                                                    <i class="far fa-star" style="font-size: 12px"></i>
                                                </c:if>                                                  
                                            </c:forEach> 
                                        </div>
                                        <c:forEach var="productColor" items="${listprodcolor}">
                                            <c:if test="${review.product_color_id == productColor.product_color_id}">
                                                <p class="review-date" style="color: rgba(0,0,0,.54);width: 200px">${review.create_at} | ${productColor.color}</p>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="review-content">
                                    <p class="review-text" style="word-wrap: break-word;">${review.description}</p>
                                </div>
                                <div class="review-content">
                                    <img src="images/${review.image}" style="width: 100px;height: 100px; object-fit: cover"></img>
                                </div>
                            </div>
                        </div>
                        <div style="margin-left: -15px">
                            <c:if test="${review.user_id == requestScope.userId}">
                                <div class="dropdown">
                                    <i class="fa-solid fa-ellipsis dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-size: 28px"></i>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="update-review?id=${review.review_id}">Update</a>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>      
                </div>   
                <hr>
            </c:forEach>
        </div>    
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <script>
            const listImg = document.querySelectorAll(".list-img span");
            const img = document.querySelector(".img-wrap img");
            const prevBtn = document.querySelector(".prev");
            const nextBtn = document.querySelector(".next");
            let currentIndex = 0;
            const listcolor = document.querySelectorAll(".color");
            const colorActiveDefault = document.querySelector(".list-color .active");
            const colorDefault = document.querySelector(".inputColor");
            colorDefault.value = colorActiveDefault.value;
            listcolor.forEach((value, index) => {
                value.addEventListener("click", function () {
                    const colorActive = document.querySelector(".list-color .active");
                    const color = document.querySelector(".inputColor");
                    color.value = value.value;
                    colorActive.classList.remove("active");
                    value.classList.add("active");
                    currentIndex = index;
                    display(currentIndex);
                    displayInformation(currentIndex);
                });
            });
            prevBtn.addEventListener("click", function () {
                if (currentIndex === 0) {
                    currentIndex = listImg.length - 1;
                    display(currentIndex);
                } else {
                    currentIndex--;
                    display(currentIndex);
                }
            });
            nextBtn.addEventListener("click", function () {
                if (currentIndex === listImg.length - 1) {
                    currentIndex = 0;
                    display(currentIndex);
                } else {
                    currentIndex++;
                    display(currentIndex);
                }
            });
            const display = function (currentIndex) {
                listImg.forEach(function (value, index) {
                    if (currentIndex === index) {
                        value.classList.add("active");
                        const x = document.querySelector(`.image\${currentIndex+1}`);
                        img.src = `images/\${x.alt}`;
                    } else {
                        value.classList.remove("active");
                    }
                });
            };
            const listPrice = document.querySelectorAll(".list-price h2");
            const listQuantiy = document.querySelectorAll(".list-quantity p");
            const displayInformation = function (currentIndex) {
                listPrice.forEach(function (value, index) {
                    if (currentIndex === index) {
                        value.classList.remove("d-none");
                    } else {
                        value.classList.add("d-none");
                    }
                });
                listQuantiy.forEach(function (value, index) {
                    if (currentIndex === index) {
                        value.classList.remove("d-none");
                        const numberInput = document.getElementById('quantity');
                        numberInput.value = 1;
                        numberInput.max = value.id; // Set the maximum value to 100 
                        console.log(value.id);
                        const inventory = document.querySelector("#inventory");
                        inventory.value = value.id;
                        console.log(inventory);
                    } else {
                        value.classList.add("d-none");
                    }
                });
            };
            displayInformation(currentIndex);
            const quantity = document.querySelector("#quantity");

            quantity.addEventListener("input", function (e) {
                if (+e.target.value > +quantity.max) { // Convert both value and max to numbers for comparison                                     
                    e.target.value = quantity.max;
                }
            });
            var accountValue = '${account}'; // Lấy giá trị từ biến "account"
            console.log(accountValue === ''); // In giá trị ra console hoặc làm gì đó khác với giá trị này
            const submitBtn = document.querySelector("#submitBtn");
            submitBtn.onclick = () => {
                if (accountValue === '') {
                    window.location = "login";
                } else {
                    const productForm = document.querySelector("#productForm");
                    const data = new FormData(productForm);
                    $.ajax({
                        type: "post",
                        url: "/PhoneStore/add-to-cart",
                        processData: false,
                        contentType: false,
                        enctype: "multipart/form-data",
                        data: data,
                        cache: false,
                        success: function (response)
                        {
                            switch (response) {
                                case 'SoldOut':
                                    Swal.fire({
                                        title: 'The product has been sold out',
                                        icon: 'error',
                                        confirmButtonText: 'OK',
                                    })
                                    break;
                                default :
                                    Swal.fire({
                                        title: 'Add to cart successfully',
                                        icon: 'success',
                                        showCancelButton: true,
                                        confirmButtonText: 'Go to cart',
                                        cancelButtonText: 'OK'
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            window.open("cart", "_self");
                                        }
                                    });
                            }

                        }
                    });
                }
            };
            const buyNow = document.querySelector("#buyNow");
            buyNow.onclick = () => {
                if (accountValue === '') {
                    window.location = "login";
                } else {
                    const productForm = document.querySelector("#productForm");
                    const data = new FormData(productForm);
                    $.ajax({
                        type: "post",
                        url: "/PhoneStore/add-to-cart",
                        processData: false,
                        contentType: false,
                        enctype: "multipart/form-data",
                        data: data,
                        cache: false,
                        success: function (response)
                        {

                            switch (response) {
                                case 'SoldOut':
                                    Swal.fire({
                                        title: 'The product has been sold out',
                                        icon: 'error',
                                        confirmButtonText: 'OK',
                                    })
                                    break;
                                default :
                                    window.open("cart", "_self");
                            }
                        }
                    });
                }
            }
        </script>     
    </body>
</html>
