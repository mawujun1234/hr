package com.mawujun.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.org.Org;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.bean.BeanUtils;
import com.mawujun.utils.page.Pager;

/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends AbstractService<User, String> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
//	@Autowired
//	private PositionRepository positionRepository;
//	@Autowired
//	private OrgRepository orgRepository;
//	@Autowired
//	private PositionOrgUserRepository positionOrgUserRepository;
	@Autowired
	private RoleUserRepository userRoleRepository;
	

	@Override
	public UserRepository getRepository() {
		return userRepository;
	}

	public void createByRole(User user, String role_id) {
		super.create(user);
		RoleUser userRole = new RoleUser(user, roleRepository.get(role_id));
		userRoleRepository.create(userRole);
	}
	
//	public void createByPosition(User user,String position_id,String org_id) {
//		super.create(user);
//		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),user);
//		positionOrgUserRepository.create(positionOrgUser);
//		
//	}
	public void addToRole(String user_id,String role_id) {
		RoleUser userRole = new RoleUser(userRepository.load(user_id), roleRepository.load(role_id));
		userRoleRepository.create(userRole);
	}
	
	
	public void deleteFromRole(String user_id,String role_id) {
		userRoleRepository.delete(new RoleUser(userRepository.load(user_id), roleRepository.load(role_id)));

		//super.delete(user);
	}
	
//	public void addToPosition(String user_id,String position_id,String org_id) {
//		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),userRepository.load(user_id));
//		positionOrgUserRepository.create(positionOrgUser);
//	}
//	public void deleteFromPosition(String user_id,String position_id,String org_id) {
//		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),userRepository.load(user_id));
//		positionOrgUserRepository.delete(positionOrgUser);
//	}

	public UserVO getByLoginName(String loginName) {
		UserVO user = userRepository.getByLoginName(loginName);
		return user;
	}

	/**
	 * 获取用户的权限
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String user_id) {
		//这里最好再添加进去用户所属的数据权限，然后放到用户里面
		List<String> list = userRepository.findPermissions(user_id);
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		return set;
	}
	public Pager<User> queryByRole(Pager<User> pager){
		return userRepository.queryByRole(pager);
	}
	public Pager<User> queryByPosition(Pager<User> pager){
		return userRepository.queryByPosition(pager);
	}
	@Override
	public void delete(User user_p) {
		
//		userRoleRepository.deleteBatch(Cnd.delete().andEquals(M.RoleUser.user.id, user_p.getId()));
//		positionOrgUserRepository.deleteBatch(Cnd.delete().andEquals(M.PositionOrgUser.user.id, user_p.getId()));
		User user=this.getRepository().get(user_p.getId());
//		if(user.getCanNotDel()!=null && user.getCanNotDel()==true){
//			throw new BusinessException("该用户不能删除！");
//		}
		this.getRepository().delete(user);
	}

//	/**
//	 * 初始化该用户可访问的组织单元
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @param userVO
//	 */
//	public List<Org> queryCurrentOrg(String user_id) {
//		//获取用户当前所属的组织单元
//		return userRepository.queryCurrentOrg(user_id);
//		
//	}
	
	public void deleteUserByLoginName(String loginName) {
		userRepository.deleteUserByLoginName(loginName);
	}
	
	public List<RoleVO> queryRoleByUser(String user_id) {
		List<RoleVO> result=new ArrayList<RoleVO>();
		Map<String,RoleVO> parentTemp=new HashMap<String,RoleVO>();
		
		List<RoleVO> roles=userRepository.queryRoleByUser(user_id);
		for(RoleVO role:roles){
			recursionRoleParent(role,result,parentTemp);
		}
		return result;
	}
	
	private void recursionRoleParent(RoleVO child,List<RoleVO> result,Map<String,RoleVO> parentTemp){
		if(child.getParent_id()!=null){
			RoleVO parentVO=null;
			if(parentTemp.get(child.getParent_id())!=null){
				parentVO=parentTemp.get(child.getParent_id());
			} else {
				Role parent=roleRepository.get(child.getParent_id());
				parentVO=BeanUtils.copyOrCast(parent, RoleVO.class);
				parentTemp.put(parentVO.getId(), parentVO);
			}

			parentVO.addChild(child);
			recursionRoleParent(parentVO,result,parentTemp);
		} else {
			result.add(child);
		}
		
	}
	
	
	public List<MenuVO> queryMenuByUser(String user_id) {
		return userRepository.queryMenuByUser(user_id);
	}
	
//	public List<PositionVO> queryOrgPositionByUser(String user_id) {
//		List<PositionVO> list= userRepository.queryOrgPositionByUser(user_id);
//		for(PositionVO vo:list){
//			String org_id=vo.getOrg_id();
//			while(org_id!=null){
//				if("root".equals(org_id)){
//					Org org=orgRepository.get(org_id);
//					vo.setOrg_all_name(org.getName()+">"+vo.getOrg_all_name());
//					org_id=null;
//				} else {
//					OrgVO org=orgRepository.getBaseOrg(org_id);
//					vo.setOrg_all_name(org.getName()+">"+vo.getOrg_all_name());
//					org_id=org.getParent_id();
//				}
//				
//			}
//		}
//		return list;
//	}
//	
//	public List<PositionOrgAccessVO> queryStoreByUser(String user_id) {
//		return userRepository.queryStoreByUser(user_id);
//	}
//	public List<PositionOrgAccessVO> queryWorkunitByUser(String user_id) {
//		return userRepository.queryWorkunitByUser(user_id);
//	}
//	public List<PositionOrgAccessVO> queryRepair_centreByUser(String user_id) {
//		return userRepository.queryRepair_centreByUser(user_id);
//	}
}
