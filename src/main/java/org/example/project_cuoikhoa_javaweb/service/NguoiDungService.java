package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.model.NguoiDungDao;
import org.example.project_cuoikhoa_javaweb.model.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiDungService {

    @Autowired
    NguoiDungDao nguoiDungDao;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    public NguoiDung layThongTinDangNhap(String tenDangNhap, String vaiTro, String idNguoiDung)
    {
        System.out.println("Đang tìm thông tin người dùng: " + tenDangNhap);
        NguoiDung nguoiDung = nguoiDungDao.layNguoiDung(tenDangNhap, vaiTro, idNguoiDung);
        if (nguoiDung == null) {
            System.out.println("Không tìm thấy người dùng!");
        } else {
            System.out.println("Người dùng tìm thấy: " + nguoiDung.getTenDangNhap());
            System.out.println("Vai trò: " + nguoiDung.getVaiTro());
            System.out.println("ID Người Dùng: " + nguoiDung.getIdNguoiDung());
        }
        return nguoiDung;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap, String idNguoiDung) {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(tenDangNhap);
        return nguoiDung != null && !nguoiDung.getIdNguoiDung().equals(idNguoiDung);
    }

    public boolean kiemTraTrungEmail(String email, String idNguoiDung) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        return nguoiDung != null && !nguoiDung.getIdNguoiDung().equals(idNguoiDung);
    }

    public boolean kiemTraTrungSoDienThoai(String DienThoai, String idNguoiDung) {
        NguoiDung nguoiDung = nguoiDungRepository.findByDienThoai(DienThoai);
        return nguoiDung != null && !nguoiDung.getIdNguoiDung().equals(idNguoiDung);
    }

    public NguoiDung  layThongTinTheoTenDangNhap(String tenDangNhap) {
        NguoiDung nguoiDung = nguoiDungDao.layNguoiDungTheoTenDangNhap(tenDangNhap);

        return nguoiDung;
    }

    public List<NguoiDung> layDanhSach() {
        return nguoiDungDao.getList();
    }

    public NguoiDung layChiTiet(String id) {
        try {
            return nguoiDungDao.getById(id);
        } catch (Exception e) {
            System.out.println(" Không tìm thấy id người dùng.Chi tiết: " + e.getMessage());
            return null;
        }
    }

    public boolean themMoi(NguoiDung objNguoiDung) {
        try {
            nguoiDungDao.add(objNguoiDung);
            return true;
        } catch (Exception e) {
            System.out.println("Thêm không thành công! Lỗi chi tiết: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhat(NguoiDung objNguoiDung) {
        return nguoiDungDao.update(objNguoiDung);
    }

    public boolean xoa(String id) {
        return nguoiDungDao.delete(id);
    }
}
