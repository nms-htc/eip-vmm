/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.entity;

import com.nms.vmm.eip.beanvalidator.Email;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cuong
 */
@Entity
@Table(name = "EIP_USER")
public class UserEntry implements Serializable {

    private static final long serialVersionUID = -6288277621340836690L;

    @NotNull(message = "user.code.notnull")
    @Column(unique = true)
    private String code;
    @NotNull(message = "user.fullname.notnull")
    private String fullName;
    @NotNull(message = "user.password.notnull")
    private String password;
    @Id
    @NotNull(message = "user.email.notnull")
    private String email;
    private String description;
    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "EIP_USER_GROUP", joinColumns = {
        @JoinColumn(name = "EMAIL")})
    @Column(name = "GROUP_NAME")
    private Collection<UserRole> userRoles;

    public UserEntry() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "com.nms.vmm.eip.entity.UserEntry[ email=" + email + " ]";
    }
}
