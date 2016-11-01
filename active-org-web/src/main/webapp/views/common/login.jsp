<div data-dojo-type="dijit/Dialog" data-dojo-id="loginDialog" title="Login" id="loginDialog">

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

    <p class="error-message" id="login-error"></p>

    <div class="dijitDialogPaneActionBar">
        <button data-dojo-type="dijit/form/Button" data-dojo-id="loginSubmit" type="submit" id="loginSubmit">Login</button>
        <button data-dojo-type="dijit/form/Button" type="button" id="cancel">Cancel</button>
    </div>
</div>