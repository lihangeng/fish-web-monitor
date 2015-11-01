Ext.define('Eway.view.operatingPlan.ImportLinkedMachine',{
          extend :'Ext.window.Window',
          alias:'widget.operatingPlan_download_linkedMachine',
          title:'导入关联设备',
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
      					fieldLabel : '选择文件',
      					name : 'file',
      					allowBlank : false,
      					width:420,
      					blankText : '请上传资源',
      					waitMsg:'正在上传文件...',
      					msgTarget : 'side',
      					regex : /^([\w|\W]*)(\.xls)$/i,
      					regexText : '导入的文件格式不支持,请按模板导入设备信息.'
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : '模板下载',
      					width:420,
      					name : 'link',
      					value :"<a class='link' href='api/dateImport/downloadFile'>点击下载导入设备号模板</a>"
      				}, {
      					xtype : 'displayfield',
      					fieldLabel : '导入说明',
      					width:420,
      					value : '<font color="red">请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据</font>'
      				} ],
      				buttonAlign:'center',
      				buttons : [ {
      					text : '确定',
      					iconCls : 'sureBtn',
      					action : 'import'
      				} ]
      			}]
      		});
      		this.callParent(arguments);
      	}

      });