package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.controller.SanPhamSpecifications;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("sanPhanDao")
public class SanPhamImpl implements SanPhamDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> timKiemTheoGiaTien(double giaTienMin, double giaTienMax) {
        return sanPhamRepository.findByGiaTienBetween(giaTienMin, giaTienMax);
    }

    @Override
    public List<SanPham> timKiemSanPham(String tuKhoa, String maChuDe) {

        tuKhoa = (tuKhoa==null ? "" : tuKhoa);

        maChuDe = (maChuDe==null ? "" : maChuDe);

        return sanPhamRepository.timKiemSanPham("%" + tuKhoa + "%", maChuDe);
    }

    @Override
    public List<SanPham> timKiemTheoTenVaLoai(String tuKhoa, String maChuDe, String maHang, String maNhaCungCap) {

        tuKhoa = (tuKhoa==null ? "" : tuKhoa);

        maChuDe = (maChuDe==null ? "" : maChuDe);

        maHang = (maHang==null ? "" : maHang);

        maNhaCungCap = (maNhaCungCap==null ? "" : maNhaCungCap);

        return sanPhamRepository.timSPTheoTenVaLoai("%" + tuKhoa + "%", maChuDe, maHang, maNhaCungCap);
    }

    @Override
    public List<SanPham> getList() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getById(String maSanPham) {
        SanPham objSanPham = null;
        try {
            objSanPham = (SanPham) entityManager.createQuery("FROM SanPham s WHERE s.maSanPham = :ma")
                    .setParameter("ma", maSanPham)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objSanPham = null;
        }
        return objSanPham;
    }

    @Override
    public boolean add(SanPham objSanPham) {
        SanPham objSanPham01 = sanPhamRepository.save(objSanPham);

        if (objSanPham01 != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean update(SanPham obj) {
        SanPham objSanPham = sanPhamRepository.getById(obj.getMaSanPham());

        if (objSanPham != null) {
            objSanPham.setTenSanPham(obj.getTenSanPham());
            objSanPham.setMoTa(obj.getMoTa());
            objSanPham.setGiaTien(obj.getGiaTien());
            objSanPham.setSoLuong(obj.getSoLuong());
            objSanPham.setNoiDung(obj.getNoiDung());
            objSanPham.setAnh(obj.getAnh());
            objSanPham.setNgayCapNhat(obj.getNgayCapNhat());
            objSanPham.setNguoiDuyet(obj.getNguoiDuyet());
            objSanPham.setDaDuyet(obj.getDaDuyet());
            objSanPham.setMaHang(obj.getMaHang());
            objSanPham.setMaNhaCungCap(obj.getMaNhaCungCap());
            objSanPham.setMaChuDe(obj.getMaChuDe());

            sanPhamRepository.save(objSanPham);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        SanPham objSanPham = getById(id);

        if(objSanPham != null) {
            sanPhamRepository.delete(objSanPham);

            return true;
        }

        return false;
    }
}
