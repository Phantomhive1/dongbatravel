package com.tedu.pj.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = 3596580339497190370L;
    private Integer id;
    private String name;
    private Integer parentId;

}
