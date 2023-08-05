package com.envisionnepal.gunasho.file;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VariableEntityService {

    VariableEntityDto createVariableEntity(VariableEntityDto variableEntity);

    VariableEntityDto updateVariableEntity(VariableEntityDto variableEntity, Long variableId);

    VariableEntityDto getVariableEntityById(Long variableId);

    List<VariableEntityDto> getAllVariableEntities();
}