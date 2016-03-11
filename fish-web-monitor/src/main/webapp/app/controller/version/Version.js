Ext.define('Eway.controller.version.Version', {
	extend : 'Eway.controller.base.Controller',
	stores : [ 'version.Version','version.ComboVersionType','version.VersionCharts','machine.DeviceAtmType','version.VersionStatus','version.AutoUpdate',
			   'version.JobType','version.JobStatus','version.JobPriority','version.VersionCatalog'],
	models : [ 'version.Version','version.VersionType','version.VersionCharts'],
	views : [ 'version.View','version.Add','version.AddJob','version.ChartsGrid'],

	requires:['Eway.model.version.VersionDownload','Eway.store.version.VersionDownload'],

	refs : [ {
		ref : 'ewayView',
		selector : 'versionView',
		autoCreate : true,
		xtype : 'versionView'
	}, {
		ref : 'grid',
		selector : 'version_grid'
	},{
		ref : 'addWin',
		selector: 'version_add'
	},{
		ref:'updateWin',
		selector:'version_update'
	},{
		ref : 'filterForm',
		selector: 'version_filterForm'
	},{
		ref : 'addJobWin',
		selector : 'version_addJob'
	},{
		ref : 'chartsGrid',
		selector : 'version_charts_grid'
	},{
		ref : 'bar3d',
		selector : 'bar_3d series[type="bar3d"]'
	}],

	init : function() {
		 this.control({
			'versionView button[action=query]' : {
				click : this.onQuery
			},
			'versionView button[action=add]' : {
				click : this.onAdd
			},
			'versionView button[action=remove]' : {
				click : this.onRemove
			},
			'versionView button[action=update]' : {
				click : this.onUpdate
			},
			'versionView version_grid':{
			    select : this.onSelect
			},
			'versionView button[action=down]' :{
				click : this.onDown
			}
		});
		 
	},
	onSelect:function( _this, record, index, eOpts ){
		this.loadChartsInfo(record);
	},
	
	loadChartsInfo:function(record){
		var me = this;
		if(undefined==record||record.get("id")==0){
			return;
		}
		var chartsStore = this.getEwayView().down("bar_3d cartesian").getStore();
		
		chartsStore.load({
		    params: {
		        versionId:record.get("id")
		    },
		    callback: function(records, operation, success) {
        		var grid = me.getEwayView().down("version_charts_grid");
        		var gridStore = grid.getStore();
        		gridStore.setBaseParam("versionId",record.get("id"));
        		gridStore.setBaseParam("flag",0);
        		gridStore.loadPage(1);
        		grid.setTitle(chartsStore.getAt(0).get("title")+"&nbsp;&nbsp;"+EwayLocale.statics.msg);//"信息");
		    }
		});
		this.getEwayView().down("bar_3d cartesian").setTitle(record.get("versionTypeDesc")+" - V"+record.get("versionNo"));
		this.getEwayView().down("panel displayfield[name='versionType']").setValue(record.get("versionType"));
		this.getEwayView().down("panel displayfield[name='versionPath']").setValue(record.get("versionPath"));
		this.getEwayView().down("panel displayfield[name='versionTime']").setValue(record.get("createdTime"));
		this.getEwayView().down("panel displayfield[name='versionPerson']").setValue(record.get("userName"));
		this.getEwayView().down("panel displayfield[name='desc']").setValue(record.get("desc"));
	},

	//获得版本的Store
	getVersionStore : function(){
		return Ext.StoreManager.get("version.Version");
	},

	//查询
	onQuery: function(){
		var form = this.getFilterForm().getForm()
		if(!form.isValid()){
			return;
		}
		var store =  this.getGrid().getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值 {code='n',name='',....}
		store.setUrlParamsByObject(data);
		store.loadPage(1);

		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record  = sm.getLastSelected();
			this.loadChartsInfo(record);
		}
	},
	//激活面板时候进行刷新Store操作
	refreshLinkedDeviceGridData:function(){
		var win = this.getAddJobWin();
		var linkedGrid = win.down('version_download_linkedDeviceGrid');
		linkedGrid.setStore(linkedGrid.getStore());
	},
	//下发
	onDown : function(){
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get('serverPath') != null){
				var win = Ext.create('Eway.view.version.AddJob');
				win.down("button[action=confirm]").on("click",this.onDownConfirm,this);
				win.on("destroy",this.onCloseDownWin,this);
				win.down("form combobox[name=jobType]").on('change',this.onJobTypeChange,this);
				win.down("version_download_selectableDeviceGrid pagingtoolbar").on("beforechange",this.onSelectalbeDeviceFresh,this);
				var pagingtoolbar = win.down("pagingtoolbar");
				win.down("version_download_linkedDeviceGrid").on("activate",this.refreshLinkedDeviceGridData,this)
				//增加请求参数
				pagingtoolbar.store.proxy.extraParams = {versionId :record.get("id")};
				var grid = win.down("version_download_selectableDeviceGrid");
				grid.down("combobox[name=pageSize]").on("change",this.onPageSizeChange,this);
				grid.down('button[action=queryDownDevice]').on('click',this.onQueryDownDevice,this);

				var form = win.down("form").getForm();
				form.findField("versionId").setValue(record.get("id"));
				form.findField("versionType").setValue(record.get("versionTypeDesc"));
				form.findField("versionNo").setValue(record.get("versionNo"));
				form.findField("serverPath").setValue(record.get("serverPath"));
				form.findField("jobName").setValue(record.get("desc"));
				
				var atmTypeStore = win.down("field_device_deviceatmtype[name=atmTypeId]").getStore();
				atmTypeStore.proxy.extraParams={versionId:record.get("id")};
				atmTypeStore.load({
					callback: function(records, operation, success) {
						atmTypeStore.proxy.extraParams={};
					}
				});
				win.show();
				win.down("textfield[name=ip]").on({keydown:this.queryOnKeyDownEnter, scope: this });
				win.down("textfield[name=terminalId]").on({keydown:this.queryOnKeyDownEnter, scope: this});
				win.down("common_orgComboOrgTree[name=orgName]").on({keydown:this.queryOnKeyDownEnter, scope: this});
				win.down("radiogroup").on({change:this.setCheckBoxModel, scope: this});
			}else{
				Eway.alert(EwayLocale.msg.missVersionFile);//"版本文件丢失,暂不能对版本进行下发控制.");
			}
		}
		else {
			Eway.alert(EwayLocale.msg.selectVersionRecord);//"请选择您要下发的版本.");
		}
	},
	setCheckBoxModel:function( _this, newValue, oldValue, eOpts ){
		var tabPanel =  this.getAddJobWin().down("tabpanel");
		var grid = this.getAddJobWin().down("version_download_selectableDeviceGrid");
		if(newValue.selectAll=="true"){
			grid.selModel.selectAll();
			var linkedGrid = this.getAddJobWin().down("version_download_linkedDeviceGrid");
			linkedGrid.getStore().removeAll();
			this.getAddJobWin().down("hidden[name='deviceIds']").setValue("");
			linkedGrid.setTitle(EwayLocale.version.selectDeviceInfo0 + EwayLocale.version.download.selectAllDevice + EwayLocale.version.selectDeviceInfo1);
			grid.selModel.setLocked(true);
			tabPanel.getEl().mask(); 
		}
		else{
			grid.selModel.setLocked(false);
			grid.selModel.deselectAll();
			tabPanel.getEl().unmask(); 
		}
	},
	queryOnKeyDownEnter:function( e, t, eOpts ){
		if(t.keyCode==13){
			var grid = this.getAddJobWin().down("version_download_selectableDeviceGrid");
			var form = grid.up('window').down('form').getForm();
			if(this.setSearchFilter(grid,form)){
				grid.getStore().loadPage(1);
			}
		}
	},

	//选择页面显示记录数
	onPageSizeChange:function(combo,newValue){
		var grid = combo.up("version_download_selectableDeviceGrid");
		grid.getStore().pageSize = newValue;
		var form = grid.up('window').down('form').getForm();
		if(this.setSearchFilter(grid,form)){
			grid.getStore().loadPage(1);
		}
	},

	//单击选择设备列表页面的查询按钮
	onQueryDownDevice : function(button){
		var grid = button.up("version_download_selectableDeviceGrid");
		var form = grid.up('window').down('form').getForm();
		if(this.setSearchFilter(grid,form)){
			grid.getStore().loadPage(1);
		}
	},

	//下发管理页面刷新可选择设备时
	onSelectalbeDeviceFresh : function(pagingtoolbar){
		var form = pagingtoolbar.up('window').down('form').getForm();
		var grid = pagingtoolbar.up('grid');
		this.setSearchFilter(grid,form);
	},

	//@private
	//获得下发设备列表的查询条件
	setSearchFilter  : function(grid,form){
		var extraParams = {versionId : form.findField("versionId").value};
		var fields = grid.query('field');
		var flag = true;
		Ext.each(fields,function(field){
			if(field.isValid()){
				if(field.name !== 'orgName' && field.name !== 'inputItem' && field.name !== 'pageSize'){
					extraParams[field.name] = field.getValue();
				}
			}
			else{
				flag=false;
			}
		});
		grid.getStore().proxy.extraParams = extraParams;
		return flag;
	},

	//关闭下发页面
	onCloseDownWin : function(){
		//刷新版本列表
		this.getVersionStore().loadPage(1);
	},

	//下发保存后跳转到分发监控页面
	onDownConfirm : function(){
		var win = this.getAddJobWin();
		var addForm = win.down("form").getForm();
		var data = addForm.getValues();
		var record = Ext.create("Eway.model.version.VersionDownload",data);
		var linkGrid = win.down('version_download_selectableDeviceGrid');
		if(addForm.isValid()){
			var deviceIdsField = addForm.findField("deviceIds");
			var allDeviceField = addForm.findField("selectAll");
			//allDeviceField
			if(Ext.isEmpty(deviceIdsField.value)&&!allDeviceField.value){
				Eway.alert(EwayLocale.msg.mustSelectDevice);//"请至少选择一个设备.");
			}
			else if(allDeviceField.value&&linkGrid.getStore().getCount()==0){
				Eway.alert(EwayLocale.msg.mustSelectDevice);
			}else{
				record.set("deviceIds",deviceIdsField.value);
				record.save({
					 success: function(ed) {
					 	deviceIdsField.setValue("");//清空隐藏字段的值
					 	var linkedGrid = win.down('version_download_linkedDeviceGrid');
						linkedGrid.getStore().removeAll();//清空已选择的设备列表
						linkedGrid.setTitle(EwayLocale.version.selectDeviceInfo0+0+EwayLocale.version.selectDeviceInfo1);//"已选择的设备(<font color='red'>0</font>)台");
					 	//保存成功后让用户选择是否跳转到分发监控页面
					 	win.close();
					 	Ext.MessageBox.confirm(EwayLocale.confirm.title,//'提示',
					 			record.get("jobName")+EwayLocale.confirm.taskConfirmInfo1,
					 			function(button, text) {
					 				if (button == "yes") {
					 					var controller = this.parent.activeController('version.monitor.VersionDownloadMonitor');
					 					controller.autoJobDetail(ed.get("id"),ed.get("jobName"));
					 				}
					 			},this);
					 },
					 failure: function(record,operation){
						Eway.alert(operation.getError());
					 },
					 button:win.down("button[action='confirm']"),
					 scope : this
				});
			}
		}
	},

	//切换作业类型的时候，显示或隐藏作业执行时间
	onJobTypeChange :function(combo,newValue){
		var jobDate = combo.up('form').down('datefield');
		if(newValue == "SCHEDULER"){
			jobDate.enable();
			jobDate.allowBlank = false;
		}else{
			jobDate.disable();
			jobDate.value = null;
			jobDate.allowBlank = true;
		}
	},

	//打开增加版本的页面
	onAdd : function(){
		var win = Ext.create('Eway.view.version.Add');
		var b = win.query("form button[action=confirm]")[0];
		b.on("click",this.onAddConfirm,this);
		var typeName = win.query('form combobox[name=versionTypeId]')[0];
		typeName.on("change",this.onVersionTypeChange,this);
		var depver = win.query('form combobox[name=dependVersion]')[0];
		depver.on("beforequery",this.onDependVersionBeforeQuery,this);
		depver.on("change",this.onDependVersionChange,this);
		var versionNo = win.down('form').getForm().findField("versionNo");
		versionNo.on("change",this.onVersionNoChange,this);
		win.show();
	},

    //选择依赖版本之前增加查询条件
	onDependVersionBeforeQuery : function(obj,options){
		var combo = obj.combo;
		delete combo.lastQuery;
		var typeId = combo.up("form").down("combobox[name=versionTypeId]").value;
		var versionNo = combo.up("form").getForm().findField('versionNo').value;
		combo.store.proxy.extraParams = {versionTypeId : typeId,versionNo : versionNo};
	},

	//依赖版本改变的时候，把值填充到隐藏字段中
	onDependVersionChange : function(field,newValue){
		var dependVersionId = field.up("form").getForm().findField("dependVersionId");
		dependVersionId.setValue(newValue);
	},

	//-------start----两个共同决定依赖版本的显示与否
	//版本增加页面，选择一个版本类型时触发的动作
	onVersionTypeChange : function(field,newValue){
		var versionNo = field.up("form").getForm().findField("versionNo");
		if(!Ext.isEmpty(field.value) && !Ext.isEmpty(versionNo.value)){
			field.up("form").getForm().findField("dependVersion").enable();
		}
		var model = field.getStore().getById(newValue);
		var versionPath = field.up("form").getForm().findField("versionPath");
		versionPath.setValue(model.data.defaultInstallPath);
	},
	//版本号失去焦点事件
	onVersionNoChange : function(field,newValue){
		var typeId = field.up("form").down("combobox[name=versionTypeId]");
		if(!Ext.isEmpty(newValue) && !Ext.isEmpty(typeId.value)){
			field.up("form").getForm().findField("dependVersion").enable();
		}else{
			field.up("form").getForm().findField("dependVersion").disable();
		}
	},
	//-------end------

	//保存版本信息
	onAddConfirm : function(){
		var me = this;
		var win = this.getAddWin();
		var grid = this.getGrid();
		var addForm = win.down('form').getForm();
		var data = addForm.getValues();
		var record = Ext.create('Eway.model.version.Version',data);
		var store = this.getVersionStore();
		var fileField = win.down('form').down("filefield");
		if(addForm.isValid()){//isValid对markInvalid不起作用
			//1.上传文件返回保存在服务器上的位置，并填充到隐藏字段中
			var winEl = win.getEl();
			winEl.mask(EwayLocale.version.View.versionFileUploadMsg);//'正在上传文件......');
			addForm.submit({
				 	url: 'api/version/version/upload',
//				 	waitMsg: '正在上传文件...',
				    success: function(form, action) {
			    		var serverPath = action.result.serverPath;
			    		record.set("serverPath",serverPath);
			    		fileField.allowBlank = true;
					     //2.提交表单
						record.save({
							 success: function(ed) {
							 	winEl.unmask();
							 	Eway.alert(EwayLocale.addSuccess);
//								store.insert(0,ed);
								win.close();
								grid.getStore().load();
							 },
							 failure: function(record,operation){
							 	winEl.unmask();
							 	fileField.allowBlank = false;
								Eway.alert(operation.getError());
							 },
							 button:win.down("button[action='confirm']"),
							 scope : this
						});
				    },
				    failure: function(form, action) {
				    	winEl.unmask();
				        switch (action.failureType) {
				            case Ext.form.action.Action.CONNECT_FAILURE:
				                Eway.alert(EwayLocale.msg.communicationFail);//'增加失败:与服务器通讯失败.');
				                break;
				            case Ext.form.action.Action.SERVER_INVALID:
				            	if(action.result.msg==0){
				            		Eway.alert(EwayLocale.msg.sameVersionNoFail);//'增加失败:已经存在相同的版本号.');
				            	}else if(action.result.msg==1){
				            		Eway.alert(EwayLocale.msg.fileSizeMaxFail);//'增加失败:超过最大文件大小限制（最大300M）');
				            	}else if(action.result.msg==2){
				            		Eway.alert(EwayLocale.msg.fileUnzipFail);//'增加失败:上传的压缩包不能正常解压');
				            	}else{
				            		Eway.alert(EwayLocale.msg.addFileFail+//"增加失败:" + 
				            				action.result.msg);
				            	}
				       }
				    },
				    scope:this
			});
		}
	},

	//打开版本更新页面
	onUpdate: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var win = Ext.create('Eway.view.version.Update');
			var form = win.down('form').getForm();
			form.loadRecord(record);
			var field_versionType = form.findField("versionTypeId");
			field_versionType.setRawValue(record.get('versionType'));
			var field_dependVersion = form.findField('dependVersion');
			field_dependVersion.setRawValue(record.get('dvDisplayName'));
			var field_dependVersionId = form.findField('dependVersionId');
			field_dependVersionId.setValue(record.get('dependVersionId'));
			if(record.get("versionStatus") == "NEW"){
				var depver = win.query('form combobox[name=dependVersion]')[0];
				depver.on("beforequery",this.onDependVersionBeforeQuery,this);
				depver.on("change",this.onDependVersionChange,this);
			}else{
				field_dependVersion.disable();
//				form.findField("versionPath").up("fieldcontainer").disable();
			}
			var b = win.query("form button[action=confirm]")[0];
			b.on("click",this.onUpdateConfirm,this);
			win.show();
		}
		else {
			Eway.alert(EwayLocale.choiceUpdateMsg);
		}
	},

	//保存 “修改后的版本信息”
	onUpdateConfirm: function() {
		var grid = this.getGrid();
		var record = grid.getSelectionModel().getLastSelected();
		var win = this.getUpdateWin();
		var id = record.get("id");
		var updateForm = win.down("form").getForm();
		var store = this.getVersionStore();
		if(updateForm.isValid() && updateForm.isDirty()){
			updateForm.updateRecord(record);//把form中的值写回到store中
//			record.set("id",id);
			record.save({
				 success: function(ed) {
					Eway.alert(EwayLocale.updateSuccess);
					win.close();
					grid.getStore().load();
				 },
				 failure: function(ed,operation){
						Eway.alert(operation.getError());
						//解决脏数据
						store.rejectChanges();
				 },
				 button:win.down("button[action='confirm']"),
				 scope: this
			});
		}
	},

	//删除版本信息
	onRemove: function() {
		var grid = this.getGrid();
		var sm = grid.getSelectionModel();
		if(sm.getCount() == 1) {
			var record = sm.getLastSelected();
			if(record.get("versionStatus") == "NEW"){
				Ext.MessageBox.confirm(EwayLocale.confirm.titleSure,//"请确认",
						EwayLocale.confirm.todoDelete,//"是否删除该记录?",
				function(button,text) {
					if(button=="yes"){
						record.erase({
							success: function(){
								this.getVersionStore().remove(record);
								Eway.alert(EwayLocale.deleteSuccess);
								grid.getStore().load();
							},
							failure:  function(record,operation){
								//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
								record.dropped = false;
								Eway.alert(operation.getError());
							},
							scope:this
						});
					}
				}, this);
			}else{
				Eway.alert(EwayLocale.msg.versionDownloaded);//'不能删除"等待下发"和"已下发"状态的版本.');
			}
		}
		else {
			Eway.alert(EwayLocale.msg.selectVersionToDelete);//"请选择您要删除的版本.");
		}
	}

});
