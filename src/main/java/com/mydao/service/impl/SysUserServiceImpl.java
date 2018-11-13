package main.java.com.mydao.service.impl;

import main.java.com.mydao.dao.SysUserMapper;
import main.java.com.mydao.entity.SysUser;
import main.java.com.mydao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public SysUser userLogin(Map<String, Object> map) {
        return sysUserMapper.userLogin(map);
    }

    @Override
    public int insertSelective(SysUser record) {
        return 0;
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return 0;
    }
}
