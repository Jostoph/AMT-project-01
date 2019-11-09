<%@ page import="ch.heigvd.amt.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg" />')">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TBM - Product ${product.id}</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />" >
    <!-- Bulma Version 0.8.x-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />" >

</head>
<body>
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
</section>
<section class="section has-text-white">
    <%-- TODO css--%>
    <img src="<% Product p = (Product) request.getAttribute("product"); out.print("https://picsum.photos/id/" + (p.getId() % 1000) +"/500/500");%>" alt="product image" />
    <h2>${product.name}</h2>
    <h2>${product.price}</h2>
    <h2>${product.origin}</h2>
    <h2>${product.description}</h2>
</section>

</body>
<script type="text/javascript" src="<c:url value="/res/lib/bulma.js" /> " defer></script>
</html>
