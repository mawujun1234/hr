package com.mawujun.org;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.constant.ConstantItem;

@Entity
@Table(name="hr_org")
public class Org {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",sort=7,hidden=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="名称",sort=6)
	private String name;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="编码",sort=6)
	private String code;
	@FieldDefine(title="是否是根节点",sort=4,hidden=true)
	private Boolean isroot=false;//是否是根节点
	@FieldDefine(title="层级",sort=4,hidden=true)	
	private Integer layer;//第几层
	private Boolean status;//状态
	@FieldDefine(title="排序",sort=4,hidden=false)	
	private Integer sort;
	
	@Column(length=30,nullable=true)
	@FieldDefine(title="电话号码",sort=4,hidden=false)	
	private String phonenumber;
	@Column(length=30,nullable=true)
	@FieldDefine(title="传真",sort=4,hidden=false)	
	private String fax;
	@Column(length=80,nullable=true)
	@FieldDefine(title="地址",sort=4,hidden=false)	
	private String address;
	@Column(length=10,nullable=true)
	@FieldDefine(title="邮编",sort=4,hidden=false)	
	private String postalcode;
	@Column(length=50,nullable=true)
	@FieldDefine(title="E-mail",sort=4,hidden=false)	
	private String email;
	@Column(length=120,nullable=true)
	@FieldDefine(title="网址",sort=4,hidden=false)	
	private String web;
	@Column(length=300,nullable=true)
	@FieldDefine(title="介绍",sort=4,hidden=false)	
	private String introduction;
	
	@FieldDefine(title="是否已删除",sort=4,hidden=true)
	private Boolean isdel=false;//是不是已经删除了，逻辑删除
	@Column(length=36,nullable=true)
	@FieldDefine(title="操作者id",sort=4,hidden=true)
	private String operator_id;//最后一次操作的人
	@FieldDefine(title="结束时间",sort=4,hidden=true)	
	private Date operatetime;//最后一次操作的时间,更新或者创建，删除
	
	@Column(length=36,nullable=true)
	@FieldDefine(title="组织单元类型",sort=4,hidden=false)	
	@FK(cls=ConstantItem.class,column="id")
	private String orgtype_id;
	@Column(length=36,nullable=true)
	@FieldDefine(title="父id",sort=4,hidden=true)	
	private String parent_id;
	
	@FieldDefine(title="开始时间",sort=4,hidden=true)	
	private Date createdate;//第一次创建的时间
	@FieldDefine(title="结束时间",sort=4,hidden=true)	
	private Date enddate;//关闭的时间
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getIsroot() {
		return isroot;
	}
	public void setIsroot(Boolean isroot) {
		this.isroot = isroot;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Boolean getIsdel() {
		return isdel;
	}
	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public String getOrgtype_id() {
		return orgtype_id;
	}
	public void setOrgtype_id(String orgtype_id) {
		this.orgtype_id = orgtype_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	

}
