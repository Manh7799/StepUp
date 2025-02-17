package org.example.project_cuoikhoa_javaweb.controller;

import org.example.project_cuoikhoa_javaweb.entities.AnhSanPham;
import org.example.project_cuoikhoa_javaweb.service.AnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AnhSanPhamController {

    @Autowired
    AnhSanPhamService anhSanPhamService;

    @RequestMapping(value = "/anh/{maSanPham}")
    public String hienThiDanhSachAnh (@PathVariable("maSanPham") String maSanPham, Model model) {
        List<AnhSanPham>  objAnh = anhSanPhamService.layDanhSachTheoMaHoaDon(maSanPham);

        model.addAttribute("lstAnh", objAnh);

        return "TrangChiTiet";
    }
}
