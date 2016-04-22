
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

	}

});