Ext.define('Ems.permission.MenuForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.permission.Menu'
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
	        fieldLabel: '菜单名称',
	        name: 'name',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"菜单名称不允许为空",
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '编码',
	        name: 'code',
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '地址',
	        name: 'url',
//	        allowBlank: false,
//            afterLabelTextTpl: Ext.required,
//            blankText:"地址不允许为空",
	        xtype:'textfield'
	    },
//	    {
//			fieldLabel: '移动端',
//			name: 'ismobile',
//			queryMode: 'local',
//			editable:false,
//			forceSelection:true,
//		    displayField: 'name',
//		    valueField: 'key',
//		    store: {
//			    fields: ['key', 'name'],
//			    data : [
//			    	{"key":"false", "name":"否"},
//			    	{"key":"true", "name":"是"}
//			    ]
//			},
//			value:'rolegroup',
//            allowBlank: false,
//            afterLabelTextTpl: Ext.required,
//            blankText:"菜单类型不允许为空",
//			xtype:'combobox'
//		},
	    {
	        fieldLabel: '排序',
	        name: 'sort',
	        xtype:'numberfield'
	    },
		{
	        fieldLabel: '备注',
	        name: 'remark',
	        xtype:'textfield'
	    },
		{
	        fieldLabel: 'id',
	        name: 'id',
            //hidden:true,
	        xtype:'hiddenfield'
	    },
	    {
	        fieldLabel: 'leaf',
	        name: 'leaf',
            //hidden:true,
	        xtype:'hiddenfield'
	    },
	    {
	        fieldLabel: '菜单类型',
	        name: 'menuType',
            hidden:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '父id',
	        name: 'parent_id',
            hidden:true,
	        xtype:'textfield'
	    }
	  ];   
	  
	  
	  this.buttons = [];
		this.buttons.push({
			text : '保存',
			itemId : 'save',
			formBind: true, //only enabled once the form is valid
       		disabled: true,
			glyph : 0xf0c7,
			handler : function(button){
				var formpanel = button.up('form');
				button.up('form').updateRecord();
				button.up('form').getForm().getRecord().save({
					failure: function(record, operation) {
				    },
				    success: function(record, operation) {
						button.up('window').close();
				    }
				});			
				
				}
			},{
				text : '关闭',
				itemId : 'close',
				glyph : 0xf00d,
				handler : function(button){
					button.up('window').close();
				}
	    });
      me.callParent();
	}
});
