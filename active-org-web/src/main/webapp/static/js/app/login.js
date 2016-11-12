/*
 * Login class to handle login authentication
 * and get user details from server.
 */
define([
    "dojo/_base/declare",
    "dojo/request",
    "dojo/dom"
], function(declare, request, dom){
    return declare("login", null, {

        username : "",
        password : "",

        constructor : function(options) {
            this.username = options.username;
            this.password = options.password;
        },

        processLogin: function(){
            var status = false;
            request.post("/active-org/rest/auth/login", {
                data: {
                    username: this.username,
                    password: this.password
                },
                sync : true,
                handleAs : "json"
            }).then(function(response){
                status = response.status;
            });
            return status;
        }
    });
});