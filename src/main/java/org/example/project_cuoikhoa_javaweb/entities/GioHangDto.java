package org.example.project_cuoikhoa_javaweb.entities;

public class GioHangDto {
    private int soLuongSanPham;

    public GioHangDto(int soLuongSanPham){
        this.soLuongSanPham = soLuongSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

}
