<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%
            if (request.getSession().getAttribute("User") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/offer_me_styles.css" rel="stylesheet">

        <title>Add New Offer</title>
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="./">OfferME</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#">About</a>
                        </li>
                        <li>
                            <a href="#">Services</a>
                        </li>
                        <li>
                            <a href="#">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <div class="col-lg-8 col-lg-offset-2 form-normal">
            <form class="form-horizontal" action="addrestaurant.jsp" method="POST">
                <fieldset>

                    <!-- Form Name -->
                    <legend class="text-center">New Offer Information</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Offer Title</label>  
                        <div class="col-md-4">
                            <input id="name" name="name" type="text" placeholder="Offer Name" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Description</label>
                        <div class="col-md-4">                     
                            <textarea class="form-control" id="desc" name="desc" required="">Short Description about your offer</textarea>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="logo">Offer Image</label>  
                        <div class="col-md-4">
                            <input id="logo" name="image" type="text" placeholder="http://www.yourrestaurant.com/logo" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Time input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telephone">Start Date:</label>  
                        <div class="col-md-4">
                            <input id="start" name="startdate" type="date" required="" class="form-control input-md">
                        </div>
                    </div>

                    <!-- Time input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telephone">End Date:</label>  
                        <div class="col-md-4">
                            <input id="end" name="enddate" type="date" required="" class="form-control input-md">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="selectbasic">Type</label>
                        <div class="col-md-4">
                            <select id="selectbasic" name="selectbasic" class="form-control">
                                <option value="1">Discount</option>
                                <option value="2">Promotion</option>
                                <option value="2">Limited</option>
                            </select>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-lg-4 col-lg-offset-4">
                            <span class="btn-group">
                                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary">Send</button>
                                <a href="index.jsp" class="btn btn-lg btn-danger">Back</a>
                            </span>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
