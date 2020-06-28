package com.tedu.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1050171467138693109L;
    private Integer id;
    private String name;
    private String url;
    private Integer type;
    private Integer sort;
    private String note;
    private Integer parentId;
    private String permission;
    private String parentName;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
