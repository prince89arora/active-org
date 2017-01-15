define("app/util/common", [
    "dojo/_base/declare",
    "app/util/htmlUtil",
], function(declare, htmlUtil){

  var common =  {
  
    CONS : {
      contentPaneId : "main-container",
      loginErrorId : "login-error",
      userDetailnav : "user-detail-nav",
      userInfoNav : "nav-user",
      loginButton : "show-login",
      closeLogin : "close-login",
      
      noUser : "anonymous",
      loginWrapper : "login-wrapper" 
    },

    login : {
      usernameId : "username",
      passwordId : "password",
      submit : "loginSubmit"
    },
  

    error : {
        invalidLogin : "Invalid login..."
      }
  };

  return common;
});
