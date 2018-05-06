package com.wm.lejia.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.Banner;
import com.wm.lejia.pojo.entity.BannerUser;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.pojo.vo.AppointmentVO;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.SeaBannerDTO;
import com.wm.lejia.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.pojo.vo.BannerUserVO;
import com.wm.lejia.pojo.vo.BannerVO;
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
	
	

	@Override
	public Result<PageInfo<BannerVO>> listBanner(SeaBannerDTO dto) {
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<BannerVO> list = bannerMapper.listBannerBack(dto);
			PageInfo<BannerVO> pageInfo = new PageInfo<BannerVO>(list);
			return new Result<>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("BannerServiceImpl   listBanner ===> 查询出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public int updateBanner(Banner banner) {
		int rows = 0;
		try {
			banner.setUpdatedTime(new Date());
			rows = bannerMapper.updateByPrimaryKeySelective(banner);
			return rows;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl   updateBanner ===> 修改数据失败",e);
		}
		return rows;
	}

	@Override
	public Banner addBanner(Banner banner) {
		try {
			banner.setCreatedTime(new Date());
			int rows = bannerMapper.insertSelective(banner);
			if(rows > 0) {
				return banner;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl   updateBanner ===> 修改添加失败",e);
		}
		return null;
	}

	@Override
	public Result<PageInfo<BannerUserVO>> listBannerUser(SeaBannerUserDTO dto) {
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<BannerUserVO> list = bannerUserMapper.listBannerUser(dto);
			PageInfo<BannerUserVO> pageInfo = new PageInfo<>(list);
			return new Result<>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("BannerServiceImpl   listBannerUser ===> 查询出错",e);
		}
		return null;
	}


	
}
