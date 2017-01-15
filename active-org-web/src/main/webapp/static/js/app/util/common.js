define("app/model/common", [
  "dojo/_base/declare",
    "dojo/request",
    "dojo/dom",
    "dojo/html",
    "app/modules",
    "app/leftPanel",
    "dijit/registry"
], function(declare, request, dom, html, modules, leftPanel, registry){

  var common =  {

    leftPanelId : "leftPane",
    contentPaneId : "main-container",
    loginErrorId : "login-error",
    loginButton : "mainToolbar.login",
    userName : "user-name",

    noUser : "anonymous",

    error : {
        invalidLogin : "Invalid login..."
      },

      getContainer : function() {
        return registry.byId(this.contentPaneId);
      } 

  };

  return common;
});
