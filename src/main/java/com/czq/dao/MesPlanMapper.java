package com.czq.dao;

import com.czq.model.MesPlan;

public interface MesPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MesPlan record);

    int insertSelective(MesPlan record);

    MesPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MesPlan record);

    int updateByPrimaryKey(MesPlan record);
}