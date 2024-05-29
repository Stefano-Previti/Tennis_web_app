<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="tenniswebapp.model.TipologiaBean" %>
<%@ page import="tenniswebapp.controller.JView" %>  
<%@ page import="tenniswebapp.utility.ServletUtility" %>

<!DOCTYPE html>
<html>
<head>
    <title>Elenco equipaggiamento</title>
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
            margin-left: 270px;
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
            max-width: 800px;
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

        form {
            display: inline-block;
            margin-right: 70px;
          
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        textarea {
            width: 150;
            height: 100px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="text"] {
            width: 240px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 160px;
            padding: 10px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
.elimina {
        color: red;
        border: none;
        padding: 5px 10px;
        position: absolute;
        top: 5px;
        right: 5px;
        cursor: pointer;
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

    </style>
</head>
<body>
     <header>
        <div class="header-content">
            <a href="<%=JView.AdminHomeCTL %>">AdminHome</a>
        </div>
        <div class="header-content">
            <form class="search-form" action="<%=JView.Search1CTL %>" method="get">
                <input type="text" name="searchTerm" placeholder="Cerca equipaggiamento...">
                <input type="submit" value="Cerca" class="submit-button"  style="background-color:#aaaaaa;">
            </form>
        </div>
    </header>
    <div class="container">
        <h1>Elenco equipaggiamento</h1>
        <% Map<String, TipologiaBean> tipologieMap = (Map<String, TipologiaBean>) session.getAttribute("tipologieMap"); %>
        <% if (tipologieMap != null && !tipologieMap.isEmpty()) { %>
            <% for (Map.Entry<String, TipologiaBean> entry : tipologieMap.entrySet()) { %>
                <% String tipologia = entry.getKey(); %>
                <% TipologiaBean tipologiaBean = entry.getValue(); %>
                <div class="rectangle">
                    <p>Tipologia: <%= tipologia %></p>
                    <p>Descrizione: <%= tipologiaBean.getDescrizione() %></p>      
                    <p>Quantità disponibile: <%= tipologiaBean.getQuantita() %></p>
                    <ul>
                        <% List<String> usernames = tipologiaBean.getUsernames(); %>
                        <% session.setAttribute("list",usernames); %>
                        <% List<Integer> quantitaPrese = tipologiaBean.getQuantitaPrese(); %>
                        <% if (usernames != null && !usernames.isEmpty() && quantitaPrese != null && !quantitaPrese.isEmpty()) { %>
                 <p>Noleggi:</p>
                <ul>
                    <% for (int i = 0; i < usernames.size(); i++) { %>
                        <li><%= usernames.get(i) %> : <%= quantitaPrese.get(i) %></li>
                    <% } %>
                </ul>
            <% } else { %>
                <p>Nessun noleggio in corso</p>
            <% } %>
                    <div class="actions">
                      <form action="<%=JView.ModificaEquipaggiamentoCTL%>" method="post">
                            <input type="hidden" name="tipologia" value="<%= tipologia %>">
                            <label for="newQuantita"></label>
                            <input type="text" id="newQuantita" name="newQuantita" placeholder="Inserisci nuova quantità disponibile" >
                            <input type="submit" value="Aggiorna Quantità" class="submit-button" >
                        </form>
                        <form action="<%=JView.ModificaEquipaggiamentoCTL%>" method="get">
                            <input type="hidden" name="tipologia" value="<%= tipologia %>">
                            <input type="submit" value="Visualizza Recensioni" class="submit-button" >
                        </form>
                          <form action="<%=JView.VisualizzaEquipaggiamentoCTL%>" method="post" class="elimina">
                            <input type="hidden" name="tipo" value="<%= tipologia %>">
                          <input type="submit" value="Elimina" class="submit-button"  style="background-color: red;">

                        </form>
                    </div>
                                  
                </div>
            <% } %>
        <% } else { %>
            <p>Nessun oggetto disponibile</p>
        <% } %>
    </div>
</body>
</html>

