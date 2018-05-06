package com.wm.lejia.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.constant.Constants;
import com.wm.lejia.db.mapper.CityMapper;
import com.wm.lejia.db.mapper.DecorationPriceMapper;
import com.wm.lejia.db.mapper.HomeDetailMapper;
import com.wm.lejia.db.mapper.HomeMapper;
import com.wm.lejia.db.mapper.TotalPriceMapper;
import com.wm.lejia.db.mapper.UserPriceMapper;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.dto.SeaCityDTO;
import com.wm.lejia.pojo.dto.SeaPriceDTO;
import com.wm.lejia.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.DecorationPrice;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.UserPrice;
import com.wm.lejia.pojo.vo.CalculationPriceVO;
import com.wm.lejia.pojo.vo.PriceCityVO;
import com.wm.lejia.pojo.vo.PriceVO;
import com.wm.lejia.pojo.vo.TotalPriceVO;
import com.wm.lejia.service.PriceService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.utils.StringUtils;

@Service
public class PriceServiceImpl implements PriceService {

	private static Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

	@Autowired
	private HomeMapper homeMapper;

	@Autowired
	private HomeDetailMapper homeDetailMapper;
	
	@Autowired
	private DecorationPriceMapper decorationPriceMapper;

	@Autowired
	private TotalPriceMapper totalPriceMapper;
	
	@Autowired
	private UserPriceMapper userPriceMapper;
	
