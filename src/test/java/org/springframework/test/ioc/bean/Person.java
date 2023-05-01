package org.springframework.test.ioc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
@Getter
@Setter
@ToString
public class Person {

    private String name;

    private int age;

    private Car car;
}
