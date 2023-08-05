package com.envisionnepal.gunasho.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableEntityResponse {
    private Long variableId;
    private List<String> profImages;
    private List<String> bannerImages;
}
