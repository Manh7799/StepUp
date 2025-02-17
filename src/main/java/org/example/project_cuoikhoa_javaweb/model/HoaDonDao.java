package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;

import java.util.List;

public interface HoaDonDao extends IHanhDong<HoaDon, String> {
    List<HoaDon> timTheoTenNguoiMua(String tenDangNhap);

    List<HoaDon> timTheoIdNguoiDung(String idNguoiDung);

    List<HoaDon> timTheoMaHoaDon(String maHoaDon);
}
