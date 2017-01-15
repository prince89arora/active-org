define("app/security/login", [
    "dojo/_base/declare",
    "app/util/common",
    "app/util/rest",
    "app/util/htmlUtil",
    "dojo/cookie"
], function(declare, common, rest, htmlUtil, cookie) {

    var login = {

        requestLogin : function() {
            console.log("username "+ htmlUtil.getValue(common.login.usernameId));
           var response = rest.login( htmlUtil.getValue(common.login.usernameId) , htmlUtil.getValue(common.login.passwordId));
           this.loginCallBack(response);            
        },

        loginCallBack: function(response) {
           if (response.status) {
               cookie("loginid", response.token, { expires: 10 }); 
               htmlUtil.hide(  common.CONS.loginWrapper );
           } else {
               console.log("error id: "+ common.CONS.loginErrorId)
               htmlUtil.setHtml( common.CONS.loginErrorId, common.error.invalidLogin ); 
           }    
        }



    };

    return login;

    // return declare("login", [rest], {

    //     constructor : function(options) {
    //         this.usernameinput = options.username;
    //         this.passwordinput = options.password;
    //     },

    //     processLogin: function() {
    //         var status = false;
    //         var token = "";

    //         request.post(this.url.login, {
    //             data: {
    //                 username: this.usernameinput,
    //                 password: this.passwordinput
    //             },
    //             sync : true,
    //             handleAs : "json"
    //         }).then(function(response) {
    //             status = response.status;
    //             token = response.token;
    //         });
    //         if (status) {
    //             cookie("loginid", token, { expires: 10 });
    //             html.set(dom.byId(common.userName), this.usernameinput);
    //             htmlUtil.hide(common.loginButton);
    //             var leftPane = dojo.dom.byId(common.leftPanelId);
    //             var mailfolder = new app.leftPanel().placeAt(leftPane);
    //         } else {
    //             html.set(dom.byId(common.loginErrorId), common.error.invalidLogin);
    //         }
    //         return status;
    //     },

    //     logOut : function() {
    //       request.post(this.url.logOut, {
    //           sync : true,
    //           handleAs : "json"
    //       }).then(function(response){
    //           cookie("loginid", "", { expires: -1 });
    //       });
    //       dojo.html.set(dojo.dom.byId(common.userName), common.noUser);
    //       htmlUtil.show(common.loginButton);
    //       dojo.html.set(dojo.dom.byId(common.leftPanelId), "");
    //     }
    // });
});
