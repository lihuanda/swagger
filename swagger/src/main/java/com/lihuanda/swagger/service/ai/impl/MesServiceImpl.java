package com.lihuanda.swagger.service.ai.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lihuanda.swagger.dto.ai.MesDTO;
import com.lihuanda.swagger.entity.ai.Mes;
import com.lihuanda.swagger.mapper.ai.MesMapper;
import com.lihuanda.swagger.service.ai.MesService;
import com.lihuanda.swagger.vo.ai.MesVO;

@Service
public class MesServiceImpl extends ServiceImpl<MesMapper, Mes> implements MesService
{

	@Override
	public Page<MesVO> query(Page page, MesDTO param)
	{
		PageHelper.setPagination(page);
		page.setRecords(baseMapper.query(param));
		page.setTotal(PageHelper.freeTotal());
		return page;
	}

	@Override
	public boolean insert(MesDTO param)
	{
		Mes mes = new Mes(param.getId(), param.getContent(), param.getVoiceFileName(), param.getRequestTime(), param.getIsRecognize(), param.getStatus());
		return baseMapper.insert(mes) > 0;
	}

	@Override
	public boolean update(MesDTO param)
	{
		Mes mes = new Mes(param.getId(), param.getContent(), param.getVoiceFileName(), param.getRequestTime(), param.getIsRecognize(), param.getStatus());
		return baseMapper.updateById(mes) > 0;
	}

}
