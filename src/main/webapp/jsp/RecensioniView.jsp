<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="tenniswebapp.model.recensioneBean" %>
<%@ page import="tenniswebapp.utility.ServletUtility" %>
<%@ page import="tenniswebapp.controller.JView" %>  


<!DOCTYPE html>
<html>
<head>
    <title>Recensioni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f2f2f2;
        }

        header {
            padding: 10px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .rectangle {
            background-color: #ffffff;
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 20px;
            position: relative;
        }

        .actions {
            text-align: right;
            margin-bottom: 10px;
        }

        .actions form {
            display: inline;
            margin-left: 10px;
        }

        form.delete-form {
            position: absolute;
            top: 0px;
            right: 10px;
        }

        form.delete-form input[type="submit"] {
            background-color: #ff0000;
        }

        form.delete-form input[type="submit"]:hover {
            background-color: #cc0000;
        }

        form {
            margin-top: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        textarea {
            width: 100%;
            height: 100px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
 
        }

        input[type="submit"]:hover {
            background-color: #45a049;
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
        <a href="<%=JView.GestioneNoleggioCTL %>">Gestione noleggio</a>
    </header>
    <div class="container">
        <h1>Recensioni</h1>
        <% Map<Integer, recensioneBean> recensioniMap = (Map<Integer, recensioneBean>) session.getAttribute("recensioniMap"); %>
        <% if (recensioniMap != null && !recensioniMap.isEmpty()) { %>
            <% for (Map.Entry<Integer, recensioneBean> entry : recensioniMap.entrySet()) { %>
                <% recensioneBean recensione = entry.getValue(); %>
                <div class="rectangle">
                    <% if (recensione.getUsername().equals(session.getAttribute("Username"))) { %>
                        <form class="delete-form" action="<%=JView.ModificaRecensioniCTL%>" method="get">
                            <input type="hidden" name="recensioneId" value="<%= entry.getKey() %>">
                            <input type="submit" value="Elimina" class="submit-button">
                        </form>
                    <% } %>
                    <p>Username: <%= recensione.getUsername() %></p>
                    <p><%= recensione.getRecensione() %></p>
                </div>
            <% } %>
        <% } else { %>
            <p>Nessuna recensione disponibile</p>
        <% } %>
        <form action="<%=JView.ModificaRecensioniCTL%>" method="post">
            <label for="recensione"></label>
            <textarea id="recensione" name="recensione" required></textarea>
            <input type="submit" value="Invia"class="submit-button">
        </form>
    </div>
</body>
</html>

