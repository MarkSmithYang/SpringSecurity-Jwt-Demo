package com.yb.springsecurity.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author yangbiao
 * @Description:权限信息类
 * @date 2018/11/2
 */
@Entity
@Table//这里就使用默认的映射策略
@ApiModel("权限信息类")
//默认是true,更新的时候只更新修改的那个字段,而不是更新整个表的字段信息,
//从sql就可以看出,性能可以提高很多,插入也是一样的道理
@DynamicUpdate
@DynamicInsert
public class Permission implements Serializable {
    private static final long serialVersionUID = -5566183753194600505L;

    @Id//这个注解和@Table一样不能少@Column是可以少的
    private String id;

    /**
     * 权限
     */
    @Column(unique = true)
    @ApiModelProperty("权限")
    private String permission;

    /**
     * 权限中文
     */
    @ApiModelProperty("权限中文")
    private String permissionCn;

    /**
     * 权限角色
     */
    @ApiModelProperty("权限角色")
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.LAZY)
    private Set<Role> roles;

    /**
     * 权限用户
     */
    @ApiModelProperty("权限用户")
    @ManyToMany(targetEntity = SysUser.class,fetch = FetchType.LAZY)
    private Set<SysUser> users;

    /**
     * 权限模块
     */
    @ApiModelProperty("权限模块")
    @ManyToMany(targetEntity = Module.class,fetch = FetchType.LAZY)
    private Set<Module> modules;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(permission, that.permission)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(permission)
                .toHashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionCn() {
        return permissionCn;
    }

    public void setPermissionCn(String permissionCn) {
        this.permissionCn = permissionCn;
    }

}
