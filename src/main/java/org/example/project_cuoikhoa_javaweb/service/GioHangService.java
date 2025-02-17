package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.GioHang;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.model.GioHangDao;
import org.example.project_cuoikhoa_javaweb.model.GioHangRepository;
import org.example.project_cuoikhoa_javaweb.model.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GioHangService {

    @Autowired
    GioHangDao gioHangDao;

    @Autowired
    GioHangRepository gioHangRepository;


    public List<GioHang> layDanhSach() {
        return gioHangDao.getList();
    }

    public GioHang layChiTiet(String maGioHang) {
        return gioHangDao.getById(maGioHang);
    }

    public boolean themMoi(GioHang objGioHang) {
        return gioHangDao.add(objGioHang);
    }

    public boolean capNhat(GioHang objGioHang) {
        return gioHangDao.update(objGioHang);
    }

    public boolean xoa(String maSanPham) {
        return gioHangDao.delete(maSanPham);
    }
}
