package com.github.bruce.model.enums;

import lombok.Getter;

public enum StudentTypeEnum {
    GUEST("游客"),
    MAJOR("主修"),
    EXCELLENT("双优");

    @Getter
    private String desc;

    StudentTypeEnum(String desc) {
        this.desc = desc;
    }
}