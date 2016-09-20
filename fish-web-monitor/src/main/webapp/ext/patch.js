//修改pie支持自定义颜色
Ext.chart.series.Pie
		.override({
			provideLegendInfo : function(target) {
				var me = this, store = me.getStore();
				if (store) {
					var items = store.getData().items, labelField = me
							.getLabel().getTemplate().getField(), field = me
							.getAngleField(), hidden = me.getHidden(), i, style, fill;

					for (i = 0; i < items.length; i++) {
						style = me.getStyleByIndex(i);
						fill = style.fillStyle;
						if (Ext.isObject(fill)) {
							fill = fill.stops && fill.stops[0].color;
						}
                target.push({
									//                    name: labelField ? String(items[i].get(labelField))  : field + ' ' + i,
									name : labelField ? String(items[i]
											.get(labelField))
											+ ': ' + items[i].get(field)
											: field + ' ' + i,
									mark : fill || style.strokeStyle || 'black',
									disabled : hidden[i],
									series : me.getId(),
									index : i
								});
					}
				}
    }
});
		/**
		 * 增加和更改页面的确定按钮是否可用
		 */
		Ext.data.Model
				.override({
					save : function(options) {
						options = Ext.apply({}, options);
						if (options.button) {
							options.button.disable();
						}
						var me = this, phantom = me.phantom, dropped = me.dropped, action = dropped ? 'destroy'
								: (phantom ? 'create' : 'update'), scope = options.scope
								|| me, callback = options.callback, proxy = me
								.getProxy(), operation;

						options.records = [ me ];
						options.internalCallback = function(operation) {
							var args = [ me, operation ], success = operation
									.wasSuccessful();
							if (success) {
								Ext.callback(options.success, scope, args);
							} else {
								Ext.callback(options.failure, scope, args);
								if (options.button) {
									options.button.enable();
								}
							}
							args.push(success);
							Ext.callback(callback, scope, args);
						};
						delete options.callback;

						operation = proxy.createOperation(action, options);

						// Not a phantom, then we must perform this operation on the remote datasource.
						// Record will be removed from the store in the callback upon a success response
						if (dropped && phantom) {
							// If it's a phantom, then call the callback directly with a dummy successful ResultSet
							operation
									.setResultSet(Ext.data.reader.Reader.prototype.nullResultSet);
							me.setErased();
							operation.setSuccessful(true);
						} else {
							operation.execute();
						}
						return operation;
					}
				}),

		Ext
				.override(
						Ext.data.Model,
						{
							drop : function(cascade) {
								var me = this, associations = me.associations, session = me.session, roleName;

								if (me.erased || me.dropped) {
									return;
								}

								me.dropped = true;
								if (associations && cascade !== false) {
									for (roleName in associations) {
										associations[roleName].onDrop(me,
												session);
									}
								}
								//	        me.callJoined('afterDrop');
								if (me.phantom) {
									me.setErased();
								}
							}
						});

/**
 * 在extjs5中，更新操作中只更新修改的字段，导致数据库中数据被清空或存在约束异常，
 * 导致数据更新失败
 */
Ext.override(Ext.data.writer.Writer, {
	getWriteAllFields : function() {
		return true;
	},
	getWriteRecordId : function() {
		return false;
	}
});

/**
 * extjs5中，快速获取服务器返回的错误信息，operation.getError()
 * 与服务器约定在报文中错误key是errorMsg,服务器端是FishConstant.ERROR_MSG
 */
Ext.override(Ext.data.reader.Reader, {
	getMessageProperty : function() {
		return "errorMsg";
	}
});

/**
 *
 * 3.重写Ext.form.field.Text类的getErrors方法
 * 文本框必填时，输入空格验证将不通过。
 * @since extjs4.1.0
 *
 **/
