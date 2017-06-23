Ext.define('Ems.org.PositionUserGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.permission.User',
	     'Ems.permission.UserForm',
	     'Ems.permission.UserTabpanel'
	],
	columnLines :true,
	stripeRows:true,

	initComponent: function () {
      var me = this;
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'name',header:'姓名'
        },
		{dataIndex:'loginName',header:'登录名'
        },
//		{dataIndex:'pwd',header:'密码'
//        },
        {dataIndex:'state_name',header:'状态'
        },
        {dataIndex:'phone',header:'电话'
        },
        {dataIndex:'mobile',header:'手机'
        },
        {dataIndex:'email',header:'邮件'
        },
		{dataIndex:'remark',header:'备注'
        }
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.permission.User',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/user/queryByPosition.do',
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
      me.dockedItems.push({
	        xtype: 'pagingtoolbar',
	        store: me.store,  
	        dock: 'bottom',
	        displayInfo: true
	  });
	  
	  me.dockedItems.push({
	  	xtype: 'toolbar',
	  	dock:'top',
	  	//enableOverflow:true,
		items:[
			{
                xtype: 'textfield',
				itemId:'name',
                fieldLabel: '姓名',
                labelWidth:40,
                width:150,
                selectOnFocus:true 
            },
			{
                xtype: 'textfield',
				itemId:'loginName',
                fieldLabel: '登录名',
                labelWidth:50,
                width:150,
                selectOnFocus:true 
            },
	    	{
            	text:'查询',
            	iconCls:'icon-search',
            	handler:function(btn){
            		var grid=btn.up("grid");
	            	grid.getStore().getProxy().extraParams=Ext.apply(grid.getStore().getProxy().extraParams,{
						"params['name']":grid.down("#name").getValue(),
						"params['loginName']":grid.down("#loginName").getValue()
	                });
            		grid.getStore().reload();
            	}
            }
	  	]
	  });
	  
	  me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[{
				text: '从职位添加',
				//itemId:'create',
				handler: function(btn){
					me.onCreateByRole();
				},
				iconCls: 'icon-plus'
			},
			{
				text: '选择用户',
				//itemId:'create',
				handler: function(btn){
					me.onCreateByUser();
				},
				iconCls: 'icon-plus'
			},{
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
			}
			,{
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
			},{
				text: '查看权限',
				handler: function(btn){
					var grid=btn.up("grid");
					grid.showPermission();
				},
				iconCls: ' icon-question-sign'
			}]
		});
	  
       
      me.callParent();
	},
	onCreateByRole:function(){
    	var me=this;
    	
    	var seluserWindow=Ext.create('Ems.org.SelPositionUserWindow',{
    		listeners:{
    			userdbclick:function(user){
    				Ext.Ajax.request({
						url:Ext.ContextPath+'/user/addToPosition.do',
						params:{
							user_id:user.get("id"),
							position_id:window.selected_position.get("id"),
							org_id:window.selected_position.get("org_id")
						},
						headers:{ 'Accept':'application/json;'},
						success:function(){
							//button.up('window').close();
							me.getStore().reload();
						}
					});
    			}
    		}
    	});
    	seluserWindow.show();
    },
    onCreateByUser:function(){
    	var me=this;
    	var seluserWindow=Ext.create('Ems.permission.SelUserWindow',{
    		listeners:{
    			userdbclick:function(user){
    				Ext.Ajax.request({
						url:Ext.ContextPath+'/user/addToPosition.do',
						params:{
							user_id:user.get("id"),
							position_id:window.selected_position.get("id"),
							org_id:window.selected_position.get("org_id")
						},
						headers:{ 'Accept':'application/json;'},
						success:function(){
							//button.up('window').close();
							me.getStore().reload();
						}
					});
    			}
    		}
    	});
    	seluserWindow.show();
    },
	onCreate:function(){
    	var me=this;
		
    	var formpanel=Ext.create('Ems.permission.UserForm',{});

		var child=Ext.create('Ems.permission.User',{

		});
		child.set("id",null);
		formpanel.loadRecord(child);
		
		formpanel.getForm().getRecord().getProxy( ).extraParams={
			position_id:window.selected_position.get("id"),
			org_id:window.selected_position.get("org_id")
		}
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
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

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null){
    		Ext.Msg.alert("提醒","请选择一行数据!");
    		return;
    	}

		var formpanel=Ext.create('Ems.permission.UserForm',{});
		formpanel.loadRecord(node);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'更新',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel]
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
				Ext.Ajax.request({
					url:Ext.ContextPath+'/user/deleteFromPosition.do',
					params:{
						user_id:node.get("id"),
						position_id:window.selected_position.get("id"),
						org_id:window.selected_position.get("org_id")
					},
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.getStore().reload();
					}
				});
			}
		});
    },
    showPermission:function(){
    	var me=this;
    	var me=this;
    	var record=me.getSelectionModel( ).getLastSelected( );

		if(!record){
		    Ext.Msg.alert("消息","请先选择一个用户");	
			return;
		}
    	
    	var userTabpanel=Ext.create('Ems.permission.UserTabpanel',{
    		user_id:record.get("id")
    	});
    	var win=Ext.create('Ext.Window',{
    		layout:'fit',
    		title:'查看用户拥有的权限(右边可以关闭窗口)----'+record.get("name"),
    		modal:true,
    		maximized:true,
    		items:[userTabpanel]
    	});
    	win.show();
    }
});
