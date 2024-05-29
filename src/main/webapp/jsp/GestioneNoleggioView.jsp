<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tenniswebapp.controller.JView" %>  
<%@ page import="tenniswebapp.utility.ServletUtility" %>
<%@ page import="tenniswebapp.model.OggettoBean" %> 
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <title>Elenco equipaggiamento </title>
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
            margin-left: 365px;
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
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .rectangle {
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
            <form class="search-form" action="<%=JView.SearchCTL %>" method="get">
                <input type="text" name="search" placeholder="Cerca equipaggiamento...">
                <input type="submit" value="Cerca" class="submit-button" style="background-color:#aaaaaa;">
            </form>
        </div>
    </header>
    <div class="container">
        <h1> Equipaggiamento disponibile per il torneo</h1>
        <% Map<String, OggettoBean> mymap = (Map<String, OggettoBean>) session.getAttribute("mymap"); %>
         <% if (mymap != null && !mymap.isEmpty()) { %>       
            
            <% for (Map.Entry<String, OggettoBean> entry : mymap.entrySet()) { %>
                <% String tipologia = entry.getKey(); %>
                <% OggettoBean oggetto = entry.getValue(); %>
                <div class="rectangle">
                    <p>Tipologia: <%= tipologia %></p>
                    <p>Quantità disponibile: <%= oggetto.getQuantita() %></p>
                    <p>Descrizione: <%= oggetto.getDescrizione() %></p>
                    
                    <form action="<%=JView.GestioneNoleggioCTL%>" method="post">
                        <% if (oggetto.getQuantita() > 0) { %>
                            <input type="hidden" name="tipologia" value="<%= tipologia %>">
                           <input type="hidden" name="disponibile" value="<%= oggetto.getQuantita() %>">
                            <input type="text" name="quantitapresa" placeholder="Inserisci quantità" required>
                            <input type="submit" value="Noleggia" class="submit-button"  >
                        <% } %>
                    </form>
                    
                    <form action="<%=JView.RecensioniCTL%>" method="get">
                        <input type="hidden" name="tipologia" value="<%= tipologia %>">
                        <input type="submit" value="Vai alla pagina delle recensioni" class="submit-button"  >
                    </form>
                </div>
            <% } %>
        <% } else { %>
            <p>Nessun oggetto disponibile</p>
        <% } %>
    </div>
</body>
</html>
