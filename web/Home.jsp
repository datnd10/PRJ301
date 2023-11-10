<%-- 
    Document   : Home
    Created on : Oct 2, 2023, 3:07:20 PM
    Author     : Dac Dat
--%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>

            .dropdown-toggle::after {
                content: none;
            }
            .dropdown-toggle {
                background-color: transparent;
            }

            body{
                overflow-x: hidden;
            }
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

            .list-img div {
                cursor: pointer;
                padding: 5px;
                background-color: #bbb;
                flex: 1;
            }

            .list-img div.active {
                background-color: rgb(220, 86, 86);
            }
            .list {
                margin-left: 60px;
                display: flex;
                gap: 40px;
                flex-wrap: wrap;
            }
        </style>   
    </head>
    <body style="background-color: #e8e8e8">
        <%@include file="Header.jsp" %>
        <div class="container mt-5">
            <div class="main border">
                <span class="control prev">
                    <i class="bx bx-chevron-left"></i>
                </span>
                <span class="control next">
                    <i class="bx bx-chevron-right"></i>
                </span>
                <div class="img-wrap" style="height: 400px;object-fit: cover">
                    <img src="images/image1.jpg" alt="" />
                </div>
            </div>
            <div class="list-img">
                <div class="d-none">
                    <img src="images/image1.jpg" alt="" />
                </div>
                <div class="d-none">
                    <img src="images/image2.jpg" alt="" />
                </div>
                <div class="d-none">
                    <img src="images/image3.jpg" alt="" />
                </div>
                <div class="d-none">
                    <img src="images/image4.jpg" alt="" />
                </div>
                <div class="d-none">
                    <img src="images/image5.jpg" alt="" />
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 100px">
            <div class="container d-flex">
                <div style="width: 15vw">
                    <form id="filterForm">
                        <input type="text" class="searchInput" name="search" hidden="" value="${requestScope.search != null ? requestScope.search : ''}">
                        <div>
                            <label for="sort" ><h4>Sort by:</h4></label>
                            <select id="sort" class="form-control" name="sort">
                                <option selected=""></option>
                                <option value="price asc" ${requestScope.sort == "price asc" ? "selected" : ""}>Price ascending</option>
                                <option value="price desc" ${requestScope.sort == "price desc" ? "selected" : ""}>Price descending</option>
                                <option value="total_sold desc" ${requestScope.sort == "sold_quantity desc" ? "selected" : ""}>Best Seller</option>
                                <option value="product.create_at desc" ${requestScope.sort == "create_at desc" ? "selected" : ""}>New Product</option>
                            </select>
                        </div>
                        <hr/>
                        <div>
                            <h4>Category</h4>
                            <c:forEach var="c" items="${requestScope.listCate}">
                                <input type="radio" id="${c.category_id}" name="category" value="${c.category_id}" ${requestScope.category == c.category_id ? "checked" : ""}>
                                <label for="${c.category_id}">${c.name}</label>
                                <br/>
                            </c:forEach>
                            <br/>
                        </div>
                        <hr/>
                        <div >
                            <h4>Price</h4>
                            <div class="d-flex">
                                <div class="mr-3">
                                    From: <input type="text" name="pricefrom" style="width: 4vw;border-radius: 5px" value="${requestScope.pricefrom != null ? requestScope.pricefrom : ''}">$
                                </div>
                                <div>
                                    To: <input type="text" name="priceto" style="width: 4vw;border-radius: 5px" value="${requestScope.priceto != null ? requestScope.priceto : ''}">$
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div>
                            <h4>Rate</h4>
                            <input type="radio" id="5" name="star" value="5" ${requestScope.star == 5 ? "checked" : ""}>
                            <label for="5">
                                <i class="fas fa-star" style="color: #ffce3d"></i>
                                <i class="fas fa-star" style="color: #ffce3d"></i>
                                <i class="fas fa-star" style="color: #ffce3d"></i>
                                <i class="fas fa-star" style="color: #ffce3d"></i>
                                <i class="fas fa-star" style="color: #ffce3d"></i>
                            </label><br>
                            <input type="radio" id="4" name="star" value="4" ${requestScope.star == 4 ? "checked" : ""}>
                            <label for="4">
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="far fa-star"></i>
                            </label><br>
                            <input type="radio" id="3" name="star" value="3" ${requestScope.star == 3 ? "checked" : ""}>
                            <label for="3">
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                            </label><br>
                            <input type="radio" id="2" name="star" value="2" ${requestScope.star == 2 ? "checked" : ""}>
                            <label for="2">
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                            </label><br>
                            <input type="radio" id="1" name="star" value="1" ${requestScope.star == 1 ? "checked" : ""}>
                            <label for="1">
                                <i class="fas fa-star"style="color: #ffce3d"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                                <i class="far fa-star"></i>
                            </label>
                        </div>
                        <hr/>
                        <input type="Submit" value="Filter" class="btn btn-primary" style="width: 15vw">
                    </form>
                </div>
                <div class="d-flex flex-wrap list" >
                    <c:forEach var="p" items="${requestScope.listProd}">
                        <c:set var="count" value="0"></c:set>
                        <c:forEach var="pc" items="${requestScope.listProdColor}">
                            <c:if test="${count ==0}">
                                <c:if test="${p.product_id == pc.product_id}">
                                    <a href="detail-product?id=${p.product_id}" style="text-decoration: none; color: inherit">
                                        <div style="background-color: #E6E6FA;max-width: 260px;max-height: 400px; padding: 10px 30px;box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;">
                                            <img src="images/${pc.image}" alt="alt" style="width: 170px;height: 170px; object-fit: cover"/>
                                            <div class="mt-3">
                                                <div>
                                                    <c:forEach begin="1" end="5" var="c">
                                                        <c:if test="${c <= p.star}">
                                                            <i class="fas fa-star" style="color: #ffce3d;font-size: 12px"></i>
                                                        </c:if>
                                                        <c:if test="${c > p.star}">
                                                            <i class="far fa-star" style="font-size: 12px"></i>
                                                        </c:if>                                                  
                                                    </c:forEach>   
                                                </div>     
                                                <div class="">
                                                    <p style="font-size: 18px; font-weight: bold;margin-bottom: 5px">${p.name}</p>
                                                    <p style="word-wrap: break-word;overflow-wrap: break-word; font-size: 14px;margin-bottom: 5px">${p.description}</p>
                                                    <p style="color: #ee4d2d; font-size: 16px; font-weight: bold;font-style: italic">$${pc.price}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <c:set var="count" value="1"></c:set>
                                </c:if>
                            </c:if> 
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="container mt-4">
            <c:if test="${requestScope.listProd != null}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination  justify-content-end">
                        <li class="page-item ${requestScope.page == 1 ? "disabled" :""}"><a class="page-link" href="home?page=${requestScope.page-1}&search=${requestScope.search}&sort=${requestScope.sort}&category=${requestScope.category}&pricefrom=${requestScope.pricefrom}&priceto=${requestScope.priceto}&star=${requestScope.star}">Previous</a></li>
                            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                            <li class="page-item ${requestScope.page == i ?"active" :""}" ><a href="home?page=${i}&search=${requestScope.usernameSearch}&search=${requestScope.search}&sort=${requestScope.sort}&category=${requestScope.category}&pricefrom=${requestScope.pricefrom}&priceto=${requestScope.priceto}&star=${requestScope.star}" class="page-link" >${i}</a></li>
                            </c:forEach>
                        <li class="page-item ${requestScope.page == requestScope.endPage || requestScope.endPage ==0 ? "disabled" :""}"><a class="page-link" href="home?page=${requestScope.page+1}&search=${requestScope.search}&sort=${requestScope.sort}&category=${requestScope.category}&pricefrom=${requestScope.pricefrom}&priceto=${requestScope.priceto}&star=${requestScope.star}">Next</a></li>
                    </ul>
                </nav>
            </c:if>   
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            const listImg = document.querySelectorAll(".list-img div");
            const img = document.querySelector(".img-wrap img");
            const prevBtn = document.querySelector(".prev");
            const nextBtn = document.querySelector(".next");

            let currentIndex = 0;

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
                        img.src = `images/image\${currentIndex + 1}.jpg`;
                    } else {
                        value.classList.remove("active");
                    }
                });
            };

            setInterval(function () {
                currentIndex++;
                if (currentIndex === listImg.length) {
                    currentIndex = 0;
                }
                display(currentIndex);
            }, 2000);

            const searchInput = document.querySelector('.searchInput');
            const btnSearch = document.querySelector('.btnSearch');
            btnSearch.addEventListener('keydown', function (e) {
                if (e.key === 'Enter') {
                    const searchValue = btnSearch.value;
                    const searchInput = document.querySelector('.searchInput');
                    searchInput.value = searchValue;
                    console.log(searchInput.value);
                    const filterForm = document.querySelector("#filterForm");
                    filterForm.submit();
                }
            });
        </script>
    </body>
</html>
