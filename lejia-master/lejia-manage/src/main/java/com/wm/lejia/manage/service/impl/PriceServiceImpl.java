package com.wm.lejia.manage.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaCityDTO;
import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.common.pojo.entity.DecorationPrice;
import com.wm.lejia.common.pojo.vo.CalculationPriceVO;
import com.wm.lejia.common.pojo.vo.PriceCityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.manage.db.mapper.CityMapper;
import com.wm.lejia.manage.db.mapper.DecorationPriceMapper;
import com.wm.lejia.manage.db.mapper.TotalPriceMapper;
import com.wm.lejia.manage.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	private static Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private DecorationPriceMapper decorationPriceMapper;

	@Autowired
	private TotalPriceMapper totalPriceMapper;
	
	@Override
	public Result<List<PriceCityVO>> listCity(SeaCityDTO dto) {
		try {
			List<PriceCityVO> list = cityMapper.listPriceCity(dto);
			return new Result<>(list);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("CityPriceServiceImpl   listCity ===> 查询数据出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public Result<?> updatePriceSetting(UpdatePriceDTO dto) throws Exception {
		Integer provinceId = dto.getProvinceId();
		Integer cityId = dto.getCityId();
		Integer updatedBy = dto.getUpdatedBy();
		// 软删除
		decorationPriceMapper.updateIsDeletedByCity(provinceId, cityId, updatedBy);
		for (DecorationPrice dp : dto.getPrices()) {
			dp.setCityId(cityId);
			dp.setProvinceId(provinceId);
			dp.setCreatedBy(updatedBy);
			String region = dp.getRegion();
			region = (StringUtils.isEmptyStr(region) ? "" : region.trim());
			dp.setRegion(region);
			Double laborUnitPrice = dp.getLaborUnitPrice();
			laborUnitPrice = (laborUnitPrice == null ? 0.00d : laborUnitPrice);
			Double materialUnionPrice = dp.getMaterialUnionPrice();
			materialUnionPrice = (materialUnionPrice == null ? 0.00d : materialUnionPrice);
			double laborCalculatePrice = new BigDecimal(laborUnitPrice * 1.3).setScale(2, RoundingMode.HALF_UP).doubleValue();
			double materialCalculatePrice = new BigDecimal(materialUnionPrice * 1.3).setScale(2, RoundingMode.HALF_UP).doubleValue();
			dp.setLaborCalculatePrice(laborCalculatePrice);
			dp.setLaborUnitPrice(laborUnitPrice);
			dp.setMaterialCalculatePrice(materialCalculatePrice);
			dp.setMaterialUnionPrice(materialUnionPrice);
		}
		Integer rows = decorationPriceMapper.insertBatchPrice(dto.getPrices());
		if(rows == null || rows <= 0) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<>();
	}

	@Override
	public Result<List<DecorationPrice>> listPriceByCity(Integer provinceId, Integer cityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		try {
			List<DecorationPrice> list = decorationPriceMapper.getDecorationPriceByCondition(map);
			return new Result<List<DecorationPrice>>(list);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("CityPriceServiceImpl   listPriceByCity",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public Result<PageInfo<CalculationPriceVO>> listCalculationPrice(SeaPriceDTO dto) {
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<CalculationPriceVO> list = totalPriceMapper.listCalculationPrice(dto);
			PageInfo<CalculationPriceVO> pageInfo = new PageInfo<>(list);
			return new Result<PageInfo<CalculationPriceVO>>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceServiceImpl   listCalculationPrice ===> 查询出错 ",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

}
