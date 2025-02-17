package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    NguoiDung findByTenDangNhapAndIdNguoiDungAndVaiTro(String ten, String vaiTro, String idNguoiDung);

    NguoiDung findByTenDangNhap(String ten);

    NguoiDung findByEmail(String email);
    NguoiDung findByDienThoai(String soDienThoai);
}
