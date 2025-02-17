package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hoaDonDao")
public class HoaDonImpl implements HoaDonDao{

    @Autowired
    HoaDonRepository hoaDonRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<HoaDon> getList() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getById(String maHoaDon) {
        HoaDon objHoaDon = null;
        try {
            objHoaDon = (HoaDon) entityManager.createQuery("FROM HoaDon hd WHERE hd.maHoaDon = :ma")
                    .setParameter("ma", maHoaDon)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objHoaDon = null;
        }
        return objHoaDon;
    }

    @Override
    public boolean add(HoaDon hoaDon) {
        return false;
    }

    @Override
    public boolean update(HoaDon hoaDon) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<HoaDon> timTheoTenNguoiMua(String tenDangNhap) {
        return hoaDonRepository.findByNguoiMua(tenDangNhap);
    }

    @Override
    public List<HoaDon> timTheoIdNguoiDung(String idNguoiDung) {
        return hoaDonRepository.findByIdNguoiDung(idNguoiDung);
    }

    @Override
    public List<HoaDon> timTheoMaHoaDon(String maHoaDon) {
        return List.of();
    }
}
