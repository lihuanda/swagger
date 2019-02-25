package com.lihuanda.swagger.mapper.ai;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lihuanda.swagger.dto.ai.MesDTO;
import com.lihuanda.swagger.entity.ai.Mes;
import com.lihuanda.swagger.vo.ai.MesVO;

public interface MesMapper extends BaseMapper<Mes>
{
	public List<MesVO> query(MesDTO param);
}
