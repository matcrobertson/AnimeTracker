<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>My Anime</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<div class="wrapper">


    <nav class="banner">

        <div class="">
            <a href="index.jsp"><img id="logo" src="images/goku_blast.png"></a>
            <ul class="right">
                <li><a href="add_anime.jsp">Add Anime</a></li>


                <li><a href="signOut">Sign Out</a></li>
            </ul>
        </div>
    </nav>
    <body class="container">
    <h1 class="centerText">Welcome, ${fullName}!<!--this name will be dynamic  --></h1>

    <nav class="banner">
        <div class="nav-wrapper">
            <form action="searchAnime" method="get">
                <div class="input-field">
                    <input id="search" type="search" name="searchTerm" required>
                    <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                    <i class="material-icons">close</i>
                </div>
            </form>
        </div>
    </nav>



    <section>
        <article>

            <div class="row">

                <c:forEach var="animeCards" items="${animeCards}">

                    <div class="col s12 m4">
                        <div class="card small">
                            <div class="card-image waves-effect waves-block waves-light">


                                <a href="deleteAnime?animeId=${animeCards.userAnimeId}" title="Delete this show"> <i class="floating material-icons deleteshow">cancel</i></a>
                                <img class="activator" alt="naruto" src="${animeCards.imageUrl}">
                            </div>
                            <div class="card-content">

                                <span class="card-title activator grey-text text-darken-4">${animeCards.animeName}<i class="material-icons right">more_vert</i></span>
                                <div class="w3-light-grey w3-small" style="border-radius: 10px">
                                    <div class="w3-container w3-green " style="width:${(animeCards.episodesWatched / animeCards.totalEpisodes) * 100}%; border-radius: 10px">${fn:substring((animeCards.episodesWatched / animeCards.totalEpisodes) * 100,0,4)}%</div>
                                </div>
                                <p><a href="${animeCards.lastUpdatedLink}" target="_blank">last updated link</a></p>

                            </div>
                            <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">${animeCards.animeName}<i class="material-icons right">close</i></span>
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header">
                                            <i class="material-icons">
                                                favorite</i>
                                            Edit My Progress
                                        </div>
                                        <div class="collapsible-body">
                                            <form action="updateAnime" method="get">
                                                <input type="number" name="seasonNumber" placeholder="Season #">
                                                <input type="number" name="episodeNumber" placeholder="Episode #">
                                                <input style="display: none" type="text" name="userAnimeId" value="${animeCards.userAnimeId}">
                                                <input type="submit" >
                                            </form>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header">
                                            <i class="material-icons">history</i>
                                            Edit Current Link
                                        </div>
                                        <div class="collapsible-body">
                                            <form action="updateAnime" method="get">
                                                <input type="text" name="linkUpdate" placeholder="url goes here">
                                                <input style="display: none" type="text" name="userAnimeId" value="${animeCards.userAnimeId}">
                                                <input type="submit">
                                            </form>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header">
                                            <i class="material-icons">
                                                favorite</i>
                                            Add/Edit a Season
                                        </div>
                                        <div class="collapsible-body">
                                            <form action="seasonsUpdate" method="get">
                                                <input type="number" name="seasonNumber" placeholder="Season #">
                                                <input type="number" name="episodeTotal" placeholder="Episode Total">
                                                <input style="display: none" type="text" name="userAnimeId" value="${animeCards.userAnimeId}">
                                                <input type="submit" >
                                            </form>
                                        </div>
                                    </li>
                                </ul>
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