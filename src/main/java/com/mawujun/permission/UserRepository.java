package com.mawujun.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends IRepository<User, String>{
	public UserVO getByLoginName(@Param("loginName")String loginName);
	public void deleteUserByLoginName(@Param("loginName")String loginName);
	
	 public List<String> findPermissions(@Param("user_id")String user_id); 
	 
	 public Pager<User> queryByPosition(Pager<User> pager);
	 public Pager<User> queryByRole(Pager<User> pager);
	 
	 //public List<User> querySjs(String positionType_id);
	 
	 
	 //public List<Org> queryCurrentOrg(@Param("user_id")String user_id);
	 
	 /**
	  * 判断用户对某个仓库是否有编辑权限
	  * @author mawujun qq:16064988 mawujun1234@163.com
	  * @param user_id
	  * @param store_id
	  * @return
	  */
	 public int check_edit_store_permission(@Param("user_id")String user_id,@Param("store_id")String store_id);
	 
	 public List<RoleVO> queryRoleByUser(@Param("user_id")String user_id);
	 public List<MenuVO> queryMenuByUser(@Param("user_id")String user_id);
//	 public List<PositionVO> queryOrgPositionByUser(@Param("user_id")String user_id);
//	 public List<PositionOrgAccessVO> queryStoreByUser(@Param("user_id")String user_id);
//	 public List<PositionOrgAccessVO> queryWorkunitByUser(@Param("user_id")String user_id);
//	 public List<PositionOrgAccessVO> queryRepair_centreByUser(@Param("user_id")String user_id);
}
