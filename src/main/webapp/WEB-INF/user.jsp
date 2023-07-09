<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>TODO List - Workspaces</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
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

      .modal-body input {
          background-color: #555;
          color: #fff;
          border: none;
      }

      a {
          text-decoration: none;
          color: white;
      }

      .center {
          margin: 15px auto;
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
  <h2 class="mt-4">
    <a href="./workspaces">
        <span class="material-symbols-outlined">
          arrow_back
        </span>
        User Data
    </a>
  </h2>
  <div class="col-md-6 center">
    <form action="update-user" method="post">
      <div class="mb-3">
        <label for="name" class="form-label">Name:</label>
        <input type="text" id="name" name="name" class="form-control" value="${user.name}">
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input type="email" id="email" name="email" class="form-control" value="${user.email}">
      </div>
      <div class="mb-3">
        <label for="newPassword" class="form-label">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" class="form-control">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input type="password" id="password" name="password" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="confirmPassword" class="form-label">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
      </div>
      <div class="mb-3">
        <input type="submit" value="Save" class="btn btn-primary">
      </div>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>