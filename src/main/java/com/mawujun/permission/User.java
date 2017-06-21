package com.mawujun.permission;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.controller.shiro.IShiroUser;
import com.mawujun.generator.model.FieldDefine;
import com.mawujun.generator.model.ShowType;

@Entity
@Table(name="t_user")
public class User implements IShiroUser{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",hidden=true)
	@Column(length=36)
	private String id;
	@FieldDefine(title="姓名",hidden=false,genQuery=true,sort=5)
	@Column(length=50,nullable=false)
	private String name;
	@FieldDefine(title="登录名",hidden=false,genQuery=true,sort=5)
	@Column(length=30,nullable=false,unique=true)
	private String loginName;
	@FieldDefine(title="密码",hidden=false,genQuery=false,sort=5)
	@Column(length=30,nullable=false)
	private String pwd;
	@FieldDefine(title="电话",hidden=false,genQuery=false,sort=5)
	@Column(length=30,nullable=true)
	private String phone;
	@FieldDefine(title="手机",hidden=false,genQuery=false,sort=5)
	@Column(length=30,nullable=true)
	private String mobile;
	@FieldDefine(title="邮件",hidden=false,genQuery=false,sort=5)
	@Column(length=30,nullable=true)
	private String email;
	@FieldDefine(title="备注")
	@Column(length=150)
	private String remark;
	@Column(length=15,nullable=true)
	@FieldDefine(title="状态",sort=4,showType=ShowType.combobox,hidden=false)//
	private Boolean state;
	
	
	private Date lastlogintime;

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}


}
