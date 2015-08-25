
Ext.define('Eway.view.monitor.business.BlackListCardFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.business_BlackListCardFilterForm',
	
	requires: ['Eway.view.field.monitor.CardNo',
	           'Eway.view.field.monitor.UserName'],
	            
	           /* title: '输入您的查询条件',*/
	        	height: 40,
	        	layout : 'column',
	        	defaults : {
	        		border : false
	        	},

	        	initComponent: function() {
	        		Ext.apply(this, {
	        			items : [{
	        				columnWidth : .3,
	        				items : [{
	        					xtype : 'field.monitor.CardNo',
	        					labelAlign : 'right',
	        					fieldLabel : '交易卡号',
	        					regex: /^[0-9]{13,19}$/,
								regexText:'只能输入13到19个数字！'
	        				}]
	        			}, {
	        				columnWidth : .3,
	        				items : [{
	        					xtype : 'field.monitor.UserName',
	        					labelAlign : 'right',
	        					fieldLabel : '用户姓名',
	        					maxLength : 20
	        				}]
	        			}, {
	        				columnWidth : .3,
	        				items : [{
	        					xtype : 'textfield',
	        					name :'organization',
	        					labelAlign : 'right',
	        					fieldLabel : '所属银行',
	        					maxLength : 60
	        				}]
	        			}]
	        		});
	        		
	        		this.callParent(arguments);
	        	}
});