Ext.define('Eway.view.agent.remote.RemoteBrowseNetWorkView', {
	extend : 'Ext.window.Window',
	alias : 'widget.agent_remote_remoteBrowseNetWorkView',
	title : '网络信息',
	modal : true,
	resizable : false,
	constrainHeader : true,
	layout : 'fit',
	width : 400,
	height : 370,
	requires : [ 'Ext.chart.*',  'Ext.chart.series.*' ],

	initComponent : function() {
		var store4 = Ext.create('Ext.data.JsonStore', {
			fields : [ 'ip', 'bandWidth' ],
			data : this.generateData()
		});

		Ext.apply(this, {
			items : {
				xtype : 'tabpanel',
				items : [ {
					title : '网络连接状态',
					xtype : 'draw',
					x : 10,
					y : 10,
					viewBox : false,
					height : 150,
					width : 380,
					sprites : [ {
						type : 'text',
						stroke : 'black',
						text : '已发送',
						x : 90,
						y : 30
					}, {
						type : 'path',
						stroke : 'black',
						path : 'M 140 30 L 160 30',
						'stroke-width' : 1
					}, {
						type : 'text',
						stroke : 'black',
						text : '已接收',
						x : 285,
						y : 30
					}, {
						type : 'path',
						stroke : 'black',
						path : 'M 250 30 L 280 30',
						'stroke-width' : 1
					}, {
						type : 'path',
						stroke : 'black',
						path : 'M 210 60 L 210 90',
						'strole-width' : 1
					}, {
						type : 'text',
						stroke : 'black',
						text : '字节:',
						x : 30,
						y : 78
					}, {
						type : 'text',
						stroke : 'black',
						text : '速度(Mbps):',
						x : 15,
						y : 120
					}, {
						type : 'image',
						stroke : 'black',
						src : 'resources/images/netWork.bmp',
						height : 60,
						width : 60,
						x : 180
					} ]
				}, {
					title : '宽带测速',
					itemId : 'bandWidth',
					xtype : 'panel',
					layout : 'border',
					items : [ {
						region : 'north',
						xtype : 'chart',
						height : 189,
						width : 262,
						style : 'background:#fff',
						animate : {
							easing : 'bounceOut',
							duration : 500
						},
						store : store4,
						insetPadding : 25,
						flex : 1,
						series : [ {
							type : 'gauge',
							position : 'gauge',
							minimum : 0,
							maximum : 10,
							steps : 10,
							title : '单位：MB',
							margin : 7
						} ],
						series : [ {
							type : 'gauge',
							field : 'bandWidth',
							donut : 80,
							colorSet : [ '#3AA8CB', '#ddd' ]
						} ]
					}, {
						region : 'center',
						xtype : 'displayfield',
						style : 'background-color: white; text-align: center;',
						fieldCls : ''
					} ],
					buttons : [ {
						text : '重新测试',
						action : 'againTest'
					} ]
				} ]
			}
		});
		this.callParent(arguments);
	},
	
	generateData : function() {
		var data = [];
		data.push({
			ip : '',
			bandWidth : 0
		})
		return data;
	}
});