<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="background-image: url('<c:url value="/res/assets/tbm-bg.jpeg" />')">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>TBM - 404</title>
  <base href="${pageContext.request.contextPath}/"/>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
  <link rel="shortcut icon" href="<c:url value="/res/assets/shopicon.png" />">
  <!-- Bulma Version 0.8.x-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/res/css/main.css" />">
</head>

<body>
<div class="columns has-text-centered">
  <div class="column is-one-fifth is-offset-two-fifths">
    <h1 class="is-size-1 has-text-white" style="margin-bottom: 1em; margin-top: 1em">
      Nothing to do here <span class="has-text-danger">4<i class="fa fa-smile-o"></i>4</span>
    </h1>
    <figure class="image">
      <img src="<c:url value="/res/assets/liechti-fck.jpg" />">
    </figure>
    <a href="${pageContext.request.contextPath}/" class="button is-success" style="margin-top: 1em">
      Back to the shop !
    </a>
  </div>
</div>
</body>
</html>
