Ext.define('Eway.controller.system.quartz.Quartz', {
			extend : 'Eway.controller.base.FishController',

			stores : ['Eway.store.quartz.Quartz'],
			models : ['Eway.model.quartz.Quartz'],

			views : ['Eway.view.system.quartz.QuartzView'],

			refs : [{
						ref : 'ewayView',
						selector : 'quartz_QuartzView',
						autoCreate : true,
						xtype : 'quartz_QuartzView'
					}, {
						ref : 'quartzFilterForm',
						selector : 'quartz_QuartzFilterForm'
					}, {
						ref : 'quartzGrid',
						selector : 'quartz_QuartzGrid'
					}, {
						ref : 'addWin',
						selector : 'quartz_add'
					}, {
						ref : 'updateWin',
						selector : 'quartz_update'
					}],

			formConfig : {
				form : 'Eway.view.system.quartz.Form',
				xtype : 'system_quartz_form',
				width: 500,
				height:280,
				title : EwayLocale.quartz.timingJob
			},	
			
			onPause:function(){
				var grid = this.getQuartzGrid();
				var sm = grid.getSelectionModel();
				if(sm.getCount() != 0){
				var records = sm.getSelection();
				var jobName = '0';
				var jobGroup = '0';
				jobName = records[0].data.jobName;
				jobGroup = records[0].data.jobGroup;
				Ext.Ajax.request({
					method:'GET',
					url:'api/system/quartz/pauseJob',
					params:{
						jobName : jobName,
						jobGroup : jobGroup
					},
					success:function(ed){
							Eway.alert(EwayLocale.quartz.pauseSucceed);
							this.onQuery();
						
					},
					failure:function(response){
						Eway.alert(EwayLocale.quartz.pauseFail);
					},
					scope:this
				});
				}else {
					Eway.alert(EwayLocale.quartz.selectJob);
				}
			},
			onResume:function(){
				var grid = this.getQuartzGrid();
				var sm = grid.getSelectionModel();
				if(sm.getCount() != 0){
				var records = sm.getSelection();
				var jobName = '0';
				var jobGroup = '0';
				jobName = records[0].data.jobName;
				jobGroup = records[0].data.jobGroup;
				Ext.Ajax.request({
					method:'GET',
					url:'api/system/quartz/resumeJob',
					params:{
						jobName : jobName,
						jobGroup : jobGroup
					},
					success:function(ed){
							Eway.alert(EwayLocale.quartz.recoverSucceed);
							this.onQuery();
						
					},
					failure:function(response){
						Eway.alert(EwayLocale.quartz.recoverFail);
					},
					scope:this
				});
				}else {
					Eway.alert(EwayLocale.quartz.selectJob);
				}
			},
			onDelJob:function(){
				var grid = this.getQuartzGrid();
				var sm = grid.getSelectionModel();
				
				if(count == 0){
					Eway.alert(EwayLocale.tip.remove.none);
					return;
				}
				else if(count > 1){
					Eway.alert(EwayLocale.tip.remove.one);
					return;
				}
				var records = sm.getSelection();
				var jobName = '0';
				var jobGroup = '0';
				jobName = records[0].data.jobName;
				jobGroup = records[0].data.jobGroup;
				Ext.Ajax.request({
					method:'GET',
					url:'api/system/quartz/deleteJob',
					params:{
						jobName : jobName,
						jobGroup : jobGroup
					},
					success:function(ed){
							Eway.alert(EwayLocale.quartz.deleteSucceed);
							this.onQuery();
						
					},
					failure:function(response){
						Eway.alert(EwayLocale.quartz.deleteFail);
					},
					scope:this
				});
			},
			onExecute:function(){
				var grid = this.getQuartzGrid();
				var sm = grid.getSelectionModel();
				if(sm.getCount() != 0){
				var records = sm.getSelection();
				var jobName = '0';
				var jobGroup = '0';
				jobName = records[0].data.jobName;
				jobGroup = records[0].data.jobGroup;
				Ext.Ajax.request({
					method:'GET',
					url:'api/system/quartz/executeJob',
					params:{
						jobName : jobName,
						jobGroup : jobGroup
					},
					success:function(ed){
							Eway.alert(EwayLocale.quartz.executeSucceed);
							this.onQuery();
						
					},
					failure:function(response){
						Eway.alert(EwayLocale.quartz.executeFail);
					},
					scope:this
				});
				}else {
					Eway.alert(EwayLocale.quartz.selectJob);
				}
			},
			beforeUpdateSave:function(win,grid,record){
				record.set("id",2);
			},
			init : function() {
				this.initBaseControl();
				this.control({
							'quartz_QuartzView button[action=query]' : {
								click : this.onQuery
							},
							'quartz_QuartzView button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'quartz_QuartzView button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'quartz_QuartzView button[action=remove]' : {
								click : this.onDelJob
							},
							'quartz_QuartzView button[action=pause]' : {
								click : this.onPause
							},
							'quartz_QuartzView button[action=resume]' : {
								click : this.onResume
							},
							'quartz_QuartzView button[action=executeJob]' : {
								click : this.onExecute
							}
						});
			}
});