dojo.require("dojo.dom");
dojo.require("dojo.parser");
dojo.require("dijit.registry");
dojo.require("dojo.domReady!");

dojo.require("dijit.dijit");
dojo.require("dojo.html");
dojo.require("app.util.htmlUtil");
dojo.require("app.util.common");


dojo.addOnLoad(function(){
    dojo.parser.parse();


    dojo.connect(app.util.htmlUtil.getById(app.util.common.CONS.userInfoNav), "onclick", function(e) {
        app.util.htmlUtil.toogleDisplay(  app.util.common.CONS.userDetailnav );
    });

});
