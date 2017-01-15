define("app/util/rest", [
    "dojo/_base/declare",
    "dojo/request",
    "dojo/dom"
], function(declare, request, dom){

  var rest =  {
  
    url : {
        login : "/active-org/rest/auth/login",
        logOut : "/active-org/rest/auth/logout"
    },

    // POST Request to handle json
    post : function(url, data) {
        var json = {};
        request.post(url, {
            data: data,
            sync : true,
            handleAs : "json"
        }).then(function(response) {
            json = response;
        });        
        return json;
    },

    // GET Request to handle json
    get : function(url) {
        request.get(url, {
            sync : true,
            handleAs : "json"
        }).then(function(response){
            return response;   
        });
    },

    // Login a user from login form
    login : function(username, password) {
        var response = this.post(this.url.login, {
            "username" : username,
            "password" : password
        });       
        return response;
    }

  };

  return rest;
});

