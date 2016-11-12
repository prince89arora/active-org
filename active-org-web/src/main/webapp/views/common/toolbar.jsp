<div id="mainToolbar" dojoType="dijit.Toolbar" data-dojo-props="region:'top'">

    <div id="menuWrapper" style="float: left">
        <div data-dojo-type="dijit/form/ToggleButton" id="mainToolbar.mail"
            data-dojo-props="iconClass:'Pan', showLabel:true" style="float:left;">Mail</div>
        <div data-dojo-type="dijit/form/ToggleButton" id="mainToolbar.project"
            data-dojo-props="iconClass:'Pan', showLabel:true" style="float:left;">Project/ Timesheet</div>
        <div data-dojo-type="dijit/form/ToggleButton" id="mainToolbar.tickets"
            data-dojo-props="iconClass:'Pan', showLabel:true" style="float:left;">Tickets</div>

    </div>

    <div data-dojo-type="dijit/form/ToggleButton" id="mainToolbar.login"
    data-dojo-props="iconClass:'Pan', showLabel:true" style="float:right;">Login</div>

    <div data-dojo-type="dijit/form/DropDownButton" id="user-info" style="float:right;">
      <span id="user-name">Prince</span>
      <div data-dojo-type="dijit/DropDownMenu" id="submenu2">
          <div data-dojo-type="dijit/MenuItem" id="logout">Logout</div>
          <div data-dojo-type="dijit/MenuItem" id="profile">Profile</div>
      </div>
    </div>

 </div>