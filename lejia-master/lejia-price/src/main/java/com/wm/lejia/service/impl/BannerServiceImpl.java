package com.wm.lejia.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wm.lejia.common.pojo.dto.UserDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.vo.AppointmentVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.db.mapper.BannerMapper;
import com.wm.lejia.db.mapper.BannerUserMapper;
import com.wm.lejia.db.mapper.UserMapper;
import com.wm.lejia.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private static Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BannerUserMapper bannerUserMapper;

	@Override
	public List<Banner> listBannerByHome(Banner banner) {
		try {
			List<Banner> list = bannerMapper.listBannerByHome(banner);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl listBannerByHome ===> 查询出错",e);
		}
		return null;
	}

	@Override
	public Result<?> addBannerUser(BannerUser bannerUser) {
		String mobile = bannerUser.getMobile();
		UserDTO userDTO = new UserDTO();
		userDTO.setMobile(mobile);
		User user = userMapper.getUserByCondition(userDTO);
		if (ObjectUtils.isEmpty(user)) {
			user = new User();
			user.setMobile(mobile);
			user.setNickname(bannerUser.getNickname());
			user.setCreatedTime(new Date());
			user.setCityId(bannerUser.getCityId());
			user.setProvinceId(bannerUser.getProvinceId());
			int rows = userMapper.insertSelective(user);
			if (rows <= 0) {
				return new Result<String>(ResultCode.INSERT_ERROR, "创建用户数据失败");
			}
		}
		Integer userId = user.getUserId();
		if (userId == null) {
			return new Result<String>(ResultCode.QUERY_ERROR, "userId为null");
		}
		bannerUser.setUserId(userId);
		bannerUser.setCreatedTime(new Date());
		int rows = bannerUserMapper.insertSelective(bannerUser);
		if (rows <= 0) {
			return new Result<String>(ResultCode.INSERT_ERROR, "创建参加活动用户数据失败");
		}
		return new Result<>();
	}

	@Override
	public List<AppointmentVO> listBannerUser(Integer userId) {
		try {
			List<AppointmentVO> list = bannerUserMapper.listBannerUserByUser(userId);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl listBannerUser ===> 查询出错",e);
		}
		return null;
	}
	
	
}
