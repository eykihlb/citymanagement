package main.java.com.mydao.dao;

import main.java.com.mydao.entity.Violation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ViolationMapper {
    int deleteByPrimaryKey(Long recId);

    int insertSelective(Violation record);

    Violation selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(Violation record);

    List<Violation> selectHisRecords(Map<String,Object> paramMap);

    Integer selectHisRecordsCount(Map<String,Object> paramMap);

    List<Violation> selectHisRecordsByUser(Map<String,Object> paramMap);

    Integer selectHisRecordsCountByUser(Map<String,Object> paramMap);
}