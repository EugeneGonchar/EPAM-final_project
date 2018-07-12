<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <title>Login</title>
</head>

<body>

    <form id="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login"/>
        Login:<br/>
        <input type="text" name="login" value=""/>
        <br/>Password:<br/>
        <input type="password" name="password" value=""/>
            <br/>
                ${errorLoginPassMessage}
            <br/>
                ${wrongAction}
            <br/>
                ${nullPage}
            <br/>
        <input type="submit" value="Log in"/>
    </form>

    <script src="../js/bootstrap.min.js"></script>
</body>
</html>
