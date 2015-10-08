
Ext.define('Eway.view.person.bankPer.Grid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.bank_person_grid',

	requires: ['Eway.lib.Util'],

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.person.person.BankPerson');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->', {
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'bankPerAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'bankPerUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'bankPerDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.bankPerlink,
				glyph : 0xf0c1,
				action: 'link',
				code : 'bankPerlink',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : Eway.locale.person.bankPer.jobNum,
				dataIndex : 'jobNum'
			},{
				header : Eway.locale.person.bankPer.name,
				dataIndex : 'name'
			}, {
				header : Eway.locale.person.bankPer.personJobName,
				dataIndex : 'personJobName'
			},{
				header : Eway.locale.person.bankPer.organizationName,
				dataIndex : 'organizationName'
			},{
				header : Eway.locale.person.bankPer.state,
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return Eway.locale.person.bankPer.comboxStatus.onJob;
		             }else if(value==2){
		                	   return Eway.locale.person.bankPer.comboxStatus.onAdjust;
		             }else if(value==3){
		                	   return Eway.locale.person.bankPer.comboxStatus.onVacation;
		             }else if(value==0){
		                	   return Eway.locale.person.bankPer.comboxStatus.other;
		             }
				}
			},{
				header : Eway.locale.person.bankPer.type,
				dataIndex : 'type',
				renderer: function(value,metadata,record){
					if(value==0){
		                	 return Eway.locale.person.bankPer.comboxType.machineManager;
		             }else if(value==1){
		                	   return Eway.locale.person.bankPer.comboxType.machineRepairer;
		             }
				}
			}, {
				header : Eway.locale.person.bankPer.mobile,
				dataIndex : 'mobile'
			},{
				header : Eway.locale.person.bankPer.email,
				dataIndex : 'email',
				width : 150
			}, {
				header : Eway.locale.person.bankPer.phone,
				dataIndex : 'phone'
			},{
				header : Eway.locale.person.bankPer.gender,
				dataIndex : 'gender',
				renderer: function(value,metadata,record){
					if(value=="MALE"){
	                	   return Eway.locale.person.bankPer.comboxGender.male;
	                   }else if(value=="FEMALE"){
	                	   return Eway.locale.person.bankPer.comboxGender.female;
	                   }else{
	                	   return Eway.locale.person.bankPer.comboxGender.unkown;
	                   }
				}
			},{
				header : Eway.locale.person.bankPer.remark,
				dataIndex : 'remark',
				width : 150
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});