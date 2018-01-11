<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h3>Group List</h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li><button type="button" id="delete1" class="btn btn-default navbar-btn">Delete with contacts</button></li>
                    <li><button type="button" id="delete2" class="btn btn-default navbar-btn">Delete without contacts</button></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Name</b></td>
            <td><b>Contacts</b></td>
        </tr>
        </thead>
        <c:forEach items="${groups}" var="group">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${group.id}" id="checkbox_${group.id}"/></td>
                <td>${group.name}</td>
                <td>${group.count}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    $('#delete1').click(function(){
        sendDeleteRequest("/groups/delete1");
    });

    $('#delete2').click(function(){
        sendDeleteRequest("/groups/delete2");
    });

    function sendDeleteRequest(url) {
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });
        $.post(url, data, function(data, status) {
            window.location.reload();
        });
    };
</script>
</body>
</html>