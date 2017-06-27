package com.mawujun.utils;
public final class M {
public static final class Constant {
	public static final String id="id";
	public static final String name="name";
	public static final String sort="sort";
}
public static final class ConstantItem {
	public static final String id="id";
	public static final String name="name";
	public static final String sort="sort";
	public static final String status="status";
	public static final String constant_id="constant_id";
}
public static final class Org {
	public static final String id="id";
	public static final String name="name";
	public static final String code="code";
	public static final String isroot="isroot";
	public static final String layer="layer";
	public static final String status="status";
	public static final String sort="sort";
	public static final String phonenumber="phonenumber";
	public static final String fax="fax";
	public static final String address="address";
	public static final String postalcode="postalcode";
	public static final String email="email";
	public static final String web="web";
	public static final String introduction="introduction";
	public static final String isdel="isdel";
	public static final String operator_id="operator_id";
	public static final String operatetime="operatetime";
	public static final String orgtype_id="orgtype_id";
	public static final String parent_id="parent_id";
	public static final String createdate="createdate";
	public static final String enddate="enddate";
}
public static final class Position {
	public static final String id="id";
	public static final String name="name";
	public static final String remark="remark";
	public static final String org_id="org_id";
	public static final String positiontype_id="positiontype_id";
}
public static final class Menu {
	public static final String id="id";
	public static final String code="code";
	public static final String name="name";
	public static final String url="url";
	public static final String leaf="leaf";
	public static final String menuType="menuType";
	public static final String parent_id="parent_id";
	public static final String sort="sort";
	public static final String remark="remark";
}
public static final class Role {
	public static final String id="id";
	public static final String name="name";
	public static final String roleType="roleType";
	public static final String remark="remark";
	public static final String parent_id="parent_id";
}
public static final class RoleMenu {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class menu {
		public static final String id="menu.id";
		public static final String code="menu.code";
		public static final String name="menu.name";
		public static final String url="menu.url";
		public static final String leaf="menu.leaf";
		public static final String menuType="menu.menuType";
		public static final String parent_id="menu.parent_id";
		public static final String sort="menu.sort";
		public static final String remark="menu.remark";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "menu";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class RoleUser {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class user {
		public static final String id="user.id";
		public static final String name="user.name";
		public static final String loginName="user.loginName";
		public static final String pwd="user.pwd";
		public static final String phone="user.phone";
		public static final String mobile="user.mobile";
		public static final String email="user.email";
		public static final String remark="user.remark";
		public static final String state="user.state";
		public static final String candel="user.candel";
		public static final String lastlogintime="user.lastlogintime";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "user";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class User {
	public static final String id="id";
	public static final String name="name";
	public static final String loginName="loginName";
	public static final String pwd="pwd";
	public static final String phone="phone";
	public static final String mobile="mobile";
	public static final String email="email";
	public static final String remark="remark";
	public static final String state="state";
	public static final String candel="candel";
	public static final String lastlogintime="lastlogintime";
}
}
