package com.yb.springsecurity.jwt.model;

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
 * @Description:(菜单)模块类--主要是菜单模块分权
 * @date 2018/11/2
 */
@Entity
@Table//这里就使用默认的映射策略
@ApiModel("模块类--主要是菜单模块分权")
//默认是true,更新的时候只更新修改的那个字段,而不是更新整个表的字段信息,
//从sql就可以看出,性能可以提高很多,插入也是一样的道理
@DynamicUpdate
@DynamicInsert
public class Module implements Serializable {
    private static final long serialVersionUID = -3486259869151800327L;

    @Id//这个注解和@Table一样不能少@Column是可以少的
    private String id;

    /**
     * 模块
     */
    @ApiModelProperty("模块")
    @Column(unique = true)
    private String module;

    /**
     * 模块中文名
     */
    @ApiModelProperty("模块中文名")
    private String moduleCn;

    /**
     * 模块用户
     */
    @ApiModelProperty("模块用户")
    @ManyToMany(targetEntity = SysUser.class, fetch = FetchType.LAZY)
    private Set<SysUser> users;

    /**
     * 模块权限
     */
    @ApiModelProperty("模块权限")
    @ManyToMany(targetEntity = Permission.class, mappedBy = "modules", fetch = FetchType.EAGER)
    private Set<Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Module module1 = (Module) o;

        return new EqualsBuilder()
                .append(id, module1.id)
                .append(module, module1.module)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(module)
                .toHashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuleCn() {
        return moduleCn;
    }

    public void setModuleCn(String moduleCn) {
        this.moduleCn = moduleCn;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
