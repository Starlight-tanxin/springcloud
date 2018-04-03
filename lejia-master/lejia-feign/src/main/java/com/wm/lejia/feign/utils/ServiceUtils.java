package com.wm.lejia.feign.utils;

import java.util.List;

import com.wm.lejia.constant.Constants;
import com.wm.lejia.feign.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.feign.pojo.dto.HomeDetailDTO;
import com.wm.lejia.utils.StringUtils;

public class ServiceUtils {
	
	/**
	 * 根据前段选择的内容格式化出不同的homeid(目前不灵活)
	 * @param projectName
	 * @param homeDetailType
	 */
	public static Integer formatHomeId(String projectName,String homeDetailType) {
		switch (projectName) {
		case Constants.PING_PU :
			switch (homeDetailType) {
				case Constants.CHU_FANG : return 31;
				case Constants.WEI_SHEN_JIAN : return 31;
				case Constants.KE_TING : return 11;
				case Constants.CAN_TING : return 11;
				case Constants.FANG_JIAN : return 11;
				case Constants.GUO_DAO : return 11;
				case Constants.YANG_TAI : return 31;
				case Constants.KE_CAN_TING : return 11;
			}
			break;
		case Constants.BIAN_XIAN :
			switch (homeDetailType) {
				case Constants.CHU_FANG : return 29;
				case Constants.WEI_SHEN_JIAN : return 29;
				case Constants.KE_TING : return 12;
				case Constants.CAN_TING : return 12;
				case Constants.FANG_JIAN : return 12;
				case Constants.GUO_DAO : return 12;
				case Constants.KE_CAN_TING : return 12;
			}
			break;
		case Constants.LING_XING :
			switch (homeDetailType) {
				case Constants.CHU_FANG : return 30;
				case Constants.WEI_SHEN_JIAN : return 30;
				case Constants.KE_TING : return 13;
				case Constants.CAN_TING : return 13;
				case Constants.FANG_JIAN : return 13;
				case Constants.GUO_DAO : return 13;
				case Constants.KE_CAN_TING : return 13;
			}
			break;
		case Constants.QIANG_CI_ZHUANG : 
			switch (homeDetailType) {
				case Constants.KE_CAN_TING : return 14;
				case Constants.GUO_DAO : return 14;
				case Constants.KE_TING : return 14;
				case Constants.CAN_TING : return 14; 
			}
			break;
		case Constants.DI_FU_HE : 
			switch (homeDetailType) {
				case Constants.KE_CAN_TING : return 16;
				case Constants.GUO_DAO : return 16;
				case Constants.KE_TING : return 16;
				case Constants.CAN_TING : return 16; 
				case Constants.FANG_JIAN : return 16;
			}
			break;
		default:
			break;
		}
		return null;
	}
	
	/**
	 * 根据所填的数据生成 部分装修详情
	 * @param homeId id
	 * @param createdBy 创建人
	 * @param details 装修详情
	 * @param calculationPriceDTO 装修对象
	 * @return
	 */
	public static List<HomeDetailDTO> createHomeDetail(Integer homeId,Integer createdBy,List<HomeDetailDTO> details, CalculationPriceDTO calculationPriceDTO) {
		Integer removeWallNum = calculationPriceDTO.getRemoveWallNum(); 
		Integer addWallNum = calculationPriceDTO.getAddWallNum();
		// 餐厅
		Integer restaurantNum = calculationPriceDTO.getRestaurantNum();
		// 客厅
		Integer livingRoomNum = calculationPriceDTO.getLivingRoomNum();
		// 房间
		Integer roomNum = calculationPriceDTO.getRoomNum();
		// 卫生间数
		Integer toiletNum = calculationPriceDTO.getToiletNum();
		// 厨房
		Integer kitchenNum = calculationPriceDTO.getKitchenNum();
		if (removeWallNum != null && !removeWallNum.equals(0)) {
			HomeDetailDTO chaQiang = new HomeDetailDTO(homeId, Constants.QUAN_WU, null, 2, createdBy);
			details.add(chaQiang);
		}
		if (addWallNum != null && !addWallNum.equals(0)) {
			HomeDetailDTO qiQiang = new HomeDetailDTO(homeId, Constants.QUAN_WU, null, 3, createdBy);
			details.add(qiQiang);
		}
		if (restaurantNum != null && !restaurantNum.equals(0)) {
			HomeDetailDTO canTing = new HomeDetailDTO(homeId, Constants.CAN_TING, null, 10, createdBy);
			HomeDetailDTO dian = new HomeDetailDTO(homeId, Constants.CAN_TING, null, 6, createdBy);
			details.add(canTing);
			details.add(dian);
		}
		if (livingRoomNum != null && !livingRoomNum.equals(0)) {
			HomeDetailDTO keTing = new HomeDetailDTO(homeId, Constants.KE_TING, null, 10, createdBy);
			HomeDetailDTO guoDao = new HomeDetailDTO(homeId, Constants.GUO_DAO, null, 10, createdBy);
			HomeDetailDTO dian = new HomeDetailDTO(homeId, Constants.KE_TING, null, 6, createdBy);
			details.add(keTing);
			details.add(guoDao);
			details.add(dian);
		}
		if (roomNum != null && !roomNum.equals(0)) {
			HomeDetailDTO fangJian = new HomeDetailDTO(homeId, Constants.FANG_JIAN, null, 10, createdBy);
			HomeDetailDTO dian = new HomeDetailDTO(homeId, Constants.FANG_JIAN, null, 6, createdBy);
			details.add(fangJian);
			details.add(dian);
		}
		if (toiletNum != null && !toiletNum.equals(0)) {
			HomeDetailDTO fangShui = new HomeDetailDTO(homeId, Constants.WEI_SHEN_JIAN, null, 28, createdBy);
			HomeDetailDTO dian = new HomeDetailDTO(homeId, Constants.WEI_SHEN_JIAN, null, 27, createdBy);
			details.add(fangShui);
			details.add(dian);
		}
		if (kitchenNum != null && !kitchenNum.equals(0)) {
			HomeDetailDTO dian = new HomeDetailDTO(homeId, Constants.CHU_FANG, null, 27, createdBy);
			details.add(dian);
		}
		HomeDetailDTO kaiCao = new HomeDetailDTO(homeId, Constants.QUAN_WU, null, 1, createdBy);
		details.add(kaiCao);
		for (int i = (details.size()-1) ; i >= 0 ; i --) {
			HomeDetailDTO detail = details.get(i);
			// 选项名字
			String projectName = detail.getInfo();
			projectName = (StringUtils.isEmptyStr(projectName) ? "" : projectName.trim());
			// 区域
			String homeDetailType = detail.getHomeDetailType();
			homeDetailType = (StringUtils.isEmptyStr(homeDetailType) ? "" : homeDetailType.trim());
			if (Constants.KE_CAN_TING.equals(homeDetailType)) {
				HomeDetailDTO guoDao = new HomeDetailDTO(homeId, Constants.GUO_DAO, projectName, null, createdBy);
				HomeDetailDTO keTing = new HomeDetailDTO(homeId, Constants.KE_TING, projectName, null, createdBy);
				HomeDetailDTO canTing = new HomeDetailDTO(homeId, Constants.CAN_TING, projectName, null, createdBy);
				details.add(keTing);
				details.add(canTing);
				details.add(guoDao);
			}
		}
		return details;
	}
}