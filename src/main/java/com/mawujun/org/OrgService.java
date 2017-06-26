package com.mawujun.org;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.permission.ShiroUtils;
import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class OrgService extends AbstractService<Org, String>{

	@Autowired
	private OrgRepository orgRepository;
	
	@Override
	public OrgRepository getRepository() {
		return orgRepository;
	}
	
	public String create(Org entity) {
		entity.setOperator_id(ShiroUtils.getUserId());
		entity.setOperatetime(new Date());
		return this.getRepository().create(entity);
	}
	
	public void update(Org entity) {
		entity.setOperator_id(ShiroUtils.getUserId());
		entity.setOperatetime(new Date());
		this.getRepository().update(entity);
	}
	
	public OrgVO load(String id) {
		return orgRepository.load(id);
	}
	
	public void updateParentOrg(String org_id,String old_parent_id,String new_parent_id) {
		Org child=orgRepository.get(org_id);
		//Org new_parent=orgRepository.load(new_parent_id);
		
		child.setParent_id(new_parent_id);
		orgRepository.update(child);
		
	}

}
