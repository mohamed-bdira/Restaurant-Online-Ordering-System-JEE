<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu | Tasty Bites</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f8f9fa; }
        .hero-section {
            background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=1920&q=80');
            background-size: cover;
            background-position: center;
            color: white;
            padding: 80px 0;
            margin-bottom: 40px;
            border-radius: 0 0 20px 20px;
        }
        .food-card {
            border: none;
            border-radius: 15px;
            transition: all 0.3s ease;
            background: white;
            overflow: hidden;
        }
        .food-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .price-tag {
            font-size: 1.25rem;
            font-weight: 600;
            color: #198754;
        }
        .btn-custom {
            border-radius: 50px;
            padding: 8px 20px;
            font-weight: 500;
        }
        .navbar-brand { font-weight: 600; letter-spacing: 1px; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="menu"><i class="bi bi-fire text-warning"></i> Tasty Bites</a>
        <div class="d-flex">
            <a href="cart" class="btn btn-outline-light btn-custom position-relative">
                <i class="bi bi-cart3"></i> Cart
                <c:if test="${not empty sessionScope.cart.items}">
                    <span class="position-absolute top-0 start-100 translate-middle p-1 bg-danger border border-light rounded-circle"></span>
                </c:if>
            </a>
        </div>
    </div>
</nav>

<div class="hero-section text-center">
    <div class="container">
        <h1 class="display-4 fw-bold">Hungry? We've got you.</h1>
        <p class="lead">Fresh ingredients, delivered hot to your door.</p>
    </div>
</div>

<div class="container pb-5">
    <div class="row g-4">
        <c:forEach var="item" items="${menuList}">
            <div class="col-md-4 col-sm-6">
                <div class="card h-100 food-card shadow-sm">
                    <div style="height: 200px; background-color: #eee; display: flex; align-items: center; justify-content: center; overflow: hidden;">
                        <c:choose>
                            <c:when test="${not empty item.imageUrl}">
                                <img src="${item.imageUrl}" alt="${item.name}" style="width:100%; height:100%; object-fit: cover;">
                            </c:when>
                            <c:otherwise>
                                <i class="bi bi-image text-muted" style="font-size: 3rem;"></i>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="card-body d-flex flex-column">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <h5 class="card-title fw-bold mb-0">${item.name}</h5>
                            <span class="badge bg-light text-dark border">${item.category}</span>
                        </div>
                        <p class="card-text text-muted small flex-grow-1">${item.description}</p>

                        <div class="d-flex align-items-center justify-content-between mt-3 pt-3 border-top">
                            <span class="price-tag">$${item.price}</span>
                            <form action="cart" method="post" class="d-flex align-items-center">
                                <input type="hidden" name="itemId" value="${item.id}">
                                <input type="hidden" name="action" value="add">
                                <input type="number" name="quantity" value="1" min="1" max="10" class="form-control form-control-sm me-2" style="width: 60px;">
                                <button type="submit" class="btn btn-dark btn-custom btn-sm">
                                    Add <i class="bi bi-plus-lg"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>