<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autorização</title>
        <script src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
        <script src="//connect.facebook.net/en_US/all.js"></script>
    </head>
    <body>
        <div id="fb-root"></div>
        <script>
          var applicationId = '1410104595870928';
       	  var response;
          var accessToken = "";
          var uid = "";
          window.fbAsyncInit = function() {
            // init the FB JS SDK
            FB.init({
              appId      : applicationId,                        // App ID from the app dashboard
              channelUrl : '//localhost', // Channel file for x-domain comms
              status     : true,                                 // Check Facebook Login status
              xfbml      : false                                  // Look for social plugins on the page
            });

            // Additional initialization code such as adding Event Listeners goes here
            FB.login(function(response) {
			   // handle the response
			}, {scope: 'user_events, publish_actions, rsvp_event'});
            rodaScriptFB(response);
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
           
           function rodaScriptFB(response){
             FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                  // the user is logged in and has authenticated your
                  // app, and response.authResponse supplies
                  // the user's ID, a valid access token, a signed
                  // request, and the time the access token 
                  // and signed request each expire
                  uid = response.authResponse.userID;
                  accessToken = response.authResponse.accessToken;
                  window.location.replace("http://localhost:8080<%=request.getContextPath()%>/PaginaInicial?token="+accessToken);
                } else if (response.status === 'not_authorized') {
                	window.location.replace("https://www.facebook.com/dialog/oauth?client_id=" + applicationId + "&redirect_uri=http://localhost:8080<%=request.getContextPath()%>/PaginaInicial?token=" + accessToken);
                } else {
                
                }
               });
            };
            </script>
    </body>
</html>