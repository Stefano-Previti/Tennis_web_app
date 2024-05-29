<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="tenniswebapp.controller.JView" %>
 <%@ page import="tenniswebapp.utility.ServletUtility" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
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
            max-height: 800px;
            overflow-y: auto;
            overflow-x: hidden;
            
        }

        h1, p {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"],
        select {
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
        <h1>Registrazione</h1>
        <form action="<%=JView.RegistrationCTL%>" method="POST">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" required>

            <label for="data di nascita">Data di nascita:</label>
            <input type="text" id="data di nascita" name="datadinascita" required>

            <label for="ruolo">Ruolo:</label>
            <select id="ruolo" name="ruolo">
                <option value="admin">admin</option>
                <option value="player">player</option>
            </select>

            <label for="punti">Punti accumulati(per i sorteggi):</label>
            <input type="text" id="punti" name="punti" placeholder="Inserisci se sei un giocatore" style="width: 250px; padding: 5px;"> 

            <label for="nomeTorneo">Nome torneo:</label>
            <input type="text" id="nomeTorneo" name="nomeTorneo" required>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confermaPassword">Conferma password:</label>
            <input type="password" id="confermaPassword" name="confermaPassword" required>
  
            <input type="submit" value="Registrati" class="submit-button" >
        </form>
       <p>Hai già un account? <a href="<%=JView.RegistrationCTL%>"> Accedi</a></p>
       <p class="error-message"> <%=ServletUtility.getErrorName(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorSurname(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorDOB(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorPoints(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorAdminwithPoints(request)%></p>
       <p class="error-message"> <%=ServletUtility.getErrorTournament(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorFullTournament(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorTorneoCompletato(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorAdminExists(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorUsernameFormat(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorUsernameExists(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorPassword(request) %></p>
       <p class="error-message"> <%=ServletUtility.getErrorConfirmedPassword(request)%></p>  
    </div>
</body>
</html>
