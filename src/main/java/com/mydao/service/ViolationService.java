package main.java.com.mydao.service;

import main.java.com.mydao.entity.Violation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ViolationService {

    int deleteByPrimaryKey(Long recId);

    int insertSelective(Violation record);

    Violation selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(Violation record);

    List<Violation> selectHisRecords(Map<String,Object> paramMap);

    Integer selectHisRecordsCount(Map<String,Object> paramMap);

    List<Violation> selectHisRecordsByUser(Map<String,Object> paramMap);

    Integer selectHisRecordsCountByUser(Map<String,Object> paramMap);
}
