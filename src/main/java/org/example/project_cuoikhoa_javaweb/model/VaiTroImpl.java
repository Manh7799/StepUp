package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.entities.VaiTro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("vaiTroDao")
public class VaiTroImpl implements VaiTroDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Override
    public List<VaiTro> getList() {
        return vaiTroRepository.findAll();
    }

    @Override
    public VaiTro getById(String vaiTro) {
        VaiTro vaiTro1 = null;
        try {
            vaiTro1 = (VaiTro) entityManager.createQuery("FROM VaiTro v WHERE v.tenVaiTro = :vt")
                    .setParameter("vt", vaiTro)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            vaiTro1 = null;
        }
        return vaiTro1;
    }

    @Override
    public boolean add(VaiTro vaiTro) {
        VaiTro objVaiTro = vaiTroRepository.save(vaiTro);

        if (objVaiTro != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean update(VaiTro vaiTro) {
        VaiTro objVaiTro = vaiTroRepository.getById(vaiTro.getId());
        if (objVaiTro != null) {
            objVaiTro.setTenVaiTro(objVaiTro.getTenVaiTro());

            vaiTroRepository.save(objVaiTro);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        VaiTro objVT = getById(id);

        if(objVT != null) {
            vaiTroRepository.delete(objVT);

            return true;
        }

        return false;
    }
}
