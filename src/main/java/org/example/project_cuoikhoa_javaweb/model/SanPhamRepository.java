package org.example.project_cuoikhoa_javaweb.model;

import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, String>, JpaSpecificationExecutor<SanPham> {
        @Query("SELECT s FROM SanPham s WHERE ((s.tenSanPham like :tuKhoa OR s.maSanPham = :tuKhoa ) AND (LENGTH(:maChuDe) = 0 OR (LENGTH(:maChuDe) > 0 AND s.maChuDe = :maChuDe)))")

    public List<SanPham> timKiemSanPham(@Param("tuKhoa") String tuKhoa, @Param("maChuDe") String maChuDe);

    @Query("SELECT s FROM SanPham s WHERE " +
            "(:tuKhoa IS NULL OR :tuKhoa = '' OR s.tenSanPham LIKE %:tuKhoa% OR s.maSanPham LIKE %:tuKhoa%) AND " +
            "(:maChuDe IS NULL OR :maChuDe = '' OR s.maChuDe = :maChuDe) AND " +
            "(:maHang IS NULL OR :maHang = '' OR s.maHang = :maHang) AND " +
            "(:maNhaCungCap IS NULL OR :maNhaCungCap = '' OR s.maNhaCungCap = :maNhaCungCap)")
    List<SanPham> timSPTheoTenVaLoai(@Param("tuKhoa") String tuKhoa,
                                     @Param("maHang") String maHang,
                                     @Param("maNhaCungCap") String maNhaCungCap,
                                     @Param("maChuDe") String maChuDe);

    List<SanPham> findAll(Specification<SanPham> spec);

    List<SanPham> findByGiaTienBetween(double giaTienMin, double giaTienMax);
}
