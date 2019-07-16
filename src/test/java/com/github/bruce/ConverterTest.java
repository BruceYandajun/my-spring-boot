package com.github.bruce;

import com.github.bruce.model.converter.DeliveryConverter;
import com.github.bruce.model.dto.Address;
import com.github.bruce.model.dto.DeliveryAddressDto;
import com.github.bruce.model.dto.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterTest {

    @Resource
    private DeliveryConverter converter;

    @Test
    public void addressConvert() {
        Person p = new Person();
        p.setName("a");
        Address a = new Address();
        a.setDetail("b");
        DeliveryAddressDto convert = converter.convert(p, a);
        System.out.println(convert);
    }

    @Test
    public void updateDeliveryFromPerson() {
        Person p = new Person();
        p.setName("a");
        p.setSex("female");
        DeliveryAddressDto delivery = new DeliveryAddressDto();
        converter.updateDeliveryFromPerson(p, delivery);
        System.out.println(delivery);
    }

    @Test
    public void updateDelivery() {
        DeliveryAddressDto delivery = new DeliveryAddressDto();
        delivery.setDetail("hello");
        delivery.setName("Jack");
        DeliveryAddressDto update = new DeliveryAddressDto();
        update.setName("Bruce");
        DeliveryAddressDto old = converter.clone(delivery);
        converter.update(update, delivery);
        System.out.println(old);
        System.out.println(delivery);
    }
}
