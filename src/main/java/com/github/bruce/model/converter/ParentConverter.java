package com.github.bruce.model.converter;

import com.github.bruce.model.Parent;
import com.github.bruce.model.dto.ParentDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(componentModel = "spring", uses = DateConverter.class, nullValueCheckStrategy = ALWAYS)
public interface ParentConverter {

    @Mappings({
            @Mapping(source = "s", target = "name")
    })
    ParentDto convert(Parent parent);

    List<ParentDto> convert(List<Parent> parents);

}
