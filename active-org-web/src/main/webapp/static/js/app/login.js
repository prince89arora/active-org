/*
 * Login class to handle login authentication
 * and get user details from server.
 */
define([
    "dojo/cookie",
    "dojo/_base/declare",
    "dojo/request",
    "dojo/dom",
    "dojo/html",
    "app/modules",
    "app/leftPanel"
], function(cookie, declare, request, dom, html, modules, leftPanel) {
    return declare("login", null, {

        usernameinput : "",
        passwordinput : "",

        constructor : function(options) {
            this.usernameinput = options.username;
            this.passwordinput = options.password;
        },

        processLogin: function() {
            var status = false;
            var usernameVar = this.usernameinput;
            request.post("/active-org/rest/auth/login", {
                data: {
                    username: usernameVar,
                    password: this.passwordinput
                },
                sync : true,
                handleAs : "json"
            }).then(function(response){
                status = response.status;
                if (status) {
                    cookie("loginid", response.token, { expires: 10 });
                    html.set(dom.byId("user-name"), usernameVar);
                    //dojo.dom.byId("mainToolbar.login").style.display = "none";
                    var leftPane = dojo.dom.byId("leftPane");
                    var mailfolder = new app.leftPanel().placeAt(leftPane);
                } else {
                  html.set(dom.byId("login-error"), "Invalid login...");
                }
            });
            return status;
        },

        logOut : function() {
          request.post("/active-org/rest/auth/logout", {
              sync : true,
              handleAs : "json"
          }).then(function(response){
              cookie("loginid", "", { expires: -1 });
          });
          dojo.html.set(dojo.dom.byId("user-name"), "anonymous");
          dojo.dom.byId("mainToolbar.login").style.display = "block";

          dojo.html.set(dojo.dom.byId("leftPane"), "");
        }
    });
});
