<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" align="center">
    <h1>Please, enter user info for registration:</h1>
    <form role="form" class="form-horizontal" action="/newuser" method="POST">
        <input class="form-control form-group" type="text" name="login" value="${login}" placeholder="Login">
        <input class="form-control form-group" type="password" name="password" placeholder="Password">
        <input class="form-control form-group" type="text" name="email" placeholder="E-mail">
        <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
        <input class="btn btn-primary" type="submit" value="Register" />

        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>
