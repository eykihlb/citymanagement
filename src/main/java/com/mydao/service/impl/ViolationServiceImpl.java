package main.java.com.mydao.service.impl;

import com.github.pagehelper.PageHelper;
import main.java.com.mydao.dao.ViolationMapper;
import main.java.com.mydao.entity.Violation;
import main.java.com.mydao.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationMapper violationMapper;
    @Override
    public int deleteByPrimaryKey(Long recId) {
        return violationMapper.deleteByPrimaryKey(recId);
    }

    @Override
    public int insertSelective(Violation record) {
        return violationMapper.insertSelective(record);
    }

    @Override
    public Violation selectByPrimaryKey(Long recId) {
        return violationMapper.selectByPrimaryKey(recId);
    }

    @Override
    public int updateByPrimaryKeySelective(Violation record) {
        return violationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Violation> selectHisRecords(Map<String, Object> paramMap) {
        //PageHelper.startPage((Integer) paramMap.get("pageIndex"), (Integer) paramMap.get("pageSize"));
        return violationMapper.selectHisRecords(paramMap);
    }

    @Override
    public Integer selectHisRecordsCount(Map<String, Object> paramMap) {
        return violationMapper.selectHisRecordsCount(paramMap);
    }

    @Override
    public List<Violation> selectHisRecordsByUser(Map<String, Object> paramMap) {
        //PageHelper.startPage((Integer) paramMap.get("pageIndex"), (Integer) paramMap.get("pageSize"));
        return violationMapper.selectHisRecordsByUser(paramMap);
    }

    @Override
    public Integer selectHisRecordsCountByUser(Map<String, Object> paramMap) {
        return violationMapper.selectHisRecordsCountByUser(paramMap);
    }
}
