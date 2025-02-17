package org.example.project_cuoikhoa_javaweb.controller;

import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.springframework.data.jpa.domain.Specification;

public class SanPhamSpecifications {

    public static Specification<SanPham> hasTenSanPhamLike(String tenSanPham) {
        return (root, query, criteriaBuilder) ->
                tenSanPham == null ? null : criteriaBuilder.like(root.get("tenSanPham"), "%" + tenSanPham + "%");
    }

    public static Specification<SanPham> hasMaChuDe(String maChuDe) {
        return (root, query, criteriaBuilder) ->
                maChuDe == null ? null : criteriaBuilder.equal(root.get("maChuDe"), maChuDe);
    }

    public static Specification<SanPham> hasMaHang(String maHang) {
        return (root, query, criteriaBuilder) ->
                maHang == null ? null : criteriaBuilder.equal(root.get("maHang"), maHang);
    }

    public static Specification<SanPham> hasMaNhaCungCap(String maNhaCungCap) {
        return (root, query, criteriaBuilder) ->
                maNhaCungCap == null ? null : criteriaBuilder.equal(root.get("maNhaCungCap"), maNhaCungCap);
    }

}

