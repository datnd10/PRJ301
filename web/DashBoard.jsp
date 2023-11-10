<%-- 
    Document   : DashBoard
    Created on : Oct 26, 2023, 8:00:34 PM
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
    if(user.getRole() != 0 && user.getRole() != 1) {
        request.getRequestDispatcher("NoPermission.jsp").forward(request, response);
        return;
    }
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
            <div class="container-fluid" id="main" style="background-color: #E5E5E5">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!--/col-->
                    <div class="col main mt-3">
                        <div class="row mb-3">
                            <div class="col-xl-3 col-sm-6 py-2">
                                <div class="card bg-success text-white h-100">
                                    <div class="card-body bg-success">
                                        <div class="rotate">
                                            <i class="fa fa-user fa-4x"></i>
                                        </div>
                                        <h6 class="text-uppercase">Users</h6>
                                        <h1 class="display-4">${requestScope.totalUser}</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 py-2">
                                <div class="card text-white bg-danger h-100">
                                    <div class="card-body bg-warning">
                                        <div class="rotate">
                                            <i class="fa-solid fa-file-invoice-dollar fa-4x"></i>
                                        </div>
                                        <h6 class="text-uppercase">Orders</h6>
                                        <h1 class="display-4">${requestScope.totalOrder}</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 py-2">
                                <div class="card text-white bg-info h-100">
                                    <div class="card-body bg-info">
                                        <div class="rotate">
                                            <i class="fa-solid fa-sack-dollar fa-4x"></i>
                                        </div>
                                        <h6 class="text-uppercase">Incomes</h6>
                                        <h1 class="display-4">$${requestScope.income}</h1>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 py-2">
                                <div class="card text-white bg-danger h-100">
                                    <div class="card-body">
                                        <div class="rotate">
                                            <i class="fa fa-share fa-4x"></i>
                                        </div>
                                        <h6 class="text-uppercase">Cancel</h6>
                                        <h1 class="display-4">${requestScope.cancelOrder}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <hr>
                        <div class="row placeholders  ml-2">
                            <div style="background-color: white; padding: 10px; margin-right: 80px">
                                <h3 style="margin-left: 30px">Status </h3>
                                <canvas id="pieChart" style="width: 400px"></canvas>
                            </div>
                            <div style="background-color: white; padding: 10px">
                                <h3 style="margin-left: 30px">Weekly Sales - Last week </h3>
                                <canvas id="lineChart" style="width: 700px"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        let cancelOrderValue = '<%= request.getAttribute("cancelOrder") %>';
        let pendingOrderValue = '<%= request.getAttribute("pendingOrder") %>';
        let approvedOrderValue = '<%= request.getAttribute("approvedOrder") %>';
        let shippingOrderValue = '<%= request.getAttribute("shippingOrder") %>';
        let receivedOrderValue = '<%= request.getAttribute("receivedOrder") %>';
        
        let totalMonday = '<%= request.getAttribute("totalMonday") %>';
        let totalTuesday = '<%= request.getAttribute("totalTuesday") %>';
        let totalWednesday = '<%= request.getAttribute("totalWednesday") %>';
        let totalThurday = '<%= request.getAttribute("totalThurday") %>';
        let totalFriday = '<%= request.getAttribute("totalFriday") %>';
        let totalSaturday = '<%= request.getAttribute("totalSaturday") %>';
        let totalSunday = '<%= request.getAttribute("totalSunday") %>';
        
        
        const ctx = document.getElementById('pieChart');
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: [
                    'Pending',
                    'Approved',
                    'Shipping',
                    'Received',
                    'Cancel'
                ],
                datasets: [{
                        label: 'Status',
                        data: [pendingOrderValue, approvedOrderValue, shippingOrderValue, receivedOrderValue, cancelOrderValue],
                        backgroundColor: [
                            'rgb(255, 99, 132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 205, 86)',
                            'rgb(40, 167, 69)',
                            'RGB(220, 53, 69)'
                        ],
                        hoverOffset: 4
                    }]
            },
        });

        const ctx1 = document.getElementById('lineChart');
        new Chart(ctx1, {
            type: 'line',
            data: {
                labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
                datasets: [{
                        label: 'Incomes',
                        data: [totalMonday, totalTuesday, totalWednesday, totalThurday, totalFriday, totalSaturday, totalSunday],
                        fill: true,
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
            },
        });
    </script>
</body>
</html>
