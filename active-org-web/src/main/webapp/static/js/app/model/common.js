define([
    "dojo/_base/declare",
    "dojo/request",
    "dojo/dom",
    "dojo/html",
    "app/modules",
    "app/leftPanel"
], function(declare, request, dom, html, modules, leftPanel) {
    return declare("common", null, {

      leftPanelId : "leftPane",
      contentPaneId : "main-container",
      loginErrorId : "login-error",
      loginButton : "mainToolbar.login",
      userName : "user-name",

      noUser : "anonymous",

      error : {
        invalidLogin : "Invalid login..."
      }



    });
});