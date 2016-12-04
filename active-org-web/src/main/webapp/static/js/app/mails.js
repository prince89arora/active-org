define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dojo/text!app/template/leftPanel.html"
], function(declare, _WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin, template) {
    return declare("mails", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {

      templateString: template,

      constructor : function() {

      }
  });

});
