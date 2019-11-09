<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg" />')">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TBM - Products</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />" >
    <!-- Bulma Version 0.8.x-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />" >
</head>
<body>
<section class="hero is-fullheight">
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
                        <a class="button is-white is-outlined" href="${pageContext.request.contextPath}/shop/products">
                          <span class="icon">
                            <i class="fa fa-shopping-cart"></i>
                          </span>
                          <span>Shop</span>
                        </a>
                      </span>
                    <span class="navbar-item">
                    <a class="button is-white is-outlined" href="${pageContext.request.contextPath}/shop/profile">
                      <span class="icon">
                        <i class="fa fa-user"></i>
                      </span>
                      <span>My Account</span>
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
                    <span class="navbar-item">
                    <a class="button is-danger is-outlined" href="${pageContext.request.contextPath}/logout">
                      <span class="icon">
                        <i class="fa fa-sign-out"></i>
                      </span>
                      <span>Logout</span>
                    </a>
                  </span>
                    </div>
                </div>
            </div>
        </nav>
    </div>

    <div class="hero-body">
        <div class="container has-text-white">
            <div class="columns">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <table style="background: transparent" class="table is-bordered is-narrow is-fullwidth has-text-white">
                        <tr>
                            <th class="has-text-primary">ID</th>
                            <th class="has-text-primary">Name</th>
                            <th class="has-text-primary">Origin</th>
                            <th class="has-text-primary">Price</th>
                            <th class="has-text-primary">Info</th>
                            <th class="has-text-primary">Buy</th>
                        </tr>

                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.origin}</td>
                                <td>${product.price}</td>
                                <td><a class="has-text-success has-text-centered" href="${pageContext.request.contextPath}/shop/product?product_id=${orderline.productId}">See Product</a></td>
                                <td>
                                    <form action="shop/products" method="post">
                                        <input type="hidden" name="product" value="${product.id}">
                                        <div class="field" style="text-align: center">
                                            <input class ="input is-inline" type="number" min="1" max="100" name="quantity" placeholder="0">
                                            <input class="button is-success" type="submit" value="Add">
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${pageNum != 1}">
                        <a class="button" href="shop/products?pageNum=${pageNum - 1}">Previous</a>
                    </c:if>
                    <c:if test="${pageNum lt totalNum}">
                        <a class="button" href="shop/products?pageNum=${pageNum + 1}">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>


</section>
</body>
<script type="text/javascript" src="<c:url value="/res/lib/bulma.js" /> " defer></script>
</html>
