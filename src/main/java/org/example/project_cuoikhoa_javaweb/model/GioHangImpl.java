package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.GioHang;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gioHang")
public class GioHangImpl implements GioHangDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    GioHangRepository gioHangResoisitory;

    @Override
    public List<GioHang> getList() {
        return gioHangResoisitory.findAll();
    }

    @Override
    public GioHang getById(String maGioHang) {
        GioHang objGioHang = null;
        try {
            objGioHang = (GioHang) entityManager.createQuery("FROM GioHang s WHERE s.maGioHang = :ma")
                    .setParameter("ma", maGioHang)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objGioHang = null;
        }
        return objGioHang;
    }

    @Override
    public boolean add(GioHang gioHang) {
        GioHang objGioHang = gioHangResoisitory.save(gioHang);

        if (objGioHang != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean update(GioHang gioHang) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
