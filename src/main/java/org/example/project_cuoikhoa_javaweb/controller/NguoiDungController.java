package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.validation.Valid;
import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.example.project_cuoikhoa_javaweb.entities.SanPham;
import org.example.project_cuoikhoa_javaweb.entities.VaiTro;
import org.example.project_cuoikhoa_javaweb.service.ChuDeService;
import org.example.project_cuoikhoa_javaweb.service.NguoiDungService;
import org.example.project_cuoikhoa_javaweb.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class NguoiDungController {

    @Autowired
    NguoiDungService nguoiDungService;

    /*
    Controller phần admin
     */
    @RequestMapping(value = "/admin/nguoidung", method = RequestMethod.GET)
    public String hienThiDanhSachNguoiDung(Model model) {
        List<NguoiDung> lstNguoiDung = nguoiDungService.layDanhSach();

        model.addAttribute("lstNguoiDung", lstNguoiDung);

        return "admin/QuanLyNguoiDung";
    }

    @RequestMapping(value = "/admin/nguoidung/them")
    public String hienThiThemMoiNguoiDung(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());

        return "admin/NguoiDung";
    }

    @RequestMapping(value = "/admin/nguoidung/sua/{id}")
    public String hienThiChiTietNguoiDung(@PathVariable("id") String id, Model model) {

        NguoiDung objNguoiDung = nguoiDungService.layChiTiet(id);

        model.addAttribute("nguoiDung", objNguoiDung);

        return "admin/NguoiDung";
    }

    @RequestMapping(value = "/admin/nguoidung/themMoiNguoiDung", method = RequestMethod.POST)
    public String themMoiHoacSuaTaiKhoan(@Valid @ModelAttribute("nguoiDung") NguoiDung objNguoiDung, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Thông tin không hợp lệ! ");
            model.addAttribute("nguoiDung", objNguoiDung);

            return "admin/NguoiDung";
        } else {

            boolean isInsert = objNguoiDung.getIdNguoiDung() == null || objNguoiDung.getIdNguoiDung().isEmpty();

            System.out.println("Id người dùng là: " + objNguoiDung.getIdNguoiDung());

//            NguoiDung objNguoiDung01 = nguoiDungService.layChiTiet(objNguoiDung.getIdNguoiDung());


            boolean ketQua = false;

            if (isInsert) {

                if (nguoiDungService.kiemTraTrungTenDangNhap(objNguoiDung.getTenDangNhap(), objNguoiDung.getIdNguoiDung())) {
                    model.addAttribute("message", "Tên đăng nhập đã tồn tại! ");
                    model.addAttribute("nguoiDung", objNguoiDung);
                    return "admin/NguoiDung";
                }

                if (nguoiDungService.kiemTraTrungEmail(objNguoiDung.getEmail(), objNguoiDung.getIdNguoiDung())) {
                    model.addAttribute("message", "Email đã được sử dụng! ");
                    model.addAttribute("nguoiDung", objNguoiDung);
                    return "admin/NguoiDung";
                }

                if (nguoiDungService.kiemTraTrungSoDienThoai(objNguoiDung.getDienThoai(), objNguoiDung.getIdNguoiDung())) {
                    model.addAttribute("message", "Số điện thoại đã được sử dụng! ");
                    model.addAttribute("nguoiDung", objNguoiDung);
                    return "admin/NguoiDung";
                }

                String idNguoiDung = "ND0" + new Random().nextInt(1000000);

                while (nguoiDungService.layChiTiet(idNguoiDung) != null) {
                    idNguoiDung = "ND0" + new Random().nextInt(1000000);
                }

                objNguoiDung.setNgayTao(new Date());
                objNguoiDung.setIdNguoiDung(idNguoiDung);

                ketQua = nguoiDungService.themMoi(objNguoiDung);
            } else {
                ketQua = nguoiDungService.capNhat(objNguoiDung);
            }

            if (ketQua) {
                return "redirect:/admin/nguoidung";
            }

            return "admin/NguoiDung";
        }
    }

    @RequestMapping(value = "/admin/nguoidung/xoa/{id}")
    public String xoaThongTinNguoiDung(@PathVariable("id") String id) {

        NguoiDung objNguoiDung = nguoiDungService.layChiTiet(id);

        if (objNguoiDung != null) {
            boolean ketQua = nguoiDungService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/nguoidung";
            }
        }

        return "/admin/QuanLyNguoiDung";
    }

    @Autowired
    VaiTroService vaiTroService;

    @ModelAttribute("lstVaiTro")
    public List<VaiTro> hienThiVaiTro() {

        List<VaiTro> lstVaiTro = vaiTroService.layDanhSach();

        return lstVaiTro;
    }

    /*
    Controller phần user
     */

    @RequestMapping(value = "/users/nguoidung/xem/{id}")
    public String hienThiNguoiDung(@PathVariable("id") String id, Model model) {

        NguoiDung objNguoiDung = nguoiDungService.layChiTiet(id);

        model.addAttribute("nguoiDung", objNguoiDung);

        return "users/NguoiDung";
    }

    @RequestMapping(value = "/users/nguoidung/themMoiNguoiDung", method = RequestMethod.POST)
    public String chinhSuaTaiKhoan(@Valid @ModelAttribute("nguoiDung") NguoiDung objNguoiDung, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Thông tin không hợp lệ! ");
            model.addAttribute("nguoiDung", objNguoiDung);

            return "users/NguoiDung";
        } else {

            boolean isInsert = true;

            NguoiDung objNguoiDung01 = nguoiDungService.layChiTiet(objNguoiDung.getIdNguoiDung());
            System.out.println("Id người dùng là: " + objNguoiDung.getIdNguoiDung());

            if (objNguoiDung01 != null) {
                isInsert = false;
            }

            boolean ketQua = false;

            if (isInsert) {
                System.out.println("Bạn không có quyền thêm mới!");
            } else {
                ketQua = nguoiDungService.capNhat(objNguoiDung);
            }

            if (ketQua) {
                return "redirect:/trangchu";
            }

            return "users/NguoiDung";
        }
    }

}
