<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Farm Dashboard</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <h1>Acres Commune</h1>
    </header>
    <nav>
        <span style="cursor:pointer" onclick="openNav()">&#9776; More</span>
        <a href="home.html">Home</a>
        <a href="readprofile.jsp">Profile</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="aboutus.html">About Us</a>
        <a href="logout.jsp">Logout</a>
    </nav>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="readtask.jsp">Tasks</a>
        <a href="readequipment.jsp">Equipments</a>
        <a href="readcrops.jsp">Crops</a>
    </div>
   
    <footer>
        <p>&copy; 2024 Community Farming Management System. All rights reserved.</p>
    </footer>
    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .dashboard-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }
        .card {
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 20px;
            margin: 10px;
            text-align: center;
            width: 200px;
        }
        .chart-container {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <main>
        <div class="dashboard-container">
            <div class="card">
                <h2>Total Acres</h2>
                <p id="total-acres">Loading...</p>
            </div>
            <div class="card">
                <h2>Total Crops</h2>
                <p id="total-crops">Loading...</p>
            </div>
            <div class="card">
                <h2>Total Equipment</h2>
                <p id="total-equipment">Loading...</p>
            </div>
            <div class="chart-container">
                <canvas id="cropChart"></canvas>
            </div>
        </div>
    </main>

    <script>
       
    </script>
</body>
</html>
