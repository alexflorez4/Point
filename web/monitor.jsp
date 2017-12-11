<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<link rel="stylesheet" href="js/css/screen.css">
	<link rel="stylesheet" href="js/css/styles.css">

	<script src="js/lib/jquery.js"></script>
	<script src="js/dist/jquery.validate.js"></script>
	<script src="js/dist/additional-methods.js"></script>

    <title>Accounts Monitor</title>
</head>
<body>
    <div class="container">
        <h1>Accounts Inventory Monitor</h1>

        <div class="jumbotron">
            <div class="form-group row">
                <div class="row">
                    <h4 class="col-sm-12">Account</h4>
                </div>
                <form:form id="token" action="processAPIToken.do" method="post">
                    <div class="row top-bottom-buffer">
                        <label for="token" class="col-sm-2 col-form-label">Token: </label>
                        <div class="col-sm-10">
                                <input id="token" name="token" class="form-control" required>
                            <!--<textarea id="token" name="token" class="form-control" required></textarea>-->
                        </div>
                    </div>
                    <div class="top-bottom-buffer">
                        <label for="accnt" class="col-sm-2 col-form-label">Account: </label>
                        <div class="col-sm-10">
                            <select id="accnt" name="accnt" class="form-control">
                              <option value="pinguinos" selected>pinguinos</option>
                              <option value="welse">welse</option>
                              <option value="aflorez">aflorez</option>
                            </select>
                        </div>
                    </div>
                    <div class="top-bottom-buffer">
                        <input type="submit" class="btn btn-success" value="Submit">
                    </div>
                </form:form>
            </div>
        </div>

	</div> <!--container-->
</body>
</html>