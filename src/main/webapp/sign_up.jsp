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
          <a href="index111.jspjsp"><img id="logo" src="images/goku_blast.png"></a>
          <ul class="right">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="sign_in.jsp">Sign In</a></li>
          </ul>
        </div>
      </nav>
    <body class="container">
        <h1 class="centerText">Welcome! Please sign up</h1>
        <div class="row">
           <form action="signUp" method="get" class="col s12">
             <div class="row">
               <div class="input-field col s6">
                 <input id="firstName" name="firstName" type="text" class="validate">
                 <label for="firstName">First Name</label>
               </div>
               <div class="input-field col s6">
                 <input id="lastName" name="lastName" type="text" class="validate">
                 <label for="lastName">Last Name</label>
               </div>
             </div>
             <div class="row">
               <div class="input-field col s6">
                 <input id="password1" name="password1" type="password" class="validate">
                 <label for="password1">Password</label>
               </div>
               <div class="input-field col s6">
                 <input id="userName" name="userName" type="text" class="validate">
                 <label for="userName">Username</label>
               </div>
             </div>
               <div class="row">
                   <div class="input-field col s6">
                       <input id="password2" name="password2" type="password" class="validate">
                       <label for="password2">retype Password</label>
                   </div>>
               </div>
             <input type="submit" name="submit" value="submit">
           </form>
         </div>
    </body>
    </div>
</html>