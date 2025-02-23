const orderArray = [];

$(document).ready(() => {
    loadCustomers();
    loadItems();
});

function loadCustomers() {
    $.ajax({
        url: "http://localhost:8090/api/v2/customer",
        type: "GET",
        success: function(data) {
            const customerSelect = $("#customerId");
            customerSelect.empty()
                .append('<option value="" disabled selected>Select Customer ID</option>');

            data.forEach(customer => {
                customerSelect.append(`<option value="${customer.id}">${customer.id}</option>`);
            });
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to load customer IDs: " + error
            });
        }
    });
}

$("#customerId").on("change", function() {
    const selectedId = $(this).val();
    if (!selectedId) return;

    $.ajax({
        url: `http://localhost:8090/api/v2/customerFind/${selectedId}`,
        type: "GET",
        success: function(customer) {
            $("#orderCustomer").val(customer.name);
            $("#orderphone").val(customer.contact);
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to fetch customer details: " + error
            });
        }
    });
});

function loadItems() {
    $.ajax({
        url: "http://localhost:8090/api/v2/item",
        type: "GET",
        success: function(data) {
            const itemSelection = $("#itemId");
            itemSelection.empty()
                .append('<option value="" disabled selected>Select Item ID</option>');

            data.forEach(item => {
                itemSelection.append(`<option value="${item.id}">${item.id}</option>`);
            });
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to load item IDs: " + error
            });
        }
    });
}

$("#itemId").on("change", function() {
    const selectedId = $(this).val();
    if (!selectedId) return;

    $.ajax({
        url: `http://localhost:8090/api/v2/findItemDetails/${selectedId}`,
        type: "GET",
        success: function(item) {
            $("#description").val(item.name);
            $("#price").val(item.price);
            $("#quantity").val(item.qty);
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to fetch item details: " + error
            });
        }
    });
});

function validatePhone(phone) {
    const phoneRegex = /^\d{10}$/;  // Assumes 10-digit phone number
    return phoneRegex.test(phone);
}

$("#order-save").on('click', function() {
    const orderData = {
        customerId: $('#customerId').val(),
        orderDate: $('#orderDate').val(),
        name: $('#orderCustomer').val().trim(),
        phone: $('#orderphone').val().trim(),
        itemCode: $('#itemId').val(),
        description: $('#description').val().trim(),
        price: parseFloat($('#price').val()),
        quantity: parseInt($('#quantity').val()),
        orderQuantity: parseInt($('#Getquantity').val()),
        discount: parseInt($('#discount').val()) || 0
    };

    if (!orderData.name) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Customer name is required!" });
        return;
    }
    if (!orderData.itemCode) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Item ID is required!" });
        return;
    }
    if (!orderData.price || isNaN(orderData.price) || orderData.price <= 0) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Price must be a positive number!" });
        return;
    }
    if (!orderData.orderQuantity || isNaN(orderData.orderQuantity) || orderData.orderQuantity <= 0) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Order quantity must be a positive number!" });
        return;
    }
    if (!validatePhone(orderData.phone)) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Invalid phone number format!" });
        return;
    }
    if (orderData.orderQuantity > orderData.quantity) {
        Swal.fire({ icon: "error", title: "Validation Error", text: "Order quantity cannot exceed available quantity!" });
        return;
    }

    const order = {
        id: orderArray.length + 1,
        ...orderData,
        total: calculateTotal(orderData.price, orderData.orderQuantity, orderData.discount)
    };

    orderArray.push(order);
    loadOrderTable();
    clearForm();
});

function calculateTotal(price, quantity, discount) {
    const subtotal = price * quantity;
    return subtotal - (subtotal * (discount / 100));
}

function loadOrderTable() {
    const orderTableBody = $("#OrderTableBody");
    orderTableBody.empty();

    let grandTotal = 0;

    orderArray.forEach((order) => {
        grandTotal += order.total;

        const row = `<tr>
            <td>${order.name}</td>
            <td>${order.phone}</td>
            <td>${order.description}</td>
            <td>${order.orderDate}</td>
            <td>$${order.price.toFixed(2)}</td>
            <td>${order.orderQuantity}</td>
            <td>$${order.total.toFixed(2)}</td>
        </tr>`;
        orderTableBody.append(row);
    });

    $("#totalAmount").val(grandTotal.toFixed(2));
}

function clearForm() {
    $("#customerId").val("");
    $("#orderDate").val("");
    $("#orderCustomer").val("");
    $("#orderphone").val("");
    $("#itemId").val("");
    $("#description").val("");
    $("#price").val("");
    $("#quantity").val("");
    $("#Getquantity").val("");
    $("#discount").val("");
}