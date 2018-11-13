package main.java.com.mydao.service.impl;

import main.java.com.mydao.dao.LoginLogMapper;
import main.java.com.mydao.entity.LoginLog;
import main.java.com.mydao.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService{


    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public int deleteByPrimaryKey(Long recId) {
        return loginLogMapper.deleteByPrimaryKey(recId);
    }

    @Override
    public int insertSelective(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }

    @Override
    public LoginLog selectByPrimaryKey(Long recId) {
        return loginLogMapper.selectByPrimaryKey(recId);
    }

    @Override
    public int updateByPrimaryKeySelective(LoginLog record) {
        return loginLogMapper.updateByPrimaryKeySelective(record);
    }
}
