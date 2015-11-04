Ext.define('Eway.view.operatingPlan.ImportLinkedMachine',{
          extend :'Ext.window.Window',
          alias:'widget.operatingPlan_download_linkedMachine',
          title:Eway.locale.report.openplan.inportLinkedMachine,
          modal:true,
          resizable : false,
          constrainHeader : true,
          initComponent : function() {
      		Ext.applyIf(this, {
      			items :[{
      				    xtype : 'form',
      					layout: {
      				        type: 'vbox',
      				        align:'center'
      					},
      					xtype: 'form',
      					bodyStyle : 'padding: 10px 10px 10px 10px',
      					trackResetOnLoad : true,
      					selectOnFocus : true,
      					defaults: {
      						width: 350,
      						labelWidth: 150,
      						labelAlign: 'right',
      						msgTarget : 'side'
      					},
      				items : [
      				{
      					xtype : 'filefield',
      					fieldLabel : Eway.locale.report.openplan.selectFile,
      					name : 'file',
      					allowBlank : false,
      					width:420,
      					blankText : Eway.locale.report.openplan.placeUploadingResource,
      					waitMsg:Eway.locale.version.View.versionFileUploadMsg,
      					msgTarget : 'side',
      					regex : /^([\w|\W]*)(\.xls)$/i,
      					regexText : Eway.locale.report.openplan.fileNotSupport
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : Eway.locale.cases.vendorCode.templateLoad,
      					width:420,
      					name : 'link',
      					value :"<a class='link' href='api/dateImport/downloadFile'>"+Eway.locale.report.openplan.thisHardToTranslate+"</a>"
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : Eway.locale.report.openplan.exportExplain,
      					width:420,
      					value : '<font color="red">'+Eway.locale.report.openplan.thisIsTooLong+'</font>'
      				} ],
      				buttonAlign:'center',
      				buttons : [ {
      					text : Eway.locale.button.sure,
      					iconCls : 'sureBtn',
      					action : 'import'
      				} ]
      			}]
      		});
      		this.callParent(arguments);
      	}

      });