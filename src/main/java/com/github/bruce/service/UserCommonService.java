package com.github.bruce.service;

import com.github.bruce.model.enums.StudentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class UserCommonService {
    @Resource
    private UserService userService;

    public StudentTypeEnum getStudentType(Long studentId) {
        Map<String, String> longStringMap = userService.studentTypeMap(studentId.toString());
        log.info("map: " + longStringMap);
        String studentType = longStringMap.getOrDefault(studentId.toString(), StudentTypeEnum.GUEST.name());
        return StudentTypeEnum.valueOf(studentType);
    }
}
