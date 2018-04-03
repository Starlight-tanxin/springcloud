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

import com.wm.lejia.constant.Constants;
import com.wm.lejia.db.mapper.DecorationPriceMapper;
import com.wm.lejia.db.mapper.HomeDetailMapper;
import com.wm.lejia.db.mapper.HomeMapper;
import com.wm.lejia.db.mapper.TotalPriceMapper;
import com.wm.lejia.db.mapper.UserPriceMapper;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.entity.DecorationPrice;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.UserPrice;
import com.wm.lejia.pojo.vo.PriceVO;
import com.wm.lejia.pojo.vo.TotalPriceVO;
import com.wm.lejia.service.PriceService;
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

//	@Autowired
//	private DecorationMapper decorationMapper;
	
	@Autowired
	private TotalPriceMapper totalPriceMapper;
	
	@Autowired
	private UserPriceMapper userPriceMapper;
	
	
	@Override
	public Home createHome(Home home) {
		int rows = 0;
		try {
			rows = homeMapper.insertSelective(home);
			log.info("PriceServiceImpl  createHome ====> rows : " + rows);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return home;
	}

	@Override
	public List<HomeDetail> createHomeDetails(List<HomeDetail> details) {
		Date date = new Date();
		for (HomeDetail detail : details) {
			detail.setCreatedTime(date);
			if(detail.getDecorationId() == null) {
				detail.setDecorationId(0);
			}
			homeDetailMapper.insertSelective(detail);
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
	 	Integer qiang = (livingRoomNum * 2 + restaurantNum) / 3;
	 	if(((livingRoomNum * 2 + restaurantNum) % 3) > 0) {
	 		qiang += 1;
	 	}
	 	// 算法 网站 E47  瓷砖镶钻 卫生间 1 *  toiletNum
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
//		Set<Integer> deIdSet = new HashSet<>();
//		for (HomeDetail detail : details) {
//			deIdSet.add((detail.getDecorationId() == null ? 0 : detail.getDecorationId()));
//		}
//		List<Decoration> decorationList = decorationMapper.listDecorationWordAndNameAndUnint(deIdSet);
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
//				Decoration d = decorationMapper.getDecorationWordAndNameAndUnint(decorationId);
//				if (d == null) {
//					continue;
//				}
				String word = decorationPrice.getDecorationWord();
				word = (StringUtils.isEmptyStr(word) ? "" : word);
				//Integer deId = d.getDecorationId();
				//deId = (deId == null ? 0 : deId);
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
						} else{
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
				/*	if (homeDetailType.equals(Constants.GUO_DAO)) {
						Double price = 0.00d;
						String numStr = "";
						if (d.getDecorationId().equals(14)) { // 墙面平铺 
							price = qiang * laborCalculatePrice + qiang * materialCalculatePrice;
							numStr = qiang.toString();
						} else if (Constants.ZHAO_PING.equals(word)) {  // 找平
							price = zhaoPing * laborCalculatePrice + zhaoPing * materialCalculatePrice;
							numStr = zhaoPing.toString();
						} else{
							price = livingRoomNum * laborCalculatePrice + livingRoomNum * materialCalculatePrice;
							numStr = livingRoomNum.toString();
						}
						priceVOList.add(createVO(d, region, numStr, price));
						sumPrice += price;
						continue;
					}*/
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
						} else{
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
						Double price = kitchenNum * laborCalculatePrice + kitchenNum * materialCalculatePrice;
						Double unitPrice = kitchenNum * laborUnitPrice + kitchenNum * materialUnionPrice;
						price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
						unitPrice = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
						priceVOList.add(createVO(decorationPrice, region, kitchenNum.toString(), price, unitPrice));
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
			log.error("插入出错", e);
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
			log.error("PriceService createPriceItem 插入出错", e);
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
			log.error("PriceService listTotalPrice 查询出错",e);
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
			log.error("PriceService listUserPrice 查询出错",e);
		}
		return null;
	}

}
