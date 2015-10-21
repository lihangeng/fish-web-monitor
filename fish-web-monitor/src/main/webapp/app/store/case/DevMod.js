
Ext.define('Eway.store.case.DevMod', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [{value:'IDC', display:'读卡器模块'},
	       {value:'CIM', display:'存款模块'},
		   {value:'CDM', display:'取款模块'},
		   {value:'RPR', display:'凭条打印模块'},
		   {value:'JPR', display:'日志打印模块'},
		   {value:'PIN', display:'密码键盘模块'},
		   {value:'TTU', display:'文本终端单元'},
		   {value:'SIU', display:'传感器模块'},
		   {value:'ISC', display:'身份证扫描仪模块'},
		   {value:'ICC', display:'发卡器模块'},
		   {value:'FGP', display:'指纹仪模块'}]

});