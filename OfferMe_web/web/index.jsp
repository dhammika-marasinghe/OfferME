<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>OfferME</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/stylish-portfolio.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/offer_me_styles.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            function show_login() {
                $('#login').fadeIn();
            }
            function hide_login() {
                $('#login').fadeOut();
            }
        </script>

    </head>

    <body>

        <!-- Navigation -->
        <a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
        <nav id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
                <li class="sidebar-brand">
                    <a href="#top"  onclick = $("#menu-close").click(); >OfferMe</a>
                </li>
                <li>
                    <a href="#top" onclick = "show_login()" >LogIn</a>
                </li>
                <li>
                    <a href="#about" onclick = $("#menu-close").click(); >About</a>
                </li>
                <li>
                    <a href="#services" onclick = $("#menu-close").click(); >Services</a>
                </li>
                <li>
                    <a href="#contact" onclick = $("#menu-close").click(); >Contact</a>
                </li>
            </ul>
        </nav>

        <!-- Header -->
        <header id="top" class="header">
            <div class="text-vertical-center">
                <h1>OfferME</h1>
                <h3>The Best Place to get the Best Offers in Town!</h3>
                <br>
                <a href="#about" class="btn btn-dark btn-lg">Find Out How</a>
                <a onclick = "show_login()" class="btn btn-dark btn-lg">LogIn</a>
                <a href="#about" class="btn btn-dark btn-lg">Join Us</a>
            </div>
        </header>

        <div class="form-login col-md-3" id="login" style="display: none">
            <h4>Welcome back.</h4>
            <form action="login" method="POST">
                <input type="text" id="userName" class="form-control input-sm" placeholder="username" />
                <br />
                <input type="text" id="userPassword" class="form-control input-sm" placeholder="password" />
                <br />
                <div class="wrapper">
                    <span>     
                        <button type="submit" class="btn btn-primary btn-dark">login <i class="fa fa-sign-in"></i></button>
                        <a class="btn btn-primary btn-dark" onclick="hide_login()">Cancel</a>
                    </span>
                </div>
            </form>
        </div>

        <!-- About -->
        <section id="about" class="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2><strong>By joining us</strong></h2>
                        <ul>
                            <li><b>Get the best advertising for your restaurant and Offers</b></li>
                            <li><b>Get real-time offers taken</b></li>
                        </ul>
                        <p class="lead"></p>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container -->
        </section>

        <!-- Services -->
        <!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
        <section id="services" class="services bg-primary">
            <div class="container">
                <div class="row text-center">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h2>Our Services</h2>
                        <hr class="small">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-android fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Android App</strong>
                                    </h4>
                                    <p>Give exclusive offers to only those who use our app</p>
                                    <a href="#" class="btn btn-light">Get offer me on Google Play</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-birthday-cake fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Exclusive Offers</strong>
                                    </h4>
                                    <p>Get offers to You and ONLY You on Your Choices</p>
                                    <a href="#" class="btn btn-light">How cool is This!!</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-filter fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Filter Offers</strong>
                                    </h4>
                                    <p>Search offers based on Your choices and Recommendations</p>
                                    <a href="#" class="btn btn-light">Learn More</a>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="service-item">
                                    <span class="fa-stack fa-4x">
                                        <i class="fa fa-circle fa-stack-2x"></i>
                                        <i class="fa fa-facebook fa-stack-1x text-primary"></i>
                                    </span>
                                    <h4>
                                        <strong>Share your Experience</strong>
                                    </h4>
                                    <p>CheckIn get offers and tell your friends what you think</p>
                                    <a href="#" class="btn btn-light">Check Out OfferMe</a>
                                </div>
                            </div>
                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.col-lg-10 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container -->
        </section>

        <!-- Callout -->
        <!--aside class="callout">
            <div class="text-vertical-center">
                <h1>Vertically Centered Text</h1>
            </div>
        </aside-->

        <!-- Portfolio -->
        <section id="portfolio" class="portfolio">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h2>Our Work</h2>
                        <hr class="small">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="portfolio-item">
                                    <a href="#">
                                        <img class="img-portfolio img-responsive" src="img/portfolio-1.jpg">
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="portfolio-item">
                                    <a href="#">
                                        <img class="img-portfolio img-responsive" src="img/portfolio-2.jpg">
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="portfolio-item">
                                    <a href="#">
                                        <img class="img-portfolio img-responsive" src="img/portfolio-3.jpg">
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="portfolio-item">
                                    <a href="#">
                                        <img class="img-portfolio img-responsive" src="img/portfolio-4.jpg">
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- /.row (nested) -->
                        <a href="#" class="btn btn-dark">View More Items</a>
                    </div>
                    <!-- /.col-lg-10 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container -->
        </section>

        <!-- Call to Action -->
        <aside class="call-to-action bg-primary">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3>The buttons below are impossible to resist.</h3>
                        <a href="#" class="btn btn-lg btn-light">Click Me!</a>
                        <a href="#" class="btn btn-lg btn-dark">Look at Me!</a>
                    </div>
                </div>
            </div>
        </aside>

        <!-- Map -->
        <section id="contact" class="map">
            <iframe src="https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d9420.959033985982!2d79.9789386769026!3d6.887568085656224!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m5!1s0x3ae25139e858ce25%3A0xa3d0ed21ed9bc68c!2sPore!3m2!1d6.887844599999999!2d79.9836108!4m0!5e0!3m2!1sen!2slk!4v1442770356279" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
        </section>

        <!-- Footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1 text-center">
                        <h4><strong>IllusionBox Origins</strong>
                        </h4>
                        <p>#38 Forbes and Walker building<br>Washington Place, Colombo 07, SriLanka</p>
                        <ul class="list-unstyled">
                            <li><i class="fa fa-phone fa-fw"></i> (0)77 3498959</li>
                            <li><i class="fa fa-envelope-o fa-fw"></i>  <a href="mailto:name@example.com">offer_me@illusion_box_origins.com</a>
                            </li>
                        </ul>
                        <br>
                        <ul class="list-inline">
                            <li>
                                <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                            </li>
                        </ul>
                        <hr class="small">
                        <p class="text-muted">Copyright &copy; IllusionBox Origins 2014</p>
                    </div>
                </div>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script>
                            // Closes the sidebar menu
                            $("#menu-close").click(function(e) {
                                e.preventDefault();
                                $("#sidebar-wrapper").toggleClass("active");
                            });

                            // Opens the sidebar menu
                            $("#menu-toggle").click(function(e) {
                                e.preventDefault();
                                $("#sidebar-wrapper").toggleClass("active");
                            });

                            // Scrolls to the selected menu item on the page
                            $(function() {
                                $('a[href*=#]:not([href=#])').click(function() {
                                    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {

                                        var target = $(this.hash);
                                        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                                        if (target.length) {
                                            $('html,body').animate({
                                                scrollTop: target.offset().top
                                            }, 1000);
                                            return false;
                                        }
                                    }
                                });
                            });
        </script>

    </body>

</html>

