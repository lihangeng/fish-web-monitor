Ext.define('Eway.view.bankOrg.Organization', {
	extend : 'Ext.container.Container',
	xtype:'organization',

	requires : [ 'Ext.ux.layout.ResponsiveColumn','Eway.view.bankOrg.Grid',
	             'Eway.view.bankOrg.OrganizationController','Eway.view.bankOrg.OrganizationModel'],
	
	controller: 'organizationController', 
	viewModel: { 
			type: 'organizationModel'
		},
	
//	layout : 'responsivecolumn',
	
	layout : 'card',
	
//	reference: 'organizationMgr',
	
	items: [{
		xtype:'container',
		layout:'responsivecolumn',
		items : [ {
	        xtype: 'form',
	        border : false,
	        responsiveCls : 'big-100 small-100',
	        defaultType: 'textfield',
	        defaults: {
	            labelWidth: 90,
	            labelAlign: 'top',
	            labelSeparator: '',
	            submitEmptyText: false,
	            anchor: '100%'
	        },
	        items:[
	            {
//	                fieldLabel:'机构编号',
	            	emptyText : 'First Name'
	            },
	            {
	                emptyText : 'Last Name'
	            },
	            {
	                emptyText : 'Company'
	            }]
		}, {
			xtype : 'bank_organization_grid',
			responsiveCls : 'big-100 small-100'
		} ]
	},{
		xtype:'panel',
		margin: 20,
		tbar:{
			items:["增加 机构信息","->",{
				xtype:'button',
				tooltip:'关闭',
//				ui:'soft-blue',
				iconCls:'x-fa fa-remove',
				listeners: {
                    click: 'onBack'
                }
			}]
		},
		items:[{
	        xtype: 'form',
	        border : false,
	        defaultType: 'textfield',
	        defaults: {
	            labelWidth: 90,
//	            labelAlign: 'top',
	            labelSeparator: '',
	            submitEmptyText: false,
	            margin: 10,
	            anchor: '80%',
	            msgTarget : 'under'
	        },
	        items:[
	            {
	                fieldLabel:'机构编号',
	            	emptyText : 'Code',
	            	allowBlank : false,
					maxLength:20
	            },
	            {
	            	 fieldLabel:'机构名称',
	            	 emptyText : 'Name',
	            	 allowBlank : false,
	 	             msgTarget : 'title'
	            },
	            {
	            	fieldLabel:'邮政编码',
	            	emptyText : 'Zip',
	            	allowBlank : false,
		            msgTarget : 'side'
	            }
	            ]
		
		}],
		bbar:{
			items:[{
				xtype:'button',
				text:'提交',
//				scale: 'medium',
//				disabled: true,
				iconCls:'x-fa fa-save',
				buttonAlign:'center',
				ui:'soft-blue'
			}]
		}
		
	}]

});
