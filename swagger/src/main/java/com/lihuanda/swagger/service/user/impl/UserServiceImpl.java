package com.lihuanda.swagger.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lihuanda.swagger.dto.user.UserDTO;
import com.lihuanda.swagger.entity.user.User;
import com.lihuanda.swagger.mapper.user.UserMapper;
import com.lihuanda.swagger.service.user.UserService;
import com.lihuanda.swagger.vo.user.UserVO;

/**
 * 服务实现
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
		
	/*
	 * 
	 * 通过用户编号，查找用户信息
	 * 
	 * @see com.ly.cloud.service.UserService#findById(java.lang.String)
	 */
	public UserVO findById(String userId) {
		User userEntity = baseMapper.selectById(userId);
		if(userEntity == null) return null;
		UserVO vo = new UserVO(userEntity.getUserId(), userEntity.getUserName(), userEntity.getFullName(), userEntity.getEmail(), userEntity.getMobile(), userEntity.getStatus());
		return vo;
	}

	/*
	 * 
	 * 通过查询条件，查找用户信息
	 * 
	 * @see com.ly.cloud.service.UserService#queryList(com.ly.cloud.dto.UserDTO)
	 */
	public Page<UserVO> query(Page page, UserDTO param) {
		//setPagination 的作用和startPage的作用是一样的，都是传入当前页数和每页的大小
        PageHelper.setPagination(page);
        page.setRecords(baseMapper.query(param));
        page.setTotal(PageHelper.getTotal());//获取总数并释放资源PageHelper.freeTotal()  也可以 PageHelper.getTotal()
        return page;        		
	}

	/**
	 * Title: insert
	 * Description: 
	 * 如果有多条插入或者更新语句，需要加事务 、 单条语句执行不需要加事务
	 * 事务即是在类名上方加个Transactional 注解，声明该类的方法需要事务
	 * Created On: 2018年1月23日
	 * <p>
	 * @param param
	 * @return  UserServiceImpl
	 * @see com.lihuanda.swagger.service.user.UserService#insert(com.lihuanda.swagger.dto.user.UserDTO)
	 */
	public boolean insert(UserDTO param) {
		User user = new User(param.getUserId(), param.getUserName(), param.getFullName(), param.getEmail(), param.getMobile(), param.getStatus());
		return baseMapper.insert(user)>0;
	}
	
	/*
	 * 
	 * 通过用户编号，更新用户信息
	 * 
	 * @see com.ly.cloud.service.UserService#update(com.ly.cloud.dto.UserDTO)
	 */
	public boolean update(UserDTO param) {
		User user = new User(param.getUserId(), param.getUserName(), param.getFullName(), param.getEmail(), param.getMobile(), param.getStatus());
		return baseMapper.updateById(user)>0;
	}

	/*
	 * 
	 * 通过用户编号，删除用户信息
	 * 
	 * @see com.ly.cloud.service.UserService#delete(java.lang.String)
	 */
	public boolean delete(String userId) {
		return baseMapper.deleteById(userId)>0;
	}

	/*
	 * 
	 * 通过查询条件，删除用户信息
	 * 
	 * @see com.ly.cloud.service.UserService#deleteMulti(com.ly.cloud.dto.UserDTO)
	 */
	@Override
	public boolean deleteMulti(List<String> params) {
		for (int i=0;i<params.size();i++){
			baseMapper.deleteById(params.get(i));
		}
		return true;
	}

}
