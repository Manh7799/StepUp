package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "HangGiay")
public class HangGiay {
    @Id
    @Column(name = "MaHang", nullable = false, length = 25)
    @NotEmpty(message = "Bạn cần phải nhập mã hãng giày")
    private String maHang;

    @Column(name = "TenHang", nullable = false, length = 100)
    @NotEmpty(message = "Bạn cần phải nhập tên hãng")
    private String tenHang;

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }
}
