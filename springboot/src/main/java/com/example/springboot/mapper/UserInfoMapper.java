package com.example.springboot.mapper;

import com.example.springboot.mapper.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(String userid);

    int insert(UserInfoEntity record);

    int insertSelective(UserInfoEntity record);

    UserInfoEntity selectByPrimaryKey(String userid);

    List<UserInfoEntity> selectSelective(Map inMap);

    int updateByPrimaryKeySelective(UserInfoEntity record);

    int updateByPrimaryKey(UserInfoEntity record);
}
