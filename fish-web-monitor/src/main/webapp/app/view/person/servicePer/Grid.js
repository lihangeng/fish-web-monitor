
Ext.define('Eway.view.person.servicePer.Grid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.ser_person_grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.SerPerson');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->', {
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'servicePerAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'servicePerUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'servicePerDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.person.servicePer.servicePerlink,
				glyph : 0xf0c1,
				action: 'link',
				code : 'servicePerlink',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.commen.jobNum,
				dataIndex : 'jobNum'
			},{
				header : EwayLocale.commen.name,
				dataIndex : 'name'
			},{
				header : EwayLocale.commen.devServiceName,
				dataIndex : 'organizationName'
			},{
				header : EwayLocale.commen.state,
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return EwayLocale.commen.comboxStatus.onJob;
		             }else if(value==2){
		                	   return EwayLocale.commen.comboxStatus.onAdjust;
		             }else if(value==3){
		                	   return EwayLocale.commen.comboxStatus.onVacation;
		             }else if(value==0){
		                	   return EwayLocale.commen.comboxStatus.other;
		             }
				}
			},{
				header : EwayLocale.commen.type,
				dataIndex : 'type',
				renderer: function(value,metadata,record){
					if(value==0){
		                	 return EwayLocale.commen.comboxType.machineManager;
		             }else if(value==1){
		                	   return EwayLocale.commen.comboxType.machineRepairer;
		             }
				}
			}, {
				header : EwayLocale.commen.mobile,
				dataIndex : 'mobile'
			},{
				header : EwayLocale.commen.email,
				dataIndex : 'email'
			}, {
				header : EwayLocale.commen.phone,
				dataIndex : 'phone'
			},{
				header : EwayLocale.commen.remark,
				dataIndex : 'remark',
				flex :1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	}
});