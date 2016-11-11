Ext.define('Eway.view.machine.detail.basic.PersonInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.detail_personInfo',

	tools: [ {
        itemId: 'refreshPersonInfo',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
    }],
    initComponent : function() {
    	var store = Ext.create("Eway.store.person.person.BankPerson")
		Ext.apply(this, {
			store:store,
			columns: [{
			              text: EwayLocale.machine.device.name,
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
			              text: EwayLocale.person.bankPer.organizationName,
			              dataIndex: 'organizationName',
			              flex:1
			          },{
			              text: EwayLocale.commen.mobile,
			              dataIndex: 'mobile',
			              width: 110
			          },{
			              text: EwayLocale.person.bankPer.personType,
			              dataIndex: 'type',
			              renderer: function(value,metadata,record){
			            	  if(value==0){
			            		  return EwayLocale.person.bankPer.bankPerson;
			            	  }
			            	  else{
			            		  return EwayLocale.person.bankPer.dealerPerson;
			            	  }
			              }
			          },{
			              text: EwayLocale.commen.email,
			              dataIndex: 'email',
			              width: 250
			          },{
			              text: EwayLocale.machine.device.phone,
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
