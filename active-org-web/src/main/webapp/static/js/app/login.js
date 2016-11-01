define([
    "dojo/_base/declare",
    "dojo/dom"
], function(declare, dom){
    return declare("login", null, {

        username : "",
        password : "",

        constructor : function(options) {
            console.log("Object Created");
            this.username = options.username;
            this.password = options.password;
        },

        processLogin: function(){
            if (this.username == "admin" && this.password == "admin") {
                return true;
            } else {
                return false;
            }

        }
    });
});