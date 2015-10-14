
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
	        					fieldLabel : Eway.locale.monitor.business.transaction.card,
	        					regex: /^[0-9]{13,19}$/,
								regexText:Eway.locale.tip.cardNo
	        				}]
	        			}, {
	        				columnWidth : .3,
	        				items : [{
	        					xtype : 'field.monitor.UserName',
	        					labelAlign : 'right',
	        					fieldLabel : Eway.locale.monitor.business.transaction.userName,
	        					maxLength : 20
	        				}]
	        			}, {
	        				columnWidth : .3,
	        				items : [{
	        					xtype : 'textfield',
	        					name :'organization',
	        					labelAlign : 'right',
	        					fieldLabel : Eway.locale.monitor.business.blackList.cardBank,
	        					maxLength : 60
	        				}]
	        			}]
	        		});
	        		
	        		this.callParent(arguments);
	        	}
});