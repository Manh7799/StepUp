package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    List<HoaDon> findByNguoiMua(String nguoiDung);

    List<HoaDon> findByIdNguoiDung(String idNguoiDung);

    List<HoaDon> findByMaHoaDon(String maHoaDon);
}
