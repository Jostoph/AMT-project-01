<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg"/>')">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>TBM - Cart</title>
  <base href="${pageContext.request.contextPath}/"/>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
  <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />">
  <!-- Bulma Version 0.8.x-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />">
</head>

<body class="has-text-white">
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
                    <i class="fa fa-money"></i>
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
                <a class="button is-white is-outlined" href="${pageContext.request.contextPath}/shop/cart">
                  <span class="icon">
                    <i class="fa fa-shopping-cart"></i>
                  </span>
                  <span>My Cart</span>
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
  <section class="section">
    <div class="container has-text-white">
      <div class="container" style="padding-bottom: 2em">
        <h1 class="title is-size-2 has-text-primary">
          Your Order
        </h1>
      </div>
        <div class="field is-grouped">
          <form action="shop/cart" method="post">
            <p class="control">
              <input class="button is-success" name="command" type="submit" value="Order !"/>
            </p>
          </form>
          <form  style="margin-left: 1em" action="shop/cart" method="post">
            <p class="control">
              <input class="button is-danger" name="cancel" type="submit" value="Cancel Order"/>
            </p>
          </form>
        </div>
      <table style="background: transparent" class="table is-bordered is-narrow is-fullwidth has-text-white">
        <tr>
          <th class="has-text-primary">Product ID</th>
          <th class="has-text-primary">Quantity</th>
          <th class="has-text-primary">Info</th>
        </tr>
        <c:forEach var="orderline" items="${order.orderLines}">
          <tr>
            <td>${orderline.productId}</td>
            <td>${orderline.quantity}</td>
            <td><a class="has-text-success has-text-centered" href="${pageContext.request.contextPath}/shop/product?product_id=${orderline.productId}">See Product</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </section>
</body>

<script type="text/javascript" src="<c:url value="/res/lib/bulma.js" /> " defer></script>
</html>
