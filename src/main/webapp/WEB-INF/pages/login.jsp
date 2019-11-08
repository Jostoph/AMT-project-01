<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TBM - Login</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />">
    <!-- Bulma Version 0.8.x-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />">
</head>

<body>
<section style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg"/>')"
         class="hero is-dark is-fullheight has-background-dark">
    <div class="hero-head">
        <nav class="navbar">
            <div class="container">
                <div class="navbar-brand">
                    <a class="navbar-item" href="#">
                        <h1 class="title">AMT Market</h1>
                    </a>
                    <span class="navbar-burger burger has-text-danger" data-target="navbarMenu">
                        <span></span>
                        <span></span>
                        <span></span>
                    </span>
                    <a href="${pageContext.request.contextPath}/logout">
                    <span class="icon has-text-danger" style="margin-top: 0.8em;margin-right: 1em">
                        <i class="fa fa-sign-out"></i>
                    </span>
                    </a>
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

    <div class="hero-body">
        <div class="container has-text-centered">
            <div class="columns is-centered">
                <div class="column is-one-third">
                    <h1 class="title has-text-primary">
                        Back in Black
                    </h1>
                    <h2 class="subtitle">
                        Not an account yet ? <span class="has-text-danger">Sign UP</span> now to get started !
                    </h2>
                    <p>
                    </p>
                    <form action="login" method="post">
                        <div class="field">
                            <p class="control has-icons-left has-icons-right">
                                <input class="input" type="text" name ="username" placeholder="Username">
                                <span class="icon is-small is-left">
                                <i class="fa fa-user"></i>
                            </span>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control has-icons-left">
                                <input class="input" type="password" name="password" placeholder="Password">
                                <span class="icon is-small is-left">
                                <i class="fa fa-lock"></i>
                            </span>
                            </p>
                        </div>
                        <div class="field is-grouped is-grouped-right">
                            <p class="control">
                                <input class= "button is-success" type="submit" value="Login">
                            </p>
                            <p class="control">
                                <a class="button" href="registration">
                                    Sign Up
                                </a>
                            </p>
                        </div>
                    </form>
                    <div class="has-text-danger">
                        <% String err = (String) request.getAttribute("invalid");
                        if(err != null) {
                            out.println(err);
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
</body>
<script type="text/javascript" src="<c:url value="/res/lib/bulma.js" /> " defer></script>
</html>
