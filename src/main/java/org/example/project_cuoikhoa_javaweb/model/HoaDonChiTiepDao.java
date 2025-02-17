package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTiepDao extends IHanhDong<HoaDonChiTiet, String> {
    List<HoaDonChiTiet> timTheoMaHoaDon(String maHoaDon);

    List<HoaDonChiTiet> timTheoIdNguoiDung(String idNguoiDung);
}
