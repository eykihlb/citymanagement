package main.java.com.mydao.dao;

import main.java.com.mydao.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser userLogin(Map<String,Object> map);
}