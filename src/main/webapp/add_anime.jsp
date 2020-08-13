<html lang="en" dir="ltr">
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
            <li><a href="my_anime.jsp">My Anime</a></li>
            <li><a href="signOut">Sign Out</a></li>
          </ul>
        </div>
      </nav>
    <body class="container">
        <h1 class="centerText">What show would you like to add?</h1>
        <form class="" action="addAnime" method="get">
            <input class="centerText" type="text" name="animeTitle" value="" placeholder="Type your shows name here" required>
            <input class="centerText" type="number" name="episodeTotal" placeholder="total episodes in season one" required>
            <input class="centerText" type="text" name="imageUrl" placeholder="grab an image link(optional)">
            <input class="centerText" type="submit" name="submit" value="submit">
        </form>
<!--        <section class="row">-->
<!--            <div class="col s12 m4 offset-m4">-->
<!--                <div class="card small">-->
<!--                     <div class="card-image waves-effect waves-block waves-light">-->
<!--                       <img class="activator" src="images/fillerCard.jpg">-->
<!--                     </div>-->
<!--                     <div class="card-content">-->
<!--                       <span class="card-title activator grey-text text-darken-4">Naruto<i class="material-icons right">more_vert</i></span>-->

<!--                       <p><a href="#" target="_blank">five trillion seasons </a></p>-->


<!--                     </div>-->
<!--                     <div class="card-reveal">-->
<!--                       <span class="card-title grey-text text-darken-4">Naruto<i class="material-icons right">close</i></span>-->
<!--                       <p>This show is about ninjas and jitsus.</p>-->

<!--                     </div>-->
<!--                 </div>-->
<!--            </div>-->
<!--        </section>-->
<!--        <section class="row">-->
<!--            <h2 class="centerText">Is this the correct show?</h2>-->
<!--                <a href="#" class="col offset-m4"> <i class="material-icons redx">cancel</i></a>-->
<!--                <a href="#" class="col offset-m2"> <i class="material-icons greencheck">check_circle</i> </a>-->


<!--        </section>-->
    </body>
    </div>
</html>