Ext
		.override(
				Ext.form.field.Text,
				{
					getErrors : function(value) {
						var me = this,
						//           errors = me.callParent(arguments), // 当value为""时，会出现两次验证提示，所以注释掉
						validator = me.validator, emptyText = me.emptyText, allowBlank = me.allowBlank, vtype = me.vtype, vtypes = Ext.form.field.VTypes, regex = me.regex, format = Ext.String.format, msg;

						var errors = [];

						value = value || me.processRawValue(me.getRawValue());

						if (Ext.isFunction(validator)) {
							msg = validator.call(me, value);
							if (msg !== true) {
								errors.push(msg);
							}
						}

						// 去除前后空格,只给allowBlank属性验证用
						var allowBlankValue = Ext.String.trim(value);
						if (allowBlankValue.length < 1
								|| allowBlankValue === emptyText) {
							if (!allowBlank) {
								errors.push(me.blankText);
							}
							//if value is blank, there cannot be any additional errors
							return errors;
						}

						if (value.length < me.minLength) {
							errors.push(format(me.minLengthText, me.minLength));
						}

						if (value.length > me.maxLength) {
							errors.push(format(me.maxLengthText, me.maxLength));
						}

						if (vtype) {
							if (!vtypes[vtype](value, me)) {
								errors.push(me.vtypeText
										|| vtypes[vtype + 'Text']);
							}
						}

						if (regex && !regex.test(value)) {
							errors.push(me.regexText || me.invalidText);
						}

						return errors;
					},

					//支持所有的Text增加 clear功能
					config : {
						hideTrigger : false,
						canClear : true,
						triggers : {
							clear : {
								cls : Ext.baseCSSPrefix + "form-clear-trigger",
								hidden : true,
								handler : 'onClearClick',
								scope : 'this'
							}
						}
					},

					listeners : {
						change : function(text, newValue, oldValue) {
							var clearTip = text.getTrigger("clear");
							if (undefined == clearTip) {
								return;
							}
							if (this.canClear && !this.readOnly
									&& !this.hideTrigger) {
								if (newValue && newValue !== "") {
									clearTip.show();
								} else {
									clearTip.hide();
								}
							}
						},
						afterrender : function(text) {
							var clearTip = text.getTrigger("clear");
							if (undefined == clearTip) {
								return;
							} else {
								clearTip.hide();
							}
						}
					},

					//可重写此方式，实现具体的业务逻辑
					onClearClick : function() {
						this.setValue(null);
					}
				});

//支持所有的ComboBox增加 clear功能,如果不需要clear功能，直接将canClear设置为false
Ext.override(Ext.form.field.ComboBox, {
	config : {
		hideTrigger : false,
		canClear : true,
		triggers : {
			clear : {
				cls : Ext.baseCSSPrefix + "form-clear-trigger",
				hidden : true,
				handler : 'onClearClick',
				scope : 'this'
			}
		},
		listeners : {
			afterrender : function(text) {
				var clearTip = text.getTrigger("clear");
				if (this.canClear) {
					return;
				} else {
					clearTip.hide();
				}
			}
		}
	}
});

/**
 * 重写picker，增加clear
 */
Ext.override(Ext.form.field.Picker, {
	config : {
		hideTrigger : false,
		canClear : true,
		triggers : {
			picker : {
				handler : 'onTriggerClick',
				scope : 'this'
			},
			clear : {
				cls : Ext.baseCSSPrefix + "form-clear-trigger",
				hidden : true,
				handler : 'onClearClick',
				scope : 'this'
			}
		}
	}
});

//修复model.destroy()时，因row.className = row.className错误，无法调用回调函数的问题
Ext.define('Ext.view.override.Table', {
	override : 'Ext.view.Table',

	doStripeRows : function(startRow, endRow) {
		var me = this, rows, rowsLn, i, row;

		if (me.rendered && me.stripeRows) {
			rows = me.getNodes(startRow, endRow);

			for (i = 0, rowsLn = rows.length; i < rowsLn; i++) {
				row = rows[i];

				if (row) { // self updating; check for row existence
					row.className = row.className.replace(me.rowClsRe, ' ');
					startRow++;

					if (startRow % 2 === 0) {
						row.className += (' ' + me.altRowCls);
					}
				}
			}
		}
	}

});

/**
 * hack IE8.0-上的文件上传2次提交问题
 */
Ext.form.field.File.override({
	checkChange : function() {
		if (!this.suspendCheckChange) {
			var me = this, newVal = me.getValue(), oldVal = me.lastValue;

			if (!me.isEqual(newVal, oldVal) && !me.isDestroyed
					&& !Ext.isEmpty(newVal)) {
				me.lastValue = newVal;
				me.fireEvent('change', me, newVal, oldVal);
				me.onChange(newVal, oldVal);
			}
		}
	}
});

Ext.define('Ext.grid.OrgPanel', {
	extend : 'Ext.panel.Table',
	requires : [ 'Ext.view.Table' ],
	alias : [ 'widget.orgGridPanel' ],
	viewType : 'tableview',

	lockable : false,

	rowLines : true
});
//针对pivot报表leftAxis进行重写;Default config  text: me.textRowLabels,
Ext.pivot.matrix.Base.override({
	generateCompactLeftAxis : function(disableChangeModel) {
		var me = this;
		if (!disableChangeModel) {
			me.model.push({
				name : me.compactViewKey,
				type : 'string'
			});
		}
		me.columns.push({
			dataIndex : me.compactViewKey,
			text : me.leftAxis.dimensions.items[0].header,
			leftAxis : true,
			width : 200
		});
	}
});

