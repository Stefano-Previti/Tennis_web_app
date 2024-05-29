<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="tenniswebapp.controller.JView" %>  
   <%@ page import="tenniswebapp.utility.TorneoUtility" %>
    <%@ page import="tenniswebapp.utility.ServletUtility" %>
   
  

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tabellone torneo</title>
   <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f0f0f0;
    }

    /* Header stile */
    header {
      padding: 10px;
    }

    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      max-height: 150vh;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
    }

    .sorteggi-button button,
    .sorteggi-button a {
      display: inline-block;
      padding: 10px 20px;
      background-color: #333;
      color: #fff;
      text-decoration: none;
      border: none;
      border-radius: 5px;
      cursor: pointer;
       margin-bottom: 20px;
      
    }

    .sorteggi-button button:hover,
    .sorteggi-button a:hover {
      background-color: #444;
    }

    form {
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-bottom: 30px;
    }

    label {
      margin-top: 10px;
    }

    .form-group {
      display: flex;
      align-items: center;
      margin-bottom: 4px;
    }

    .form-group label {
      margin-right: 10px;
    }

    input[type="text"], input[type="number"] {
      width: 130px;
      padding: 5px;
    }

    input[type="submit"] {
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
   .error-message-container {
      position: relative;
    }
   .errMessage {
      color: red;
  
    }
    .error-message {
      position: absolute;
      top: 0;
      right: -230px; /* Distanza a destra dal form */
      color: red;
      font-size: 12px;
      margin: 0;
    }
    
    .succMessage {
      color: green;
    }
    .form-container {
  width: 550px; 
}
.nota {
      position: absolute;
      top: 10px;
      right: 10px;
      background-color: #f0f0f0;
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    
  </style>
</head>
<body>
  <header>
    <a href="<%=JView.AdminHomeCTL %>">AdminHome</a>
  </header>
  <div class="container">
  <div class="nota">
      <p>Elenco risultati validi: 3-0, 3-1, 3-2, 0-3, 1-3, 2-3</p>
    </div>
    <h1>Tabellone torneo</h1>
     <div class="sorteggi-button">
        <a href="<%=JView.SorteggiCTL%>">Sorteggi</a>
      </div>
    <form action="<%=JView.RisultatiQuartiCTL%>" method="post">
      <div class="form-group error-message-container">
        <label for="partita1_giocatore1">Partita 1:</label>
       <input type="text" id="partita1_giocatore1" name="partita1_giocatore1"<% if(session.getAttribute("playerA1") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerA1") %>"readonly<% } %>>  
       <input type="text" id="partita1_giocatore2" name="partita1_giocatore2"<% if(session.getAttribute("playerB1") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerB1") %>"readonly<% } %>>     
         <% if (session.getAttribute("ris1") != null) { %> <input type="text" id="partita1_risultato_lettura" name="partita1_risultato_lettura" value="<%= session.getAttribute("ris1") %>" readonly> <% } else { %> <input type="text" id="partita1_risultato_lettura" name="partita1_risultato_lettura" value="Da giocare" readonly><% } %>
        <input type="text" id="partita1_risultato" name="partita1_risultato" placeholder="Aggiorna risultato">
       <p class="error-message"><%=ServletUtility.getErrorRisultatoPartita1(request) %></p>
       
      </div>
      <div class="form-group error-message-container">
        <label for="partita2_giocatore1">Partita 2:</label>
       <input type="text" id="partita2_giocatore1" name="partita2_giocatore1"<% if(session.getAttribute("playerA2") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerA2") %>"readonly<% } %>>  
       <input type="text" id="partita2_giocatore2" name="partita2_giocatore2"<% if(session.getAttribute("playerB2") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerB2") %>"readonly<% } %>>     
       <% if (session.getAttribute("ris2") != null) { %> <input type="text" id="partita2_risultato_lettura" name="partita2_risultato_lettura" value="<%= session.getAttribute("ris2") %>" readonly> <% } else { %> <input type="text" id="partita2_risultato_lettura" name="partita2_risultato_lettura" value="Da giocare" readonly><% } %>
       <input type="text" id="partita2_risultato" name="partita2_risultato" placeholder="Aggiorna risultato">
       <p class="error-message"><%=ServletUtility.getErrorRisultatoPartita2(request) %></p>
      </div>
      <div class="form-group error-message-container">
        <label for="partita3_giocatore1">Partita 3:</label>
        <input type="text" id="partita3_giocatore1" name="partita3_giocatore1"<% if(session.getAttribute("playerA3") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerA3") %>"readonly<% } %>>  
       <input type="text" id="partita3_giocatore2" name="partita3_giocatore2"<% if(session.getAttribute("playerB3") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerB3") %>"readonly<% } %>>     
       <% if (session.getAttribute("ris3") != null) { %> <input type="text" id="partita3_risultato_lettura" name="partita3_risultato_lettura" value="<%= session.getAttribute("ris3") %>" readonly> <% } else { %> <input type="text" id="partita3_risultato_lettura" name="partita3_risultato_lettura" value="Da giocare" readonly><% } %>
       <input type="text" id="partita3_risultato" name="partita3_risultato" placeholder="Aggiorna risultato">
        <p class="error-message"> <%=ServletUtility.getErrorRisultatoPartita3(request) %></p>
      </div>

      <div class="form-group error-message-container">
        <label for="partita4_giocatore1">Partita 4:</label>
        <input type="text" id="partita4_giocatore1" name="partita4_giocatore1"<% if(session.getAttribute("playerA4") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerA4") %>"readonly<% } %>>  
       <input type="text" id="partita4_giocatore2" name="partita4_giocatore2"<% if(session.getAttribute("playerB4") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerB4") %>"readonly<% } %>>     
       <% if (session.getAttribute("ris4") != null) { %> <input type="text" id="partita4_risultato_lettura" name="partita4_risultato_lettura" value="<%= session.getAttribute("ris4") %>" readonly> <% } else { %> <input type="text" id="partita4_risultato_lettura" name="partita4_risultato_lettura" value="Da giocare" readonly><% } %>
       <input type="text" id="partita4_risultato" name="partita4_risultato" placeholder="Aggiorna risultato">
       <p class="error-message"> <%=ServletUtility.getErrorRisultatoPartita4(request) %></p>         
      </div>
      <input type="submit" value="Registra risultati quarti">
    </form> 
       <p class="errMessage">  <%=ServletUtility.getErrorSorteggi(request) %> <%=ServletUtility.getErrorSorteggiNonRegistrati(request) %></p>
       <form action="<%=JView.RisultatiSemifinaliCTL%>" method="post">
      <div class="form-group error-message-container">
        <label for="partita5_giocatore1">Partita 5:</label>
        <input type="text" id="partita5_giocatore1" name="partita5_giocatore1"<% if(session.getAttribute("playerVincente1") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerVincente1") %>"readonly<% } %>>  
       <input type="text" id="partita5_giocatore2" name="partita5_giocatore2"<% if(session.getAttribute("playerVincente2") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerVincente2") %>"readonly<% } %>>
        <% if (session.getAttribute("ris5") != null) { %> <input type="text" id="partita5_risultato_lettura" name="partita5_risultato_lettura" value="<%= session.getAttribute("ris5") %>" readonly> <% } else { %> <input type="text" id="partita5_risultato_lettura" name="partita5_risultato_lettura" value="Da giocare" readonly><% } %>
        <input type="text" id="partita5_risultato" name="partita5_risultato" placeholder="Aggiorna risultato">
        <p class="error-message"> <%=ServletUtility.getErrorRisultatoPartita5(request) %></p>
      </div>

      <div class="form-group error-message-container">
        <label for="partita6_giocatore1">Partita 6:</label>
        <input type="text" id="partita6_giocatore1" name="partita6_giocatore1"<% if(session.getAttribute("playerVincente3") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerVincente3") %>"readonly<% } %>>  
       <input type="text" id="partita6_giocatore2" name="partita6_giocatore2"<% if(session.getAttribute("playerVincente4") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerVincente4") %>"readonly<% } %>>
        <% if (session.getAttribute("ris6") != null) { %> <input type="text" id="partita6_risultato_lettura" name="partita6_risultato_lettura" value="<%= session.getAttribute("ris6") %>" readonly> <% } else { %> <input type="text" id="partita6_risultato_lettura" name="partita6_risultato_lettura" value="Da giocare" readonly><% } %>
        <input type="text" id="partita6_risultato" name="partita6_risultato" placeholder="Aggiorna risultato">
        <p class="error-message"> <%=ServletUtility.getErrorRisultatoPartita6(request) %></p>
      </div>
      <input type="submit" value="Registra risultati semifinali">
    </form>
    <p class="errMessage">  <%=ServletUtility.getErrorQuartiNonRegistrati(request) %></p>  
    <form action="<%=JView.RisultatoFinaleCTL%>" method="post">
      <div class="form-group error-message-container">
        <label for="partita7_giocatore1">Partita 7:</label>
       <input type="text" id="partita7_giocatore1" name="partita7_giocatore1"<% if(session.getAttribute("playerVincente5") == null) { %>value="Nome giocatore 1"<% } else { %>value="<%= session.getAttribute("playerVincente5") %>"readonly<% } %>>  
       <input type="text" id="partita7_giocatore2" name="partita7_giocatore2"<% if(session.getAttribute("playerVincente6") == null) { %>value="Nome giocatore 2"<% } else { %>value="<%= session.getAttribute("playerVincente6") %>"readonly<% } %>>
        <% if (session.getAttribute("ris7") != null) { %> <input type="text" id="partita7_risultato_lettura" name="partita7_risultato_lettura" value="<%= session.getAttribute("ris7") %>" readonly> <% } else { %> <input type="text" id="partita7_risultato_lettura" name="partita7_risultato_lettura" value="Da giocare" readonly><% } %>
        <input type="text" id="partita7_risultato" name="partita7_risultato" placeholder="Aggiorna risultato">
       <p class="error-message"> <%=ServletUtility.getErrorRisultatoPartita7(request) %></p>
      </div>
      <input type="submit" value="Registra risultato finale">
    </form> 
    <p class="errMessage">  <%=ServletUtility.getErrorSemifinaliNonRegistrate(request) %></p>  
      <% if (session.getAttribute("ris7") != null) { %>
      <p class="succMessage"> Il vincitore del Torneo è: <%= session.getAttribute("playerVincente7") %></p>
      <form action="<%=JView.AdminNewTorneoCTL%>" method="post" class="form-container">
        <input type="text" name="nomeTorneo" placeholder="Vuoi iniziare un nuovo torneo? Scrivi qui il nome del nuovo torneo che vuoi creare"style="width: 500px; padding: 5px;">
        <input type="submit" value="Invia">
      </form>
       <p class="errMessage"> <%=ServletUtility.getErrorAdminExists(request) %></p>
       <p class="errMessage"> <%=ServletUtility.getErrorTournament(request) %></p>   
       
    <% } %>
  </div>
</body>
</html>





