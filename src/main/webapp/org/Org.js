Ext.define("Ems.org.Org",{
	extend:"Ext.data.Model",
	fields:[
		{name:'name',type:'string'},
		{name:'code',type:'string'},
		{name:'layer',type:'int'},
		{name:'sort',type:'int'},
		{name:'phonenumber',type:'string'},
		{name:'fax',type:'string'},
		{name:'address',type:'string'},
		{name:'postalcode',type:'string'},
		{name:'email',type:'string'},
		{name:'web',type:'string'},
		{name:'introduction',type:'string'},
		{name:'operator_id',type:'string'},
		{name:'orgtype_id',type:'string'},
		{name:'isroot',type:'bool'},
		{name:'status',type:'bool'},
		{name:'isdel',type:'bool'},
		{name:'operatetime',type:'string'},
		{name:'id',type:'string'},
		{name:'parent_id',type:'string'},
		{name:'createdate',type:'string'},
		{name:'enddate',type:'string'}
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