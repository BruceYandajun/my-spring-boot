package com.github.bruce.model.converter;

import com.github.bruce.model.Parent;
import com.github.bruce.model.dto.ParentDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(componentModel = "spring", uses = DateConverter.class, nullValueCheckStrategy = ALWAYS)
public interface ParentConverter {

    ParentDto convert(Parent parent);

    List<ParentDto> convert(List<Parent> parents);

}
