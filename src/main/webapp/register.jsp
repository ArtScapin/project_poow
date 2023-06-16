<!DOCTYPE html>
<html>
<head>
  <title>TODO List - Registration</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body {
      background-color: #222;
      color: #fff;
      font-family: Arial, sans-serif;
    }

    .container {
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="col-md-6">
    <h2 class="text-center">Sign Up</h2>
    <form action="register-user" method="post">
      <div class="mb-3">
        <label for="name" class="form-label">Name:</label>
        <input type="text" id="name" name="name" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input type="email" id="email" name="email" class="form-control" required>
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
        <input type="submit" value="Register" class="btn btn-primary">
      </div>
      <div class="mb-3 text-center">
        <p>Already have an account? <a href="./">Log In</a></p>
      </div>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
