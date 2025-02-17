package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.AnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, String> {
    List<AnhSanPham> findByMaSanPham (String maSanPham);
}
