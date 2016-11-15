dojo.require("dojo.dom");
dojo.require("dojo.parser");
dojo.require("dijit.registry");
dojo.require("dijit.Toolbar");
dojo.require("dijit.form.Button");
dojo.require("dojo.domReady!");

dojo.require("dijit.dijit");
dojo.require("dijit.Dialog");
dojo.require("dojo._base.event");
dojo.require("dojo.html");

dojo.require("app.login");
dojo.require("app.modules");

dojo.addOnLoad(function(){
    dojo.parser.parse();

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

        if (login.processLogin()) {
            console.log("login success");
            var modules = new app.modules("leftPane");
        } else {
            dojo.html.set(dojo.dom.byId("login-error"), "Invalid login...");
            dojo._base.event.stop(e);
        }
    });


});