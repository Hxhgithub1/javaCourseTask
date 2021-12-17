package com.hxh.demo.config;

import com.hxh.demo.pojo.Klass;
import com.hxh.demo.pojo.School;
import com.hxh.demo.pojo.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import java.util.Properties;

@Configuration
@Import({DemoProperties.class})
@RequiredArgsConstructor
class MyConfig {

    private final DemoProperties props;

    @Bean
    public Student student() {
        Student obj = new Student();
        Properties properties = props.getProps();
        obj.setName(properties.getProperty("student.name"));
        return obj;
    }

    @Bean
    public Klass klass() {
        Klass obj = new Klass();
        Properties properties = props.getProps();
        obj.setClassName((properties.getProperty("klass.name")));
        obj.setGrade(properties.getProperty("klass.grade"));
        return obj;
    }

    @Bean
    public School school() {
        School obj = new School();
        Properties properties = props.getProps();
        obj.setSchoolName((properties.getProperty("school.name")));
        return obj;
    }
}

