Ext.override(Eway.locale,{
//	addSuccess : '增加成功.',//Eway.addSuccess
//	updateSuccess : '更改成功.',//Eway.updateSuccess
//	deleteSuccess : '删除成功.',//Eway.deleteSuccess
//	choiceUpdateMsg :'请选择您要更改的记录.',//Eway.choiceUpdateMsg
//	choiceDeleteMsg :'请选择您要删除的记录.',//Eway.choiceDeleteMsg
//	locale:{
//		commen:{
//			desc:'备注'
//		},
		view:{
			version:{
				View:{
					title:'版本管理'
				},
				VersionInstallInfo:{
					title:'版本安装信息统计图'
				},
				Update:{
					title:'更改版本信息',
					items:{
						items:{
							versionTypeId:'版本类型',
							versionNo:'版本号',
							dependVersion:{
								fieldLabel:'依赖版本',
								emptyText:'请选择依赖类型'
							},
							execBefore:{
								fieldLabel:'升级前执行脚本',
								emptyText:'请输入升级包中的以bat或cmd结尾的文件',
								regexText:'只能输入bat或cmd结尾的文件'
							},
							otherConfig:{
								containerTitle:'其他配置',
								autoDown:'允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)',
								uncompress:'自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>'
							}
						}
					}
				}
			}
	}
			
		
//	}
});