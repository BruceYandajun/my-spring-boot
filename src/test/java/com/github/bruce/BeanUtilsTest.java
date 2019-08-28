package com.github.bruce;

import org.springframework.beans.BeanUtils;

import static com.github.bruce.utils.BaseUtil.line;

public class BeanUtilsTest {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        BeanUtils.copyProperties(a, b);
        line(b.status);// Can't copy status, because they have different types
        line(b.name);
    }

    static class A {
        Integer status = 1;
        String name = "a";

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class B {
        String status;
        String name;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
