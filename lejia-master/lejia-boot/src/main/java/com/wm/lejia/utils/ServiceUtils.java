package com.wm.lejia.utils;

import java.util.List;

import com.wm.lejia.constant.Constants;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.entity.HomeDetail;

public class ServiceUtils {

	/**
	 * 根据前段选择的内容格式化出不同的homeid(目前不灵活)
	 * 
	 * @param projectName
	 * @param homeDetailType
	 */
	public static Integer formatHomeId(String projectName, String homeDetailType) {
		switch (projectName) {
		case Constants.PING_PU:
			switch (homeDetailType) {
			case Constants.CHU_FANG:
				return 31;
			case Constants.WEI_SHEN_JIAN:
				return 31;
			case Constants.KE_TING:
				return 11;
			case Constants.CAN_TING:
				return 11;
			case Constants.FANG_JIAN:
				return 11;
			case Constants.GUO_DAO:
				return 11;
			case Constants.KE_CAN_TING:
				return 11;
			}
			break;
		case Constants.BIAN_XIAN:
			switch (homeDetailType) {
			case Constants.CHU_FANG:
				return 29;
			case Constants.WEI_SHEN_JIAN:
				return 29;
			case Constants.KE_TING:
				return 12;
			case Constants.CAN_TING:
				return 12;
			case Constants.FANG_JIAN:
				return 12;
			case Constants.GUO_DAO:
				return 12;
			case Constants.KE_CAN_TING:
				return 12;
			}
			break;
		case Constants.LING_XING:
			switch (homeDetailType) {
			case Constants.CHU_FANG:
				return 30;
			case Constants.WEI_SHEN_JIAN:
				return 30;
			case Constants.KE_TING:
				return 13;
			case Constants.CAN_TING:
				return 13;
			// case Constants.FANG_JIAN : return 13;
			case Constants.GUO_DAO:
				return 13;
			case Constants.KE_CAN_TING:
				return 13;
			}
			break;
		case Constants.QIANG_CI_ZHUANG:
			switch (homeDetailType) {
			case Constants.KE_CAN_TING:
				return 14;
			case Constants.GUO_DAO:
				return 14;
			case Constants.KE_TING:
				return 14;
			case Constants.CAN_TING:
				return 14;
			}
			break;
		case Constants.DI_FU_HE:
			switch (homeDetailType) {
			case Constants.KE_CAN_TING:
				return 16;
			case Constants.GUO_DAO:
				return 16;
			case Constants.KE_TING:
				return 16;
			case Constants.CAN_TING:
				return 16;
			case Constants.FANG_JIAN:
				return 16;
			}
			break;
		default:
			break;
		}
		return null;
	}

	/**
	 * 根据所填的数据生成 部分装修详情
	 * 
	 * @param homeId
	 *            id
	 * @param createdBy
	 *            创建人
	 * @param details
	 *            装修详情
	 * @param calculationPriceDTO
	 *            装修对象
	 * @return
	 */
	public static List<HomeDetail> createHomeDetail(Integer homeId, Integer createdBy, List<HomeDetail> details,
			CalculationPriceDTO calculationPriceDTO) {
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
		// 阳台数
		Integer balconyNum = calculationPriceDTO.getBalconyNum();

		if (removeWallNum != null && removeWallNum > 0) {
			HomeDetail chaQiang = new HomeDetail(homeId, Constants.QUAN_WU, null, 2, createdBy);
			details.add(chaQiang);
		}
		if (addWallNum != null && addWallNum > 0) {
			HomeDetail qiQiang = new HomeDetail(homeId, Constants.QUAN_WU, null, 3, createdBy);
			details.add(qiQiang);
		}
		if (restaurantNum != null && restaurantNum > 0) {
			HomeDetail canTing = new HomeDetail(homeId, Constants.CAN_TING, null, 10, createdBy);
			HomeDetail dian = new HomeDetail(homeId, Constants.CAN_TING, null, 6, createdBy);
			details.add(canTing);
			details.add(dian);
		}
		if (livingRoomNum != null && livingRoomNum > 0) {
			HomeDetail keTing = new HomeDetail(homeId, Constants.KE_TING, null, 10, createdBy);
			HomeDetail guoDao = new HomeDetail(homeId, Constants.GUO_DAO, null, 10, createdBy);
			HomeDetail dian = new HomeDetail(homeId, Constants.KE_TING, null, 6, createdBy);
			details.add(keTing);
			details.add(guoDao);
			details.add(dian);
		}
		if (roomNum != null && roomNum > 0) {
			HomeDetail fangJian = new HomeDetail(homeId, Constants.FANG_JIAN, null, 10, createdBy);
			HomeDetail dian = new HomeDetail(homeId, Constants.FANG_JIAN, null, 6, createdBy);
			details.add(fangJian);
			details.add(dian);
		}
		if (toiletNum != null && toiletNum > 0) {
			HomeDetail fangShui = new HomeDetail(homeId, Constants.WEI_SHEN_JIAN, null, 28, createdBy);
			HomeDetail dian = new HomeDetail(homeId, Constants.WEI_SHEN_JIAN, null, 27, createdBy);
			details.add(fangShui);
			details.add(dian);
		}
		if (kitchenNum != null && kitchenNum > 0) {
			HomeDetail dian = new HomeDetail(homeId, Constants.CHU_FANG, null, 27, createdBy);
			details.add(dian);
		}
		if (balconyNum != null && balconyNum > 0) {
			HomeDetail yangTai = new HomeDetail(homeId, Constants.YANG_TAI, null, 31, createdBy);
			details.add(yangTai);
		}
		HomeDetail kaiCao = new HomeDetail(homeId, Constants.QUAN_WU, null, 1, createdBy);
		HomeDetail daKong = new HomeDetail(homeId, Constants.QUAN_WU, null, 4, createdBy);
		HomeDetail yunFei = new HomeDetail(homeId, Constants.QUAN_WU, null, 5, createdBy);
		details.add(kaiCao);
		details.add(daKong);
		details.add(yunFei);
		for (int i = (details.size() - 1); i >= 0; i--) {
			HomeDetail detail = details.get(i);
			// 选项名字
			String projectName = detail.getInfo();
			projectName = (StringUtils.isEmptyStr(projectName) ? "" : projectName.trim());
			// 区域
			String homeDetailType = detail.getHomeDetailType();
			homeDetailType = (StringUtils.isEmptyStr(homeDetailType) ? "" : homeDetailType.trim());
			if (Constants.KE_CAN_TING.equals(homeDetailType)) {
				HomeDetail guoDao = new HomeDetail(homeId, Constants.GUO_DAO, projectName, null, createdBy);
				HomeDetail keTing = new HomeDetail(homeId, Constants.KE_TING, projectName, null, createdBy);
				HomeDetail canTing = new HomeDetail(homeId, Constants.CAN_TING, projectName, null, createdBy);
				details.add(keTing);
				details.add(canTing);
				details.add(guoDao);
			}
			if (Constants.FANG_JIAN.equals(homeDetailType) && Constants.BIAN_XIAN.equals(projectName)) {
				HomeDetail fangJianLX = new HomeDetail(homeId, Constants.FANG_JIAN, Constants.LING_XING, 13,
						createdBy);
				details.add(fangJianLX);
			}
		}
		return details;
	}
}
