package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.HangGiay;
import org.example.project_cuoikhoa_javaweb.model.HangGiayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HangGiayService {

    @Autowired
    HangGiayDao hangGiayDao;

    public List<HangGiay> layDanhSach() {
        return hangGiayDao.getList();
    }

    public HangGiay layChiTiet(String maSanPham) {
        return hangGiayDao.getById(maSanPham);
    }

    public boolean themMoi(HangGiay objSanPham) {
        return hangGiayDao.add(objSanPham);
    }

    public boolean capNhat(HangGiay objSanPham) {
        return hangGiayDao.update(objSanPham);
    }

    public boolean xoa(String maSanPham) {
        return hangGiayDao.delete(maSanPham);
    }
}
