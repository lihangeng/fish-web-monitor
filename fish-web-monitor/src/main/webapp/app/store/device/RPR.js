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
                	  canPopupReceipt:EwayLocale.machine.device.unable,
       	           	  canEatReceipt:EwayLocale.machine.device.unable,
       	           	  canTempStore:EwayLocale.machine.device.able,
       	           	  maxRetrieveSizecan : 5,
       	           	  deviceId:2
	              }   
	            ]
    		}
});