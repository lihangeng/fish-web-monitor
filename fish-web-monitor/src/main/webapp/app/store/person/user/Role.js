
Ext.define('Eway.store.person.user.Role', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.permission.Role',
	
	initComponent : function(){
		this.callParent(arguments);
	},
	listeners : {
		load : function(store,records){
			store.filterBy(function(record,id){
				if(record.get('system')==true){
					return false;
				}else{
					return true;
				}
			});
		}
	},
	pageSize : 200
	
});