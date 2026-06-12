package br.com.pizzaplaza.product.entities.fatherofall;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class Odin {

    @Getter
    @Setter
    @Id
    @Column(length = 36)
    private String oid;

    @Getter
    @Setter
    @Column(name = "id_user_who_created")
    private String oidUserWhoCreated;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "id_user_who_changed")
    private String oidUserWhoChanged;

    @Getter
    @Setter
    @Column(name = "changed_at")
    private Date changedAt;

    @PrePersist
    public void beforePersist(){
        createdAt = new Date(System.currentTimeMillis());
        if (oid == null) {
            oid = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    public void beforeUpdate(){
        changedAt = new Date(System.currentTimeMillis());
    }

    public String toString() {
        return "criado por usuário: " + this.oidUserWhoCreated;
    }
}
