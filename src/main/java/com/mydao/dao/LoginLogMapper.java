package main.java.com.mydao.dao;

import main.java.com.mydao.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface LoginLogMapper {
    int deleteByPrimaryKey(Long recId);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(LoginLog record);

}