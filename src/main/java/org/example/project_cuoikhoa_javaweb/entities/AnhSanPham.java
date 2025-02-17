package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "AnhSanPham")
public class AnhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MaSanPham", nullable=false, length = 25)
    private String maSanPham;

    @Column(name="AnhSanPham", nullable=false, length = 255)
    private String anhSanPham;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }
}
