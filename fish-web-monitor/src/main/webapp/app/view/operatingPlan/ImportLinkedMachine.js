Ext.define('Eway.view.operatingPlan.ImportLinkedMachine',{
          extend :'Ext.window.Window',
          alias:'widget.operatingPlan_download_linkedMachine',
          title:EwayLocale.report.serviceplan.inportLinkedMachine,
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
      					fieldLabel : EwayLocale.report.serviceplan.selectFile,
      					name : 'file',
      					allowBlank : false,
      					width:420,
      					blankText : EwayLocale.report.serviceplan.placeUploadingResource,
      					waitMsg:EwayLocale.version.View.versionFileUploadMsg,
      					msgTarget : 'side',
      					regex : /^([\w|\W]*)(\.xls)$/i,
      					regexText : EwayLocale.report.serviceplan.fileNotSupport
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : EwayLocale.cases.vendorCode.templateLoad,
      					width:420,
      					name : 'link',
      					value :"<a class='link' href='api/dateImport/downloadFile'>"+EwayLocale.report.serviceplan.thisHardToTranslate+"</a>"
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : EwayLocale.report.serviceplan.exportExplain,
      					width:420,
      					value : '<font color="red">'+EwayLocale.report.serviceplan.thisIsTooLong+'</font>'
      				} ],
      				buttonAlign:'center',
      				buttons : [ {
      					text : EwayLocale.button.sure,
      					iconCls : 'sureBtn',
      					action : 'import'
      				} ]
      			}]
      		});
      		this.callParent(arguments);
      	}

      });