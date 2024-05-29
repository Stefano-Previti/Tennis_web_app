<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@ page import="tenniswebapp.controller.JView" %>
  <%@ page import="tenniswebapp.utility.ServletUtility" %>
 
<html>
<head>
    <title>Login</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }

        .container {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h1, p {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 90%;
            padding: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
           
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p {
            color: red;
            margin-top: 10px;
        }

        p a {
            color: #4caf50;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }
               .submit-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.submit-button:hover {
    background-color: #45a049;
}
  
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="<%=JView.LoginCTL%>" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Accedi" class="submit-button"  >
        </form>
         <p>Non sei ancora registrato? <a href="<%=JView.LoginCTL%>"> Registrati</a></p>
        <p class="error-message"> <%=ServletUtility.getErrorLogin(request) %></p>
    </div>
</body>
</html>


