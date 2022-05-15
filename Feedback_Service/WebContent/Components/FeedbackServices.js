$(document).on("click", "#btnSave", function (event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validateItemForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
           url: "/Feedback_Service/myservice/Feedback",
            type: type,
            data: $("#formItem").serialize(),
            dataType: "text",
            complete: function (response, status) {
                onItemSaveComplete(response.responseText, status);
            }
        });    
});

function onItemSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hidItemIDSave").val("");
    $("#formItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function (event) {
    $("#hidItemIDSave").val($(this).data("FEEDBACKID"));
    $("#CostomerName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#Branch").val($(this).closest("tr").find('td:eq(1)').text());
    $("#Review").val($(this).closest("tr").find('td:eq(2)').text());
    $("#Rate").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function (event) {
    $.ajax(
        {
            url: "/Feedback_Service/myservice/Feedback",
            type: "DELETE",
            data: "itemID=" + $(this).data("FEEDBACKID"),
            dataType: "text",
            complete: function (response, status) {
                onItemDeleteComplete(response.responseText, status);
            }
        });
});

function onItemDeleteComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}

function validateItemForm() {

return true;
}