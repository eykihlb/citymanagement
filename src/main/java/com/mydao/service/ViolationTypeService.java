package main.java.com.mydao.service;

import main.java.com.mydao.entity.ViolationType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ViolationTypeService {

    List<ViolationType> findList();
}
