package com.lihuanda.swagger.service.ai;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lihuanda.swagger.dto.ai.MesDTO;
import com.lihuanda.swagger.entity.ai.Mes;
import com.lihuanda.swagger.vo.ai.MesVO;

public interface MesService extends IService<Mes>
{
	/**
	 * 分页查询ai消息
	 * @param page
	 * @param param
	 * @return
	 */
	public Page<MesVO> query(Page page, MesDTO param);
	
	public boolean insert(MesDTO param);
	
	public boolean update(MesDTO param);

	
}
