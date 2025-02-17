package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, String> {
    List<HoaDonChiTiet> findByMaHoaDon(String maHoaDon);

    List<HoaDonChiTiet> findByIdNguoiDung(String idNguoiDung);

}
