Ext.define("Ems.org.Org",{
	extend:"Ext.data.Model",
	fields:[
		{name:'id',type:'string'},
		{name:'code',type:'string'},
		{name:'name',type:'string'},
		{name:'reportCode',type:'string'},
		{name:'reportLevel',type:'int'},
		{name:'name_short',type:'string'},
		{name:'orgType',type:'string'},
		{name:'state',type:'string'},
		{name:'sort',type:'int'}
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
			read:Ext.ContextPath+'/org/load.do',
			//load : Ext.ContextPath+'/org/load.do',
			create:Ext.ContextPath+'/org/create.do',
			update:Ext.ContextPath+'/org/update.do',
			destroy:Ext.ContextPath+'/org/destroy.do'
		}
	}
});