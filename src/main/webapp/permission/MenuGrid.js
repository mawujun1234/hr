Ext.define('Ems.permission.MenuGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.permission.Menu'
	],
	columnLines :true,
	stripeRows:true,
	readOnly:false,//true表示不显示增删该查按钮
	viewConfig:{
		enableTextSelection:true
	},
	initComponent: function () {
      var me = this;
     var store_menuType=Ext.create('Ext.data.Store',{
     	storeId:'store_menuType',
		fields: ['key', 'name'],
		data : [
			{"key":"menu", "name":"菜单"},
			{"key":"element", "name":"界面元素"}
		]
	});
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'name',header:'菜单名称'
        },
		{dataIndex:'code',header:'编码',width:250
        },
		{dataIndex:'url',header:'地址',width:200
        },
//        {dataIndex:'ismobile_name',header:'移动端',width:60
//        },
		{dataIndex:'remark',header:'备注'
        }
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.permission.Menu',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/menu/query.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    extraParams:{limit:50},
			    reader:{
					type:'json',
					rootProperty:'root',
					successProperty:'success',
					totalProperty:'total'		
				}
			}
	  });
	  me.dockedItems=[];
	  if(!me.readOnly){
	    me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[{
				text: '新增',
				itemId:'create',
				handler: function(btn){
					me.onCreate();
				},
				iconCls: 'icon-plus'
			},{
			    text: '更新',
			    itemId:'update',
			    handler: function(){
			    	me.onUpdate();
					
			    },
			    iconCls: 'icon-edit'
			},{
			    text: '删除',
			    itemId:'destroy',
			    handler: function(){
			    	me.onDelete();    
			    },
			    iconCls: 'icon-trash'
			},{
				text: '刷新',
				itemId:'reload',
				disabled:me.disabledAction,
				handler: function(btn){
					var grid=btn.up("grid");
					grid.getStore().reload();
				},
				iconCls: 'icon-refresh'
			}]
		});
	  }

       
      me.callParent();
	},
	onCreate:function(){
    	var me=this;
    	if(!me.parent_id){
    		Ext.Msg.alert("消息","请先选择一个菜单!");
    		return;
    	}
		
    	var form=Ext.create('Ems.permission.MenuForm',{});

		var child=Ext.create('Ems.permission.Menu',{
			parent_id:me.parent_id,
			menuType:'element'
		});
		child.set("id",null);
		form.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[form],
    		listeners:{
    			close:function(){
    				me.getStore().reload();
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdate:function(){
    	var me=this;
		
    	var form=Ext.create('Ems.permission.MenuForm',{});
    	
    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null){
    		Ext.Msg.alert("提醒","请选择一行数据!");
    		return;
    	}

		form.loadRecord(node);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'更新',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[form]
    	});
    	win.show();
    },
    
    onDelete:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择一行数据");	
			return;
		}
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
				if (btn == 'yes'){
					node.erase({
					    failure: function(record, operation) {
			            	me.getStore().reload();
					    },
					    success:function(){
					    	me.getStore().reload();
					    }
				});
			}
		});
    }
});
