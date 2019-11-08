<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TBM - Profile</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />" >
    <!-- Bulma Version 0.8.x-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />" >
</head>
<body style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg" />')">
<section class="hero">
    <div class="hero-head">
        <nav class="navbar">
            <div class="container">
                <div class="navbar-brand">
                    <a class="navbar-item" href="#">
                        <h1 class="title has-text-white">AMT Market</h1>
                    </a>
                    <span class="navbar-burger burger has-text-danger" data-target="navbarMenu">
                  <span></span>
                  <span></span>
                  <span></span>
                </span>
                </div>
                <div id="navbarMenu" class="navbar-menu" style="background: transparent">
                    <div class="navbar-end">
                  <span class="navbar-item">
                    <a class="button is-white is-outlined" href="${pageContext.request.contextPath}/">
                      <span class="icon">
                        <i class="fa fa-home"></i>
                      </span>
                      <span>Home</span>
                    </a>
                  </span>
                        <span class="navbar-item">
                    <a class="button is-white is-outlined" href="https://github.com/Jostoph/AMT-project-01">
                      <span class="icon">
                        <i class="fa fa-github"></i>
                      </span>
                      <span>Project Source</span>
                    </a>
                  </span>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</section>
<section class="section">
    <div class="container">

        <div class="container" style="padding-bottom: 2em">
            <h1 class="title is-size-2 has-text-primary">
                Personal Informations
            </h1>
        </div>


        <div>
            <span class="icon is-medium has-text-danger">
                <i class="fa fa-user"></i>
            </span>
            <span class="has-text-primary is-size-4">Username : </span>
            <span class="is-size-4 has-text-white">
                <% String username = (String) request.getAttribute("username"); out.print(username);%>
            </span>
        </div>
        <div>
            <span class="icon is-medium has-text-danger">
            <i class="fa fa-envelope"></i>
        </span>
            <span  class="has-text-primary is-size-4">Email : </span>
            <span class="is-size-4 has-text-white">
                <% String email = (String) request.getAttribute("email"); out.print(email);%>
            </span>
        </div>

        <div class="container" style="padding-bottom: 2em; padding-top: 2em">
            <h1 class="title is-size-3 has-text-primary">
                Orders History
            </h1>
        </div>
        <%-- TODO CSS FTW, delete order ?, EDITION page--%>
        <c:if test="${orders != null}">
            <c:forEach var="order" items="${orders}">
                <h2>${order.id}</h2>
                <h2>${order.date}</h2>

                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                    </tr>
                    <c:forEach var="orderline" items="${order.orderLines}">
                        <tr>
                            <td>${orderline.productId}</td>
                            <td>${productNames[orderline.productId]}</td>
                            <td>${orderline.quantity}</td>
                            <td><a href="${pageContext.request.contextPath}/shop/product?product_id=${orderline.productId}"><button>See Product</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
        </c:if>

    </div>
</section>
</body>
<script type="text/javascript" src="<c:url value="/res/lib/bulma.js" /> " defer></script>

</html>