	@Autowired
	private CityMapper cityMapper;
	
	
	@Override
	public Home createHome(Home home) {
		int rows = 0;
		try {
			rows = homeMapper.insertSelective(home);
			log.info("PriceServiceImpl  createHome ====> rows : " + rows);
			if(rows > 0) {
				return home;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceServiceImpl  createHome ====> 插入数据出错",e);
		}
		return null;
	}

	@Override
	public List<HomeDetail> createHomeDetails(List<HomeDetail> details) {
		Date date = new Date();
		for (HomeDetail detail : details) {
			detail.setCreatedTime(date);
			if(detail.getDecorationId() == null) {
				detail.setDecorationId(0);
			}
			try {
				homeDetailMapper.insertSelective(detail);
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("PriceServiceImpl  createHomeDetails ====> 插入数据出错",e);
				return null;
			}
		}
		return details;
	}

	@Override
	public TotalPriceVO calculationPrice(CalculationPriceDTO dto) {
		// 砌墙数
		Integer addWallNum = dto.getAddWallNum();
		addWallNum = (addWallNum == null ? 12 : addWallNum);
		// 拆墙数
		Integer removeWallNum = dto.getRemoveWallNum();
		removeWallNum = (removeWallNum == null ? 0 : removeWallNum);
		// 阳台数
		Integer balconyNum = dto.getBalconyNum();
		balconyNum = (balconyNum == null ? 0 : balconyNum);
		// 卫生间数
		Integer toiletNum = dto.getToiletNum();
		toiletNum = (toiletNum == null ? 0 : toiletNum);
		// 厨房
		Integer kitchenNum = dto.getKitchenNum();
		kitchenNum = (kitchenNum == null ? 0 : kitchenNum);
		// 餐厅
		Integer restaurantNum = dto.getRestaurantNum();
		restaurantNum = (restaurantNum == null ? 0 : restaurantNum);
		// 客厅
		Integer livingRoomNum = dto.getLivingRoomNum();
		livingRoomNum = (livingRoomNum == null ? 0 : livingRoomNum);
		// 房间
		Integer roomNum = dto.getRoomNum();
		roomNum = (roomNum == null ? 0 : roomNum);
		// 面积
		Double areaNum = dto.getAreaNum();
		areaNum = (areaNum == null ? 0.00d : areaNum);
		// 算法 E26 未被引用
		// 算法 E27
		Integer zhaoPing = livingRoomNum * 2 + restaurantNum;
		// 算法 网站E32  墙面材质 算法
	 	Double qiang = new BigDecimal((double) (livingRoomNum * 2 + restaurantNum) / 3).setScale(2, RoundingMode.UP).doubleValue();
	 	// 算法 网站 E47  瓷砖镶钻 卫生间 1 *  toiletNum  
	 	//算法E50 + E51+ E52
	 	Integer homeId = dto.getHomeId();
	 	Integer kitchenSelectedNum = homeDetailMapper.countDetailNumByHome(homeId, Constants.CHU_FANG);
		kitchenSelectedNum = (kitchenSelectedNum == null ? 0 : kitchenSelectedNum);
		kitchenSelectedNum = kitchenSelectedNum * kitchenNum;
	 	// 省 市
		Integer provinceId = dto.getProvinceId();
		Integer cityId = dto.getCityId();
		List<HomeDetail> details = dto.getDetails();
		Map<String, Object> condition = new HashMap<>();
		condition.put("provinceId", provinceId);
		condition.put("cityId", cityId);
		List<DecorationPrice> prices = decorationPriceMapper.getDecorationPriceByCondition(condition);
		List<PriceVO> priceVOList = new ArrayList<>();
		Double sumPrice = 0.00d;
		Double unitSumPrice = 0.00d; 
		// 计算价格
		for (HomeDetail detail : details) {
			log.info("detail" + detail.toString());
			// 装修区域
			String homeDetailType = detail.getHomeDetailType();
			homeDetailType = (StringUtils.isEmptyStr(homeDetailType) ? "" : homeDetailType.trim());
			Integer decorationId = detail.getDecorationId();
			decorationId = (decorationId == null ? 0 : decorationId);
			for (DecorationPrice decorationPrice : prices) {
				String region = decorationPrice.getRegion().trim();
				Integer dPriceId = decorationPrice.getDecorationId();
				dPriceId = (dPriceId == null ? 0 : dPriceId);
				// 人工费(单价)
				Double laborUnitPrice = decorationPrice.getLaborUnitPrice();
				// 人工费测算价格
				Double laborCalculatePrice = decorationPrice.getLaborCalculatePrice();
				// 材料单价
				Double materialUnionPrice = decorationPrice.getMaterialUnionPrice();
				// 测算材料价格
				Double materialCalculatePrice = decorationPrice.getMaterialCalculatePrice();
				String word = decorationPrice.getDecorationWord();
				if (homeDetailType.equals(region) && decorationId.equals(dPriceId)) {
					if (homeDetailType.equals(Constants.QUAN_WU)) {
						Double price = 0.00d;
						Double unitPrice = 0.00d;
						String numStr = "";
						if (word.equals(Constants.CHAI_QIANG)) {
							price = removeWallNum * laborCalculatePrice + removeWallNum * materialCalculatePrice;
							unitPrice = removeWallNum * laborUnitPrice + removeWallNum * materialUnionPrice;
							numStr = removeWallNum.toString();
						} else if (word.equals(Constants.QI_QIANG)) {
							price = addWallNum * laborCalculatePrice + addWallNum * materialCalculatePrice;
							unitPrice = addWallNum * laborUnitPrice + addWallNum * materialUnionPrice;
							numStr = addWallNum.toString();
						} else if (word.equals(Constants.DA_KONG)) {
							price = 1 * laborCalculatePrice + 1 * materialCalculatePrice;
							unitPrice = 1 * laborUnitPrice + 1 * materialUnionPrice;
							numStr = "1";
						} else if (word.equals(Constants.YUN_FEI)) {
							price = 1 * laborCalculatePrice + 1 * materialCalculatePrice;
							unitPrice = 1 * laborUnitPrice + 1 * materialUnionPrice;
							numStr = "1";
						} else {
							price = areaNum * laborCalculatePrice + areaNum * materialCalculatePrice;
							unitPrice = areaNum * laborUnitPrice + areaNum * materialUnionPrice;
							numStr = areaNum.toString();
						}
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, numStr, price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
					// 过道算法 和 客厅一致
					if (homeDetailType.equals(Constants.KE_TING) || homeDetailType.equals(Constants.GUO_DAO)) {
						Double price = 0.00d;
						Double unitPrice = 0.00d;
						String numStr = "";
						if (dPriceId.equals(14)) { // 墙面平铺 
							price = qiang * laborCalculatePrice + qiang * materialCalculatePrice;
							unitPrice = qiang * laborUnitPrice + qiang * materialUnionPrice;
							numStr = qiang.toString();
						} else if (Constants.ZHAO_PING.equals(word)) {  // 找平
							price = zhaoPing * laborCalculatePrice + zhaoPing * materialCalculatePrice;
							unitPrice = zhaoPing * laborUnitPrice + zhaoPing * materialUnionPrice;
							numStr = zhaoPing.toString();
						} else if (Constants.XIANG_TIE.equals(word)) { // 客厅平铺
							price = 1 * laborCalculatePrice + 1 * materialCalculatePrice;
							unitPrice = 1 * laborUnitPrice + 1 * materialUnionPrice;
							numStr = "1";
						} else {
							price = livingRoomNum * laborCalculatePrice + livingRoomNum * materialCalculatePrice;
							unitPrice = livingRoomNum * laborUnitPrice + livingRoomNum * materialUnionPrice;
							numStr = livingRoomNum.toString();
						}
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, numStr, price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
					if (homeDetailType.equals(Constants.CAN_TING)) {
						Double price = 0.00d;
						Double unitPrice = 0.00d;
						String numStr = "";
						if (dPriceId.equals(14)) { // 墙面平铺  
							price = qiang * laborCalculatePrice + qiang * materialCalculatePrice;
							unitPrice = qiang * laborUnitPrice + qiang * materialUnionPrice;
							numStr = qiang.toString();
						} else if (Constants.ZHAO_PING.equals(word)) {  // 找平
							price = zhaoPing * laborCalculatePrice + zhaoPing * materialCalculatePrice;
							unitPrice = zhaoPing * laborUnitPrice + zhaoPing * materialUnionPrice;
							numStr = zhaoPing.toString();
						}  else if (Constants.XIANG_TIE.equals(word)) { // 瓷砖镶贴
							price = 1 * laborCalculatePrice + 1 * materialCalculatePrice;
							unitPrice = 1 * laborUnitPrice + 1 * materialUnionPrice;
							numStr = "1";
						}else{
							price = restaurantNum * laborCalculatePrice + restaurantNum * materialCalculatePrice;
							unitPrice = restaurantNum * laborUnitPrice + restaurantNum * materialUnionPrice;
							numStr = restaurantNum.toString();
						}
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, numStr, price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
					if (homeDetailType.equals(Constants.FANG_JIAN)) {
						Double price = roomNum * laborCalculatePrice + roomNum * materialCalculatePrice;
						Double unitPrice = restaurantNum * laborUnitPrice + restaurantNum * materialUnionPrice;
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, roomNum.toString(), price, unitPrice));
						sumPrice += price;
						continue;
					}
					if (homeDetailType.equals(Constants.WEI_SHEN_JIAN)) {
						Double price = toiletNum * laborCalculatePrice + toiletNum * materialCalculatePrice;
						Double unitPrice = toiletNum * laborUnitPrice + toiletNum * materialUnionPrice;
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, toiletNum.toString(), price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
					if (homeDetailType.equals(Constants.CHU_FANG)) {
						Double price = 0.00d;
						Double unitPrice = 0.00d;
						String numStr = "";
						if (dPriceId.equals(27)) {
							price = kitchenSelectedNum * laborCalculatePrice + kitchenSelectedNum * materialCalculatePrice;
							unitPrice = kitchenSelectedNum * laborUnitPrice + kitchenSelectedNum * materialUnionPrice;
							numStr = kitchenSelectedNum.toString();
						} else {
							price = kitchenNum * laborCalculatePrice + kitchenNum * materialCalculatePrice;
							unitPrice = kitchenNum * laborUnitPrice + kitchenNum * materialUnionPrice;
							numStr = kitchenNum.toString();
						}
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, numStr, price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
					if (homeDetailType.equals(Constants.YANG_TAI)) {
						Double price = balconyNum * laborCalculatePrice + balconyNum * materialCalculatePrice;
						Double unitPrice = balconyNum * laborUnitPrice + balconyNum * materialUnionPrice;
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, balconyNum.toString(), price, unitPrice));
						sumPrice += price;
						unitSumPrice += unitPrice;
						continue;
					}
				}
			}
		}
		log.info("sumPrice" + sumPrice);
		log.info("unitSumPrice" + unitSumPrice);
		TotalPriceVO vo = new TotalPriceVO();
		vo.setPriceItem(priceVOList);
		vo.setSumPrice(sumPrice);
		vo.setUnitSumPrice(unitSumPrice);
		vo.setCityId(cityId);
		vo.setProvinceId(provinceId);
		return vo;
	}
	
	private PriceVO createVO(DecorationPrice d,String region,String numStr,Double price,Double unitPrice) {
		PriceVO vo = new PriceVO();	
		vo.setDecorationName(d.getDecorationName());
		vo.setDecorationWord(d.getDecorationWord());
		vo.setNumStr(numStr);
		vo.setRegion(region);
		vo.setUnit(d.getUnit());
		vo.setPrice(price);
		vo.setUnitPrice(unitPrice);
		return vo;
	}

	@Override
	public Home getHomeById(Integer homeId) {
		return homeMapper.selectByPrimaryKey(homeId);
	}

	@Override
	public TotalPrice createTotalPrice(TotalPrice totalPrice) {
		try {
			int rows = totalPriceMapper.insertSelective(totalPrice);
			if (rows > 0) {
				return totalPrice;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceServiceImpl   createTotalPrice ===> 插入数据出错", e);
		}
		return null;
	}

	@Override
	public Boolean createPriceItem(List<UserPrice> list) {
		try {
			Integer rows = userPriceMapper.insertBatchPrice(list);
			if(rows != null && rows > 0) {
				return true;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceService   createPriceItem ===> 插入数据出错", e);
		}
		return false;
	}

	@Override
	public List<TotalPrice> listTotalPrice(Integer userId) {
		try {
			List<TotalPrice> list = totalPriceMapper.listTotalPriceByUserId(userId);
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceService   listTotalPrice 查询出错",e);
		}
		return null;
	}

	@Override
	public List<UserPrice> listUserPrice(Integer totalPriceId) {
		try {
			List<UserPrice> list = userPriceMapper.listPriceByTotalPriceId(totalPriceId);
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceService   listUserPrice 查询出错",e);
		}
		return null;
	}

	
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
		String describe = dto.getDescribe();
		if(!StringUtils.isEmptyStr(describe)) {
			City city = new City();
			city.setDescribe(describe);
			city.setUpdatedBy(updatedBy);
			city.setUpdatedTime(new Date());
			city.setCityId(cityId);
			cityMapper.updateByPrimaryKeySelective(city);
		}
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

	@Override
	public CalculationPriceDTO getCalculationPriceById(Integer homeId) {
		return homeMapper.selectById(homeId);
	}
}
