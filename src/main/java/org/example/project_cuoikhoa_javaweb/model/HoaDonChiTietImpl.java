package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.HoaDonChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hoaDonChiTietDao")
public class HoaDonChiTietImpl implements HoaDonChiTiepDao{

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<HoaDonChiTiet> getList() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet getById(String maHoaDon) {
        HoaDonChiTiet objHoaDon = null;
        try {
            objHoaDon = (HoaDonChiTiet) entityManager.createQuery("FROM HoaDonChiTiet hd WHERE hd.maHoaDon = :ma")
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
    public boolean add(HoaDonChiTiet hoaDonChiTiet) {
        return false;
    }

    @Override
    public boolean update(HoaDonChiTiet hoaDonChiTiet) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<HoaDonChiTiet> timTheoMaHoaDon(String maHoaDon) {
        return hoaDonChiTietRepository.findByMaHoaDon(maHoaDon);
    }

    @Override
    public List<HoaDonChiTiet> timTheoIdNguoiDung(String idNguoiDung) {
        return hoaDonChiTietRepository.findByIdNguoiDung(idNguoiDung);
    }
}
