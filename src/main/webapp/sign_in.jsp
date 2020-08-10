<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/index.css">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

        <title></title>
    </head>
    <div class="wrapper">


    <nav class="banner">
        <div class="">
          <a href="index111.jspjsp"><img id="logo" alt="gokublaset" src="images/goku_blast.png"></a>
          <ul class="right">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="sign_up.jsp">Sign Up</a></li>
          </ul>
        </div>
      </nav>
    <body class="container">
        <h1 class="centerText">Welcome! Please sign in</h1>
        <div class="row">
           <form class="col s12" action="signIn" method="get">
             <div class="row">
               <div class="input-field col s12">
                 <input id="user_name" name="user_name" type="text" class="validate">
                 <label for="user_name">Username</label>
               </div>
               <div class="input-field col s12">
                   <input id="password" name="password" type="password" class="validate">
                   <label for="password">Password</label>
               </div>
             </div>
             <input type="submit" name="submit" value="submit">
           </form>
         </div>
    </body>
    </div>
</html>