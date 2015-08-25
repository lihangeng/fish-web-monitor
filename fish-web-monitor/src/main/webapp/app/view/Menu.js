/**
 * 菜单栏
 */
Ext.define('Eway.view.Menu', {
	alias: 'widget.appmenu',
	extend: 'Ext.toolbar.Toolbar',
	
	initComponent: function() {
		Ext.apply(this, {
		   id: 'appmenu'
		});
        
        this.callParent(arguments);
	}
});