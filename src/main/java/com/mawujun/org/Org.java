package com.mawujun.org;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.generator.model.FieldDefine;

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
	
	private Boolean isroot;//是否是根节点
	@FieldDefine(title="层级",sort=4,hidden=false)	
	private Integer level;
	private Boolean status;//状态
	@FieldDefine(title="排序",sort=4,hidden=false)	
	private Integer sort;
	
	@Column(length=30,nullable=true)
	@FieldDefine(title="电话号码",sort=4,hidden=false)	
	private String phonenumber;
	@Column(length=30,nullable=true)
	@FieldDefine(title="传真",sort=4,hidden=false)	
	private String fax;
	@Column(length=30,nullable=true)
	@FieldDefine(title="地址",sort=4,hidden=false)	
	private String address;
	@Column(length=30,nullable=true)
	@FieldDefine(title="邮编",sort=4,hidden=false)	
	private String postalcode;
	@Column(length=30,nullable=true)
	@FieldDefine(title="E-mail",sort=4,hidden=false)	
	private String email;
	@Column(length=30,nullable=true)
	@FieldDefine(title="网址",sort=4,hidden=false)	
	private String web;
	@Column(length=30,nullable=true)
	@FieldDefine(title="介绍",sort=4,hidden=false)	
	private String introduction;
	
	@Column(length=36,nullable=true)
	@FieldDefine(title="操作者id",sort=4,hidden=false)
	private String operator_id;
	private Date operatetime;
	
	@Column(length=36,nullable=true)
	@FieldDefine(title="组织单元类型",sort=4,hidden=false)	
	private String orgtype_id;
	@Column(length=30,nullable=true)
	@FieldDefine(title="父id",sort=4,hidden=true)	
	private String parent_id;
	
	@FieldDefine(title="开始时间",sort=4,hidden=true)	
	private Date begindate;
	@FieldDefine(title="结束时间",sort=4,hidden=true)	
	private Date enddate;
	
	
	
	
	
	
	
	
	

}
