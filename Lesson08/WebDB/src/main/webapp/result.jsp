<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#delete").click(function(){
                var val = [];
                $(':checkbox:checked').parent().siblings("#id").each(function(){
                    val.push($(this).text());
                });
                $.post("/delete", { ids: val.toString() }, null);
            });
        });
    </script>
</head>
<body>
<table border="1">
    <c:forEach items="${personList}" var="person">
        <tr>
            <td id="id"><c:out value="${person.id}"/></td>
            <td><c:out value="${person.name}"/></td>
            <td><c:out value="${person.age}"/></td>
            <td><input type="checkbox" name="checkbox" id="checkbox"></td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" id="delete" value="Delete">
</body>
</html>
