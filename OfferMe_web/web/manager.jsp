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

        <title>Request to join OfferME</title>
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
            <form class="form-horizontal" action="requesttojoin.jsp" method="POST">
                <fieldset>

                    <!-- Form Name -->
                    <legend class="text-center">Request To Join OfferME</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Name</label>  
                        <div class="col-md-4">
                            <input id="name" name="name" type="text" placeholder="Preferred Name" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="email">Email</label>  
                        <div class="col-md-4">
                            <input id="email" name="email" type="text" placeholder="email@domain.com" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password">Password</label>
                        <div class="col-md-4">
                            <input id="password" name="password" type="password" placeholder="Password" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Address</label>
                        <div class="col-md-4">                     
                            <textarea class="form-control" id="address" name="address">Enter Address</textarea>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telephone">Telephone No:</label>  
                        <div class="col-md-4">
                            <input id="telephone" name="telephone" type="tel" placeholder="+94" class="form-control input-md">

                        </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="message">Custom Message</label>
                        <div class="col-md-4">                     
                            <textarea class="form-control" id="address" name="message">Hi! I would love to join OfferME!</textarea>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-lg-4 col-lg-offset-4">
                            <span class="btn-group">
                                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary">Send</button>
                                <a href="./" class="btn btn-lg btn-danger">Back</a>
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
