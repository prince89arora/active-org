define([
    "dojo/_base/declare",
    "dojo/dom",
    "dijit/layout/AccordionContainer",
    "dijit/layout/ContentPane",
    "dojo/store/Memory",
    "dijit/tree/ObjectStoreModel",
    "dijit/Tree",
    "dojo/html"
], function(declare, dom, AccordionContainer, ContentPane, Memory, ObjectStoreModel, Tree, html){
    return declare("modules", null, {

        usertype : "",
        sectionId : "",

        constructor : function(sectionId) {
            //this.usertype = user.type;
            this.sectionId = sectionId;
            this.addSections();
        },

        addSections: function() {
            var aContainer = new AccordionContainer({}, this.sectionId);

                aContainer.addChild(new ContentPane({
                    title: "Emails",
                    content: "<div id='emailTree'>Email Folders here.. </div>"
                }));
                aContainer.addChild(new ContentPane({
                    title:"Tasks/ Projects",
                    content:"List of projects and tasks"
                }));
                aContainer.addChild(new ContentPane({
                    title:"Team",
                    content:"List of Team / Employees"
                }));
                aContainer.startup();

                this.emailTree();
        },

        //Prepare Email Tree section
        emailTree : function() {
            var store = new Memory({
                data: [
                    { id: 0, label: "Folders"},
                       {id: 1, label: "Inbox", parent: 0},
                       {id: 2, label: "Sent Items", parent: 0},
                       {id: 3, label: "Drafts", parent: 0}
                ],
                getChildren: function(object) {
                    return this.query({parent: object.id});
                }
            });

            var model = new ObjectStoreModel({
                store: store,
                query: {id: 0},
                labelAttr: "label"
            });


            // Create the Tree.
            var tree = new Tree({
                model: model,
                onClick: function(item){
                    html.set(dom.byId("main-container"), "CLicked : "+ item.id);
                 }
            });
            tree.placeAt("emailTree");
            tree.startup();
        }
    });
});