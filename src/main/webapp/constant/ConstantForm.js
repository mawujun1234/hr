Ext.define('Ems.constant.ConstantForm',{
	extend:'Ext.form.Panel',
	requires: [
	     'Ems.constant.Constant'
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
	        xtype:'textfield'
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
