package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.SanPham;

import java.util.List;

public interface SanPhamDao extends IHanhDong<SanPham, String> {

    List<SanPham> timKiemSanPham(String tuKhoa, String maChuDe);

    List<SanPham> timKiemTheoTenVaLoai(String tuKhoa, String maChuDe, String maHang, String maNhaCungCap);

    List<SanPham> timKiemTheoGiaTien(double giaTienMin, double giaTienMax);
}
