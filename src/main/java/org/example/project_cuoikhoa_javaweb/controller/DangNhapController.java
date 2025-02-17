package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.project_cuoikhoa_javaweb.entities.NguoiDung;
import org.example.project_cuoikhoa_javaweb.service.NguoiDungService;
import org.example.project_cuoikhoa_javaweb.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@Controller
public class DangNhapController {

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    VaiTroService vaiTroService;

    @RequestMapping(value = "/dangnhap", method = RequestMethod.GET)
    public String hienThiDangNhap(Model model) {
        model.addAttribute("user", new NguoiDung());

        return "/DangNhap";
    }

    @RequestMapping(value = "/thucHienDangNhap", method = RequestMethod.POST)
    public String xuLyDangNhap(@ModelAttribute("user") NguoiDung objUser, Model model, HttpSession session) {
        model.addAttribute("user", objUser);

        if (objUser.getTenDangNhap().isEmpty()) {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên đăng nhập");
            return "/DangNhap";
        }

        if (objUser.getMatKhau().isEmpty()) {
            model.addAttribute("thongBao", "Bạn cần phải nhập mật khẩu đăng nhập");
            return "/DangNhap";
        }

        if (!objUser.getTenDangNhap().isEmpty() && !objUser.getMatKhau().isEmpty()) {
            NguoiDung objUser2 = nguoiDungService.layThongTinTheoTenDangNhap(objUser.getTenDangNhap());

            if (objUser2 != null) {
                if (objUser.getMatKhau().equals(objUser2.getMatKhau())) {
                    session.setAttribute("userOnline", objUser2.getTenDangNhap());
                    System.out.println("Tên đăng nhập là: " + objUser2.getTenDangNhap());
                    String tenVaiTro = vaiTroService.getTenVaiTroById(objUser2.getVaiTro());
                    session.setAttribute("userRole", tenVaiTro);
                    session.setAttribute("userIdNguoiDung", objUser2.getIdNguoiDung());
                    if(!tenVaiTro.equals("admin")){
                        return "redirect:/trangchu";
                    } else {
                        return "redirect:/admin/sanpham";
                    }

                } else {
                    model.addAttribute("thongBao", "Mật khẩu đăng nhập không chính xác");
                    return "/DangNhap";
                }
            } else {
                model.addAttribute("thongBao", "Tài khoản không tồn tại. Bạn vui lòng kiểm tra lại thông tin");
                return "/DangNhap";
            }
        }

        return "/DangNhap";
    }

    @RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
    public String hienThiDangXuat(Model model, HttpSession session, HttpServletResponse response) {

        session.removeAttribute("userOnline");
        session.removeAttribute("userRole");
        session.removeAttribute("userIdNguoiDung");

        return "redirect:/dangnhap";
    }

    @RequestMapping(value = "/dangky")
    public String hienThiDangKyNguoiDung(Model model) {
        model.addAttribute("user", new NguoiDung());

        return "/DangKy";
    }

    @RequestMapping(value = "/dangKyTaiKhoan", method = RequestMethod.POST)
    public String themMoiTaiKhoan(@Valid @ModelAttribute("user") NguoiDung objNguoiDung, BindingResult result, Model model) {
        boolean ketQua = false;

        String idNguoiDung = "ND" + new Random().nextInt(1000000);

        objNguoiDung.setIdNguoiDung(idNguoiDung);
        objNguoiDung.setNgayTao(new Date());
        objNguoiDung.setVaiTro("customer");

        ketQua = nguoiDungService.themMoi(objNguoiDung);

        if (ketQua) {
            return "redirect:/dangnhap";
        }

        return "/DangKy";
    }

    @ControllerAdvice
    public class GlobalController {
        @ModelAttribute("tenDangNhap")
        public String addTenDangNhapToModel(HttpSession session) {
            Object userOnline = session.getAttribute("userOnline");
            return userOnline != null ? userOnline.toString() : null;
        }
    }

    @RequestMapping("/err")
    public String showAccessDeniedPage() {
        return "/Loi";
    }
}
