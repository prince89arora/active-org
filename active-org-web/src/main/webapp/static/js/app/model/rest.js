define([
    "dojo/_base/declare",
    "dojo/request",
    "dojo/dom",
    "dojo/html",
    "app/modules",
    "app/leftPanel"
], function(declare, request, dom, html, modules, leftPanel) {
    return declare("rest", null, {

      url : {
        login : "/active-org/rest/auth/login",
        logOut : "/active-org/rest/auth/logout"
      }

    });
});
