Ext.define('Ems.constant.ConstantItemForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.constant.ConstantItem'
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
	        fieldLabel: '编码',
	        name: 'id',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"编码不允许为空",
            selectOnFocus:true,
	        xtype:'textfield',
	        listeners:{
	        	blur:function(field){
	        		var newValue=field.getValue();
	        		var constant_id=field.up("form").getForm().findField("constant_id").getValue();
	        		if(newValue.indexOf(constant_id)!=0){
	        			field.setValue(constant_id+"-"+newValue);
	        		}
	        	}
	        
	        }
	    },
		{
	        fieldLabel: '名称',
	        name: 'name',
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
            xtype      : 'fieldcontainer',
            fieldLabel : '状态',
            defaultType: 'radiofield',
            defaults: {
                flex: 1
            },
            layout: 'hbox',
            items: [
           		{
                    boxLabel  : '可用',
                    name: 'status',
                    checked:true,
                    inputValue: 'true'
                },{
                    boxLabel  : '禁用',
                    name: 'status',
                    inputValue: 'false'
                }
            ]
        },
		
		{
	        fieldLabel: 'constant_id',
	        name: 'constant_id',
            hidden:true,
            selectOnFocus:true,
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
				formpanel.updateRecord();
				formpanel.getForm().getRecord().save({
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
