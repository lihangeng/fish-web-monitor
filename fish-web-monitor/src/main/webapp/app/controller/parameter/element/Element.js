Ext.define('Eway.controller.parameter.element.Element', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.element.Element',
			          'parameter.element.ParamRights',
			          'parameter.element.ParamBelongs',
			          'parameter.element.ElementClassify',
			          'parameter.element.ParamType'
			          ],
			models : ['parameter.element.Element','Dict'],

			views : ['parameter.element.View','parameter.element.Form'],

			refs : [{
						ref : 'ewayView',
						selector : 'element_View',
						autoCreate : true,
						xtype : 'element_View'
					}, {
						ref : 'elementFilterForm',
						selector : 'element_FilterForm'
					}, {
						ref : 'elementGrid',
						selector : 'element_Grid'
					},{
						ref :'importWin',
						selector:'element_ImportParamFile'
					}, {
						ref : 'addWin',
						selector : 'element_add'
					}, {
						ref : 'updateWin',
						selector : 'element_update'
					}
					],

			formConfig : {
				form : 'Eway.view.parameter.element.Form',
				xtype : 'parameter_element_form',
				width: 500,
				height:280,
				title : '参数元数据',
			},

			init : function() {
				this.initBaseControl();
				this.control({
							'element_View button[action=query]' : {
								click : this.onQuery
							},
							'element_View button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'element_View button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'element_View button[action=remove]' : {
								click : this.onRemove
							},
							'element_View button[action=import]' :{
								click : this.onImport
							},
							'parameter_element_form field_paramElement_ParamType':{
								change:this.onChange
							},
							'element_View element_FilterForm field_paramElement_ParamBelongsRadioGroup':{
                                change:this.onChange2
							}
						});
			},

			initBaseControl : function(){

			},

			onImport:function(){
				var win = Ext.create('Eway.view.parameter.element.ImportParamFile');
				this.win = win;
				win.down('button[action="import"]').on('click',this.onImportConfirm,this);
				win.show();
			},
			onImportConfirm :function(){

				var win = this.win;
				var importForm = this.getImportWin().down("form").getForm();
				var paramBelongs = win.down('field_paramElement_ParamBelongs').getValue();
//				var view = this.getEwayView();
//				var paramBelongs=view.down('element_FilterForm').down("field_paramElement_ParamBelongsRadioGroup").getValue();
				if(importForm.isValid()){
					Ext.Msg.wait(EwayLocale.cases.nowExportFile);
					importForm.submit({
						url : 'api/parameter/element/import',
						params : {
							appSystem : paramBelongs
						},
						success : function(form, action){
							Ext.Msg.hide();
							win.close();
							var store = this.getElementGrid().getStore();
							store.load();
							Eway.alert(EwayLocale.param.element.importSuccess);
						},
						failure :function(form, action){
							Eway.alert(action.result.content);
							Ext.Msg.hide();
						},
						scope :this
					});
				}
			},

			onChange:function( _this, newValue, oldValue, eOpts){
				var paramValue = _this.up("parameter_element_form").down("field_paramElement_ParamValue");
				if(newValue==1){
					return paramValue.regex=/(^([0-9]\d{0,17})$)/,paramValue.regexText='只能输入数字';
				}
				if(newValue==2){
					return paramValue.regex=null;
				}

			},
			onChange2: function(_this, newValue, oldValue, eOpts){
				var view = this.getEwayView();
				var paramBelongsRadioGroup=view.down('element_FilterForm').down("field_paramElement_ParamBelongsRadioGroup").getValue();

				var store = Ext.create('Eway.store.parameter.element.Element');


				var store = this.getGridPanel().getStore();
				store.setUrlParamsByObject(paramBelongsRadioGroup);
				store.loadPage(1);
			}

});