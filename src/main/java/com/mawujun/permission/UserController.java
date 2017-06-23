package com.mawujun.permission;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
//	@Resource
//	private OrdService ordService;


	@RequestMapping("/user/login.do")
	//@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response,String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject(); 
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password); 
		//subject.login(token);
		String error=null;
		try {  
            subject.login(token);  
        } catch (UnknownAccountException e) {  
            error = "用户名/密码错误";  
        } catch (IncorrectCredentialsException e) {  
            error = "用户名/密码错误";  
        } catch (AuthenticationException e) {  
            //其他错误，比如锁定，如果想单独处理请单独catch处理  
            error = "认证失败，账号不存在!";  
            e.printStackTrace();
        }  
        if(error != null) {//出错了，返回登录页面  
        	model.addAttribute("msg", error);
        	model.addAttribute("success", false);
        	//return "/main/login.jsp";
        	return "/main/failure.jsp";
        } else {//登录成功  
            //req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(req, resp); 
        	 String successUrl = null;
        	 SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
             if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
                 successUrl = savedRequest.getRequestUrl();
             }
             if(successUrl==null){
            	 successUrl="/main/index.jsp";
             }
             model.addAttribute("success", true);
             ShiroUtils.getAuthenticationInfo().setIpAddr(getIpAddr(request));
             
             //
             Cookie cookie_usernId = new Cookie("usernId",ShiroUtils.getUserName());
             cookie_usernId.setPath("/");
             response.addCookie(cookie_usernId);
             Cookie cookie = new Cookie("loginName",ShiroUtils.getLoginName());
             cookie.setPath("/");
             response.addCookie(cookie);
             Cookie cookie_username = new Cookie("usernName",ShiroUtils.getUserName());
             cookie_username.setPath("/");
             response.addCookie(cookie_username);
             
             userService.update(Cnd.update().set(M.User.lastlogintime, new Date()).andEquals(M.User.id, ShiroUtils.getUserId()));
             //显示调用这个，来初始化ShiroAuthorizingRealm中的doGetAuthorizationInfo方法，来获取用户可以访问的资源,否则将不会调用doGetAuthorizationInfo
             SecurityUtils.getSubject().hasRole("XXX") ;
             return successUrl;
        }  
       // return model;
	}
	@RequestMapping("/user/queryCurrentUser.do")
	@ResponseBody
	public UserVO queryCurrentUser(String user_id) {
//		Map<String,Object> user=new HashMap<String,Object>();
//		user.put("loginName", ShiroUtils.getLoginName());
//		user.put("userName", ShiroUtils.getUserName());
//		return user;
		return ShiroUtils.getAuthenticationInfo();

	}
	/**
	 * 保持在线的请求
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	@RequestMapping("/user/onlineling.do")
	@ResponseBody
	public String onlineling() {
		if(!ShiroUtils.isLogon()){
			//JsonConfigHolder.setSuccessValue(false);
			return "{\"success\":false}";
		}
		return "{\"success\":true}";
	}
//	/**
//	 * 
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @param username
//	 * @param password
//	 * @param isscan true表示是通过扫描登录的
//	 * @return
//	 */
//	@RequestMapping("/user/mobile/login.do")
//	@ResponseBody
//	public Map<String,Object> mobile_login(String username,String password,Boolean isscan) {
//		
//		Map<String,Object> model=new HashMap<String,Object>();
//		Subject subject = SecurityUtils.getSubject(); 
//		
//		UsernamePasswordToken1 token = new UsernamePasswordToken1(username, password); 
//		token.setIsscan(isscan==null?false:isscan);
//		//subject.login(token);
//		String error=null;
//		try {  
//            subject.login(token);  
//        } catch (UnknownAccountException e) {  
//            error = "用户名/密码错误";  
//        } catch (IncorrectCredentialsException e) {  
//            error = "用户名/密码错误";  
//        } catch (AuthenticationException e) {  
//            //其他错误，比如锁定，如果想单独处理请单独catch处理  
//            error = "认证失败，账号不存在!";  
//            e.printStackTrace();
//        }  
//		
//		//显示调用这个，来初始化ShiroAuthorizingRealm中的doGetAuthorizationInfo方法，来获取用户可以访问的资源,否则将不会调用doGetAuthorizationInfo
//        SecurityUtils.getSubject().hasRole("XXX") ;
//		
//        Org org=ShiroUtils.getAuthenticationInfo().getFirstCurrentOrg();
//		//创建订单主表
//		try {
//			//判断用户是否是订货单位，如果是订货单位的话，就创建订货单
//			if(ordService.isOrmtnoOrdorg(org.getOrgno())){
//				ordService.create();
//			} else {
//				if(org.getChanno()!=Chancl.YXGS){
//					model.put("success", false);
//					model.put("msg", "你不是订货单位，不能登录!");
//					return model;
//				} 
//			}
//			
//		 } catch (BusinessException e) {  
//	        error = e.getMessage();  
//	     }
//        
//        if(error != null) {//出错了，返回登录页面  
//        	model.put("msg", error);
//        	model.put("success", false);
//        	//return "/main/login.jsp";
//        } else {//登录成功  
//   
//             model.put("success", true);
//             
//             
//             model.put("orgnm", org.getOrgnm());
//             model.put("channo",org.getChanno().toString());
//             
//        }  
//		return model;
//	}
//	@RequestMapping("/user/mobile/getWxConfig.do")
//	@ResponseBody
//	public Map<String,Object> getWxConfig(String url) {
//		//设置微信调用设想头的信息
//        Map<String,Object> wxConfig=new HashMap<String,Object>();
//        wxConfig.put("appId", SignUtil.APPID);
//        wxConfig.put("nonceStr", SignUtil.noncestr);
//        //如果不能访问外网，就不去获取签名,在测试库的时候
//        if(url.indexOf("192.168.188.69")==-1){
//       	 wxConfig.put("signature", SignUtil.getSignature(url));
//        }
//        wxConfig.put("timestamp", SignUtil.timestamp);
//        //wxConfig.put("jsApiList", new String[]{"scanQRCode"});
// 
//        return wxConfig;
//	}
	

	private String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	@RequestMapping("/user/logout.do")
	//@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logout();
		//return "/main/login";
		response.sendRedirect(request.getContextPath()+"/main/login.jsp");
		//response.getRequestDispatcher("/main/login").forward(request, response);
	}
	/**
	 * 没有跳转的，在别的地方调用的
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 */
	public void logout(){
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
	}
	@RequestMapping("/user/queryPage.do")
	@ResponseBody
	public Pager<User> queryPage(Pager<User> pager){
		return userService.queryPage(pager);
	}
	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/user/queryByRole.do")
	@ResponseBody
	public Pager<User> queryByRole(Pager<User> pager){
//		pager.addParam_like(M.User.name,  name);
//		pager.addParam_like(M.User.loginName,  loginName);
//		pager.addParam(M.RoleUser.role.id,  role_id);
		return userService.queryByRole(pager);
	}
	
	@RequestMapping("/user/queryByPosition.do")
	@ResponseBody
	public Pager<User> queryByPosition(Pager<User> pager){
//		pager.addParam_like(M.User.name,  name);
//		pager.addParam_like(M.User.loginName,  loginName);
//		pager.addParam(M.PositionOrgUser.position.id,  position_id);
		return userService.queryByPosition(pager);
	}

	@RequestMapping("/user/queryAll.do")
	@ResponseBody
	public List<User> queryAll() {	
		List<User> useres=userService.queryAll();
		return useres;
	}
	

	@RequestMapping("/user/load.do")
	@ResponseBody
	public User load(String id) {
		return userService.get(id);
	}
	/**
	 * 把用户和角色建立关联，通过从用户中选择或者在职位中选择
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	@RequestMapping("/user/addToRole.do")
	@ResponseBody
	public String addToRole(String user_id,String role_id) {
		userService.addToRole(user_id,role_id);
		return "{success:true}";
	}
	@RequestMapping("/user/deleteFromRole.do")
	@ResponseBody
	public String deleteFromRole(String user_id,String role_id) {
		userService.deleteFromRole(user_id,role_id);
		return "{success:true}";
	}
//	/**
//	 * 把用户添加到职位上
//	 * @param user_id
//	 * @param role_id
//	 * @return
//	 */
//	@RequestMapping("/user/addToPosition.do")
//	@ResponseBody
//	public String addToPosition(String user_id,String position_id,String org_id) {
//		userService.addToPosition(user_id,position_id,org_id);
//		return "{success:true}";
//	}
//	@RequestMapping("/user/deleteFromPosition.do")
//	@ResponseBody
//	public String deleteFromPosition(String user_id,String position_id,String org_id) {
//		userService.deleteFromPosition(user_id,position_id,org_id);
//		return "{success:true}";
//	}
//	/**
//	 * 用于在角色，职位上新建用户的时候，同时新建用户，同时和角色，职位建立关系
//	 * @param user
//	 * @param role_id
//	 * @param position_id
//	 * @param org_id
//	 * @return
//	 */
//	@RequestMapping("/user/create.do")
//	@ResponseBody
//	public User create(@RequestBody User user,String role_id,String position_id,String org_id) {
//		if(StringUtils.hasText(position_id)){
//			userService.createByPosition(user,position_id,org_id);
//		} else if(StringUtils.hasText(role_id)){
//			userService.createByRole(user, role_id);
//		} else {
//			userService.create(user);
//		}
//		
//		return user;
//	}
	
	@RequestMapping("/user/update.do")
	//@ResponseBody
	public  User update(@RequestBody User user) {
		userService.update(user);
		return user;
	}
	
	@RequestMapping("/user/updatePwd.do")
	//@ResponseBody
	public String updatePwd(String password,String password_new) {
		userService.update(Cnd.update().set(M.User.pwd, password_new).andEquals(M.User.id, ShiroUtils.getUserId()).andEquals(M.User.pwd, password));
		return ShiroUtils.getUserId();
	}
	
