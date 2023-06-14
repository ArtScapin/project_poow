<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Dark Theme</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #222;
            color: #fff;
            font-family: Arial, sans-serif;
        }

        .navbar {
            background-color: #333;
        }

        .navbar-brand {
            color: #fff;
        }

        .navbar-text {
            color: #fff;
        }

        .workspace-list {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        .workspace-list li {
            margin: 10px;
            background-color: #333;
            padding: 10px;
            border-radius: 5px;
        }

        .workspace-list li:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Dashboard</a>
        <div class="navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <span class="navbar-text">Logged in as John Doe</span>
                    <i class="fas fa-user-circle fa-lg"></i>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-6">
        <h2 class="text-center">Dashboard</h2>
        <h4 class="text-center">My Workspaces:</h4>
        <ul class="workspace-list">
            <li>Workspace 1</li>
            <li>Workspace 2</li>
            <li>Workspace 3</li>
        </ul>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
