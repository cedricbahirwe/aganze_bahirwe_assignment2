<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="form">
    <%
        String userEmail = (String) request.getAttribute("email");
        if (userEmail != null) {
    %>
    <h1>Welcome, Your email is <%= userEmail %>
    </h1>
    <%
    } else {
    %>
    <h2>Ooh!!,It seems you're not logged in</h2>
    <%
        }
    %>

    <div class="labeled-links">
        <a href="index.jsp">Go back to Login Page</a>
    </div>
    <p class="labeled-links" style="text-align: center">
        Made by
        <a href="https://github.com/cedricbahirwe" target="_blank">Aganze Bahirwe Cedric: 23455</a>
    </p>
</div>

</body>
</html>