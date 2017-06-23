Ext.define("Ems.permission.User",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
		{name:'loginName',type:'string'},
		{name:'pwd',type:'string'},
		{name:'canNotDel',type:'string'},
		{name:'remark',type:'string'},
		{name:'phone',type:'string'},
		{name:'mobile',type:'string'},
		{name:'email',type:'string'},
		{name:'state',type:'string'},
		{name:'state_name',type:'string'},
		{name:'id',type:'string'}
	],
	proxy:{
		type:'ajax',
		actionMethods: { read: 'POST' },
		timeout :600000,
		headers:{ 'Accept':'application/json;'},
		writer:{
			type:'json',
			writeAllFields:true
		},
		api:{
			read:Ext.ContextPath+'/user/query.do',
			load : Ext.ContextPath+'/user/load.do',
			create:Ext.ContextPath+'/user/create.do',
			update:Ext.ContextPath+'/user/update.do',
			destroy:Ext.ContextPath+'/user/destroy.do'
		}
	}
});