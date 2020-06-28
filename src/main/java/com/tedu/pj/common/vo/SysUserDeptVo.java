package com.tedu.pj.common.vo;

import com.tedu.pj.sys.entity.SysDept;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserDeptVo implements Serializable {
    private static final long serialVersionUID = 6145460525806811784L;
    private Integer id;

    private String username;

    private String password;//md5

    private String salt;

    private String email;

    private String mobile;

    private Integer valid=1;

    private SysDept sysDept; //private Integer deptId;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;
}