//针对pivot报表进行渲染，将leftAxis的宽度设置为可调整的
Ext.pivot.feature.PivotView
		.override({
			recordCompactRenderer : function(config) {
				var me = this, prevRenderer = config['renderer'], group = config['group'];
				return function(value, metaData, record, rowIndex, colIndex,
						store, view) {
					if (Ext.isFunction(prevRenderer)) {
						value = prevRenderer.apply(this, arguments);
					}
					// the value has to be encoded to avoid messing up the DOM
					value = me.encodeValue(value, group);
					if (group.level > 0) {
						metaData.style = (me.isRTL() ? 'margin-right: '
								: 'margin-left: ')
								+ (me.compactLayoutPadding * group.level)
								+ 'px;';
					}
					//将leftAxis的宽度设置为可调整的
					metaData.column.width = group.dimension.width;
					metaData.tdCls = me.groupHeaderCls + ' ' + me.groupTitleCls
							+ ' ' + me.compactGroupHeaderCls;
					return value;
				};
			}
		});

//修复IE9浏览器下filefield文本框不兼容问题
Ext.form.field.File.override({
	onRender : function() {
		var me = this, inputEl, button, buttonEl, trigger;

		me.callParent(arguments);

		inputEl = me.inputEl;
		//name goes on the fileInput, not the text input
		inputEl.dom.name = '';
		// Some browsers will show a blinking cursor in the field, even if it's readonly. If we do happen
		// to receive focus, forward it on to our focusEl. Also note that in IE, the file input is treated as
		// 2 elements for tabbing purposes (the text, then the button). So as you tab through, it will take 2
		// tabs to get to the next field. As far as I know there's no way around this in any kind of reasonable way.
		inputEl.on('focus', me.focus, me);

		trigger = me.getTrigger('filebutton');
		button = me.button = trigger.component;
		me.fileInputEl = button.fileInputEl;
		buttonEl = button.el;

		if (me.buttonOnly) {
			me.inputWrap.setDisplayed(false);
			if (Ext.isIE) {
				me.setStyle("width", "60px");
				me.setStyle("margin", "2px 100px 0px 0px");
			}
			me.shrinkWrap = 3;
		}

		// Ensure the trigger element is sized correctly upon render
		trigger.el.setWidth(buttonEl.getWidth() + buttonEl.getMargin('lr'));
		if (Ext.isIE) {
			me.button.getEl().repaint();
		}
	}
});

Ext.pivot.plugin.Exporter.override({
	getExporter: function(config) {
	    var me = this;
	    config = config || {};
	    me.matrix = me.pivot.getMatrix();
	    me.onlyExpandedNodes = config.onlyExpandedNodes;
	    delete (config.onlyExpandedNodes);
	    return Ext.Factory.exporter(Ext.apply({
	        type: 'excel',
	        data: me.prepareData(),
	        //增加导出的容器指定，主要为指定导出excel的列宽
	        cmp:me.getCmp( )
	    }, config));
	}
});


