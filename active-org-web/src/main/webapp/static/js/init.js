dojo.require("dojo.dom");
dojo.require("dojo.parser");
dojo.require("dijit.registry");
dojo.require("dijit.Toolbar");
dojo.require("dijit.form.Button");
dojo.require("dojo.domReady!");
dojo.require("dojo.dom-style");

dojo.require("dijit.dijit");
dojo.require("dijit.Dialog");
dojo.require("dojo._base.event");
dojo.require("dojo.html");
dojo.require("dojo.request");

dojo.require("app.login");
dojo.require("app.modules");
dojo.require("app.leftPanel");

dojo.addOnLoad(function(){
    dojo.parser.parse();

    var context = {
        user : {}

    };

    dojo.connect(dojo.dom.byId("logout"), "onclick", function(e) {
      var login = new app.login({
          username : "",
          password : ""
      });
      login.logOut();
    });

    dojo.connect(dojo.dom.byId("mainToolbar.login"), "onclick", function(e) {
        dojo.html.set(dojo.dom.byId("login-error"), "");
        dijit.registry.byId("username").set("value", "");
        dijit.registry.byId("password").set("value", "");
        dijit.registry.byId("loginDialog").show();
    });

    dojo.connect(dojo.dom.byId("cancel"), "onclick", function(e) {
        dijit.registry.byId("loginDialog").hide();
    });

    dojo.connect(dojo.dom.byId("loginSubmit"), "onclick", function(e) {
        dojo.html.set(dojo.dom.byId("login-error"), "");
        var login = new app.login({
            username : dijit.registry.byId("username").get("value"),
            password : dijit.registry.byId("password").get("value")
        });
        if (!login.processLogin()) {
          dojo._base.event.stop(e);
        }
    });

    dojo.request.get("/active-org/rest/auth/user", {
        sync : true,
        handleAs : "json"
    }).then(function(response){
        context.user = response;
        if (response.username != null && response.username != undefined) {
            /*
             * Handling Logged in user
             */
            dojo.dom.byId("mainToolbar.login").style.display = "none";
            dojo.html.set(dojo.dom.byId("user-name"), response.username);
            var leftPane = dojo.dom.byId("leftPane");
            var mailfolder = new app.leftPanel().placeAt(leftPane);
        }
    });


});
