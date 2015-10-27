/**
 * 机构下的人员列表Grid
 */
Ext.define('Eway.view.machine.device.person.PersonTMManagerGrid', {
	alias: 'widget.personTM_manager_grid',
	extend: 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	multiSelect:true,
	selModel:{selType:'checkboxmodel'},

	initComponent: function() {
		var store = Ext.create('Eway.store.machine.PersonTM',{
			autoLoad : false
		});
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: '刷新',
				iconCls: 'refreshBtn',
				action: 'queryRe'
				}, {
				text: '添加',
				iconCls :'sureBtn',
				action: 'add'
				}, {
				text: '删除',
				iconCls :'deleteBtn',
				action: 'remove'}],
			columns : [{
				header : '工号',
				dataIndex : 'jobNum'
			},{
				header : '姓名',
				dataIndex : 'name'
			}, {
				header : '手机',
				dataIndex : 'mobile'
			}, {
				header : '固话',
				dataIndex : 'phone'
			},{
				header : '状态',
				dataIndex : 'state',
				renderer: function(value,metadata,record){
					if(value==1){
		                	 return "在岗";
		             }else if(value==2){
		                	   return "调休";
		             }else if(value==3){
		                	   return "休假";
		             }else if(value==0){
		                	   return "其他";
		             }
				}
			},{
				header : '邮箱',
				dataIndex : 'email'
			},{
				header : '机构名称',
				dataIndex : 'organizationName',
				flex : 1
			}]
		});

		this.callParent(arguments);
	}
});