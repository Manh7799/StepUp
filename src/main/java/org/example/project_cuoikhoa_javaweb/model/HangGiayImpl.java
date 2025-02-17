package org.example.project_cuoikhoa_javaweb.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.project_cuoikhoa_javaweb.entities.HangGiay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hangGiayDao")
public class HangGiayImpl implements HangGiayDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    HangGiayRepository hangGiayRepository;

    @Override
    public List<HangGiay> getList() {
        return hangGiayRepository.findAll();
    }

    @Override
    public HangGiay getById(String id) {
        return null;
    }

    @Override
    public boolean add(HangGiay hangGiay) {
        return false;
    }

    @Override
    public boolean update(HangGiay hangGiay) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
