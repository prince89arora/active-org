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
      
      noUser : "anonymous",
      loginWrapper : "login-wrapper" 
    }, 
  

    error : {
        invalidLogin : "Invalid login..."
      }
  };

  return common;
});
