$(document).ready(function () {
    loadItems();

    let selectedItemId = null;

    // Load items and populate the table
    function loadItems() {
        $.ajax({
            url: "http://localhost:8090/api/v2/item",
            type: "GET",
            success: function (data) {
                let tbody = $("table tbody").empty();
                data.forEach(item => {
                    tbody.append(`
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.qty}</td>
                            <td>${item.price}</td>
                            <td>
                                <button class="btn btn-warning update-btn" 
                                    data-id="${item.id}" 
                                    data-name="${item.name}" 
                                    data-qty="${item.qty}" 
                                    data-price="${item.price}">
                                    Update
                                </button>
                                <button class="btn btn-danger delete-btn" data-id="${item.id}">Delete</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function () {
                alert("Error loading items!");
            }
        });
    }

    // Handle form submission (Add or Update item)
    $("#itemForm").submit(function (event) {
        event.preventDefault();

        const item = {
            id:$("#id").val().trim(),
            name: $("#name").val().trim(),
            qty: $("#qty").val().trim(),
            price: $("#price").val().trim()
        };

        if  ( !item.id || !item.name || !item.qty || !item.price) {
            alert("Please fill in all fields!");
            return;
        }

        if (selectedItemId) {
            // Update Item
            $.ajax({
                url: `http://localhost:8090/api/v2/item/${selectedItemId}`,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(item),
                success: function () {
                    alert("Item updated successfully!");
                    resetForm();
                    loadItems();
                },
                error: function () {
                    alert("Error updating item!");
                }
            });
        } else {
            // Add Item
            $.ajax({
                url: "http://localhost:8090/api/v2/item",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(item),
                success: function () {
                    alert("Item added successfully!");
                    resetForm();
                    loadItems();
                },
                error: function () {
                    alert("Error adding item!");
                }
            });
        }
    });

    // Click Update button to populate form
    $(document).on("click", ".update-btn", function () {
        selectedItemId = $(this).data("id");

        $("#id").val($(this).data("id"))
        $("#name").val($(this).data("name"));
        $("#qty").val($(this).data("qty"));
        $("#price").val($(this).data("price"));

        // Change button text and style
        $("#itemForm button[type='submit']")
            .text("Update Item")
            .removeClass("btn-primary")
            .addClass("btn-success");
    });

    // Click Delete button
    $(document).on("click", ".delete-btn", function () {
        const itemId = $(this).data("id");

        if (confirm("Are you sure you want to delete this item?")) {
            $.ajax({
                url: `http://localhost:8090/api/v2/item/${itemId}`,
                type: "DELETE",
                success: function () {
                    alert("Item deleted successfully!");
                    loadItems();
                    resetForm();
                },
                error: function () {
                    alert("Error deleting item!");
                }
            });
        }
    });

    // Reset form after add/update
    function resetForm() {
        $("#itemForm")[0].reset();
        selectedItemId = null;
        $("#itemForm button[type='submit']")
            .text("Add Item")
            .removeClass("btn-success")
            .addClass("btn-primary");
    }
});
