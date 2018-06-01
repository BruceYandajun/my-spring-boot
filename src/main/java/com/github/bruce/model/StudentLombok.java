package com.github.bruce.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class StudentLombok {

    private Integer age;

    @NonNull
    private String name;

    private Boolean isSuccess;

    private interface SimpleCollection {
        boolean add(String item);
        boolean remove(Object item);
    }

    @Delegate(types=SimpleCollection.class)
    private final Collection<String> collection = new ArrayList<>();

    public void show() {
        log.info("student show : {}", this.toString());
    }

    public static void main(String[] args) {
        log.info("this is a lombok log info");
        StudentLombok student = new StudentLombok(22, "Bruce", true);
        student.add("hhh");
        student.add("lll");
        student.remove("hhh");
        System.out.println(student.toString());
        System.out.println(student.hashCode());
    }
}
