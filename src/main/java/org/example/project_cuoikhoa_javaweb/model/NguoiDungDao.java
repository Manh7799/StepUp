package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;

public interface NguoiDungDao extends IHanhDong<NguoiDung, String>{
    NguoiDung layNguoiDung(String tenDangNhap, String vaiTro, String idNguoiDung);

    NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap);

    NguoiDung layNguoiDungTheoId(String idNguoiDung);
}
