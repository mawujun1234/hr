Ext.define("Ems.constant.ConstantItem",{
	extend:"Ext.data.Model",
	fields:[
		{name:'id',type:'string'},
		{name:'name',type:'string'},
		{name:'sort',type:'int'},
		{name:'status',type:'string'},
		{name:'constant_id',type:'string'}
	],
	proxy:{
		type:'ajax',
		actionMethods: { read: 'POST' },
		timeout :600000,
		headers:{ 'Accept':'application/json;'},
		writer:{
			type:'json',
			writeRecordId:true,
			writeAllFields:true
		},
		reader:{
			type:'json'
			///rootProperty:'root',
			//successProperty:'success',
			//totalProperty:'total'		
		},
		api:{
			read:Ext.ContextPath+'/constantItem/load.do',
			//load : Ext.ContextPath+'/constantItem/load.do',
			create:Ext.ContextPath+'/constantItem/create.do',
			update:Ext.ContextPath+'/constantItem/update.do',
			destroy:Ext.ContextPath+'/constantItem/destroy.do'
		}
	}
});