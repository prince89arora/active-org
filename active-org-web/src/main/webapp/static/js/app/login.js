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
    "app/leftPanel",
    "app/model/common",
    "app/model/rest",
    "app/util/htmlUtil"
], function(cookie, declare, request, dom, html, modules, leftPanel, common, rest, htmlUtil) {
    return declare("login", [common, rest], {

        usernameinput : "",
        passwordinput : "",

        constructor : function(options) {
            this.usernameinput = options.username;
            this.passwordinput = options.password;
        },

        processLogin: function() {
            var status = false;
            var token = "";

            request.post(this.url.login, {
                data: {
                    username: this.usernameinput,
                    password: this.passwordinput
                },
                sync : true,
                handleAs : "json"
            }).then(function(response) {
                status = response.status;
                token = response.token;
            });
            if (status) {
                cookie("loginid", token, { expires: 10 });
                html.set(dom.byId(this.userName), this.usernameinput);
                htmlUtil.hide(this.loginButton);
                var leftPane = dojo.dom.byId(this.leftPanelId);
                var mailfolder = new app.leftPanel().placeAt(leftPane);
            } else {
                html.set(dom.byId(this.loginErrorId), this.error.invalidLogin);
            }
            return status;
        },

        logOut : function() {
          request.post(this.url.logOut, {
              sync : true,
              handleAs : "json"
          }).then(function(response){
              cookie("loginid", "", { expires: -1 });
          });
          dojo.html.set(dojo.dom.byId(this.userName), this.noUser);
          htmlUtil.show(this.loginButton);
          dojo.html.set(dojo.dom.byId(this.leftPanelId), "");
        }
    });
});
