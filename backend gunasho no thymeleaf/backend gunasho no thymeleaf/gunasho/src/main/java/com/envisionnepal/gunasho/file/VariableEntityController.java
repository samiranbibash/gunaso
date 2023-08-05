package com.envisionnepal.gunasho.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/gunasho/authenticate")
public class VariableEntityController {

    @Autowired
    VariableEntityService variableEntityService;

    @PostMapping("/image")
    public ResponseEntity<VariableEntityDto> createVariableEntity(@RequestBody VariableEntityDto variableEntityDto) {

        VariableEntityDto createdVariableEntityDto = this.variableEntityService.createVariableEntity(variableEntityDto);
        return new ResponseEntity<>(createdVariableEntityDto, HttpStatus.CREATED);
    }

    // PUT- update variableEntity
    @PutMapping("/image/{variableEntityId}")
    public ResponseEntity<VariableEntityDto> updateVariableEntity(@RequestBody VariableEntityDto variableEntityDto, @PathVariable("variableEntityId") Long Id) {
        VariableEntityDto updatedVariableEntity = this.variableEntityService.updateVariableEntity(variableEntityDto, Id);
        return ResponseEntity.ok(updatedVariableEntity);
    }



//    @GetMapping("/images")
//    public ResponseEntity<List<VariableEntityDto>> getAllVariableEntitys() {
//        return ResponseEntity.ok(this.variableEntityService.getAllVariableEntities());
//    }

    @GetMapping("/{variableEntityId}")
    public ResponseEntity<VariableEntityDto> getSingleVariableEntity(@PathVariable Long variableEntityId) {
        return ResponseEntity.ok(this.variableEntityService.getVariableEntityById(variableEntityId));
    }

//    @GetMapping("/getVariableEntity/{variableId}")
//    public ResponseEntity<VariableEntityDto> getVariableEntity(@PathVariable Long variableId) {
//        VariableEntity variableEntity = variableEntityService.getVariableEntityById(variableId);
//        if (variableEntity != null) {
//            VariableEntityDto dto = VariableEntityMapper.toDto(variableEntity);
//            return new ResponseEntity<>(dto, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/getVariableEntity/{variableId}")
//    public ResponseEntity<VariableEntityResponse> getVariableEntity(@PathVariable Long variableId) {
//        VariableEntity variableEntity = variableEntityService.getVariableEntityById(variableId);
//        if (variableEntity != null) {
//            VariableEntityResponse responseDTO = new VariableEntityResponse();
//            responseDTO.setVariableId(variableEntity.getVariableId());
//            responseDTO.setProfImages(Arrays.asList(variableEntity.getProfImg1(), variableEntity.getProfImg2(), variableEntity.getProfImg3()));
//            responseDTO.setBannerImages(Arrays.asList(variableEntity.getBannerImg1(), variableEntity.getBannerImg2(), variableEntity.getBannerImg3()));
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}

