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

        <title>Add New Restaurant</title>
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
            <form class="form-horizontal" action="add_restaurant" method="POST">
                <fieldset>

                    <!-- Form Name -->
                    <legend class="text-center">New Restaurant Information</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Restaurant Name</label>  
                        <div class="col-md-4">
                            <input id="name" name="name" type="text" placeholder="Restaurant Name" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Location input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="lng">Location</label>  
                        <div class="col-md-4">
                            <input id="name" name="lat" type="text" placeholder="Lattitude" class="form-control input-md" required="">
                            <input id="name" name="lng" type="text" placeholder="Longitude" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Address</label>
                        <div class="col-md-4">                     
                            <textarea class="form-control" id="address" name="address" required="">Enter Address</textarea>
                        </div>
                    </div>

                    <!-- Tel input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telephone">Telephone No:</label>  
                        <div class="col-md-4">
                            <input id="telephone" name="telephone" type="text" placeholder="+94" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="web">Website</label>  
                        <div class="col-md-4">
                            <input id="web" name="web" type="text" placeholder="http://www.yourrestaurant.com" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="logo">LogoURL</label>  
                        <div class="col-md-4">
                            <input id="logo" name="logo" type="text" placeholder="http://www.yourrestaurant.com/logo" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="banner">BannerURL</label>  
                        <div class="col-md-4">
                            <input id="banner" name="banner" type="text" placeholder="http://www.yourrestaurant.com/banner" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Time input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telephone">Opening Hours:</label>  
                        <div class="col-md-4">
                            <input id="time" name="opentime" type="time" required="" class="form-control input-md">
                        </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Description</label>
                        <div class="col-md-4">                     
                            <textarea class="form-control" id="desc" name="desc" required="">Short Description about your restaurant</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="selectbasic">Type</label>
                        <div class="col-md-4">
                            <select id="selectbasic" name="type" class="form-control">
                                <option value="Fine Dining">Fine Dining</option>
                                <option value="Pub">Pub</option>
                                <option value="Fast Food">Fast Food</option>
                            </select>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-lg-4 col-lg-offset-4">
                            <span class="btn-group">
                                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary">Send</button>
                                <a href="dashboard.jsp" class="btn btn-lg btn-danger">Back</a>
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
