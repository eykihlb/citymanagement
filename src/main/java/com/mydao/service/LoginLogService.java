package main.java.com.mydao.service;

import main.java.com.mydao.entity.LoginLog;
import org.springframework.stereotype.Component;


@Component
public interface LoginLogService {
    int deleteByPrimaryKey(Long recId);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(LoginLog record);
}
