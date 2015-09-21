<%@page import="com.illusionbox.offerme.model.Administrator"%>
<%@page import="com.illusionbox.offerme.model.Offer"%>
<%@page import="com.illusionbox.offerme.model.Restaurant"%>
<%@page import="com.illusionbox.offerme.model.RestaurantManager"%>
<%@page import="com.illusionbox.offerme.manager.OfferMeHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
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

        <title>OfferME Dashboard</title>

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
                    <a class="navbar-brand" href="index.jsp">OfferME</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <!--<li>
                            <a href="restaurant.jsp">Add Restaurant</a>
                        </li>
                        <li>
                            <a href="offer.jsp">Add Offer</a>
                        </li>-->
                        <li>
                            <a href="#">About</a>
                        </li>
                        <li>
                            <a href="logout">Logout</a>
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
                        <%
                            String name = (String) request.getSession().getAttribute("name");
                        %>
                        <h1 class="tagline">Hi <%=name%></h1>
                    </div>
                </div>
            </div>
        </header>

        <!-- Page Content -->
        <div class="container">
            <hr>
            <%
                Session hbSession = OfferMeHibernateUtil.getSessionFactory().openSession();
                String email = (String) request.getSession().getAttribute("User");
                if (request.getSession().getAttribute("Admin") != null) {
                    Administrator a = (Administrator) hbSession.load(Administrator.class, email);
                    for (RestaurantManager rm : a.getRestaurantManagers()) {
            %>
            <!--Restaurant Manager start-->
            <div class="row panel panel-default">
                <div class="col-lg-12 panel-heading">
                    <h3>
                        Restaurant Manager: <%=rm.getName()%> <span class="btn-group">
                            <%
                                if (rm.getState().equals("New") || rm.getState().equals("Deactive")) {
                            %>
                            <a href="AcceptRestaurantManager?email=<%=rm.getEmail()%>&accept=YES" class="btn btn-sm btn-danger">Activate</a>
                            <%
                            } else {
                            %>
                            <a href="AcceptRestaurantManager?email=<%=rm.getEmail()%>&accept=NO" class="btn btn-sm btn-success">Deactivate</a>
                            <%
                                }
                            %>
                        </span>
                    </h3>                   
                    <h4>Remarks</h4> <p><%=rm.getRemarks()%></p>
                    <h4>Contact Details</h4>
                    <address>
                        <abbr title="Address">A:</abbr> <%=rm.getAddress()%>
                        <abbr title="Phone">P:</abbr> <%=rm.getTel()%> 
                        <abbr title="Email">E:</abbr> <a href="mailto:<%=rm.getEmail()%>"><%=rm.getEmail()%></a>
                    </address>                    
                </div>
                <%
                    for (Restaurant r : rm.getRestaurants()) {
                %>
                <!--Restaurant start-->
                <div class="row panel-body panel-collapse">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h3>Restaurant: <%=r.getName()%> </h3>
                        <div class="col-sm-2">
                            <img class="img-responsive" src="<%=r.getLogoUrl()%>" alt="" width="100px" height="100px">
                        </div>
                        <div class="col-sm-8">
                            <p><%=r.getDescription()%></p>
                        </div>
                        <h4>Contact Details</h4>
                        <address>
                            <abbr title="Address">A:</abbr> <%=r.getAddress()%>
                            <abbr title="Phone">P:</abbr> <%=r.getTel()%> 
                            <abbr title="URL">U:</abbr> <a href="<%=r.getWeb()%>"><%=r.getWeb()%></a>
                        </address>  
                    </div>
                </div>
                <!--Restaurant end-->
                <%
                    }
                %>
            </div>
            <hr>
            <!--Restaurant Manager end-->
            <%
                }
            } else if (request.getSession().getAttribute("User") != null) {
                RestaurantManager rm = (RestaurantManager) hbSession.load(RestaurantManager.class, email);
            %>
            <div class="row">
                <div class="col-sm-8">
                    <span class="btn-group">
                        <!--<a class="btn btn-warning btn-lg" href="#">Edit Details</a>-->
                        <a class="btn btn-default btn-lg" href="restaurant.jsp">Add Restaurant &raquo;</a>
                    </span>
                </div>
            </div>
            <!-- /.row -->
            <hr>
            <%
                for (Restaurant r : rm.getRestaurants()) {
            %>
            <!--Restaurant start-->
            <div class="row panel panel-default">
                <div class="col-lg-12 panel-heading">
                    <h2><%=r.getName()%></h2>
                    <!--<img class="img-responsive img-center" src="http://placehold.it/1280x300" alt="">-->
                    <img class="img-responsive img-center" src="<%=r.getBannerUrl()%>" alt="" height="300px" width="auto">
                    <div class="col-sm-8">
                        <h4>Short Description</h4>
                        <p><%=r.getDescription()%></p>
                    </div>
                    <div class="col-sm-4">
                        <h4>Contact Details</h4>
                        <address>
                            <strong><%=r.getAddress()%></strong>
                            <br>
                        </address>
                        <address>
                            <abbr title="Phone">P:</abbr> <%=r.getTel()%>
                            <br>
                            <abbr title="URL">U:</abbr> <a href="<%=r.getWeb()%>"><%=r.getWeb()%></a>
                        </address>
                    </div>
                    <span class="btn-group">
                        <!--<a href="#" class="btn btn-danger">Deactivate Restaurant</a>
                        <a href="#" class="btn btn-success">Activate Restaurant</a>-->
                        <!--<a href="#" class="btn btn-warning">Edit Restaurant</a>-->
                        <a href="offer.jsp" class="btn btn-info">Add Offer</a>
                    </span>
                </div>
                <br />
                <%
                    for (Offer o : r.getOffers()) {
                %>
                <!--Offer start-->
                <div class="row panel-body panel-collapse">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h3>
                            Offer: <%=o.getTitle()%> <span class="btn-group">
                                <%
                                    if (o.getValid()) {
                                %>                                
                                <!--<a href="" class="btn btn-sm btn-success disabled">Offer Active</a>-->
                                <a href="activate_offer?id=<%=o.getIdoffer()%>&active=NO" class="btn btn-sm btn-danger">Deactivate Offer</a>
                                <span class="glyphicon glyphicon-ok-circle"/></span>
                                <%
                                } else {
                                %>                                
                                <a href="activate_offer?id=<%=o.getIdoffer()%>&active=YES" class="btn btn-sm btn-success">Activate Offer</a>
                                <!--<a href="" class="btn btn-sm btn-danger disabled">Offer Not Active</a>-->
                                <span class="glyphicon glyphicon-remove-circle"/></span>
                                <%
                                    }
                                %>
                                <!--<a href="edit_offer?id=<%=o.getIdoffer()%>" class="btn btn-sm btn-warning">Edit Offer</a>-->
                            </span>
                        </h3>
                        <div class="col-sm-2">
                            <img class="img-responsive" src="<%=o.getImageUrl()%>" alt="" width="100px" height="100px">

                        </div>
                        <div class="col-sm-8">
                            <p><%=o.getDescription()%></p>
                            Start: <%=o.getStartDate()%> End: <%=o.getEndDate()%>

                        </div>
                    </div>
                </div>
                <hr>
                <!--Offer end-->
                <%
                    }
                %>
            </div>
            <!--Restaurant end-->
            <%
                }
            %>

            <%
                }
            %>
            <!-- /.row -->
            <hr>

            <!-- Footer -->
            <footer>
                <div class="row text-center">
                    <div class="col-lg-12">
                        <p>Copyright &copy; OfferME.lk 2015</p>
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

