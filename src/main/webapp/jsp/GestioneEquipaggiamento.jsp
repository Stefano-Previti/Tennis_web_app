<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="tenniswebapp.controller.JView" %>
 <%@ page import="tenniswebapp.utility.ServletUtility" %>

<html>
<head>
    <title>Registrazione Equipaggiamento</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        header {
            padding: 10px;
            position: absolute;
            top: 0;
            left: 0;
        }

        .container {
            max-width: 400px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        form {
            margin-top: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="file"],
        input[type="text"],
        textarea {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            /*background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;*/
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
            text-align: center;
            
        }

        #descrizione {
            width: 100%;
            height: 100px;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
            white-space: pre-wrap;
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
    <header>
        <a href="<%=JView.AdminHomeCTL %>">AdminHome</a>
    </header>
    <div class="container">
        <h1>Gestione equipaggiamento </h1>
        <form action="<%=JView.GestioneEquipaggiamentoCTL%>" method="POST" >
            <label for="tipologia">Tipologia:</label>
            <input type="text" id="tipologia" name="tipologia" required>
            
             <label for="quantita">Quantità disponibile:</label>
             <input type="text" id="quantita" name="quantita" required>

            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" wrap="soft" required></textarea>

            <input type="submit" value="Carica" class="submit-button">
        </form>        
        <p class="error-message"><%=ServletUtility.getErrorTipologiaFormat(request) %></p>
        <p class="error-message"><%=ServletUtility.getErrorTipologia(request) %></p>
        <p class="error-message"><%=ServletUtility.getErrorQuantità(request) %></p>
    </div>
</body>
</html>