//	@RequestMapping("/user/deleteById.do")
//	//@ResponseBody
//	public String deleteById(String id) {
//		userService.deleteById(id);
//		return id;
//	}
	
	@RequestMapping("/user/destroy.do")
	//@ResponseBody
	public User destroy(@RequestBody User user,String role_id) {
		userService.delete(user);
		return user;
	}
	

	@RequestMapping("/user/queryRoleByUser.do")
	@ResponseBody
	public List<RoleVO> queryRoleByUser(String user_id) {
		
		return userService.queryRoleByUser(user_id);
	}
	
	@RequestMapping("/user/queryMenuByUser.do")
	@ResponseBody
	public List<MenuVO> queryMenuByUser(String user_id) {
		
		return menuService.queryByUser(null, user_id);
	}
//	@RequestMapping("/user/queryOrgPositionByUser.do")
//	@ResponseBody
//	public List<PositionVO> queryOrgPositionByUser(String user_id) {
//		
//		return userService.queryOrgPositionByUser( user_id);
//	}
//	@RequestMapping("/user/queryStoreByUser.do")
//	@ResponseBody
//	public List<PositionOrgAccessVO> queryStoreByUser(String user_id) {
//		
//		return userService.queryStoreByUser( user_id);
//	}
//	@RequestMapping("/user/queryWorkunitByUser.do")
//	@ResponseBody
//	public List<PositionOrgAccessVO> queryWorkunitByUser(String user_id) {
//		
//		return userService.queryWorkunitByUser( user_id);
//	}
//	
//	@RequestMapping("/user/queryRepair_centreByUser.do")
//	@ResponseBody
//	public List<PositionOrgAccessVO> queryRepair_centreByUser(String user_id) {
//		
//		return userService.queryRepair_centreByUser( user_id);
//	}
	
}
