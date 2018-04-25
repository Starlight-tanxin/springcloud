package com.wm.lejia.manage.db.mapper;

import java.util.List;

import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.vo.CalculationPriceVO;

public interface TotalPriceMapper {
    int deleteByPrimaryKey(Integer totalPriceId);

    int insert(TotalPrice record);

    int insertSelective(TotalPrice record);

    TotalPrice selectByPrimaryKey(Integer totalPriceId);

    int updateByPrimaryKeySelective(TotalPrice record);

    int updateByPrimaryKey(TotalPrice record);
    
    List<CalculationPriceVO> listCalculationPrice(SeaPriceDTO dto);
}