package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "VaiTro")
public class VaiTro {

    @Id
    @Column(name = "Id", nullable = false, length = 15)
    private String id;

    @Column(name = "TenVaiTro", nullable = false, length = 15)
    private String tenVaiTro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String vaiTro) {
        this.tenVaiTro = vaiTro;
    }
}
