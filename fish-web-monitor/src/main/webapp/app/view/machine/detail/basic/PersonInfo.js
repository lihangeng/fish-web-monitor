Ext.define('Eway.view.machine.detail.basic.PersonInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.detail_personInfo',
	title : '设备人员信息',
    
    initComponent : function() {
    	var store = Ext.create("Eway.store.person.person.BankPerson")
		Ext.apply(this, {
			store:store,
			columns: [{
			              text: '姓名',
			              dataIndex: 'name'
			          },{
			        	  header : EwayLocale.commen.gender,
			        	  dataIndex : 'gender',
			        	  renderer: function(value,metadata,record){
			        		  if(value=="MALE"||value==0){
			                	   return EwayLocale.commen.comboxGender.male;
			                   }else if(value=="FEMALE"||value==1){
			                	   return EwayLocale.commen.comboxGender.female;
			                   }else{
			                	   return EwayLocale.commen.comboxGender.unknow;
			                   }
			        	  }
			          },{
			              text: '机构',
			              dataIndex: 'organizationName',
			              flex:1
			          },{
			              text: '手机',
			              dataIndex: 'mobile',
			              width: 100
			          },{
			              text: '人员类型',
			              dataIndex: 'type',
			              renderer: function(value,metadata,record){
			            	  if(value==0){
			            		  return "银行人员";
			            	  }
			            	  else{
			            		  return "维护商人员";
			            	  }
			              }
			          },{
			              text: '邮箱',
			              dataIndex: 'email',
			              width: 250
			          },{
			              text: '固定电话',
			              dataIndex: 'phone',
			              width: 200
			          }
			      ],
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
