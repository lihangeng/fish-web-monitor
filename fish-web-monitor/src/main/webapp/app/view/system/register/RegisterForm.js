    Ext.define('Eway.view.system.register.RegisterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.register_form',
	initComponent : function(){
		var me = this;
		Ext.apply(this, {
			height : 400,
			width : 800,
			layout : 'card',
			title : '系统注册',
			header:false,
			activeItem : 0,
			items : [{
				xtype : 'form',
				action : 'displayInfo',
				border : false,
				buttonAlign : 'center',
				bodyStyle : {
					'background-color' : '#dfe8f5',
					padding : '65px 55px 55px 55px'
				},
				items : [{
							xtype : 'textfield',
							fieldLabel : '注册码',
							name : 'serial',
							width: 450
						}, {
							xtype : 'textfield',
							fieldLabel : '开始时间',
							name : 'startDate'
						}, {
							xtype : 'textfield',
							fieldLabel : '到期时间',
							name : 'endDate'
						}, {
							xtype : 'textfield',
							fieldLabel : '注册类型',
							name : 'type'
						}],
				listeners : {
					'render' : function(panel) {
						Ext.Ajax.request({
									method : 'GET',
									url : 'api/system/register/getInfo',
									success : function(response) {
										var object = Ext
												.decode(response.responseText);
										me._fillDisplayForm(panel, object);
									},
									failure : function() {
										Eway.alert('内部错误');
									}
								});
					}
				}
			},
			{
				xtype : 'form',
				action : 'register',
				border : false,
				bodyStyle: {
					'background-color':'#dfe8f5',
				    padding: '50px 50px 50px 50px'
				},
				items : [{
					xtype : 'fieldcontainer',
					fieldLabel : '序列号',
					layout : 'column',
					defaults : {
						hideLabel : true
					},
					items : [{
						xtype : 'textfield',
						fieldLabel : '',
						name : 'key',
						width: 100,
						readOnly : true
					},{
						name : 'getKey',
						xtype : 'displayfield',
						columnWidth: .5,
						value : '<font color="blue">正在获取序列号......</font>'
					}]
				},{
					xtype : 'textfield',
					hideMode : 'visibility',
					hidden : true
				},{
					xtype : 'fieldcontainer',
					fieldLabel : '校验码',
					layout : 'column',
					defaults : {
						hideLabel : true
					},
					items : [
						{xtype : 'textfield',width : 80,name:'serial1'},
						{xtype : 'displayfield',value : '-'},
						{xtype : 'textfield',width : 80,name:'serial2'},
						{xtype : 'displayfield',value : '-'},
						{xtype : 'textfield',width : 80,name:'serial3'},
						{xtype : 'displayfield',value : '-'},
						{xtype : 'textfield',width : 80,name:'serial4'},
						{xtype : 'displayfield',value : '-'},
						{xtype : 'textfield',width : 120,name:'serial5'}
					]
				}],
				buttonAlign : 'center',
				fbar : [{
					xtype : 'button',
					text : '确定',
					name : 'ok',
					disabled : true,
					iconCls :'sureBtn',
					handler : me._register,
					scope : me
				}],
				listeners : {
					show : me.onShowRegisterPanel,
					scope : me
				}
			}],
			tbar : ['->',{
				xtype : 'button',
				text : '注册',
				iconCls :'sureBtn',
				pressed : true,
				action : 'register',
				handler : function(btn){
					btn.hide();
					var win = btn.up('form');
					win.down('button[action="return"]').show();
					var returnForm = win.down('form[action="register"]');
					win.getLayout().setActiveItem(returnForm);
				}
			},{
				xtype : 'button',
				text : '返回',
				hidden : true,
				action : 'return',
				iconCls :'returnBtn',
				handler : function(btn){
					btn.hide();
					var win = btn.up('form');
					win.down('button[action="register"]').show();
					var displayForm = win.down('form[action="displayInfo"]');
					win.getLayout().setActiveItem(displayForm);
				}
			}]
		});
		this.callParent(arguments);
	},
	_fillDisplayForm : function(panel,object){
		var serial = object.serial;
		var startDate = object.startDate;
		var endDate = object.endDate;
		var type = '';
		if(object.type=='0'){
			type = '试用';
			var endDateField = panel.down('textfield[name="endDate"]');
			endDateField.show();
			endDateField.setValue(endDate);
		}
		else if(object.type=='1') {
			type = '没有限制';
			panel.down('textfield[name="endDate"]').hide();
		}
		panel.down('textfield[name="serial"]').setValue(serial);
		panel.down('textfield[name="startDate"]').setValue(startDate);
		panel.down('textfield[name="type"]').setValue(type);
	},

	onShowRegisterPanel : function(panel){
		var keyField = panel.down('textfield[name="key"]');
		var getKeyField = panel.down('displayfield[name="getKey"]');
		var key = keyField.getValue();
		var button = keyField.up('form[action="register"]').down('button[name="ok"]');
		if(Ext.isEmpty(key)){
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/system/register',
				success : function(response){
					var object = Ext.decode(response.responseText);
					keyField.setValue(object.key);
					getKeyField.setValue('');
					button.enable();
				},
				failure : function(){
					getKeyField.setValue('<font color="red">序列号获取失败</font>');
				}
			});
		}
		else {
			getKeyField.setValue('');
			button.enable();
		}

	},
	_register : function(btn){
		var me = this;
		var form = btn.up('form[action="register"]');
		var keyValue = form.down('textfield[name="key"]').getValue();
		var serialValue = form.down('textfield[name="serial1"]').getValue()
							+'-'
							+form.down('textfield[name="serial2"]').getValue()
							+'-'
							+form.down('textfield[name="serial3"]').getValue()
							+'-'
							+form.down('textfield[name="serial4"]').getValue()
							+'-'
							+form.down('textfield[name="serial5"]').getValue();
		Ext.Ajax.request({
			method : 'POST',
			url : 'api/system/register',
			params : {
				key : keyValue,
				serial : serialValue
			},
			success : function(response){
				var object = Ext.decode(response.responseText);
				if(object.success){
					Eway.alert('注册成功');
					var win = btn.up('form');
					var form = win.down('form[action="displayInfo"]');
					win.getLayout().setActiveItem(form);
					me._fillDisplayForm(form,object);
					win.down('button[action="register"]').show();
					win.down('button[action="return"]').hide();
				}
				else {
					Eway.alert('注册失败');
				}

			},
			failure : function(){
				Eway.alert('出现内部错误');
			}
		});
	}
	});