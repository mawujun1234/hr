Ext.define('Ems.org.OrgFormRead',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.org.Org'
	],
	
    frame: true,
    autoScroll : true,
	buttonAlign : 'center',
    bodyPadding: '5 5 0',


    defaults: {
        msgTarget: 'under',
        labelWidth: 75,
        labelAlign:'right',
        anchor: '90%'
    },
	initComponent: function () {
       var me = this;
       me.items= [
		{
	        fieldLabel: '名称',
	        name: 'name',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '编码',
	        name: 'code',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
	        fieldLabel: '组织单元类型',
	        name: 'orgtype_id',
	        hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '组织单元类型',
	        name: 'orgtype_name',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '排序',
	        name: 'sort',
            allowDecimals:false,
            selectOnFocus:true,
	        xtype:'numberfield'   
	    },
		{
	        fieldLabel: '电话号码',
	        name: 'phonenumber',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '传真',
	        name: 'fax',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '地址',
	        name: 'address',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '邮编',
	        name: 'postalcode',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: 'E-mail',
	        name: 'email',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '网址',
	        name: 'web',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '介绍',
	        name: 'introduction',
            selectOnFocus:true,
	        xtype:'textfield'
	    },		
		
        {
        	fieldLabel: 'isroot',
            name:'isroot',
            hidden:true,
            xtype: 'checkbox',
            cls: 'x-grid-checkheader-editor'
        },
        {
	        fieldLabel: '层级',
	        name: 'layer',
	        hidden:true,
            allowDecimals:false,
            selectOnFocus:true,
	        xtype:'numberfield'   
	    },
        {
        	fieldLabel: 'status',
            name:'status',
            hidden:true,
            xtype: 'checkbox',
            cls: 'x-grid-checkheader-editor'
        },
        {
        	fieldLabel: 'isdel',
            name:'isdel',
            hidden:true,
            xtype: 'checkbox',
            cls: 'x-grid-checkheader-editor'
        },
        {
	        fieldLabel: '操作者',
	        name: 'operator_id',
	        hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
	    {
	        fieldLabel: '操作者',
	        name: 'operator_name',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
            fieldLabel: '最后操作时间',
            name: 'operatetime',
            editable:false,
            xtype: 'textfield',
            format: 'Y-m-d'   
        },
		{
	        fieldLabel: 'id',
	        name: 'id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '父id',
	        name: 'parent_id',
            hidden:true,
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
            fieldLabel: '开始时间',
            name: 'createdate',
            hidden:true,
            editable:false,
            xtype: 'datefield',
            format: 'Y-m-d'   
        },
		{
            fieldLabel: '结束时间',
            name: 'enddate',
            hidden:true,
            editable:false,
            xtype: 'datefield',
            format: 'Y-m-d'   
        }
	  ];   
	  
	  
//	  this.buttons = [];
//		this.buttons.push({
//			text : '保存',
//			itemId : 'save',
//			formBind: true, //only enabled once the form is valid
//       		disabled: true,
//			glyph : 0xf0c7,
//			handler : function(button){
//				var formpanel = button.up('form');
//				formpanel.updateRecord();
//				formpanel.getForm().getRecord().save({
//					failure: function(record, operation) {
//				    },
//				    success: function(record, operation) {
//						button.up('window').close();
//				    }
//				});			
//				
//				}
//			},{
//				text : '关闭',
//				itemId : 'close',
//				glyph : 0xf00d,
//				handler : function(button){
//					button.up('window').close();
//				}
//	    });
      me.callParent();
	},
	load:function(id){
		var me=this;
		Ems.org.Org.load(id,{
			success:function(record){
				//alert(record.get("name"));
				me.loadRecord(record);
			}
		});
	}
});
