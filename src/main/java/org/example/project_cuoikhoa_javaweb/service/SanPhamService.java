package org.example.project_cuoikhoa_javaweb.service;

import jakarta.persistence.criteria.Predicate;
import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.entities.HangGiay;
import org.example.project_cuoikhoa_javaweb.entities.NhaCungCap;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamDao sanPhamDao;

    @Autowired
    ChuDeRepository chuDeRepository;

    @Autowired
    HangGiayRepository hangGiayRepository;

    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    public List<SanPham> timKiemSanPham(String tuKhoa, String maChuDe, String maHang, String maNhaCungCap, Double giaTienMin, Double giaTienMax) {
        Specification<SanPham> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("tenSanPham"), "%" + tuKhoa.trim() + "%"));
            }
            if (maChuDe != null && !maChuDe.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("maChuDe"), maChuDe.trim()));
            }
            if (maHang != null && !maHang.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("maHang"), maHang.trim()));
            }
            if (maNhaCungCap != null && !maNhaCungCap.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("maNhaCungCap"), maNhaCungCap.trim()));
            }
            if (giaTienMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("giaTien"), giaTienMin));
            }
            if (giaTienMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("giaTien"), giaTienMax));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return sanPhamRepository.findAll(spec);
    }

    public List<SanPham> timKiemSanPhamTheoGia(double giaTienMin, double giaTienMax) {
        return sanPhamRepository.findByGiaTienBetween(giaTienMin, giaTienMax);
    }

    public List<ChuDe> getAllChuDe(){
        return chuDeRepository.findAll();
    }
    public List<HangGiay> getAllHangGiay(){
        return hangGiayRepository.findAll();
    }
    public List<NhaCungCap> getAllNhaCungCap(){
        return nhaCungCapRepository.findAll();
    }

    public List<SanPham> timKiemThongTinSanPham(String tuKhoa, String maChuDe) {
        return sanPhamDao.timKiemSanPham(tuKhoa, maChuDe);
    }

    public List<SanPham> timKiemTheoTenVaLoai(String tuKhoa, String maHang, String maNhaCungCap, String maChuDe) {
        return sanPhamDao.timKiemTheoTenVaLoai(tuKhoa, maHang, maNhaCungCap, maChuDe);
    }

    public List<SanPham> layDanhSach() {
        return sanPhamDao.getList();
    }

    public SanPham layChiTiet(String maSanPham) {
        return sanPhamDao.getById(maSanPham);
    }

    public boolean themMoi(SanPham objSach) {
        return sanPhamDao.add(objSach);
    }

    public boolean capNhat(SanPham objSach) {
        return sanPhamDao.update(objSach);
    }

    public boolean xoa(String maSanPham) {
        return sanPhamDao.delete(maSanPham);
    }
}
