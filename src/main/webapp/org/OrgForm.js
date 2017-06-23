Ext.define('Ems.org.OrgForm',{
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
	        fieldLabel: 'id',
	        name: 'id',
	        xtype:'hiddenfield'
	    },
		{
	        fieldLabel: '组织编码',
	        name: 'code',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '组织名称',
	        name: 'name',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"组织名称不允许为空",
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
	        fieldLabel: '汇报编码',
	        name: 'reportCode',
	        xtype:'hiddenfield'
	    },
		{
	        fieldLabel: '层级',
	        name: 'reportLevel',
	        xtype:'hiddenfield'   
	    },
		{
	        fieldLabel: '组织短名称',
	        name: 'name_short',
            selectOnFocus:true,
	        xtype:'textfield'
	    },
		{
			fieldLabel: '组织类型',
			name: 'orgType',
			queryMode: 'local',
			editable:false,
			forceSelection:true,
		    displayField: 'name',
		    valueField: 'key',
		    store: {
		    	autoLoad:true,
			    fields: ['key', 'name'],
			    proxy:{
			    	actionMethods :{read: 'POST'},
				    type: 'ajax',
				    url: Ext.ContextPath+'/enum/queryOrgTypes.do',
				    reader: {
				        type: 'json'
				    }
			    }
			},
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"组织类型不允许为空",
			xtype:'combobox'
		},
		{
			fieldLabel: '状态',
			name: 'state',
			queryMode: 'local',
			editable:false,
			forceSelection:true,
		    displayField: 'name',
		    valueField: 'key',
		    store: {
		    	autoLoad:true,
			    fields: ['key', 'name'],
			    proxy:{
			    	actionMethods :{read: 'POST'},
				    type: 'ajax',
				    url: Ext.ContextPath+'/enum/queryStates.do',
				    reader: {
				        type: 'json'
				    }
			    }
			},
			value:'valid',
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            blankText:"状态不允许为空",
			xtype:'combobox'
		},
		{
	        fieldLabel: '排序',
	        name: 'sort',
            hidden:true,
            allowDecimals:false,
            selectOnFocus:true,
	        xtype:'numberfield'   
	    },
	    {
	        fieldLabel: '父id',
	        name: 'parent_id',
	        xtype:'hiddenfield'   
	    },
	    {
	        fieldLabel: '维度',
	        name: 'dim',
	        xtype:'hiddenfield'   
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
