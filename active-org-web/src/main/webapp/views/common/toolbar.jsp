<div id="mainToolbar" dojoType="dijit.Toolbar" data-dojo-props="region:'top'">
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