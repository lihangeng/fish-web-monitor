Ext.define('Eway.view.monitor.cashinit.Info', {
	extend : 'Ext.window.Window',
	alias : 'widget.monitor_cashinit_info',
	title : '加钞详细信息',
	modal : true,
	constrainHeader : true,
	resizable : false,
	maximizable : true,
	width : 700,
	height : 450,
	layout : 'fit',
	autoScroll : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding:10px 10px 10px 10px',
				height : 440,
				defaults : {
					anchor : '100%'
				},
				items : [ {
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaultType : 'textfield',
						style : 'padding-top:10px',
						defaults : {
							anchor : '90%',
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							fieldLabel : Eway.locale.commen.terminalId,
							name : 'termId'
						}, {
							fieldLabel : '加钞ID',
							name : 'uuId'
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						style : 'padding-top:10px',
						defaults : {
							anchor : '90%',
							xtype : 'textfield',
							style : 'padding-top:10px',
							readOnly : true
						},
						items : [ {
							fieldLabel : '加钞总金额',
							name : 'amt'
						}, {
							fieldLabel : '加钞时间',
							name : 'date'
						} ]
					} ]
				}, {
					title : '详细信息',
					itemid : 'detailItemId',
					xtype : 'grid',
					store : 'monitor.cashinit.CashInitDetail',
					border:true,
					columns : [ Ext.create('Ext.grid.RowNumberer'), {
						header : '钞箱ID',
						dataIndex : 'boxId'
					}, {
						header : '币种',
						dataIndex : 'boxCurrency'
					}, {
						header : '初始金额',
						dataIndex : 'boxInitAmt',
						flex:1
					} ]
				} ]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});