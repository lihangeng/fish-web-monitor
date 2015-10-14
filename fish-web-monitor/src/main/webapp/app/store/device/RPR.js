/**
 * 凭条打印机的store定义
 */
Ext.define('Eway.store.device.RPR', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.device.RPR',
	
    autoLoad: true,
    
    data : {data:[
//                  {
//                	  id:1,
//                	  type:'Journal',
//                	  canPopupReceipt:'可以',
//       	           	  canEatReceipt:'可以',
//       	           	  canTempStore:'不能',
//       	           	  maxRetrieveSizecan : 10,
//       	           	  deviceId:1
//                  },
	              {
                   	  id:2,
                	  type:'Journal',
                	  canPopupReceipt:Eway.locale.machine.device.unable,
       	           	  canEatReceipt:Eway.locale.machine.device.unable,
       	           	  canTempStore:Eway.locale.machine.device.able,
       	           	  maxRetrieveSizecan : 5,
       	           	  deviceId:2
	              }   
	            ]
    		}
});