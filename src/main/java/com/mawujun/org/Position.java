package com.mawujun.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.generator.model.FieldDefine;

@Entity(name="hr_position")
public class Position {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",hidden=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=false)
	@FieldDefine(title="岗位名称",sort=6)
	private String name;
	@Column(length=300,nullable=true)
	@FieldDefine(title="备注",sort=6)
	private String remark;
//	@Column(length=15,nullable=true)
//	@Enumerated(EnumType.STRING)
//	@FieldDefine(title="权限规则",sort=4,showType=ShowType.combobox,hidden=false)//
//	private AccessRule accessRule;
	
	@FieldDefine(title="组织id",hidden=true)
	@Column(length=36,nullable=false)
	private String org_id;
	
	@FieldDefine(title="职位类型",hidden=false)
	@Column(length=36,nullable=true)
	private String positiontype_id;
	
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getPositiontype_id() {
		return positiontype_id;
	}
	public void setPositiontype_id(String positiontype_id) {
		this.positiontype_id = positiontype_id;
	}

}
