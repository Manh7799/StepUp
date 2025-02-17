package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chuDeDao")
public class ChuDeImpl implements ChuDeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ChuDeRepository chuDeReposito;

    @Override
    public List<ChuDe> getList() {
        return chuDeReposito.findAll();
    }

    @Override
    public ChuDe getById(String maChuDe) {
        ChuDe objChuDe = null;
        try {
            objChuDe = (ChuDe) entityManager.createQuery("FROM ChuDe cd WHERE cd.maChuDe = :ma")
                    .setParameter("ma", maChuDe)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objChuDe = null;
        }

        return objChuDe;
    }

    @Override
    public boolean add(ChuDe objCD) {
        ChuDe objChuDe = chuDeReposito.save(objCD);

        if (objChuDe != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean update(ChuDe obj) {
        ChuDe objChuDe = getById(obj.getMaChuDe());

        if (objChuDe != null) {
            objChuDe.setTenChuDe(obj.getTenChuDe());

            chuDeReposito.save(objChuDe);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        ChuDe objChuDe = getById(id);

        if (objChuDe != null) {
            chuDeReposito.delete(objChuDe);

            return true;
        }

        return false;
    }
}
