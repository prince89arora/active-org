dojoConfig = {
	has: {
	 "dojo-firebug": true,
	 "dojo-debug-messages": true
	},
    parseOnLoad: false,
    baseUrl: "static/js",
    packages: [
		{ name: "dojo", location: "libs/dojo" },
		{ name: "dijit", location: "libs/dijit" },
		{ name: "dojox", location: "libs/dojox" },
		{ name: "app", location: "app"}
	]
};