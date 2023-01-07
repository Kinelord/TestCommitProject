<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
 Hello ${requestScope.user.username}
 Hello ${requestScope.userReadDtoMA.username}
 Hello ${requestScope.request.username}
Hello ${sessionScope.session.username}
</body>
</html>