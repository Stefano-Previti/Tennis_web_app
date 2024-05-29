<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tenniswebapp.controller.JView" %>  
<%@ page import="tenniswebapp.utility.ServletUtility" %>
<%@ page import="tenniswebapp.model.OggettoBean" %> 
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>


<!DOCTYPE html>
<html>
<head>
    <title>Elenco noleggi </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f2f2f2;
        }

        header {
            text-align: left
        }

        .header-content {
            display: inline-block;
            margin: 10px;
        }

        .search-form {
            margin-left: 470px;
            margin-bottom: 20px;
        }

        .search-form input[type="text"] {
            width: 300px;
            height:22px;
        }

        .search-form input[type="submit"] {
            width: 100px;
            
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
        }

        .rectangle {
            text-align:center;
            background-color: #ffffff;
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 20px;
        }

        form {
            margin-top: 10px;
            display: inline-block;
            margin-right: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 200px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 200;
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
        <div class="header-content">
            <a href="<%=JView.PlayerHomeCTL %>">PlayerHome</a>
        </div>
        <div class="header-content">
            <form class="search-form" action="<%=JView.SearchCTL %>" method="post">
                <input type="text" name="Search" placeholder="Cerca equipaggiamento...">
                <input type="submit" value="Cerca" class="submit-button" style="background-color:#aaaaaa;">
            </form>
        </div>
    </header>
    <div class="container">
        <h1>Miei noleggi durante il torneo</h1>
        <% Map<String, Integer> mymap = (Map<String, Integer>) session.getAttribute("myPlayerMap"); %>
         <% if (mymap != null && !mymap.isEmpty()) { %>       
            <% for (Map.Entry<String, Integer> entry : mymap.entrySet()) { %>
                <% String tipologia = entry.getKey(); %>
                <% int quantitapresa = entry.getValue(); %>
                <div class="rectangle">
                    <p>Tipologia: <%= tipologia %></p>
                    <p>Quantità noleggiata: <%= quantitapresa %></p>
                    <form action="<%=JView.VisualizzaNoleggioCTL %>" method="post">
                        <input type="hidden" name="miatipologia" value="<%= tipologia %>">
                         <input type="hidden" name="miaquantita" value="<%= quantitapresa %>">
                        <input type="submit" value="Annulla Noleggio" class="submit-button">
                    </form>
                </div>
            <% } %>
        <% } else { %>
            <p>Nessun noleggio effettuato</p>
        <% } %>
    </div>
</body>
</html>