//导出Excel样式渲染
Ext.exporter.Excel.override({
			//Title行样式移除，单元格增加样式
			addTitle : function(config, colMerge) {
				if (!Ext.isEmpty(config.title)) {
					this.table.addRow({
						autoFitHeight : 1,
						height : 22.5
					}).addCell({
								mergeAcross : colMerge - 1,
								value : config.title,
								styleId : this.workbook.addStyle(
										config.titleStyle).getId()
							});
				}
			},
			//数据展示行样式移除，单元格增加样式
			buildRows : function(colMerge) {
				var me = this, data = me.getData(), groups = Ext
						.isDefined(data.groups) ? data.groups : Ext.Array
						.from(data), row;
				me.buildSummaryRows(groups, colMerge, 1);
				if (me.getShowSummary() !== false && Ext.isDefined(data.groups)
						&& data.summary && data.summary.length > 0) {
					row = me.table.addRow({});
					for (var j = 0; j < data.summary.length; j++) {
						row.addCell({
							styleId : me.groupFooterStyleId,
							value : data.summary[j]
						});
					}
				}
			},
			//构建所有数据信息
			buildSummaryRows : function(groups, colMerge, level) {
				var me = this, showSummary = me.getShowSummary(), g, row, styleH, styleF;
				if (!groups) {
					return;
				}
				styleH = me.workbook.addStyle({
					parentId : me.groupHeaderStyleId,
					alignment : {
						Horizontal : 'Left',
						Indent : level - 1
					}
				});
				styleF = me.workbook.addStyle({
					parentId : me.groupFooterStyleId,
					alignment : {
						Horizontal : 'Left',
						Indent : level - 1
					}
				});
				for (var i = 0; i < groups.length; i++) {
					g = groups[i];
					//如果不显示统计信息，此处不执行
					if (showSummary !== false && !Ext.isEmpty(g.text)) {
						me.table.addRow({}).addCell({
							mergeAcross : colMerge - 1,
							value : g.text,
							styleId : styleH.getId()
						});
					}
					//针对子项进行迭代
					me.buildSummaryRows(g.groups, colMerge, level + 1);
					//针对明细信息进行展示
					me.buildGroupRows(g.rows);
					//如果显示统计信息并且统计信息的数据显示
					if (showSummary !== false && g.summary
							&& g.summary.length > 0) {
						// that's the summary footer
						row = me.table.addRow({});
						for (var j = 0; j < g.summary.length; j++) {
							row.addCell({
								value : g.summary[j],
								styleId : styleF.getId()
							});
						}
					}
				}
			},
			buildGroupRows : function(lines) {
				var me = this,
	            config = this.getConfig();
				me.detailStyle = me.workbook.addStyle(config.detailStyle).getId();
				var l, row, i, j;
				if (!lines) {
					return;
				}
				for (i = 0; i < lines.length; i++) {
					row = this.table.addRow();
					l = lines[i];
					for (j = 0; j < l.length; j++) {
						row.addCell({
							value : l[j],
							styleId : me.detailStyle
						});
					}
				}
			},
			//表头的行样式移除,增加cell样式
			buildHeader : function() {
				var me = this, ret = {}, keys, row, i, j, len, lenCells;
				me.buildHeaderRows(me.getData().columns, ret);
				keys = Ext.Object.getKeys(ret);
				len = keys.length;
				for (i = 0; i < len; i++) {
					row = me.table.addRow({
						height : 20.25,
						autoFitHeight : 1
					});
					lenCells = ret[keys[i]].length;
					for (j = 0; j < lenCells; j++) {
						row.addCell( Ext.apply(ret[keys[i]][j],{
							styleId : me.tableHeaderStyleId
						}));
					}
				}
			},
	        cmpIndex: 0,
	        columnsIndex: 0,
			//表头的宽度及缩进处理
			buildHeaderRows: function(columns, result) {
				var col, count, s;
		        if (!columns) {
		            return;
		        }
		        var cmpColums = this.cmp.columnManager.columns;
		        for (var i = 0; i < columns.length; i++) {
		            col = columns[i];
		            count = this.getColumnCount(col.columns);
		            result['s' + col.level] = result['s' + col.level] || [];
		            s = {
		                value: this.sanitizeHtml(col.text)
		            };
		            if (count > 1) {
		                Ext.apply(s, {
		                    mergeAcross: count - 1
		                });
		            }
		            else if(count==0){
		            	var cmpColum=cmpColums[this.cmpIndex];
		            	if(cmpColum!=undefined){
		            		this.cmpIndex++;
		            	}else{
		            		this.cmpIndex--;
		            		cmpColum=cmpColums[this.cmpIndex]
		            	}
		            	 this.columnsIndex++;
		            	 this.table.addColumn({width:cmpColum.width,index:this.columnsIndex});
		            }
		            result['s' + col.level].push(s);
		            this.buildHeaderRows(col.columns, result);
		        }
		    },
			config : {
				defaultStyle : {
					alignment : {
						Vertical : 'Top'
					},
					font : {
						FontName : 'Calibri',
						Family : 'Swiss',
						Size : 11,
						Color : '#000000'
					}
				},
				detailStyle:{
					id:'detailStyle',
					alignment : {
						Vertical : 'Top'
					},
					font : {
						FontName : 'Calibri',
						Family : 'Swiss',
						Size : 11,
						Color : '#000000'
					},
					borders : [ {
						Position : 'Top',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}, {
						Position : 'Bottom',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Left',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Right',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}  ]
				},
				/**
				 * @cfg {Ext.exporter.file.excel.Style} titleStyle
				 *
				 * Default style applied to the title
				 */
				titleStyle : {
					name : 'Title',
					alignment : {
						Horizontal : 'Center',
						Vertical : 'Center'
					},
					font : {
						FontName : 'Cambria',
						Family : 'Swiss',
						Size : 18,
						Color : '#1F497D'
					},
					borders : [ {
						Position : 'Top',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}, {
						Position : 'Bottom',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Left',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Right',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} ]
				},
				/**
				 * @cfg {Ext.exporter.file.excel.Style} groupHeaderStyle
				 *
				 * Default style applied to the group headers
				 */
				groupHeaderStyle : {
					name : 'Group Header',
					borders : [ {
						Position : 'Top',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}, {
						Position : 'Bottom',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Left',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Right',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} ]
				},
				/**
				 * @cfg {Ext.exporter.file.excel.Style} groupFooterStyle
				 *
				 * Default style applied to the group footers
				 */
				groupFooterStyle : {
					name : 'Total Footer',
					borders : [ {
						Position : 'Top',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}, {
						Position : 'Bottom',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Left',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Right',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}  ]
				},
				/**
				 * @cfg {Ext.exporter.file.excel.Style} tableHeaderStyle
				 *
				 * Default style applied to the table headers
				 */
				tableHeaderStyle : {
					name : 'Heading 1',
					alignment : {
						Horizontal : 'Center',
						Vertical : 'Center'
					},
					borders : [ {
						Position : 'Top',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					}, {
						Position : 'Bottom',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Left',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} , {
						Position : 'Right',
						LineStyle : 'Continuous',
						Weight : 1,
						Color : '#000000'
					} ],
					font : {
						FontName : 'Calibri',
						Family : 'Swiss',
						Size : 11,
						Color : '#1F497D'
					}
				}
			}
		});

