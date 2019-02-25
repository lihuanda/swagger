package com.lihuanda.swagger.mapper.user;

/** 
 * Class Name: UserMapper  
 * Description: 数据操作，用来操作用户数据
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */  

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lihuanda.swagger.dto.user.UserDTO;
import com.lihuanda.swagger.entity.user.User;
import com.lihuanda.swagger.vo.user.UserVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{
	
		
	/**
	 * 
	 * 通过参数UserDTO, 查询用户数据
	 * 
	 * @param param
	 * @return
	 */
	public List<UserVO> query(@Param("param") UserDTO param);
	

}
