package main.java.com.mydao.service.impl;

import main.java.com.mydao.dao.ViolationTypeMapper;
import main.java.com.mydao.entity.ViolationType;
import main.java.com.mydao.service.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationTypeServiceImpl implements ViolationTypeService {

    @Autowired
    private ViolationTypeMapper violationTypeMapper;
    @Override
    public List<ViolationType> findList() {
        return violationTypeMapper.findList();
    }
}
