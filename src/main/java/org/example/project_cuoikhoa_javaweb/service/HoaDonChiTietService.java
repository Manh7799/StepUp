package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.HoaDonChiTiet;
import org.example.project_cuoikhoa_javaweb.model.HoaDonChiTiepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietService {

    @Autowired
    HoaDonChiTiepDao hoaDonChiTiepDao;

    public List<HoaDonChiTiet> layDanhSach() {
        return hoaDonChiTiepDao.getList();
    }

    public HoaDonChiTiet layChiTiet(String maHoaDon) {
        return hoaDonChiTiepDao.getById(maHoaDon);
    }

    public boolean themMoi(HoaDonChiTiet objHoaDon) {
        return hoaDonChiTiepDao.add(objHoaDon);
    }

    public boolean capNhat(HoaDonChiTiet objHoaDon) {
        return hoaDonChiTiepDao.update(objHoaDon);
    }

    public boolean xoa(String maHoaDon) {
        return hoaDonChiTiepDao.delete(maHoaDon);
    }

    public List<HoaDonChiTiet> layDanhSachTheoMaHoaDon(String maHoaDon) {
        return hoaDonChiTiepDao.timTheoMaHoaDon(maHoaDon);
    }

    public List<HoaDonChiTiet> layDanhSachTheoId(String idNguoiDung) {
        return hoaDonChiTiepDao.timTheoIdNguoiDung(idNguoiDung);
    }
}
