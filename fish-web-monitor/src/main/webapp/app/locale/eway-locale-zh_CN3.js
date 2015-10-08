Ext.apply(Eway,{
	addSuccess : '增加成功.',
	updateSuccess : '更改成功.',
	deleteSuccess : '删除成功.',
	choiceUpdateMsg :'请选择您要更改的记录.',
	choiceDeleteMsg :'请选择您要删除的记录.',
	locale:{
		button:{
			search:'查询',
			add : '增加',
			update:'更改',
			remove:'删除',
			refresh:'刷新',
			reset:'重置',
			back:'返回',
			//bankOrg
			deepQuery:'深度查询',
			bankOrgMove:'组织迁移',
			bankOrgAdmin:'管理员',
			//bankPerson
			bankPerlink:'绑定设备',
			confirm:'确认',
			cancle:'取消',
		},
		combox:{
			select:'--请选择--'
		},
		tip : {
			search :{
				warn:'查询条件存在错误项.'
			},
			update:{
				one:'只能选择一条记录更改.'
			},
			remove :{
				none:'请选择您要删除的记录.',
				one:'只能选择一条记录删除',
				confirm:{
					title:'请确认',
					info:'是否删除该记录?'
				},
				error:'删除失败:'
			},
			success:'成功.',
			fail:'失败:',
			phone:'请输入正确的电话号码'
		},
		vtype:{
			ip:'请输入正确的IP地址',
			zip:'请输入正确的邮编格式，6位的数字',
			versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
			terminalId:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
			mobile:'输入错误,手机号码只能输入8到11位数字。',
			cardNo:'输入错误,银行卡号只能输入16到19位数字。',
			telephone:'输入错误,固定电话号码只能输入8到11位数字。',
			daterange:'日期段不正确.',
			numberrange:'金额范围不正确.',
			//banOrg
			bankOrgCode:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',
			zip:'只能输入6个‘0-9’的数字！'
		},
		machine:{
			atmBrand : {
				title:'品牌管理',
				name: "品牌名称",
				country:'生产商国家或地区',
				hotline1:'生产商热线1',
				hotline2:'生产商热线2',
				address:'生产商地址',
				status:'生产商状态',
				comboxStatus:{
					provider:'设备供应',
					maintance:'设备服役'
				}
			},
			atmCatalog:{
				title:'ATM分类',
				name:'分类名称',
				note:'备注',
				addTitle:'增加ATM分类信息',
				updateTitle:'更改ATM型号信息'
			},
			atmType:{
				
			}
		},
		person:{
			bankOrg :{
				title:'银行机构管理',
				moveBankTitle:'组织迁移',
				updateBankTitle:'更改银行机构信息',
				addBankOrgTitle:'增加银行机构信息',
				code:'机构编号',
				name:'机构名称',
				orgLevel:'机构级别',
				orgNavi:'机构导航',
				zip:'邮政编码',
				setManager:'设置',
				removeManager:'删除',
				manager:'管理员',
				address:'机构地址',
				upgradeOrg:'上级机构',
				description:'机构描述',
				serOrganization:'机构描述',
				organizationType:{
					bank:'银行',
					serviceOrg:'维护商'
				},
				organizationLevelDict:{
					rootBank:'总行',
					branchBank:'分行',
					tagBranchBank:'支行',
					netBank:'网点'
				},
				organizationStateDict:{
					newCreate:'新建',
					normal:'正常',
					locked:'锁定',
					disable:'无效',
					frozen:'冻结'
				}
			},
			bankPer :{
				title:'银行人员管理',
				jobNum:'工号',
				name:'姓名',
				personJobName:'岗位',
				organizationName:'机构',
				state:'状态',
				comboxStatus:{
					onJob:'在岗',
					onAdjust:'调休',
					onVacation:'休假',
					other:'其他'
					
				},
				orgNavi:'机构导航',
				type:'类型',
				comboxType:{
					machineManager:'管机员',
					machineRepairer:'维修人员'
				},
				mobile:'手机',
				email:'邮箱',
				phone:'固话',
				gender:'性别',
				allGender:'全部',
				comboxGender:{
					male:'男',
					female:'女',
					unkown:'未知'
				},
				remark:'备注',
				addBankPerTitle:'增加银行人员信息',
				updateBankPerTitle:'更改银行人员信息',
				
			}
		}
	}
});