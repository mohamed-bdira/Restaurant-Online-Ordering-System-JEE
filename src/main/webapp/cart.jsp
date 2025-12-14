<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your Cart | Tasty Bites</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f8f9fa; }
        .table-custom th { border-top: none; color: #6c757d; font-weight: 500; text-transform: uppercase; font-size: 0.85rem; }
        .table-custom td { vertical-align: middle; }
        .summary-card { border: none; border-radius: 15px; background: white; }
    </style>
</head>
<body>

<nav class="navbar navbar-dark bg-dark mb-4 shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="menu"><i class="bi bi-arrow-left"></i> Back to Menu</a>
        <span class="navbar-text text-white">Shopping Cart</span>
    </div>
</nav>

<div class="container mt-5">
    <c:if test="${empty sessionScope.cart.items}">
        <div class="text-center py-5">
            <i class="bi bi-basket3 display-1 text-muted mb-3"></i>
            <h3 class="text-muted">Your cart is empty</h3>
            <a href="menu" class="btn btn-dark btn-lg mt-3 rounded-pill px-4">Start Ordering</a>
        </div>
    </c:if>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="row">
            <div class="col-lg-8">
                <div class="card shadow-sm border-0 mb-4">
                    <div class="card-body p-0">
                        <table class="table table-custom table-hover mb-0">
                            <thead class="bg-light">
                            <tr>
                                <th class="ps-4 py-3">Item</th>
                                <th>Price</th>
                                <th>Qty</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cartItem" items="${sessionScope.cart.items}">
                                <tr>
                                    <td class="ps-4 fw-bold text-dark">${cartItem.menuItem.name}</td>
                                    <td class="text-muted">$${cartItem.menuItem.price}</td>
                                    <td><span class="badge bg-light text-dark border">${cartItem.quantity}</span></td>
                                    <td class="fw-bold">$${cartItem.totalPrice}</td>
                                    <td class="text-end pe-4">
                                        <form action="cart" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="remove">
                                            <input type="hidden" name="itemId" value="${cartItem.menuItem.id}">
                                            <button type="submit" class="btn btn-link text-danger p-0" title="Remove">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="card summary-card shadow-sm p-3">
                    <div class="card-body">
                        <h5 class="card-title fw-bold mb-4">Order Summary</h5>
                        <div class="d-flex justify-content-between mb-2">
                            <span class="text-muted">Subtotal</span>
                            <span class="fw-bold">$${sessionScope.cart.total}</span>
                        </div>
                        <div class="d-flex justify-content-between mb-4">
                            <span class="text-muted">Delivery</span>
                            <span class="text-success">Free</span>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between mb-4">
                            <span class="fs-5 fw-bold">Total</span>
                            <span class="fs-4 fw-bold text-success">$${sessionScope.cart.total}</span>
                        </div>
                        <a href="checkout.jsp" class="btn btn-dark w-100 py-2 rounded-pill fw-bold shadow-sm">
                            Checkout <i class="bi bi-arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>