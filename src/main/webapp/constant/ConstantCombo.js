/**
 * 
 * 
 * 使用方法:{
	        fieldLabel: 'XXX',
	        name: 'xxx',
	        //selFirst:true,
	        showBlank:false,
            allowBlank: false,
            afterLabelTextTpl: Ext.required,
            //value:'Y',
            blankText:"XXX不允许为空",
	        xtype:'constantcombo',
	        constant_id:'XXXX'
	    }
 */
Ext.define('Ems.constant.ConstantCombo',{
	extend:'Ext.form.field.ComboBox',
	xtype:'constantcombo',
	//fieldLabel: '角色类型',
	//		name: 'roleType',
	//value:'rolegroup',
	constant_id:'',//常数累心的id
	
	selFirst:false,
	showBlank:true,//是否显示“无”的数据
	//fitno:'',
	autoLoad:true,

	queryMode: 'local',
	editable:true,
	forceSelection:true,
	displayField: 'name',
	valueField: 'id',
//    allowBlank: false,
//    afterLabelTextTpl: Ext.required,
//    blankText:"菜单类型不允许为空",
	initComponent: function () {
		var me=this;

		if(!me.constant_id){
			alert("设置constant_id编码！");
			return;
		}
		
		if(me.queryMode=='remote'){
			me.editable=true;
			me.typeAhead= true;
			me.queryDelay=1000;
			me.minChars=2;
		}
		
		me.store=Ext.create('Ext.data.Store',{
			fields: ['id', 'name'],
			autoLoad:me.autoLoad,
			proxy: {
			    actionMethods :{read: 'POST'},
			    type: 'ajax',
			    extraParams:{
			    	constant_id:me.constant_id,
			    	showBlank:me.showBlank,
			    	status:true
					//fitno:me.fitno
			    },
			    url: Ext.ContextPath+'/constantItem/query4Combo.do',
			    reader: {
			        type: 'json'
			        //rootProperty: '${propertyColumn.property}'
			    }
			},
			listeners:{
//				beforeload:function(store){
//					//添加是否当季
//					//console.log(window.stat_xtrydeeeeeeeee);
//					if(typeof(window.stat_xtrydeeeeeeeee)=='undefined' || window.stat_xtrydeeeeeeeee==null){
//						window.stat_xtrydeeeeeeeee=1;
//					}
//					store.getProxy().extraParams=Ext.apply(store.getProxy().extraParams,{
//						stat_stat:window.stat_xtrydeeeeeeeee
//					});
//				}
			}
			
		});
		
		if(!me.value && me.selFirst){
			me.store.on("load",function(myStore){
				if(myStore.getCount( ) >0){
					var r=null;
					if(me.showBlank==true){
						r=myStore.getAt(1);//第一行是无
					} else {
						r=myStore.getAt(0);//第一行是,正确的数据
					}
			 		me.select( r );
				 	me.fireEvent("select", me, r);
			 	}
			})
		}
		me.callParent();
	},
//	changeBradno:function(bradno){
//		var me=this;
//		me.getStore().getProxy().extraParams=Ext.apply(me.getStore().getProxy().extraParams,{
//	        bradno:bradno
//	    });
//	},
	/**
	 * 调用这个的都是说明有父节点的
	 * @param {} fitno
	 */
	reload:function(fitno){
		var me=this;
		me.reloaded=false;

//		if(!fitno){
////			alert("请输入fitno参数!");
////			return;
//			
//			fitno="none";
//		}
//		
//		me.getStore().getProxy().extraParams=Ext.apply(me.getStore().getProxy().extraParams,{
//	        fitno:fitno
//	    })
	    me.getStore().reload();
	    me.reloaded=true;
	}
	
	
});