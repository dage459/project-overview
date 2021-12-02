package com.vodafone.projectoverview.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class TypedPage<T> {
    private List<T> elements;
    private Integer pages;
    private Long size;
}
