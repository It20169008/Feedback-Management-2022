<%@page import="model.Feedback" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>Feedback Management</title>
            <link rel="stylesheet" href="Views/bootstrap.min.css">
            <script src="Components/jquery-3.6.0.min.js"></script>
            <script src="Components/FeedbackServices.js"></script>
        </head>

        <body>
            <div class="container">
                <div class="row">
                    <div class="col-6">
                        <h1>Feedback Management</h1>
                        <form id="formItem" name="formItem">
                            Item code:
                            <input id="CustomerName" name="CustomerName" type="text" class="form-control form-control-sm">
                            <br> Item name:
                            <input id="Branch" name="Branch" type="text" class="form-control form-control-sm">
                            <br> Item price:
                            <input id="Review" name="Review" type="text" class="form-control form-control-sm">
                            <br> Item description:
                            <input id="Rate" name="Rate" type="text" class="form-control form-control-sm">
                            <br>
                            <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
                            <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
                        </form>
                        <div id="alertSuccess" class="alert alert-success"></div>
                        <div id="alertError" class="alert alert-danger"></div>
                        <br>
                        <div id="divItemsGrid">
                            <% Feedback itemObj=new Feedback(); out.print(itemObj.readFeedback()); %>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>