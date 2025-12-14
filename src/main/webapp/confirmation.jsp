<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order Confirmed | Tasty Bites</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .success-card {
            border: none;
            border-radius: 20px;
            background: white;
            padding: 40px;
            max-width: 500px;
            width: 100%;
            position: relative;
            overflow: hidden;
        }

        /* Animated Success Icon */
        .icon-circle {
            width: 80px;
            height: 80px;
            background-color: #d1e7dd;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px auto;
            color: #198754;
            font-size: 40px;
            animation: popIn 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        }

        .order-id-box {
            background-color: #f8f9fa;
            border: 2px dashed #dee2e6;
            border-radius: 10px;
            padding: 15px;
            margin: 20px 0;
        }

        .btn-home {
            background-color: #343a40;
            border: none;
            border-radius: 50px;
            padding: 12px 30px;
            font-weight: 600;
            transition: transform 0.2s;
        }
        .btn-home:hover {
            background-color: #212529;
            transform: scale(1.05);
        }

        @keyframes popIn {
            0% { transform: scale(0); opacity: 0; }
            100% { transform: scale(1); opacity: 1; }
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-auto">
            <div class="card success-card shadow-lg text-center">

                <div class="icon-circle">
                    <i class="bi bi-check-lg"></i>
                </div>

                <h2 class="fw-bold text-success mb-3">Order Placed!</h2>
                <p class="text-muted mb-4">
                    Thank you for ordering with <strong>Tasty Bites</strong>.<br>
                    Your food is being prepared right now! 🍳
                </p>

                <div class="order-id-box">
                    <small class="text-uppercase text-muted fw-bold">Order Reference</small>
                    <div class="fs-4 fw-bold text-dark">#${requestScope.orderId}</div>
                </div>

                <p class="small text-muted mb-4">You will receive an email confirmation shortly.</p>

                <a href="menu" class="btn btn-home text-white shadow-sm">
                    <i class="bi bi-arrow-left"></i> Back to Menu
                </a>

            </div>
        </div>
    </div>
</div>

</body>
</html>