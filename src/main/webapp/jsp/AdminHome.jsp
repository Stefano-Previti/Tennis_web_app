<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="tenniswebapp.controller.JView" %>
    
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ffffff; 
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .user-info {
            flex-basis: 100%;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        .user-info h2 {
            margin-top: 0;
        }

        .user-data {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .user-data-block {
            flex-basis: 30%;
        }

        .images-column {
            flex-basis: 100%;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .image-box {
            flex-basis: calc(33.33% - 20px); 
            background-size: cover;
            background-position: center;
            height: 300px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            position: relative;
        }

 .image-box.manage {
            background-image: url('/tenniswebapp/image/gestione_equipaggiamento.jpg');
}

.image-box.view-equipment {
    background-image: url('/tenniswebapp/image/visualizzazione_equipaggiamento.jpg');
}

.image-box.view-tournament {
    background-image: url('/tenniswebapp/image/gestione_torneo.jpg');
}

        .image-box .image-button {
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%); 
        }
        .image-button .button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none; 
            display: inline-block; 
        }
        
        .image-box .image-button button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

     

        .logout-button {
            padding: 10px 20px;
            background-color: #dc3545;
           
        }
                       .logout-button {
    background-color: red;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
}

.logout-button:hover {
    background-color: red;
}
    </style>
</head>
<body>
       <a href="<%=JView.LogoutCTL %>"   class="logout-button">Logout</a>
  <div class="container">
        <div class="user-info">
            <h2>Dati Utente</h2>
            <div class="user-data">
                <div class="user-data-block">
                    <p>Nome: <%=session.getAttribute("nome") %></p>
                    <p>Cognome: <%=session.getAttribute("cognome") %></p>
                </div>
                <div class="user-data-block">
                    <p>Data di nascita: <%=session.getAttribute("Data di nascita") %></p>
                    <p>Ruolo: <%=session.getAttribute("Ruolo") %></p>
                </div>
                <div class="user-data-block">
                    <p>Nome Torneo: <%=session.getAttribute("Nome torneo") %></p>
                </div>
            </div>
        </div>
    </div>
    <div class="container images-column">
        <div class="image-box view-tournament">
            <div class="image-button">
                 <a href="<%=JView.GestioneTorneoCTL %>" class="button">Gestione torneo</a>
            </div>
        </div>

        <div class="image-box manage"> 
            <div class="image-button">
                 <a href="<%=JView.GestioneEquipaggiamentoCTL %>" class="button">Gestione equipaggiamento</a>
            </div>
        </div>

        <div class="image-box view-equipment"> 
            <div class="image-button">
                 <a href="<%=JView.VisualizzaEquipaggiamentoCTL %>" class="button">Visualizza equipaggiamento</a>
            </div>
        </div>
    </div>
</body>
</html>