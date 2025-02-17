package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.AnhSanPham;
import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("anhSanPhamDao")
public class AnhSanPhamImpl implements AnhSanPhamDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    AnhSanPhamRepository anhSanPhamRepository;

    @Override
    public List<AnhSanPham> getList() {
        return anhSanPhamRepository.findAll();
    }

    @Override
    public AnhSanPham getById(String maSanPham) {
        AnhSanPham objAnh = null;
        try {
            objAnh = (AnhSanPham) entityManager.createQuery("FROM AnhSanPham a WHERE a.maSanPham = :ma")
                    .setParameter("ma", maSanPham)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy NguoiDung với ID: " + maSanPham);
        } catch (Exception ex) {
            objAnh = null;
        }
        return objAnh;
    }

    @Override
    public List<AnhSanPham> timTheoMaSanPham(String maSanPham) {
        return anhSanPhamRepository.findByMaSanPham(maSanPham);
    }

    @Override
    public boolean add(AnhSanPham anhSanPham) {
        return false;
    }

    @Override
    public boolean update(AnhSanPham anhSanPham) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
