<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tenniswebapp.controller.JView" %>
 <%@ page import="tenniswebapp.utility.ServletUtility" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Torneo a 8 giocatori - Tabellone</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        h1 {
            text-align: center;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }
        
        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }
        
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        
        .back-link {
            position: absolute;
            top: 20px;
            left: 20px;
            font-size: 18px;
        }
        
        /* Aggiungiamo sfumature di colore alle celle */
        td {
            background-color: #f9f9f9;
        }
        
        /* Stilizziamo i titoli delle tabelle */
        .table-title {
            font-weight: bold;
            background-color: #e0e0e0;
        }
        .msg{
        color:green;
        text-align: center;
        
        }
         .msg1{
        color:red;
        text-align: center;
        
        }
        .errMessage{
        color:red;
        text-align: center;
        
        }
    </style>
</head>
<body>
    <div class="container">
        <a class="back-link" href="<%=JView.PlayerHomeCTL %>">PlayerHome</a>
        <h1>Torneo a 8 giocatori - Tabellone</h1>

        <table>
            <tr class="table-title">
                <th>Quarti di finale</th>
                <th>Risultato</th>
            </tr>
            <tr>
            <tr>
            <td><% if (session.getAttribute("A1") != null) { %> <%= session.getAttribute("A1") %> vs <%= session.getAttribute("B1") %><% } else { %>Giocatore A vs Giocatore B <% } %></td>
            <td><% if (session.getAttribute("result1") != null) { %> <%= session.getAttribute("result1") %><% } else { %> Da giocare<% } %></td>
            </tr>
     <tr>
     <td><% if (session.getAttribute("A2") != null) { %> <%= session.getAttribute("A2") %> vs <%= session.getAttribute("B2") %><% } else { %>Giocatore C vs Giocatore D <% } %></td>
    <td><% if (session.getAttribute("result2") != null) { %> <%= session.getAttribute("result2") %><% } else { %> Da giocare<% } %></td>
     </tr>
            

            <tr>
             <td><% if (session.getAttribute("A3") != null) { %> <%= session.getAttribute("A3") %> vs <%= session.getAttribute("B3") %><% } else { %>Giocatore E vs Giocatore F <% } %></td>
             <td><% if (session.getAttribute("result3") != null) { %> <%= session.getAttribute("result3") %><% } else { %> Da giocare<% } %></td>
            </tr>
            <tr>
              <td><% if (session.getAttribute("A4") != null) { %> <%= session.getAttribute("A4") %> vs <%= session.getAttribute("B4") %><% } else { %>Giocatore G vs Giocatore H <% } %></td>
              <td><% if (session.getAttribute("result4") != null) { %> <%= session.getAttribute("result4") %><% } else { %> Da giocare<% } %></td>
            </tr>
        </table>

        <table>
            <tr class="table-title">
                <th>Semifinali</th>
                <th>Risultato</th>
            </tr>
            <tr>
            <td><% if (session.getAttribute("A5") != null) { %> <%= session.getAttribute("A5") %> vs <%= session.getAttribute("B5") %><% } else { %>Vincitore 1 vs Vincitore 2 <% } %></td>
            <td><% if (session.getAttribute("result5") != null) { %> <%= session.getAttribute("result5") %><% } else { %> Da giocare<% } %></td>
            </tr>
            <tr>
         <td><% if (session.getAttribute("A6") != null) { %> <%= session.getAttribute("A6") %> vs <%= session.getAttribute("B6") %><% } else { %>Vincitore 3 vs Vincitore 4  <% } %></td>
         <td><% if (session.getAttribute("result6") != null) { %> <%= session.getAttribute("result6") %><% } else { %> Da giocare<% } %></td>
            </tr>
        </table>

        <table>
            <tr class="table-title">
                <th>Finale</th>
                <th>Risultato</th>
            </tr>
            <tr>
           <td><% if (session.getAttribute("A7") != null) { %> <%= session.getAttribute("A7") %> vs <%= session.getAttribute("B7") %><% } else { %> Finalista 1 vs Finalista 2 <% } %></td>
           <td><% if (session.getAttribute("result7") != null) { %> <%= session.getAttribute("result7") %><% } else { %> Da giocare<% } %></td>
            </tr>
        </table>
       <p class="msg"> <%=ServletUtility.getVincoQuarti(request) %></p>
       <p class="msg1"> <%=ServletUtility.getPerdoQuarti(request) %></p>
       <p class="msg"> <%=ServletUtility.getVincoSemifinali(request) %></p>
       <p class="msg1"> <%=ServletUtility.getPerdoSemifinali(request) %></p>
       <p class="msg"> <%=ServletUtility.getVincoFinale(request)%></p>
       <p class="msg1"> <%=ServletUtility.getPerdoFinale(request) %></p>
        <% if (session.getAttribute("A7") != null) { %>
      <form action="<%=JView.PlayerNewTorneoCTL%>" method="post" class="form-container">
        <input type="text" name="nomeTorneo" placeholder="Vuoi iniziare un nuovo torneo? Scrivi qui il nome del nuovo torneo a cui vuoi iscriverti"style="width: 500px; padding: 5px;">
        <input type="submit" value="Invia">
      </form>
       <p class="errMessage"> <%=ServletUtility.getErrorFullTournament(request) %></p>
       <p class="errMessage"> <%=ServletUtility.getErrorTournament(request) %></p>   

    <% } %>
    </div>
    
</body>
</html>