Ext.form.field.Date.override({
	getErrors: function(value) {
        value = arguments.length > 0 ? value : this.formatDate(this.processRawValue(this.getRawValue()));

        var me = this,errors,
            format = Ext.String.format,
            clearTime = Ext.Date.clearTime;
        if(value==""||value == undefined){
        	return;
        }
          var  errors = me.callParent.caller.$owner.superclass.callParent([value]);
          if(errors.length==1){
        	  Ext.Array.removeAt( errors, 0 ) ;
          }
          var  disabledDays = me.disabledDays,
            disabledDatesRE = me.disabledDatesRE,
            minValue = me.minValue,
            maxValue = me.maxValue,
            len = disabledDays ? disabledDays.length : 0,
            i = 0,
            svalue,
            fvalue,
            day,
            time;
        if (value === null || value.length < 1) { // if it's blank and textfield didn't flag it then it's valid
             return errors;
        }

        svalue = value;
        value = Ext.Date.parse(value,"Y-m-d H:i:s");
        if (!value) {
            errors.push(format(me.invalidText, svalue, Ext.Date.unescapeFormat(me.format)));
            return errors;
        }

        time = value.getTime();
        if (minValue && time < minValue.getTime()) {
            errors.push(format(me.minText, me.formatDate(minValue)));
        }

        if (maxValue && time > maxValue.getTime()) {
            errors.push(format(me.maxText, me.formatDate(maxValue)));
        }

        if (disabledDays) {
            day = value.getDay();

            for(; i < len; i++) {
                if (day === disabledDays[i]) {
                    errors.push(me.disabledDaysText);
                    break;
                }
            }
        }

        fvalue = me.formatDate(value);
        if (disabledDatesRE && disabledDatesRE.test(fvalue)) {
            errors.push(format(me.disabledDatesText, fvalue));
        }

        return errors;
    }
});
Ext.grid.plugin.Exporter.override({
	saveDocumentAs : function(config) {
		var exporter;

		if (this.disabled) {
			return;
		}

		exporter = this.getExporter.apply(this, arguments || {
			defaultStyle : {
				alignment : {
					Vertical : 'Top'
				},
				font : {
					FontName : 'Calibri',
					Family : 'Swiss',
					Size : 11,
					Color : '#f1ac23'
				}
			}
		});
		exporter.saveAs();

		Ext.destroy(exporter);
	}
});
//增加判断是否有行编辑权限
Ext.grid.plugin.RowEditing.override({
	startEdit: function(record, columnHeader) {
//		增加判断是否有行编辑权限，如果没有直接返回
		if(!this.grid.showUpdate()){
			return false;
		}
        var me = this,
            editor = me.getEditor(),
            context;
            
        if (Ext.isEmpty(columnHeader)) {
            columnHeader = me.grid.getTopLevelVisibleColumnManager().getHeaderAtIndex(0);
        }

        if (editor.beforeEdit() !== false) {
            context = me.getEditingContext(record, columnHeader);
            if (context && me.beforeEdit(context) !== false && me.fireEvent('beforeedit', me, context) !== false && !context.cancel) {
                me.context = context;

                // If editing one side of a lockable grid, cancel any edit on the other side.
                if (me.lockingPartner) {
                    me.lockingPartner.cancelEdit();
                }
                editor.startEdit(context.record, context.column, context);
                me.editing = true;
                return true;
            }
        }
        return false;
    }
});


