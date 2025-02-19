$(document).ready(function () {
    loadItems();

    let selectedItemId = null;

    function loadItems() {
        $.ajax({
            url: "http://localhost:8090/api/v2/item",
            type: "GET",
            success: function (data) {
                let tbody = $("table tbody");
                tbody.empty();
                data.forEach(item => {
                    tbody.append(`<tr data-id="${item.id}" data-name="${item.name}" data-email="${item.qty}" data-contact="${item.price}">
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.qty}</td>
                        <td>${item.price}</td>
                        <td>
                            <button class="update-btn" data-id="${item.id}" data-name="${item.name}" data-qty="${item.qty}" data-price="${item.price}">Update</button>
                            <button class="delete-btn" data-id="${item.id}">Delete</button>
                        </td>
                    </tr>`);
                });
            },
            error: function () {
                alert("Error loading items!");
            }
        });
    }

    $("#itemForm").submit(function (event) {
        event.preventDefault();

        let name = $("#name").val();
        let qty = $("#qty").val();
        let price = $("#price").val();

        if (!name || !qty || !price) {
            alert("Please fill in all fields!");
            return;
        }

        $.ajax({
            url: "http://localhost:8090/api/v2/item",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ name: name, qty: qty, price: price }),
            success: function () {
                alert("Item added successfully!");
                $("#itemForm")[0].reset();
                loadItems();
            },
            error: function () {
                alert("Error adding items!");
            }
        });
    });


    $(document).on("click", "table tbody tr", function () {
        selectedItemId = $(this).data("id");
        let name = $(this).data("name");
        let qty = $(this).data("qty");
        let price = $(this).data("price");

        $("#name").val(name);
        $("#qty").val(qty);
        $("#price").val(price);
        $(".save-btn").text("Update item");
    });

    $("#itemForm").submit(function (event) {
        event.preventDefault();

        let name = $("#name").val();
        let qty= $("#qty").val();
        let price = $("price").val()

        if (!selectedItemId) {
            alert("No customer selected for updating!");
            return;
        }

        $.ajax({
            url: `http://localhost:8090/api/v2/item/${selectedItemId}`,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({ name: name, qty: qty , price:price }),
            success: function () {
                alert("Item updated successfully!");
                selectedItemId = null;
                $(".save-btn").text("Save Customer");
                $("#itemForm")[0].reset();
                loadItems();
            },
            error: function () {
                alert("Error updating customer!");
            }
        });
    });


    $(document).on("click", ".delete-btn", function () {
        let itemId = $(this).data("id");
        if (confirm("Are you sure you want to delete this Item?")) {
            $.ajax({
                url: `http://localhost:8090/api/v2/item/${itemId}`,
                type: "DELETE",
                success: function () {
                    alert("Item deleted successfully!");
                    loadItems();
                },
                error: function () {
                    alert("Error deleting item!");
                }
            });
        }
    });
});
