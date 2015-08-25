Ext.define('Eway.view.monitor.device.BaseWindow',{
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_device_baseWindow',
	layout : 'card',
	width : 600,
	height : 550,
	buttonAlign : 'center',
	action : undefined,
	modal : true,
	
	initComponent : function(){
		var me = this;
		this.buttons = [{
				text : '确定',
				iconCls :'sureBtn',
				action :'save'
			},{
				text : '取消',
				iconCls :'returnBtn',
				handler : function(btn){
					btn.up('window').hide();
					var panel = btn.up('window').getLayout().getActiveItem();
					if(panel.action=="monitor_device_stateConfig"){
						panel.getForm().setValues(panel.getMemoryRecord());
					}
				}
			}];
		this.on('beforeclose',function(){
			this.hide();
			var panel = me.getLayout().getActiveItem();
			if(panel.action=="monitor_device_stateConfig"){
				panel.getForm().setValues(panel.getMemoryRecord());
			}
			return false;
		},this);
		this.callParent(arguments);
	},
	
	addComponent : function(panel){
		var layout = this.getLayout();
		var panels = layout.getLayoutItems();
		var hasPanel = false;
		for(var i=0;i<panels.length;i++){
			var item = panels[i];
			if(item.action == panel.action){
				hasPanel = true;
				return item;
			}
		}
		if(!hasPanel){
			this.add(panel);
			return panel;
		}
	},
	
	
	display : function(panel,comp){
		var item = this.addComponent(panel);
		this.show();
//		var winx = this.getWidth();
//		var winh = this.getHeight();
		/*var winx = 500,
			winh = 450;
		this.animate({
			from : {
				opacity : 0,
				width : 0,
				height : 0
			},
			to : {
				opacity : 1,
				width : winx,
				height : winh
			},
			duration : 500,
			easing : 'easeIn'
		});*/
		this.getLayout().setActiveItem(item);
		/*this.el.setOpacity(0);
		this.el.setOpacity(1,true);
		if(comp){
			var pos = comp.getPosition();
//			this.setPosition(pos[0],pos[1]+comp.getHeight());
		}*/
	}
	
});