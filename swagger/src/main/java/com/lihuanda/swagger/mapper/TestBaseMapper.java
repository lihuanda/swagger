package com.lihuanda.swagger.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lihuanda.swagger.entity.user.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestBaseMapper extends BaseMapper<User> {

    // BaseMapper 封装了很多通用的方法  ， 也可以像之前一样 对应Mapper.xml文件
    @Select("selectUserList")   //对应xml文件的id ， 在mybatis中是默认同名对应
    List<Map> selectUserList();  //mapper xml 文件必须 是在mapper目录的下一级目录中 否则报错  （按照配置文件中的配置来）

}
