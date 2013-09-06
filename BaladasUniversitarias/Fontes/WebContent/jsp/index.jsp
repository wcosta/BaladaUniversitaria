<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicial</title>
        <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="//connect.facebook.net/en_US/all.js"></script>
    </head>
    <body>
        <div id="fb-root"></div>
        <script>
          var accessToken = "";
          var uid = "";
          window.fbAsyncInit = function() {
            // init the FB JS SDK
            FB.init({
              appId      : '1410104595870928',                        // App ID from the app dashboard
              channelUrl : '//localhost', // Channel file for x-domain comms
              status     : true,                                 // Check Facebook Login status
              xfbml      : false                                  // Look for social plugins on the page
            });

            // Additional initialization code such as adding Event Listeners goes here
            FB.login();
            rodaScriptFB();
          };

          // Load the SDK asynchronously
          (function(d, s, id){
             var js, fjs = d.getElementsByTagName(s)[0];
             if (d.getElementById(id)) {return;}
             js = d.createElement(s); js.id = id;
             js.src = "//connect.facebook.net/en_US/all.js";
             fjs.parentNode.insertBefore(js, fjs);
           }(document, 'script', 'facebook-jssdk'));
           
           var body = 'Reading JS SDK documentation';
           
           function rodaScriptFB(){
             FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                  // the user is logged in and has authenticated your
                  // app, and response.authResponse supplies
                  // the user's ID, a valid access token, a signed
                  // request, and the time the access token 
                  // and signed request each expire
                  uid = response.authResponse.userID;
                  accessToken = response.authResponse.accessToken;
                  window.location.replace('http://localhost:8080<%=request.getContextPath()%>/PaginaInicial?token='+accessToken);
                } else if (response.status === 'not_authorized') {
                  alert("Usuário não autorizado!");
                } else {
                	alert("Usuário não está logado no Facebook!");
                }
               });
            };
            </script>
    </body>
</html>