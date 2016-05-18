Ext.define('Eway.view.bankOrg.OrganizationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.organizationController',

    listen : {
//        controller : {
//            '#' : {
//                unmatchedroute : 'onRouteChange'
//            }
//        }
    },
    
    onAdd : function(btn){
    	var me = this,
    	organizationMgrLayout = null;
//        refs = me.getReferences(),
//        organizationMgr = refs.organizationMgr,
//    	organizationMgrLayout = organizationMgr.getLayout();
    	organizationMgrLayout = btn.up('organization');
    	organizationMgrLayout.setActiveItem(1);
    },
    
    onBack : function(btn){
      	var me = this,
    	organizationMgrLayout = null;
    	organizationMgrLayout = btn.up('organization');
    	organizationMgrLayout.setActiveItem(0);
    }
    

   
});
