<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authentication</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="form">
    <h1>Login</h1>
    <form action="home" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email">
        <br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <br><br>

        <input type="submit" value="Login" class="button button-block">
    </form>
</div>
</body>
</html>