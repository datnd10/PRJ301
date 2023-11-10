<%-- 
    Document   : CheckOut
    Created on : Oct 18, 2023, 9:17:50 PM
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
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">    
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
        <c:set var="user" value="${sessionScope.account}"></c:set>
        <c:set var="listCart" value="${requestScope.listCart}"></c:set>
        <c:set var="listProduct" value="${requestScope.listProduct}"></c:set>
        <c:set var="listProductColor" value="${requestScope.listProductColor}"></c:set>
        <c:set var="listShipping" value="${requestScope.listShipping}"></c:set>
            <div class="container">
                <div class="row cart-body p-5 bg-white mt-3" >
                    <form class="d-flex row w-100" id="checkOutForm">
                        <div class="col-lg-6">
                            <!-- SHIPPING METHOD -->
                            <div class="card">
                                <div class="card-header" style="font-size: 22px;color: chocolate;font-weight: bold">Information Customer</div>
                                <div class="card-body">
                                    <div class="form-group col-md-12">
                                        <label for="Name" style="font-weight: bold">Full Name:</label>
                                        <input type="text" class="form-control fullName" id="Name" placeholder="Full name" value="${user.fullname}" name="name" required="">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Phone" style="font-weight: bold">Phone:</label>
                                    <input type="text" class="form-control phone" id="Phone" placeholder="Phone" value="${user.phone}" name="phone" required="">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="city" style="font-weight: bold">City:</label>
                                    <select class="form-select form-control" id="city" aria-label=".form-select-sm" required="" name="city">
                                        <option value="" selected>Select city</option>           
                                    </select>                              
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="district" style="font-weight: bold">District: </label>
                                    <select class="form-select form-control" id="district" aria-label=".form-select-sm" required="" name="district">
                                        <option value="" selected >Select district</option>
                                    </select>                            
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="ward" style="font-weight: bold">Ward:</label>
                                    <select class="form-select form-control" id="ward" aria-label=".form-select-sm" required=""name="ward">
                                        <option value="" selected>Select ward</option>
                                    </select>                            
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address" style="font-weight: bold">Specific Address:</label>
                                    <input type="text" required="" class="form-control specificAddress" id="Address" placeholder="Specific Address" value="${user.address}" name="address">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="shipping" style="font-weight: bold">Shipping</label>
                                    <select id="shipping" class="form-control" name="shipping">
                                        <c:forEach var="shipping" items="${listShipping}">
                                            <option value="${shipping.shipping_id}" class="${shipping.price}">${shipping.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Message" style="font-weight: bold">Message:</label>
                                    <input type="text" class="form-control" id="Message" placeholder="Message" value="" name="message">
                                </div>                    
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <!-- REVIEW ORDER -->
                        <div class="card">
                            <div class="card-header d-flex align-items-center justify-content-between" >
                                <div style="font-size: 22px;color: chocolate;font-weight: bold"">Review Order</div>
                                <a  href="cart" style="font-size: 14px;text-align: center;text-decoration: none;color: green;font-weight: bold">Edit Cart</a>
                            </div>
                            <div class="card-body">
                                <c:forEach var="cartItem" items="${listCart}">
                                    <div class="form-group row">
                                        <c:forEach var="prodColor" items="${listProductColor}">
                                            <c:if test="${prodColor.product_color_id == cartItem.product_color_id}">
                                                <div class="col-sm-3 col-3">
                                                    <img class="img-fluid" src="images/${prodColor.image}" />
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach var="prodColor" items="${listProductColor}">
                                            <c:if test="${prodColor.product_color_id == cartItem.product_color_id}">
                                                <c:forEach var="prod" items="${listProduct}">
                                                    <c:if test="${prodColor.product_id == prod.product_id}">
                                                        <div class="col-sm-6 col-6">
                                                            <div class="col-12" style="font-weight: bold;font-size: 18px">${prod.name}</div>
                                                            <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Quantity: <span style="font-weight: lighter ;font-style: normal">${cartItem.quantity}</span></small></div>
                                                            <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Color: <span style="font-weight: lighter ;font-style: normal">${prodColor.color}</span></small></div>
                                                            <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Price: <span style="font-weight: lighter ;font-style: normal">$${prodColor.price}</span></small></div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach var="prodColor" items="${listProductColor}">
                                            <c:if test="${prodColor.product_color_id == cartItem.product_color_id}">
                                                <div class="col-sm-3 col-3 text-right">
                                                    <h6><span>$</span><span class="price">${prodColor.price * cartItem.quantity}</span></h6>                          
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                                <hr />
                                <hr />
                                <div class="form-group row">
                                    <div class="col-12">
                                        <strong>Order</strong>
                                        <span class="float-right" style="font-weight: bold"><span>$</span><span class="totalPrice"></span></span>
                                        <input type="text" class="form-control" id="createat" name="creatat" hidden="" value="<%= java.time.LocalDate.now()%>">
                                    </div>
                                    <div class="col-12">
                                        <strong>Shipping</strong>
                                        <span class="float-right" style="font-weight: bold"><span>$</span><span class="shipvalue"></span></span>
                                    </div>
                                    <div class="col-12">
                                        <strong>Order Total</strong>
                                        <span class="float-right" style="font-weight: bold"><span>$</span><span class="totalPriceAndShip"></span></span>
                                        <input type="text" class="totalOrder" value="" name="total" hidden="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="button" style="background-color: #ee4d2d; color: white; padding: 13px 26px" class="btn float-right mt-5 submitBtn">Purchase</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@include file="Footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        const totalPrice = document.querySelector('.totalPrice');
        const prices = document.querySelectorAll('.price');
        const totalInput = document.querySelector('input[name="total"]');
        const shipping = document.querySelector("#shipping");
        const totalOrder = document.querySelector(".totalOrder");
        const totalPriceAndShip = document.querySelector(".totalPriceAndShip");
        let total = 0;
        prices.forEach((value) => {
            let price = +(value.innerHTML);
            total += +(value.innerHTML);
        });
        totalPrice.innerHTML = total;
        var selectedOption = shipping.querySelector(`option[value="\${1}"]`);
        var selectedOptionClassName = selectedOption.className;
        console.log(selectedOptionClassName);
        const shipvalue = document.querySelector(".shipvalue");
        shipvalue.innerHTML = selectedOptionClassName;
        totalPriceAndShip.innerHTML = total + (+selectedOptionClassName);
        totalInput.value = total + (+selectedOptionClassName);
        shipping.addEventListener("change", function () {
            var selectedValue = shipping.value;
            var selectedOption = shipping.querySelector(`option[value="\${selectedValue}"]`);
            var selectedOptionClassName = selectedOption.className;
            shipvalue.innerHTML = selectedOptionClassName;
            totalPriceAndShip.innerHTML = total + (+selectedOptionClassName);
            totalInput.value = total + (+selectedOptionClassName);
        });


        var citis = document.getElementById("city");
        var districts = document.getElementById("district");
        var wards = document.getElementById("ward");
        var Parameter = {
            url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
            method: "GET",
            responseType: "application/json",
        };
        var promise = axios(Parameter);
        promise.then(function (result) {
            console.log(result);
            renderCity(result.data);
        });

        let cityName = "";
        let districtName = "";
        let wardName = "";
        let dataWards;
        function normalizeText(text) {
            text = text.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
            return text.replace(/Đ/g, "D").replace(/đ/g, "d");
        }
        function renderCity(data) {
            for (const x of data) {
                citis.options[citis.options.length] = new Option(x.Name, x.Id);
            }
            citis.onchange = function () {
                district.length = 1;
                ward.length = 1;
                if (this.value != "") {
                    const result = data.filter(n => n.Id === this.value);
                    cityName = normalizeText(result[0].Name);
                    for (const k of result[0].Districts) {
                        district.options[district.options.length] = new Option(k.Name, k.Id);
                    }
                }
            };
            district.onchange = function (value) {
                ward.length = 1;
                const dataCity = data.filter((n) => n.Id === citis.value);
                dataCity[0].Districts.forEach((item) => {
                    if (item.Id === value.target.value) {
                        districtName = normalizeText(item.Name);
                    }
                });
                if (this.value != "") {
                    dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;
                    for (const w of dataWards) {
                        wards.options[wards.options.length] = new Option(w.Name, w.Id);
                    }
                }
            };
            wards.onchange = function (event) {
                dataWards.forEach((value) => {
                    if (value.Id === event.target.value) {
                        wardName = normalizeText(value.Name);
                    }
                })
            }
        }
        const submitBtn = document.querySelector(".submitBtn");
        submitBtn.onclick = () => {
            const city = document.querySelector("#city");
            let idCity = city.options[city.selectedIndex].value;
            city.options[city.selectedIndex].value = cityName;
            const district = document.querySelector("#district");
            let idDistrict = district.options[district.selectedIndex].value;
            district.options[district.selectedIndex].value = districtName;
            const ward = document.querySelector("#ward");
            let idWard = ward.options[ward.selectedIndex].value;
            ward.options[ward.selectedIndex].value = wardName;
            const checkOutForm = document.querySelector("#checkOutForm");
            const data = new FormData(checkOutForm);
            $.ajax({
                type: "post",
                url: "/PhoneStore/check-out",
                processData: false,
                contentType: false,
                enctype: "multipart/form-data",
                data: data,
                cache: false,
                success: function (response)
                {
                    console.log(response);
                    switch (response) {
                        case 'Invalid input':
                            Swal.fire({
                                title: 'Invalid Input',
                                icon: 'error',
                                confirmButtonText: 'OK',
                            })
                            break;
                        case 'OK':
                            city.options[city.selectedIndex].value = idCity;
                            district.options[district.selectedIndex].value = idDistrict;
                            ward.options[ward.selectedIndex].value = idWard;
                            
                            Swal.fire({  
                                title: 'Cancel successfully',
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Go to history purchase',
                            }).then((result) => {
                                sendToastNotificationInfo();
                                if (result.isConfirmed) {
                                    window.open("history-purchase", "_self");
                                }
                            })
                            break;
                    }
                }
            });
        };
    </script>
</body>
</html>
