$(document).ready(function () {
    loadCustomers();

    let selectedCustomerId = null;

    // Load customers and populate the table
    function loadCustomers() {
        $.ajax({
            url: "http://localhost:8090/api/v2/customer",
            type: "GET",
            success: function (data) {
                let tbody = $("table tbody").empty();
                data.forEach(customer => {
                    tbody.append(`
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.contact}</td>
                            <td>${customer.email}</td>
                            <td>
                                <button class="btn btn-warning update-btn"
                                    data-id="${customer.id}" 
                                    data-name="${customer.name}" 
                                    data-contact="${customer.contact}" 
                                    data-email="${customer.email}">
                                    Update
                                </button>
                                <button class="btn btn-danger delete-btn" data-id="${customer.id}">Delete</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function () {
                alert("Error loading customers!");
            }
        });
    }

    // Handle form submission (Add or Update customer)
    $("#customerForm").submit(function (event) {
        event.preventDefault();

        const customer = {
            id: $("#id").val().trim(),
            name: $("#name").val().trim(),
            email: $("#email").val().trim(),
            contact: $("#contact").val().trim()
        };

        // Validate fields
        if (!customer.id || !customer.name || !customer.email || !customer.contact) {
            alert("Please fill in all fields!");
            return;
        }

        if (selectedCustomerId) {
            // Update Customer
            $.ajax({
                url: `http://localhost:8090/api/v2/customer/${selectedCustomerId}`,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(customer),
                success: function () {
                    alert("Customer updated successfully!");
                    resetForm();
                    loadCustomers();
                },
                error: function () {
                    alert("Error updating customer!");
                }
            });
        } else {
            // Add Customer
            $.ajax({
                url: "http://localhost:8090/api/v2/customer",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(customer),
                success: function () {
                    alert("Customer added successfully!");
                    resetForm();
                    loadCustomers();
                },
                error: function () {
                    alert("Error adding customer!");
                }
            });
        }
    });

    // Populate form when Update button is clicked
    $(document).on("click", ".update-btn", function () {
        selectedCustomerId = $(this).data("id");
        $("#id").val($(this).data("id"));
        $("#name").val($(this).data("name"));
        $("#contact").val($(this).data("contact"));
        $("#email").val($(this).data("email"));

        // Change button text and style
        $("#customerForm button[type='submit']")
            .text("Update Customer")
            .removeClass("btn-primary")
            .addClass("btn-success");
    });

    // Delete customer
    $(document).on("click", ".delete-btn", function () {
        const customerId = $(this).data("id");

        if (confirm("Are you sure you want to delete this customer?")) {
            $.ajax({
                url: `http://localhost:8090/api/v2/customer/${customerId}`,
                type: "DELETE",
                success: function () {
                    alert("Customer deleted successfully!");
                    loadCustomers();
                    resetForm();
                },
                error: function () {
                    alert("Error deleting customer!");
                }
            });
        }
    });

    // Reset form
    function resetForm() {
        $("#customerForm")[0].reset();
        selectedCustomerId = null;
        $("#customerForm button[type='submit']")
            .text("Add Customer")
            .removeClass("btn-success")
            .addClass("btn-primary");
    }
});
