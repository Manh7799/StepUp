package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.model.HoaDonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    HoaDonDao hoaDonDao;

    public List<HoaDon> layDanhSach() {
        return hoaDonDao.getList();
    }

    public HoaDon layChiTiet(String maHoaDon) {
        return hoaDonDao.getById(maHoaDon);
    }

    public boolean themMoi(HoaDon objHoaDon) {
        return hoaDonDao.add(objHoaDon);
    }

    public boolean capNhat(HoaDon objHoaDon) {
        return hoaDonDao.update(objHoaDon);
    }

    public boolean xoa(String maHoaDon) {
        return hoaDonDao.delete(maHoaDon);
    }

    public List<HoaDon> layDanhSachTheoTenNguoiMua(String nguoiDung) {
        return hoaDonDao.timTheoTenNguoiMua(nguoiDung);
    }

    public List<HoaDon> layDanhSachTheoId(String idNguoiDung) {
        return hoaDonDao.timTheoIdNguoiDung(idNguoiDung);
    }

}
