<%-- 
    Document   : AdminViewOrder
    Created on : Oct 24, 2023, 3:52:22 PM
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
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <style>
            .dropdown-toggle::after {
                content: none;
            }
            .dropdown-toggle {
                background-color: transparent;
            }
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
        <c:set var="listOrder" value="${requestScope.listOrder}"></c:set>
        <c:set var="listProd" value="${requestScope.listProd}"></c:set>
        <c:set var="listProdColor" value="${requestScope.listProdColor}"></c:set>
        <c:set var="listStatus" value="${requestScope.listStatus}"></c:set>
        <c:set var="order" value="${requestScope.order}"></c:set>
            <div class=" d-flex">
                <div><%@include  file="SideBarAdmin.jsp"%></div>
            <div class="container bg-white" style="height: 100%vh">     
                <div class="col-md-6 mt-3 mb-5 mx-auto">
                    <a href="list-order" style="text-decoration: none; color: inherit"><h1 style="color: chocolate">List Orders</h1></a>
                    <!-- REVIEW ORDER -->
                    <form id="changeStatus">
                        <div class="form-group col-md-12 p-0">
                            <label for="status" style="font-weight: bold">Status</label>
                            <c:if test="${order.status_id != 5}">
                                <select id="status" class="form-control" name="status">
                                    <c:forEach var="status" items="${listStatus}">
                                        <c:if test="${status.status_id != 5}">
                                            <option value="${status.status_id}" ${order.status_id == status.status_id ?"selected" :""}>${status.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <input type="text" value="${requestScope.order.order_id}" name="orderId" hidden="">
                            </c:if>
                            <c:if test="${order.status_id == 5}">
                                <select id="status" class="form-control" name="status">
                                    <c:forEach var="status" items="${listStatus}">
                                        <c:if test="${status.status_id == 5}">
                                            <option value="${status.status_id}" ${order.status_id == status.status_id ?"selected" :""} disabled="">${status.description}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </c:if>    
                        </div>
                    </form>
                    <div class="card">
                        <div class="card-header d-flex align-items-center justify-content-between" >
                            <div style="font-size: 22px;color: chocolate;font-weight: bold; font-style: italic"">Review Order #${requestScope.orderID}</div>
                        </div>
                        <div class="card-body ">
                            <c:forEach var="orderItem" items="${listOrder}">
                                <div class="form-group row">
                                    <c:forEach var="prodColor" items="${listProdColor}">
                                        <c:if test="${prodColor.product_color_id == orderItem.product_color_id}">
                                            <div class="col-sm-3 col-3">
                                                <img class="img-fluid" src="images/${prodColor.image}" />
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="prodColor" items="${listProdColor}">
                                        <c:if test="${prodColor.product_color_id == orderItem.product_color_id}"> 
                                            <c:forEach var="prod" items="${listProd}">
                                                <c:if test="${prodColor.product_id == prod.product_id}">                                              
                                                    <div class="col-sm-6 col-6">

                                                        <div class="col-12" style="font-weight: bold;font-size: 18px">${prod.name}</div>
                                                        <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Quantity: <span style="font-weight: lighter ;font-style: normal">${orderItem.quantity}</span></small></div>
                                                        <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Color: <span style="font-weight: lighter ;font-style: normal">${prodColor.color}</span></small></div>
                                                        <div class="col-12"><small style="font-weight: bold ;font-style: italic;font-size: 14px">Price: <span style="font-weight: lighter ;font-style: normal">$${prodColor.price}</span></small></div>
                                                    </div>
                                                    <div class="col-sm-3 col-3 text-right">
                                                        <h6><span>$</span><span class="price">${prodColor.price * orderItem.quantity}</span></h6>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>                                  
                                </div>
                            </c:forEach>

                            <hr />
                            <div class="form-group row">
                                <div class="col-12">
                                    <strong>Order</strong>
                                    <span class="float-right" style="font-weight: bold"><span>$</span><span class="totalPrice"></span></span>
                                </div>
                                <div class="col-12">
                                    <strong>Shipping</strong>
                                    <span class="float-right" style="font-weight: bold">
                                        <span>$</span>
                                        <span class="shipvalue">
                                            <c:forEach var="item" items="${requestScope.shipping}">
                                                <c:if test="${item.shipping_id == requestScope.order.shipping_id}">
                                                    ${item.price}
                                                </c:if>
                                            </c:forEach>
                                        </span>                                         
                                    </span>
                                </div>
                                <div class="col-12">
                                    <strong>Order Total</strong>
                                    <span class="float-right" style="font-weight: bold"><span>$</span><span>${requestScope.order.total_money}</span></span>
                                    <input type="text" class="totalOrder" value="" name="total" hidden="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${order.status_id != 5}">
                        <button type="button" class="btn btn-primary mt-3 saveBtn">Save</button>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            const totalPrice = document.querySelector('.totalPrice');
            const prices = document.querySelectorAll('.price');
            let total = 0;
            prices.forEach((value) => {
                let price = +(value.innerHTML);
                total += +(value.innerHTML);
            });
            totalPrice.innerHTML = total;
            const saveBtn = document.querySelector('.saveBtn');
            saveBtn.onclick = () => {
                const changeStatus = document.querySelector("#changeStatus");
                const data = new FormData(changeStatus);
                $.ajax({
                    type: "post",
                    url: "update-status",
                    processData: false,
                    contentType: false,
                    enctype: "multipart/form-data",
                    data: data,
                    cache: false,
                    success: function (response)
                    {
                        Swal.fire({
                            title: 'Update Status successfully',
                            icon: 'success',
                            showCancelButton: true,
                            confirmButtonText: 'Go to list Order',
                            cancelButtonText: 'Ok'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.open("list-order", "_self");
                            }
                        })
                    }
                });
            }
        </script>
    </body>
</html>
