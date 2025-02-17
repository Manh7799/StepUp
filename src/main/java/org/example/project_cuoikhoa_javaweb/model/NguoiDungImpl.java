package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("nguoiDungDao")
public class NguoiDungImpl implements NguoiDungDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Override
    public NguoiDung layNguoiDung(String tenDangNhap, String vaiTro, String idNguoiDung) {
        return nguoiDungRepository.findByTenDangNhapAndIdNguoiDungAndVaiTro(tenDangNhap, vaiTro, idNguoiDung);
    }

    @Override
    public NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public NguoiDung layNguoiDungTheoId(String idNguoiDung) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        NguoiDung objNguoiDung = getById(id);

        if(objNguoiDung != null) {
            nguoiDungRepository.delete(objNguoiDung);

            return true;
        }

        return false;
    }

    @Override
    public NguoiDung getById(String idNguoiDung) {
        NguoiDung objNguoiDung = null;
        try {
            objNguoiDung = (NguoiDung) entityManager.createQuery("FROM NguoiDung nd WHERE nd.idNguoiDung = :id")
                    .setParameter("id", idNguoiDung)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy NguoiDung với ID: " + idNguoiDung);
        } catch (Exception ex) {
            objNguoiDung = null;
        }
        return objNguoiDung;
    }

    @Override
    public List<NguoiDung> getList() {
        return nguoiDungRepository.findAll();
    }

    @Override
    public boolean add(NguoiDung objNguoiDung) {

        entityManager.clear();

        NguoiDung objNguoiDung01 = nguoiDungRepository.save(objNguoiDung);

        if (objNguoiDung01 != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean update(NguoiDung obj) {
        NguoiDung objNguoiDung = nguoiDungRepository.getById(obj.getIdNguoiDung());

        if (objNguoiDung != null) {
            objNguoiDung.setTenDangNhap(obj.getTenDangNhap());
            objNguoiDung.setMatKhau(obj.getMatKhau());
            objNguoiDung.setEmail(obj.getEmail());
            objNguoiDung.setDienThoai(obj.getDienThoai());
            objNguoiDung.setVaiTro(obj.getVaiTro());

            nguoiDungRepository.save(objNguoiDung);
            return true;
        }

        return false;
    }


}
