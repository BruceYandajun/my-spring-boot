package com.github.bruce.model.converter;

import com.github.bruce.model.dto.Address;
import com.github.bruce.model.dto.DeliveryAddressDto;
import com.github.bruce.model.dto.Person;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DeliveryConverter {

    @Mappings({
            @Mapping(source = "p.name", target = "name"),
            @Mapping(source = "a.detail", target = "detail")
    })
    DeliveryAddressDto convert(Person p, Address a);

    void updateDeliveryFromPerson(Person person, @MappingTarget DeliveryAddressDto delivery);

    DeliveryAddressDto clone(DeliveryAddressDto deliveryAddressDto);

    void update(DeliveryAddressDto update, @MappingTarget DeliveryAddressDto delivery);

    @Mappings({
            @Mapping(ignore = true, source = "name", target = "name")
    })
    DeliveryAddressDto convertIgnore(Person person);
}
