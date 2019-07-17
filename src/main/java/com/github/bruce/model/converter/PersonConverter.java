package com.github.bruce.model.converter;

import com.github.bruce.model.dto.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonConverter {

    @Mappings(
            @Mapping(target = "name", defaultValue = "XX")
    )
    Person clone(Person person);
}
