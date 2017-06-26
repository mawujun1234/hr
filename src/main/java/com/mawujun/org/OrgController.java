package com.mawujun.org;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/org")
public class OrgController {

	@Resource
	private OrgService orgService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/org/queryPager.do")
//	@ResponseBody
//	public Pager<Org> queryPager(Pager<Org> pager){
//		
//		return orgService.queryPage(pager);
//	}

	@RequestMapping("/org/query.do")
	@ResponseBody
	public List<Org> query(String parent_id,String exclude_id) {	
		List<Org> orges=orgService.query(Cnd.select().andEquals(M.Org.parent_id, parent_id)
				.andNotEqualsIf(M.Org.id, exclude_id)
				.asc(M.Org.sort));
		return orges;
	}
	

	@RequestMapping("/org/load.do")
	@ResponseBody
	public Org load(String id) {
		//return ResultModel.getInstance().setRoot(orgService.get(id));
		return orgService.load(id);
	}
	
	@RequestMapping("/org/create.do")
	@ResponseBody
	public Org create(@RequestBody Org org) {
		orgService.create(org);
		return org;
	}
	
	@RequestMapping("/org/update.do")
	@ResponseBody
	public  Org update(@RequestBody Org org) {
		orgService.update(org);
		return org;
	}
	
	@RequestMapping("/org/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		orgService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/org/destroy.do")
	@ResponseBody
	public Org destroy(@RequestBody Org org) {
		orgService.delete(org);
		return org;
	}
	@RequestMapping("/org/updateParentOrg.do")
	@ResponseBody
	public  String updateParentOrg(String org_id,String old_parent_id,String new_parent_id) {
		orgService.updateParentOrg(org_id, old_parent_id, new_parent_id);
		return "{success:true}";
	}
	
	
}
