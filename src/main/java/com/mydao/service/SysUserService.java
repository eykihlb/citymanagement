package main.java.com.mydao.service;

import main.java.com.mydao.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface SysUserService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser userLogin(Map<String,Object> map);
}
