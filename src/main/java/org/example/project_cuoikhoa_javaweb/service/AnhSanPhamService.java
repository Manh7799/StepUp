package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.AnhSanPham;
import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.model.AnhSanPhamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnhSanPhamService {

    @Autowired
    AnhSanPhamDao anhSanPhamDao;

    public List<AnhSanPham> layDanhSach() {
        return anhSanPhamDao.getList();
    }

    public AnhSanPham layChiTiet(String maSanPham) {
        return anhSanPhamDao.getById(maSanPham);
    }

    public List<AnhSanPham> layDanhSachTheoMaHoaDon ( String maHoaDon) {
        return anhSanPhamDao.timTheoMaSanPham(maHoaDon);
    }

    public boolean themMoi(AnhSanPham objSanPham)
    {
        return anhSanPhamDao.add(objSanPham);
    }

    public boolean capNhat(AnhSanPham objSanPham)
    {
        return anhSanPhamDao.update(objSanPham);
    }

    public boolean xoa(String maSanPham)
    {
        return anhSanPhamDao.delete(maSanPham);
    }

}
