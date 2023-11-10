<%-- 
    Document   : Header
    Created on : Sep 30, 2023, 7:36:23 PM
    Author     : Dac Dat
--%>
<div class="row" style="background-color: #020202">
    <div class="container d-flex justify-content-between mt-3">
        <p class="text-white text-center ">Mon-Sat:  9:00 AM - 5:30 PM</p>
        <p class="text-white text-center ">Visit our showroom in 1234 Street Adress City Address, 1234  Contact Us</p>
        <p class="text-white text-center ">Call Us: (00) 1234 5678</p>
    </div>
</div>

<div class="row mt-2">
    <div class="container d-flex align-items-center justify-content-between">
        <a href="home" style="text-decoration: none"><h1 style="margin-top: 5px;color: cornflowerblue">9:53</h1></a>
        <div class="input-group col-md-7" >
            <input type="text" class="form-control btnSearch" placeholder="Search product" style="width: 50%;border: 32px;height: 50%;border: 2px solid gray; border-radius: 30px; margin-left:  40px" value="${requestScope.search != null ? requestScope.search : ''}">
        </div>  
        <c:if test="${sessionScope.account != null}">
            <div class="input-group" >
                <div class="col-md-1">
                    <c:if test="${sessionScope.account.role == 0}"><a href="dash-board" class="btn btn-outline-info" style="text-decoration: none; margin-left: 20px">Admin</a></c:if>
                    <c:if test="${sessionScope.account.role == 1}"><a href="dash-board" class="btn btn-outline-info" style="text-decoration: none; margin-left: 20px">Staff</a></c:if>
                </div>
            </div>
            <div class="col-md-2 d-flex align-items-center justify-content-between">
                <a href="cart" id="cartLink" style="text-decoration: none;color: inherit;"><i class="fas fa-shopping-cart fa-2x"style="margin-right: 30px"></i></a>
                <div class="dropdown">
                    <button class="btn  dropdown-toggle d-flex align-items-center justify-content-center border border-secondary p-2" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <div>${sessionScope.account.username}</div>
                        <img src="images/${sessionScope.account.avatar}" style="width: 20px;height: 20px;border-radius: 50%;margin-left: 20px"/>
                    </button>
                    <div class="dropdown-menu " aria-labelledby="dropdownMenuButton">
                        <a href="information" class="dropdown-item" style="font-weight: bold">Information</a>
                        <a href="history-purchase" class="dropdown-item" style="font-weight: bold">History Purchase</a>
                        <a href="change-password" class="dropdown-item" style="font-weight: bold">Change Password</a>                            
                        <a href="logout" class="dropdown-item" href="#" style="font-weight: bold">Logout</a>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.account == null}">
            <div class="col-md-2">
                <a href="login" type="button" class="btn btn-light mr-2">Login</a>
                <a href="signup" type="button" class="btn btn-dark text-white">Signup</a>
            </div>
        </c:if>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    const id = "${sessionScope.account.user_id}";
    var toastMixin = Swal.mixin({
        toast: true,
        icon: 'success',
        title: 'General Title',
        animation: false,
        position: 'bottom-right',
        showConfirmButton: false,
        timer: 10000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    });

    const notiSocket = new WebSocket("ws://localhost:9999/PhoneStore/notification");

    notiSocket.onopen = function () {
        console.log("Toast is connected");
    };

    notiSocket.onmessage = function (event) {
        var toastObj = JSON.parse(event.data);
        console.log(toastObj);
        console.log();

        toastMixin.fire({
            animation: true,
            title: toastObj.name + " has just ordered " + toastObj.totalItems + " products with a total amount of " + toastObj.totalPrice+"$",
        });
    };

    notiSocket.onclose = function (event) {
        console.log("Toast is closed.");
    };

    notiSocket.onerror = function (error) {
        console.error("Toast error: " + error.message);
    };

    function sendToastNotificationInfo() {
        notiSocket.send(JSON.stringify({
            action: "order-toast",
            userID: id,
        }));
    }


</script>
