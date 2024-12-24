package com.paya.pleaserservice.core;


import com.paya.pleaserservice.enums.DataState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@MappedSuperclass
@Getter
@Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Version
    @Column(name = "version")
    private Integer version;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_date")
    private Date deleteDate;
    @Column(name = "datastate")
    private Integer dataState;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "delete_user")
    private String deleteUser;


    public void softDelete() {
     this.deleteDate = new Date();
     this.dataState = DataState.DELETED.getState();
    }

    @PrePersist
    public void createUID(){
        this.id = UUID.randomUUID().toString();
    }

}
