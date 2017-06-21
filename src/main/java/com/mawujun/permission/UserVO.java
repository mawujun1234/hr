package com.mawujun.permission;

import java.util.Date;

public class UserVO extends User {
	
	private Date loginDate;
	private String ipAddr;//登录的IP地址
	
	//private List<PubCode> brandes;//可访问的品牌
	//private List<String> classes;//可访问的大类
	
	//private List<Org> compes;//可访问的营销公司
	//private List<Org> regnes;//可访问的区域
	
	//private List<Org> currentOrges;//当前所属的组织单元
	

//	
//	/**
//	 * 获取第一个组织单元，在订货会的时候一个账号只属于一个组织单元，否则就会报错，这里定义了这个规则，只能有一个组织单元才能登录
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @return
//	 */
//	public Org getFirstCurrentOrg(){
//		return currentOrges.get(0);
//	}
//	/**
//	 * 判断用户是否是在这个组织节点内
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @return
//	 */
//	public Boolean hasTheOrg(String orgno){
//		if(currentOrges==null){
//			return false;
//		}
//		for(Org org:currentOrges) {
//			if(org.getOrgno().equals(orgno)){
//				return true;
//			}
//		}
//		return false;
//	}
	

	

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
