Ext.define('Eway.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Eway',

    stores: [
        'NavigationTree'
    ],

    defaultToken : 'bankOrg',

    //controllers: [
        // TODO - Add Global View Controllers here
    //],

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
