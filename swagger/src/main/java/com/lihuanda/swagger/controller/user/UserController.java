package com.lihuanda.swagger.controller.user;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lihuanda.swagger.entity.user.User;
import com.lihuanda.swagger.mapper.TestBaseMapper;
import com.lihuanda.swagger.util.JsonResult;
import io.swagger.annotations.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.lihuanda.swagger.config.R;
import com.lihuanda.swagger.dto.user.UserDTO;
import com.lihuanda.swagger.service.user.UserService;
import com.lihuanda.swagger.vo.user.UserVO;


/** 
 * Class Name: UserController  
 * Description: 
 * 控制器
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */  
@RestController
@RequestMapping("/users")
@Api(value="user_resource", tags="user_resource")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private TestBaseMapper baseMapperTest;
	

	/*@ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = false, value = "用户编号"),
        @ApiImplicitParam(paramType = "query", name = "userName", dataType = "string", required = false, value = "用户账号，可以模糊查找"),  
        @ApiImplicitParam(paramType = "query", name = "fullName", dataType = "string", required = false, value = "用户姓名，可以模糊查找"),  
        @ApiImplicitParam(paramType = "query", name = "email", dataType = "string", required = false, value = "电子邮件，可以模糊查找"),
        @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "string", required = false, value = "联系方式，可以模糊查找"),
        @ApiImplicitParam(paramType = "query", name = "status", dataType = "string", required = false, value = "用户状态，0：停用、1：正常")
	})  */
	@RequestMapping(value = "query" , method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="查询用户信息", notes="通过用户输入的条件，查询满足条件的用户信息列表", httpMethod = "POST")
	public JsonResult<Page<UserVO>> query(@RequestBody @ApiParam(name = "UserDTO",value="UserDTO") UserDTO param) {
		try {
			int i = 1;
			Page page=new Page(1, 5);
			if (param.getPageNum()!=null){
				page.setCurrent(param.getPageNum());
			}
			Page<UserVO> users=userService.query(page, param);
			if (users!=null) {				
				return JsonResult.success(users);
			}else {
				return JsonResult.failure(R.queryFailure);
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.queryFailure);
		}
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public JsonResult<String> helloOne() {		
		return JsonResult.success("hello world");
	}
	
	@ApiOperation(value="用户登录", notes="通过账号、密码登录系统", httpMethod = "POST")  
	@ApiImplicitParams({  
        @ApiImplicitParam(paramType = "query", name = "userName", dataType = "string", required = true, value = "用户账号"),  
        @ApiImplicitParam(paramType = "query", name = "password", dataType = "string", required = true, value = "用户密码")
    })  
	@RequestMapping(value="/login", method=RequestMethod.POST)
    //swagger 通过单元格进行一个一个传递参数，或者通过json进行对象传值
	public JsonResult login(String userName,String password,@RequestBody(required = false)  UserDTO param) {
		try {
			Pagination page = new Pagination();
			page.setSize(10);
			page.setCurrent(1);
			Wrapper<User> wra = new Wrapper<User>() {
				@Override
				public String getSqlSegment() {
					return null;
				}
			};
		//	List  userList = baseMapperTest.selectList(wra);
			List  userList = baseMapperTest.selectUserList();
			return JsonResult.success();			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.insertFailure);
		}
	}
	
	@ApiOperation(value="用户登出", notes="用户登出系统", httpMethod = "POST")
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public JsonResult logout() throws Exception{

		throw new Exception("ddd");	
	}
	
	@ApiOperation(value="添加用户信息", notes="通过用户编号，查询满足条件的用户信息", httpMethod = "POST")  
	@ApiImplicitParams({  
        @ApiImplicitParam(paramType = "query", name = "userName", dataType = "string", required = true, value = "用户账号"),  
        @ApiImplicitParam(paramType = "query", name = "fullName", dataType = "string", required = true, value = "用户姓名"),  
        @ApiImplicitParam(paramType = "query", name = "email", dataType = "string", required = true, value = "电子邮件"),    
        @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "string", required = true, value = "联系方式")
    })  
	@RequestMapping(method=RequestMethod.POST)
	public JsonResult insert(@RequestBody  UserDTO param) {
		try {
			if (userService.insert(param)){
				return JsonResult.success();
			}else{
				return JsonResult.failure();
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.insertFailure);
		}
	}
		
	@ApiOperation(value="更新用户信息", notes="通过用户编号，更新用户信息", httpMethod = "POST")  
	@ApiImplicitParams({  
        @ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = true, value = "用户编号"),
        @ApiImplicitParam(paramType = "query", name = "userName", dataType = "string", required = true, value = "用户账号"),  
        @ApiImplicitParam(paramType = "query", name = "fullName", dataType = "string", required = true, value = "用户姓名"),  
        @ApiImplicitParam(paramType = "query", name = "email", dataType = "string", required = true, value = "电子邮件"),    
        @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "string", required = true, value = "联系方式")
    })  
	@RequestMapping(value="/{userId}/update", method=RequestMethod.POST)

	public JsonResult update(@RequestBody  UserDTO param) {
		try {
			if (userService.update(param)){
				return JsonResult.success();
			}else{
				return JsonResult.failure();
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.updateFailure);
		}
	}
	
	@ApiOperation(value="删除用户信息", notes="通过用户编号，删除用户信息", httpMethod = "POST")  
	@ApiImplicitParams({  
        @ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = true, value = "用户编号")
    })  
	@RequestMapping(value="/{userId}/delete", method=RequestMethod.POST)

	public JsonResult delete(@PathVariable("userId") String userId) {
		try {
			if (userService.deleteById(userId)){;
				return JsonResult.success();
			}else{
				return JsonResult.failure();
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.deleteFailure);
		}
	}
	
	@ApiOperation(value="删除用户信息", notes="通过条件，删除用户信息", httpMethod =    "POST")
	@ApiImplicitParams({  
		 @ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = false, value = "用户编号"),  
	     @ApiImplicitParam(paramType = "query", name = "userName", dataType = "string", required = false, value = "用户账号，可以模糊查找"),  
	     @ApiImplicitParam(paramType = "query", name = "fullName", dataType = "string", required = false, value = "用户姓名，可以模糊查找"),  
	     @ApiImplicitParam(paramType = "query", name = "email", dataType = "string", required = false, value = "电子邮件，可以模糊查找"),    
	     @ApiImplicitParam(paramType = "query", name = "mobile", dataType = "string", required = false, value = "联系方式，可以模糊查找"),
	     @ApiImplicitParam(paramType = "query", name = "status", dataType = "string", required = false, value = "用户状态，0：停用、1：正常")
    })  
	@RequestMapping(value="/delete", method=RequestMethod.POST)

	public JsonResult deleteMulti(@RequestBody  List<String> params) {
		try {
			if (userService.deleteMulti(params)){
				return JsonResult.success();	
			}else{
				return JsonResult.failure();				
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.deleteFailure);
		}
	}

	//根据用户编号获取用户信息
	@ApiOperation(value = "查询用户信息",notes="通过用户编号查询用户编号",httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query",name = "yhzh",dataType = "string",required = false,value="用户账号")
	})
	@RequestMapping(value = "/findUserByYHZH" ,method=RequestMethod.POST)
	public JsonResult FindUserByYHZH(String  yhzh){
		int i = 1;
		Page page=new Page(1, 5);
		UserDTO param = new UserDTO();
		param.setUserName(yhzh);
		Page<UserVO> users=userService.query(page, param);
		if (users!=null) {
			return JsonResult.success(users);
		}else {
			return JsonResult.failure(R.queryFailure);
		}
	}



}
