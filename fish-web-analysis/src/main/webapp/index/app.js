/*
 * This file is responsible for launching the application. Application logic should be
 * placed in the Eway.Application class.
 */
Ext.application({
    name: 'Eway',
    extend: 'Eway.Application',
    //appFolder : 'app',


    // Simply require all classes in the application. This is sufficient to ensure
    // that all Admin classes will be included in the application build. If classes
    // have specific requirements on each other, you may need to still require them
    // explicitly.
    //
    requires: [
        'Eway.view.main.Viewport','Eway.view.main.ViewportController','Eway.view.main.ViewportModel'
        ,'Eway.view.main.MainContainerWrap'
    ],

    // The name of the initial view to create. With the classic toolkit this class
    // will gain a "viewport" plugin if it does not extend Ext.Viewport. With the
    // modern toolkit, the main view will be added to the Viewport.
    //
    mainView: 'Eway.view.main.Viewport'
});
