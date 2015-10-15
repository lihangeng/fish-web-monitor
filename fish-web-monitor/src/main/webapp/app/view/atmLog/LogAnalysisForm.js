Ext.define('Eway.view.atmLog.LogAnalysisForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.LogAnalysisregister_form',

	initComponent : function(){
		var me = this;
		Ext.apply(this,{
		    title : Eway.locale.atmLog.busLogAnalysis,
		    layout: 'border',
		    header:false,
		    bodyStyle:'padding:0px',
		    border:false,
		    items: [{
			    	region:'north',
			    	height: 50,
			    	border:false,
			    	xtype:'panel',
			    	html:'<br>' +
			    			'<font size="2"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+Eway.locale.atmLog.selectAnalysis+'</b></font>'
				},{
			    	xtype : 'form',
			    	border:false,
			    	region:'center',
			    	items : [{
			    		xtype : 'filefield',
						width : 350,
						fieldLabel : '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+Eway.locale.atmLog.selectLog,
						allowBlank : true,
						name : 'file',
						buttonText : Eway.locale.combox.explorer,
						checkboxToggle: true,
						listeners : {
						'change' : function(fileForm, value){
							var upFileForm = this.up("form");
							var items = upFileForm.getForm().items;
							//解决IE8下文件上传提交两次
							if(Ext.isEmpty(value)){
								return ;
							}
							upFileForm.submit({
					                  url: 'api/machine/atmLog/uploade',
					                  success: function(form, action) {
					                  	upFileForm.add({
					                  		fieldLabel :' ',
					                  		labelSeparator:'',
					                  		xtype : 'checkboxfield',
					                  		boxLabel : action.result.serverPath,
							                inputValue : action.result.serverPath,
							                checked : true,
					                  		border:0
					                  	});
					                  },
					                  failure: function(form, action) {
					                     Eway.alert(action.result.errors);
					                  }
					              });
							}}
						}]
				},{
					region:'south',
			    	height: 50,
			    	layout: 'border',
			    	xtype:'panel',
			    	html:'<br>'+'<font size="2"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+Eway.locale.atmLog.pleaseDownload+'</b></font>',
			    	items:[{
			    		region:'center',
			    		xtype : 'button',
			    		id : 'downFile',
			    		buttonAlign : 'center',
			    		text: '',
			    		hidden : true,
			    		handler : function(btn){
							var win = btn.up('form');
							var itemEl = win.getEl();
							var iframe = itemEl.prev();
							iframe = Ext.core.DomHelper.createDom({
									tag : 'iframe',
									src : 'api/machine/atmLog/download?path=' + btn.getText(),
									style : "display:none",
									action : 'test'
							});
							itemEl.insertSibling(iframe);
							win.close();
						}
			    	},{
			    		region:'west',
			    		width:120,
			    		xtype:'panel',
			    		border:false,
			    		html:'<br>'+'<font size="2"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+Eway.locale.atmLog.pleaseDownload+'</b></font>'
			    	}]
				}],
				buttons: [{
					xtype : 'button',
					text: '分析',
					handler : function(btn){
						var win = btn.up('form');
						var form = win.down("form");
						if(form.query('checkboxfield')!= null) {
							var info = '';
							Ext.each(form.query('checkboxfield'),function(item){
								if(item.checked == true){
									info += item.inputValue+',';
								}
							})
						}
						Ext.Ajax.request({
							method : 'GET',
							url : 'api/machine/atmLog/poiExcel?info=' + info,
							success : function(response){
								var object = Ext.decode(response.responseText);
								if(object.success==true){
									Ext.getCmp('downFile').setText(object.path);
									Ext.getCmp('downFile').setVisible(true);
								}else{
									Eway.alert(object.errors);
								}
							},
							failure : function(){
								Eway.alert(Eway.locale.cases.caseNotify.innerFault);
							}
						})
					}
				}]
		}).show();
		this.callParent(arguments);
	}
	});