<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%
            if (request.getSession().getAttribute("User") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>OfferME dashboard</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/business-frontpage.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

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
                    <a class="navbar-brand" href="#">OfferME</a>
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

        <!-- Image Background Page Header -->
        <!-- Note: The background image is set within the business-casual.css file. -->
        <header class="business-header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="tagline">Restaurant Manager Name</h1>
                    </div>
                </div>
            </div>
        </header>

        <!-- Page Content -->
        <div class="container">

            <hr>

            <div class="row">
                <div class="col-sm-8">
                    <h2>What We Do</h2>
                    <p>Introduce the visitor to the business using clear, informative text. Use well-targeted keywords within your sentences to make sure search engines can find the business.</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Et molestiae similique eligendi reiciendis sunt distinctio odit? Quia, neque, ipsa, adipisci quisquam ullam deserunt accusantium illo iste exercitationem nemo voluptates asperiores.</p>
                    <span class="btn-group">
                        <a class="btn btn-warning btn-lg" href="#">Edit Details</a>
                        <a class="btn btn-default btn-lg" href="#">Add Restaurant &raquo;</a>
                    </span>
                </div>
                <div class="col-sm-4">
                    <h2>Contact Us</h2>
                    <address>
                        <strong>Start Bootstrap</strong>
                        <br>3481 Melrose Place
                        <br>Beverly Hills, CA 90210
                        <br>
                    </address>
                    <address>
                        <abbr title="Phone">P:</abbr>(123) 456-7890
                        <br>
                        <abbr title="Email">E:</abbr> <a href="mailto:#">name@example.com</a>
                    </address>
                </div>
            </div>
            <!-- /.row -->

            <hr>

            <!--Restaurant start-->
            <div class="row panel panel-default">
                <div class="col-lg-12 panel-heading">
                    <h2>Restaurant #1</h2>
                    <img class="img-responsive img-center" src="http://placehold.it/1280x300" alt="">
                    <div class="col-sm-8">
                        <h2>Short Description</h2>
                        <p>Introduce the visitor to the business using clear, informative text. Use well-targeted keywords within your sentences to make sure search engines can find the business.</p>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Et molestiae similique eligendi reiciendis sunt distinctio odit? Quia, neque, ipsa, adipisci quisquam ullam deserunt accusantium illo iste exercitationem nemo voluptates asperiores.</p>
                    </div>
                    <div class="col-sm-4">
                        <h2>Contact Details</h2>
                        <address>
                            <strong>Start Bootstrap</strong>
                            <br>3481 Melrose Place
                            <br>Beverly Hills, CA 90210
                            <br>
                        </address>
                        <address>
                            <abbr title="Phone">P:</abbr>(123) 456-7890
                            <br>
                            <abbr title="Email">E:</abbr> <a href="mailto:#">name@example.com</a>
                        </address>
                    </div>
                    <span class="btn-group">
                        <a href="#" class="btn btn-danger">Deactivate Restaurant</a>
                        <a href="#" class="btn btn-success">Activate Restaurant</a>
                        <a href="#" class="btn btn-warning">Edit Restaurant</a>
                        <a href="#" class="btn btn-info">Add Offer</a>
                    </span>
                </div>
                <br />
                <!--Offer start-->
                <div class="row panel-body panel-collapse">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h3>Offer #1</h3>
                        <img class="img-responsive" src="http://placehold.it/100x100" alt="">
                        <p>These marketing boxes are a great place to put some information. These can contain summaries of what the company does, promotional information, or anything else that is relevant to the company. These will usually be below-the-fold.</p>
                        <span class="btn-group">
                            <a href="#" class="btn btn-sm btn-danger">Deactivate Offer</a>
                            <a href="#" class="btn btn-sm btn-success">Activate Offer</a>
                            <a href="#" class="btn btn-sm btn-warning">Edit Offer</a>
                        </span>
                    </div>
                </div>
                <!--Offer end-->
                <!--Offer start-->
                <div class="row panel-body panel-collapse">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h3>Offer #2</h3>
                        <img class="img-responsive" src="http://placehold.it/100x100" alt="">
                        <p>These marketing boxes are a great place to put some information. These can contain summaries of what the company does, promotional information, or anything else that is relevant to the company. These will usually be below-the-fold.</p>
                        <span class="btn-group">
                            <a href="#" class="btn btn-sm btn-danger">Deactivate Offer</a>
                            <a href="#" class="btn btn-sm btn-success">Activate Offer</a>
                            <a href="#" class="btn btn-sm btn-warning">Edit Offer</a>
                        </span>
                    </div>
                </div>
                <!--Offer end-->
            </div>
            <!--Restaurant end-->
            <!-- /.row -->

            <hr>

            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
                <!-- /.row -->
            </footer>

        </div>
        <!-- /.container -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>
