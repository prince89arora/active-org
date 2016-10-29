<div data-dojo-type="dijit/Dialog" data-dojo-id="loginDialog" title="Login">
    <table class="dijitDialogPaneContentArea">
        <tr>
            <td><label for="username">Username:</label></td>
            <td><input type="text" data-dojo-type="dijit/form/TextBox" name="username" id="username"></td>
        </tr>
        <tr>
            <td><label for="password">Password:</label></td>
            <td><input type="password" data-dojo-type="dijit/form/TextBox" name="password" id="password"></td>
        </tr>
    </table>

    <div class="dijitDialogPaneActionBar">
        <button data-dojo-type="dijit/form/Button" type="submit" id="login">Login</button>
        <button data-dojo-type="dijit/form/Button" type="button" data-dojo-props="onClick:function(){loginDialog.hide();}"
                id="cancel">Cancel</button>
    </div>
</div>