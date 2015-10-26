
Ext.define('Eway.view.machine.param.ParamGrid', {
	alias: 'widget.param_ParamGrid',
	extend: 'Eway.view.base.Grid',
	
	
	store: 'machine.param.Param',
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store1 = Ext.create('Eway.store.machine.param.Param');
		store1.loadPage(1);
		var store = Ext.create('Eway.store.machine.param.Param',{
	        model: 'Eway.model.machine.param.Param',
	        store1: store1,
	        sorters: {property: 'paramKey', direction: 'ASC'},
	        groupField: 'paramType'
		});

	    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	    });
		Ext.apply(this,{
	        width: 840,
	        height: 450,
	        frame: true,
//	        title: 'Sponsored Projects',
	        iconCls: 'icon-grid',
	        renderTo: document.body,
	        store: store,
	        plugins: [cellEditing],
//	        dockedItems: [{
//	            dock: 'top',
//	            xtype: 'toolbar',
//	            items: [{
//	                tooltip: 'Toggle the visibility of the summary row',
//	                text: 'Toggle Summary',
//	                enableToggle: true,
//	                pressed: true,
//	                handler: function() {
//	                    grid.getView().getFeature('group').toggleSummaryRow();
//	                }
//	            }]
//	        }],
	        
	        
	        features: [{
	            id: 'group',
	            ftype: 'groupingsummary',
	            groupHeaderTpl: '{name}',
	            hideGroupedHeader: true,
	            enableGroupingMenu: false
	        }],
	        
	        
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'configurationUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
	            text: '参数类型',
	            flex: 1,
	            tdCls: 'paramKey',
	            sortable: true,
	            dataIndex: 'paramKey',
	            hideable: false,
	            summaryType: 'count',
	            summaryRenderer: function(value, summaryData, dataIndex) {
	                return ((value === 0 || value > 1) ? '(' + value + ' Params)' : '(1 Param)');
	            }
	        }, {
	            header: 'paramType',
	            width: 180,
	            sortable: true,
	            dataIndex: 'paramType',
	            renderer: function(value){
					if(value == 0){
						return Eway.locale.machine.param.comboxClassify.unableUpdate;
					}else if(value == 1){
						return Eway.locale.machine.param.comboxClassify.ableUpdate;
					}
				}
	        }, {
	            header: '是否可以修改',
	            width: 136,
	            sortable: true,
	            dataIndex: 'classify',
	            renderer: function(value){
					if(value == 0){
						return Eway.locale.machine.param.comboxClassify.unableUpdate;
					}else if(value == 1){
						return Eway.locale.machine.param.comboxClassify.ableUpdate;
					}
				}
	            
	        }
	        , {
	            header: '参数',
	            width: 136,
	            sortable: true,
	            dataIndex: 'paramKey'
	        }
	        
	        , {
	            header: '参数值',
	            width: 100,
	            sortable: true,
	            dataIndex: 'paramValue',
	        }
	        , {
	            header: '备注',
	            width: 100,
	            sortable: true,
	            dataIndex: 'description',
	        }
	        ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	},
	
	onReload: function() {
		this.getStore().load();
	}
});