<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>TODO List - Workspaces</title>
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
            text-align: center;
        }

        .workspace-list li {
            margin: 10px;
            background-color: #333;
            padding: 10px;
            border-radius: 5px;
            position: relative;
        }

        .workspace-list li:hover {
            background-color: #555;
        }

        .workspace-list li::after {
            content: '\f054';
            font-family: 'Font Awesome 5 Free';
            font-weight: 900;
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
        }

        .add-workspace-button {
            float: right;
        }

        .modal-content {
            background-color: #333;
            color: #fff;
        }

        .modal-header {
            border-bottom: none;
        }

        .modal-title {
            color: #fff;
        }

        .modal-body input[type="text"] {
            background-color: #555;
            color: #fff;
            border: none;
        }

        .modal-footer {
            border-top: none;
        }

        a {
            text-decoration: none;
            color: white;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <span class="navbar-brand">TODO List</span>
        <div class="navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <span class="navbar-text">${user.name} </span>
                        <i class="fas fa-user-circle fa-lg"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                        <li><a class="dropdown-item" href="#">Edit Profile</a></li>
                        <li><a class="dropdown-item" href="./logout-user">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-12">
        <button class="btn btn-primary add-workspace-button" data-bs-toggle="modal" data-bs-target="#workspaceModal"><i class="fas fa-plus"></i></button>
        <h2 class="mt-4">My Workspaces</h2>
        <ul class="workspace-list">
            <c:forEach var="workspace" items="${workspaces}">
                <a href="list?workspace=${workspace.id}"><li>Workspace ${workspace.name}</li></a>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- Workspace Modal -->
<div class="modal fade" id="workspaceModal" tabindex="-1" aria-labelledby="workspaceModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="workspaceModalLabel">Create Workspace</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="register-workspace">
                    <div class="mb-3">
                        <label for="name" class="form-label">Workspace Name:</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>