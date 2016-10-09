Ext.define('Eway.view.machine.detail.PersonInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.detail_personInfo',
	title : '设备人员信息',
    
    initComponent : function() {
		Ext.apply(this, {
			columns: [{
			              text: '姓名',
			              dataIndex: 'name',
			              flex: 1
			          },{
			              text: '性别',
			              dataIndex: 'sex',
			              flex: 1
			          },{
			              text: '机构',
			              dataIndex: 'orgName'
			          },{
			              text: '手机',
			              dataIndex: 'phone',
			              width: 100
			          },{
			              text: '人员类型',
			              dataIndex: 'personType',
			              hidden: true
			          },{
			              text: '邮箱',
			              dataIndex: 'phone',
			              width: 100
			          },{
			              text: '固定电话',
			              dataIndex: 'phone',
			              width: 100
			          }
			      ]
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
