define("app/util/htmlUtil",
  ["../../dojo/_base/kernel",
  "../../dojo/_base/declare",
  "../../dojo/dom",
  "../../dojo/dom-construct",
  "../../dojo/parser"],
	function(kernel, declare, dom, domConstruct, parser) {

	var html = {

    //Show an element
    show : function(element) {
      dojo.dom.byId(element).style.display = "block";
    },

    //Hide and element
    hide : function(element) {
      dojo.dom.byId(element).style.display = "none";
    }
  }

	return html;
});
