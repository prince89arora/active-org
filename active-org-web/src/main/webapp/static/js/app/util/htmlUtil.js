define("app/util/htmlUtil",
  ["../../dojo/_base/kernel",
  "../../dojo/_base/declare",
  "../../dojo/dom",
  "../../dojo/dom-construct",
  "../../dojo/parser",
  "../../dojo/_base/fx"],
	function(kernel, declare, dom, domConstruct, parser, fx) {

	var html = {

    //Show an element
    show : function(element) {
      var node = dojo.dom.byId(element);
      dojo.addClass(node, "show");
      dojo.removeClass(node, "hide");
    },

    //Hide and element
    hide : function(element) {
      var node = dojo.dom.byId(element);
      dojo.addClass(node, "hide");
          dojo.removeClass(node, "show");
    },

    setHtml : function(element, html) {
        dojo.dom.byId(element).innerHTML = html;
    },

    getValue : function(element) {
          var node =  dojo.dom.byId(element);
          return dojo.attr(node, "value");
    },

    getHtml : function(element) {
        return dojo.dom.byId(element).innerHTML;
    },

    getById : function(element) {
      return dojo.dom.byId(element);
    },

    toogleDisplay : function(element) {
      var node = dojo.dom.byId(element);  
      if (dojo.hasClass(node, "hide")) {
          dojo.addClass(node, "show");
          dojo.removeClass(node, "hide");
      } else if (dojo.hasClass(node, "show")) {
          dojo.addClass(node, "hide");
          dojo.removeClass(node, "show");
      }
      //fx.fadeOut({ node: node }).play();
    }

  }

	return html;
});
