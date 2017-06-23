Ext.define("Ems.constant.Constant",{
	extend:"Ext.data.Model",
	fields:[
		{name:'id',type:'string'},
		{name:'name',type:'string'},
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
			read:Ext.ContextPath+'/constant/load.do',
			//load : Ext.ContextPath+'/constant/load.do',
			create:Ext.ContextPath+'/constant/create.do',
			update:Ext.ContextPath+'/constant/update.do',
			destroy:Ext.ContextPath+'/constant/destroy.do'
		}
	}
});