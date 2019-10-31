package com.github.bruce.model.converter;

import com.github.bruce.model.Parent;
import com.github.bruce.model.dto.ParentDto;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(componentModel = "spring", uses = DateConverter.class, nullValueCheckStrategy = ALWAYS)
public interface ParentConverter {

    ParentDto convert(Parent parent);

}
