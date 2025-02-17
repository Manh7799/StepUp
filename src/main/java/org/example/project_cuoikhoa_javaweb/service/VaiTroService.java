package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.entities.VaiTro;
import org.example.project_cuoikhoa_javaweb.model.VaiTroDao;
import org.example.project_cuoikhoa_javaweb.model.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    VaiTroDao vaiTroDao;

    public String getTenVaiTroById(String id){
        VaiTro vaiTro = vaiTroRepository.findById(id).orElse(null);
        return vaiTro != null ? vaiTro.getTenVaiTro() : null;
    }

    public List<VaiTro> layDanhSach() {
        return vaiTroDao.getList();
    }

    public VaiTro layChiTiet(String id) {
        return vaiTroDao.getById(id);
    }

    public boolean themMoi(VaiTro objVaiTro) {
        return vaiTroDao.add(objVaiTro);
    }

    public boolean capNhat(VaiTro objVaiTro) {
        return vaiTroDao.update(objVaiTro);
    }

    public boolean xoa(String id) {
        return vaiTroDao.delete(id);
    }
}
