
Ext.define('Eway.view.field.paramElement.ParamBelongsRadioGroup', {
	extend: 'Ext.form.RadioGroup',
	alias: 'widget.field_paramElement_ParamBelongsRadioGroup',

	fieldLabel : '<font color="red">*</font>'+EwayLocale.param.element.paramBelongs,

	listeners : {


		beforerender : function(_this,opt) {
			Ext.Ajax.request({
			    url: 'api/parameter/element/queryAppsystemRadioGroup',
			    success: function(response, opts) {
			        var obj = Ext.decode(response.responseText).data;
			        for(var index=0;index<obj.length;index++){
			        	if(index==0){
			        	var radio = Ext.create("Ext.form.field.Radio",{boxLabel:obj[index].name+'&nbsp&nbsp&nbsp', name:'appSystem',inputValue:obj[index].id,checked:true});
			        	_this.add(radio);
			        	}else{
			        		var radio = Ext.create("Ext.form.field.Radio",{boxLabel:obj[index].name+'&nbsp&nbsp&nbsp', name:'appSystem',inputValue:obj[index].id});
				        	_this.add(radio);
			        	}
			        }
			    },
			    failure: function(response, opts) {
			        console.log('server-side failure with status code ' + response.status);
			    }
			});

		},

//		change:function(){
//			var store = Ext.create('Eway.store.parameter.element.Element');
//			var appSystem = this.getValue();
//			Ext.Ajax.request({
//				method: 'get',
//			    url: 'api/parameter/element',
//			    params : {
//			    	appSystem : appSystem,
//			    	start :0,
//			    	limit:25
//				},
//				scope : this,
//
//				success: function(response, opts) {
//
//
//				},
//			    failure: function(response, opts) {
//			        console.log('server-side failure with status code ' + response.status);
//			    },
//				callback : function() {//
//				}
//			});
//			store.load({
//				scope: this,
//				params : {
//					appSystem : appSystem,
//			    	start :0,
//			    	limit:25
//				},
////				callback: function(records, operation, success){
////					if(success == false){
////						Eway.alert(operation.getError());
////					}
////				}
//			});


//		}
	}

});