<html lang="en" dir="ltr">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <head>
        <meta charset="utf-8">
        <title>My Anime</title>
    <link rel="stylesheet" href="css/index.css">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    </head>
    <div class="wrapper">


    <nav class="banner">

        <div class="">
          <a href="index.jsp"><img id="logo" src="images/goku_blast.png"></a>
          <ul class="right">
            <li><a href="add_anime.jsp">Add Anime</a></li>


            <li><a href="sign_in.jsp">Sign Out</a></li>
          </ul>
        </div>
      </nav>
    <body class="container">
        <h1 class="centerText">Welcome, Bob Saget!<!--this name will be dynamic  --></h1>

            <nav class="banner">
                <div class="nav-wrapper">
                  <form>
                    <div class="input-field">
                      <input id="search" type="search" required>
                      <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                      <i class="material-icons">close</i>
                    </div>
                  </form>
                </div>
              </nav>



        <section>
            <article>

                <div class="row">
                    <c:forEach var="anime" items="${userAnime}">
                        <div class="col s12 m4">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <a href="#" title="Delete this show"> <i class="floating material-icons deleteshow">cancel</i></a>
                                    <img class="activator" alt="naruto" src="images/fillerCard.jpg">
                                </div>
                                <div class="card-content">

                                    <span class="card-title activator grey-text text-darken-4">${anime.title}<i class="material-icons right">more_vert</i></span>
                                    <div class="w3-light-grey w3-small" style="border-radius: 10px">
                                        <div class="w3-container w3-green " style="width:50%; border-radius: 10px">50%</div>
                                    </div>
                                    <p><a href="#" target="_blank">last updated link</a></p>

                                </div>
                                <div class="card-reveal">
                                    <span class="card-title grey-text text-darken-4">Naruto<i class="material-icons right">close</i></span>
                                    <p>This show is about ninjas and jitsus.</p>
                                    <a href="#">Edit My Progress</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>



            </article>
        </section>
    </body>
    </div>
</html>
