<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acres Commune</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Lato:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <h1>Dashboard</h1>
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
 
</head>
<body>
    <main>
        <div class="dashboard-container">
            <div class="card">
                <h2>Total Products</h2>
                <p id="total-products"><%= request.getAttribute("totalProducts") %></p>
            </div>
            <div class="card">
                <h2>Total Crops</h2>
                <p id="total-crops"><%= request.getAttribute("totalCrops") %></p>
            </div>
            <div class="card">
                <h2>Total Equipment</h2>
                <p id="total-equipment"><%= request.getAttribute("totalEquipment") %></p>
            </div>
            <div class="chart-container">
                <canvas id="cropChart"></canvas>
            </div>
        </div>
    </main>
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
</body>
</html>
