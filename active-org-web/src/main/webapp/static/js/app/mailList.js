define([
    "dojo/_base/declare",
    "dojo/dom",
    "dojo/html",
    "app/model/common",
    "app/model/rest",
    "app/util/htmlUtil",
    "dijit/registry"
], function(declare, dom, html, common, rest, htmlUtil, registry) {
    return declare("mailList", [common, rest], {

      constructor : function(folderId) {
          console.log("getting mails for : "+ folderId);

          var mainContainer = registry.byId(this.contentPaneId);
          mainContainer.setContent("Try clicking doc0, doc1 or doc2 instead...");
      }
  });

});
