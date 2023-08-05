package com.envisionnepal.gunasho.file;


import java.util.List;
import java.util.stream.Collectors;

import com.envisionnepal.gunasho.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariableEntityServiceImpl implements VariableEntityService{


    @Autowired
    private VariableEntityRepository variableEntityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VariableEntityDto createVariableEntity(VariableEntityDto variableEntityDto) {
        VariableEntity variableEntity = this.dtoToVariableEntity(variableEntityDto);
        VariableEntity savedVariableEntity = this.variableEntityRepository.save(variableEntity);
        return this.variableEntityToDto(savedVariableEntity);
    }

    public VariableEntity dtoToVariableEntity(VariableEntityDto variableEntityDto) {
        VariableEntity variableEntity = this.modelMapper.map(variableEntityDto, VariableEntity.class);
        return variableEntity;
    }

    public VariableEntityDto variableEntityToDto(VariableEntity variableEntity) {
        VariableEntityDto variableEntityDto = this.modelMapper.map(variableEntity, VariableEntityDto.class);
        return variableEntityDto;
    }


    @Override
    public VariableEntityDto updateVariableEntity(VariableEntityDto variableEntityDto, Long variableId) {
        VariableEntity variableEntity = this.variableEntityRepository.findById(variableId)
                .orElseThrow(() -> new ResourceNotFoundException("variableEntity", "variableId", variableId));




        VariableEntity updatedVariableEntity = this.variableEntityRepository.save(variableEntity);
        return this.modelMapper.map(updatedVariableEntity, VariableEntityDto.class);
    }

    @Override
    public VariableEntityDto getVariableEntityById(Long variableId) {
        VariableEntity variableEntity = this.variableEntityRepository.findById(variableId)
                .orElseThrow(() -> new ResourceNotFoundException("VariableEntity", "variableEntity id", variableId));
        return this.modelMapper.map(variableEntity, VariableEntityDto.class);
    }

    @Override
    public List<VariableEntityDto> getAllVariableEntities() {
        List<VariableEntity> variableEntitys = this.variableEntityRepository.findAll();
        List<VariableEntityDto> variableEntityDtos = variableEntitys.stream()
                .map((variableEntity) -> this.modelMapper.map(variableEntity, VariableEntityDto.class)).collect(Collectors.toList());
        return variableEntityDtos;
    }




}