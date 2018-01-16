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
        <h1>Welcome!</h1>
        <form role="form" class="form-horizontal" action="/j_spring_security_check" method="POST">
            <input class="form-control form-group" type="text" name="j_login" placeholder="Login">
            <input class="form-control form-group" type="password" name="j_password" placeholder="Password">
            <input class="btn btn-primary" type="submit" />

            <p><a href="/register">Register new user</a></p>

            <c:if test="${param.error ne null}">
                <p>Wrong login or password!</p>
            </c:if>

            <c:if test="${param.logout ne null}">
                <p>Chao!</p>
            </c:if>
        </form>
    </div>
</body>
</html>
