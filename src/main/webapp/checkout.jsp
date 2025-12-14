<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Checkout | Tasty Bites</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f8f9fa; }

        .section-title { font-weight: 600; color: #343a40; margin-bottom: 20px; }

        /* Left Column: Form */
        .form-card { border: none; border-radius: 15px; background: white; }
        .form-control:focus { border-color: #198754; box-shadow: 0 0 0 0.25rem rgba(25, 135, 84, 0.25); }
        .input-group-text { background: #fff; border-right: none; color: #6c757d; }
        .form-control { border-left: none; }

        /* Right Column: Summary */
        .summary-card { border: none; border-radius: 15px; background: white; }
        .item-row { font-size: 0.9rem; border-bottom: 1px solid #f0f0f0; padding: 10px 0; }
        .item-row:last-child { border-bottom: none; }

        .btn-place-order {
            background-color: #198754;
            border: none;
            transition: all 0.3s ease;
        }
        .btn-place-order:hover {
            background-color: #146c43;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(20, 108, 67, 0.3);
        }
    </style>
</head>
<body>

<nav class="navbar navbar-dark bg-dark mb-4 shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="cart"><i class="bi bi-arrow-left"></i> Back to Cart</a>
        <span class="navbar-text text-white"><i class="bi bi-shield-lock-fill"></i> Secure Checkout</span>
    </div>
</nav>

<div class="container py-4">
    <form action="checkout" method="post">
        <div class="row g-5">

            <div class="col-md-7 col-lg-8">
                <h4 class="section-title"><i class="bi bi-truck"></i> Delivery Details</h4>

                <div class="card form-card shadow-sm p-4 mb-4">
                    <div class="row g-3">
                        <div class="col-12">
                            <label class="form-label text-muted small">Full Name</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-person"></i></span>
                                <input type="text" class="form-control" name="name" placeholder="John Doe" required>
                            </div>
                        </div>

                        <div class="col-12">
                            <label class="form-label text-muted small">Email Address</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                <input type="email" class="form-control" name="email" placeholder="john@example.com" required>
                            </div>
                        </div>

                        <div class="col-12">
                            <label class="form-label text-muted small">Phone Number</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                                <input type="tel" class="form-control" name="phone" placeholder="+1 234 567 8900" required>
                            </div>
                        </div>

                        <div class="col-12">
                            <label class="form-label text-muted small">Delivery Address</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                                <textarea class="form-control" name="address" rows="3" placeholder="123 Main St, Apartment 4B" required></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <h4 class="section-title mt-5"><i class="bi bi-credit-card"></i> Payment</h4>
                <div class="card form-card shadow-sm p-4">
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="radio" name="paymentMethod" id="cod" checked>
                        <label class="form-check-label fw-bold" for="cod">
                            Cash on Delivery
                        </label>
                        <div class="text-muted small">Pay securely when your food arrives.</div>
                    </div>
                    <div class="form-check opacity-50">
                        <input class="form-check-input" type="radio" name="paymentMethod" id="card" disabled>
                        <label class="form-check-label" for="card">
                            Credit Card (Currently Unavailable)
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-md-5 col-lg-4">
                <h4 class="section-title d-flex justify-content-between align-items-center">
                    <span>Your Order</span>
                    <span class="badge bg-primary rounded-pill">${sessionScope.cart.items.size()}</span>
                </h4>

                <div class="card summary-card shadow-sm p-3">
                    <div class="card-body">
                        <div class="mb-3">
                            <c:forEach var="item" items="${sessionScope.cart.items}">
                                <div class="item-row d-flex justify-content-between">
                                    <div>
                                        <h6 class="my-0">${item.menuItem.name}</h6>
                                        <small class="text-muted">Qty: ${item.quantity}</small>
                                    </div>
                                    <span class="text-muted">$${item.totalPrice}</span>
                                </div>
                            </c:forEach>
                        </div>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between mb-2">
                            <span class="text-muted">Subtotal</span>
                            <strong>$${sessionScope.cart.total}</strong>
                        </div>
                        <div class="d-flex justify-content-between mb-4">
                            <span class="text-muted">Delivery Fee</span>
                            <span class="text-success fw-bold">Free</span>
                        </div>

                        <div class="d-flex justify-content-between align-items-center p-3 bg-light rounded mb-3">
                            <span class="h5 mb-0">Total (USD)</span>
                            <strong class="h4 mb-0 text-success">$${sessionScope.cart.total}</strong>
                        </div>

                        <button class="btn btn-place-order w-100 btn-lg text-white fw-bold py-3" type="submit">
                            Place Order <i class="bi bi-check-lg"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>