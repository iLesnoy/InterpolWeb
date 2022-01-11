<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Interpol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /*body background */
        body{background: #ffffff
        }

        /*Navbar effect */
        .navbar-brand { position: relative; z-index: 2; }
        .navbar-nav.navbar-right .btn { position: relative; z-index: 2; padding: 4px 20px; margin: 10px auto; }
        .navbar .navbar-collapse { position: relative; }
        .navbar .navbar-collapse .navbar-right > li:last-child { padding-left: 22px; }
        .navbar .nav-collapse { position: absolute; z-index: 1; top: 0; left: 0; right: 0; bottom: 0; margin: 0; padding-right: 120px; padding-left: 80px; width: 100%; }
        .navbar.navbar-default .nav-collapse { background-color: #ffffff; }
        .navbar.navbar-inverse .nav-collapse { background-color: #222; }
        .navbar .nav-collapse .navbar-form { border-width: 0; box-shadow: none; }
        .nav-collapse>li { float: right; }
        .btn.btn-circle { border-radius: 50px; }
        .btn.btn-outline { background-color: transparent; }
        @media screen and (max-width: 767px) {
            .navbar .navbar-collapse .navbar-right > li:last-child { padding-left: 15px; padding-right: 15px; }
            .navbar .nav-collapse { margin: 7.5px auto; padding: 0; }
            .navbar .nav-collapse .navbar-form { margin: 0; }
            .nav-collapse>li { float: none; }
        }
    </style>
</head>
<body>

<!-- Second navbar for sign in -->
<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Interpol</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-2">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="pages/newsfeed.jsp">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_news_feed" class="button">News</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_missing" class="button">Missing People</a></li>
                <li><a href="pages/wantedcriminals.jsp">WantedCriminals</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_account" class="button">Account</a></li>
                <li>
                    <a class="btn btn-default btn-outline btn-circle" data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Sign in</a>
                </li>
            </ul>
            <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                <form action="${pageContext.request.contextPath}/controller" method="post" class="navbar-form navbar-right form-inline">
                    <input type="hidden" name="command" value="log_in">
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="email" name="email" class="form-control" id="email" placeholder="Email" autofocus required />
                    </div>
                    <div class="form-group">
                        <label for="password"></label>
                        <input type="text"  name="password" class="form-control" id="password" placeholder="Password" required />
                    </div>
                    <input type="submit" class="btn btn-success" value="Sign"/>
                    <a href="pages/signup.jsp" class="button">Create new account</a>
                </form>
            </div>
            <img alt="img" src="src/main/webapp/images/home.jpg"/>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->
</body>

