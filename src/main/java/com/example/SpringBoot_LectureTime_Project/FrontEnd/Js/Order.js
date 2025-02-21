loadCustomers()

function loadCustomers() {
    $.ajax({
        url: "http://localhost:8090/api/v2/customer",
        type: "GET",
        success: function (data) {
            let customerSelect = $("#customerId").empty();
            customerSelect.append('<option value="" disabled selected>Select Customer ID</option>');

            data.forEach(customer => {
                customerSelect.append(`<option value="${customer.id}">${customer.id}</option>`);
            });
        },
        error: function () {
            alert("Error loading customer IDs!");
        }
    });
}

$("#customerId").on("change", function () {
    const selectedId = $(this).val();

    $.ajax({
        url: `http://localhost:8090/api/v2/customerFind/${selectedId}`,
        type: "GET",
        success: function (customer) {
            $("#orderCustomer").val(customer.name);
            $("#orderphone").val(customer.contact);
        },
        error: function () {
            alert("Error fetching customer details!");
        }
    });
});


loadItems()
function loadItems() {
    $.ajax({
        url: "http://localhost:8090/api/v2/item",
        type: "GET",
        success: function (data) {
            let itemSelection = $("#itemId").empty();
            itemSelection.append('<option value="" disabled selected>Select Item ID</option>');

            data.forEach(item => {
                itemSelection.append(`<option value="${item.id}">${item.id}</option>`);
            });
        },
        error: function () {
            alert("Error loading Id IDs!");
        }
    });
}

$("#itemId").on("change", function () {
    const selectedId = $(this).val();

    $.ajax({
        url: `http://localhost:8090/api/v2/findItemDetails/${selectedId}`,
        type: "GET",
        success: function (item) {
            $("#description").val(item.name);
            $("#price").val(item.price);
            $("#quantity").val(item.qty);
        },
        error: function () {
            alert("Error fetching item details!");
        }
    });
});


