package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.AnhSanPham;

import java.util.List;

public interface AnhSanPhamDao extends IHanhDong<AnhSanPham, String> {
    List<AnhSanPham> timTheoMaSanPham (String maSanPham);
}
