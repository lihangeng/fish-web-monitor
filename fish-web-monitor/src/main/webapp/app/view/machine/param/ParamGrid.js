
Ext.define('Eway.view.machine.param.ParamGrid', {
	alias: 'widget.param_ParamGrid',
	extend: 'Eway.view.base.Grid',
	
	
	store: 'machine.param.Param',
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.param.Param',{
			sorters: {property: 'paramKey', direction: 'ASC'},
	        groupField: 'paramType'
		});
		store.loadPage(1);

	    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	        clicksToEdit: 1
	    });
		Ext.apply(this,{
	        width: 840,
	        height: 450,
	        frame: true,
	        renderTo: document.body,
	        store: store,
	        plugins: [cellEditing],
	        
	        
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
	            text: Eway.locale.machine.param.paramType,
	            width:150,
	            tdCls: 'paramKey',
	            sortable: true,
	            hideable: false,
	            summaryType: 'count',
	            summaryRenderer: function(value, summaryData, dataIndex) {
	                return ((value === 0 || value > 1) ? '(' + value + Eway.locale.machine.param.paramCount : Eway.locale.machine.param.oneParam);
	            }
	        }
	        , {
	            header: Eway.locale.machine.param.paramKey,
	            width: 150,
	            sortable: true,
	            dataIndex: 'paramKey'
	        }
	        
	        , {
	            header: Eway.locale.machine.param.paramValue,
	            width: 280,
	            sortable: true,
	            dataIndex: 'paramValue',
	        }, {
	            header: Eway.locale.machine.param.modifyFlag,
	            width: 120,
	            sortable: true,
	            dataIndex: 'classify',
	            renderer: function(value){
					if(value == 0){
						return Eway.locale.machine.param.comboxClassify.unableUpdate;
					}else if(value == 1){
						return Eway.locale.machine.param.comboxClassify.ableUpdate;
					}
				}
	            
	        },{
	            header: Eway.locale.version.View.remark,
	            flex: 1,
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