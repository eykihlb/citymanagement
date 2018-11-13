package main.java.com.mydao.dao;

import main.java.com.mydao.entity.ViolationType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ViolationTypeMapper {
    int insertSelective(ViolationType record);

    List<ViolationType> findList();
}