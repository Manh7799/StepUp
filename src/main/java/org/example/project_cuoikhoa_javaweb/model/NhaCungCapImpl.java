package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.NhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("nhaCungCapDao")
public class NhaCungCapImpl implements NhaCungCapDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    @Override
    public List<NhaCungCap> getList() {
        return nhaCungCapRepository.findAll();
    }

    @Override
    public NhaCungCap getById(String id) {
        return null;
    }

    @Override
    public boolean add(NhaCungCap nhaCungCap) {
        return false;
    }

    @Override
    public boolean update(NhaCungCap nhaCungCap) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
