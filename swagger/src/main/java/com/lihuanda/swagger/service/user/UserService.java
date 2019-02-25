package com.lihuanda.swagger.service.user;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lihuanda.swagger.dto.user.UserDTO;
import com.lihuanda.swagger.entity.user.User;
import com.lihuanda.swagger.vo.user.UserVO;

/** 
 * Class Name: UserService  
 * Description: 
 * 服务接口
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */  
public interface UserService extends IService<User>{
	
	/**
	 * 
	 * 分页查询用户信息
	 * 
	 * @param page
	 * @param param
	 * @return
	 */
	public Page<UserVO> query(Page page, UserDTO param);

	/**
	 * 
	 * 
	 * 批量删除用户信息
	 * 
	 * @param params
	 * @return
	 */	
	public boolean deleteMulti(List<String> params);

	public boolean insert(UserDTO param);

	public boolean update(UserDTO param);

}
