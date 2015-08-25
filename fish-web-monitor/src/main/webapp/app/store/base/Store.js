Ext.define('Eway.store.base.Store',{
	extend : 'Ext.data.Store',
	autoLoad : false,
	autoSync : false,
	constructor : function(){
		this.callParent(arguments);
		this._cleanProxy();
	},

	_cleanProxy : function(){
		this.proxy.pageParam = undefined;
	},


	setBaseParam : function(name,value){
		this.proxy.extraParams = this.proxy.extraParams || {};
		this.proxy.extraParams[name] = value;
		this.proxy.applyEncoding(this.proxy.extraParams);
		return this;
	},


	setUrlParamsByObject : function(object){
		this.proxy.extraParams = {};
		for(var i in object){
			if(!Ext.isEmpty(object[i])){
				this.setBaseParam(i,object[i]);
			}
		}
	},

	setUrlParam : function(name,value){
		if(!Ext.isDefined(value) && Ext.isEmpty(value)){
			delete this.proxy.extraParams[name];
			return ;
		}
		this.setBaseParam(name,value);
	},

	cleanUrlParam : function(){
		this.proxy.extraParams = {};
	},

	getUrlParams : function(){
		return this.proxy.extraParams;
	},

	addData : function(values){
		this.insert(0,[values]);
	},

	getModelName: function(){
		return this.model;
	}

});