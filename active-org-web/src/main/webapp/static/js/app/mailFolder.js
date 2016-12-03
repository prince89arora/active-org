define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/layout/AccordionContainer",
    "dojo/text!app/template/mail/folderlist.html"
], function(declare, _WidgetBase, _TemplatedMixin, template) {
    return declare("mailFolder", [_WidgetBase, _TemplatedMixin], {

      templateString: template,

      constructor : function() {

      }
  });

});
