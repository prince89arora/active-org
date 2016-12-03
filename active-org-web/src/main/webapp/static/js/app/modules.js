define([
    "dojo/_base/declare",
    "dojo/dom",
    "dijit/layout/AccordionContainer",
    "dijit/layout/ContentPane",
    "dojo/store/Memory",
    "dijit/tree/ObjectStoreModel",
    "dijit/Tree",
    "dojox/grid/DataGrid",
    "dojo/data/ItemFileWriteStore",
    "dojo/_base/lang",
    "dijit/layout/BorderContainer",
    "dijit/layout/ContentPane",
    "dojo/html"
], function(declare, dom, AccordionContainer, ContentPane, Memory, ObjectStoreModel, Tree, DataGrid, ItemFileWriteStore,
lang, BorderContainer, ContentPane, html){
    return declare("modules", null, {

        usertype : "",
        sectionId : "",

        constructor : function(sectionId) {
            //this.usertype = user.type;
            this.sectionId = sectionId;
        },

        startLayout : function () {
          this.addSections();
        },

        addSections: function() {
            var aContainer = new AccordionContainer({}, this.sectionId);

                aContainer.addChild(new ContentPane({
                    title: "Emails",
                    content: "<div id='emailTree'></div>"
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
                onClick: function(item) {
                    //main-container
                    var listPanel = new BorderContainer({
                        region: top,
                        id: "email-list",
                        content: "Testing content...."
                    }, "main-container");
                 }
            });
            tree.placeAt("emailTree");
            tree.startup();
        },

        inboxMail : function() {

            var data = {
              identifier: "id",
              items: [
                   { id: 1, sender: "Adam Arlen", subject: 'But are not followed by two hexadecimal', date: "18/12/2005"},
                   { id: 2, sender: "Bob Baxter", subject: 'Because a % sign always indicates', date: "18/12/2005"},
                   { id: 3, sender: "Adam Arlen", subject: 'Signs can be selectively', date: "18/12/2005"}
                 ]
            };

            var store = new ItemFileWriteStore({data: data});

            var layout = [[
                  {'name': '#', 'field': 'id', 'width': '100px'},
                  {'name': 'Sender', 'field': 'sender', 'width': '20%'},
                  {'name': 'Subject', 'field': 'subject', 'width': '60%'},
                  {'name': 'Date', 'field': 'date', 'width': '20%'}
                ]];


            var grid = new DataGrid({
                    id: 'grid',
                    store: store,
                    structure: layout,
                    rowSelector: '20px'
                });

                grid.placeAt("gridDiv");
                grid.startup();
        },

        destroy : function () {
            html.set(dom.byId(this.sectionId), "");
        }
    });
});
