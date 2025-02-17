package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.validation.Valid;
import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.service.ChuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ChuDeController {
    @Autowired
    ChuDeService chuDeService;

    @RequestMapping(value = "/admin/chude", method = RequestMethod.GET)
    public String hienThiDanhSachChuDe(Model model) {
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);

        return "admin/QuanLyChuDe";
    }

    @RequestMapping(value = "/admin/chude/them")
    public String hienThiThemMoiChuDe(Model model) {
        model.addAttribute("chuDe", new ChuDe());

        return "/admin/ChuDeAdd";
    }

    @RequestMapping(value = "/admin/chude/sua/{id}")
    public String hienThiChiTietChuDe(@PathVariable("id") String id, Model model) {

        ChuDe objChuDe = chuDeService.layChiTiet(id);

        model.addAttribute("chuDe", objChuDe);

        return "admin/ChuDeAdd";
    }

    @RequestMapping(value = "/admin/chude/themMoiChuDe", method = RequestMethod.POST)
    public String themMoiHoacSuaChuDe(@ModelAttribute("chuDe") @Valid ChuDe objChuDe, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("chuDe", objChuDe);

            return "admin/ChuDeAdd";
        } else {
            boolean isInsert = true;

            // Neu da co sach thi la sua
            ChuDe objChuDe01 = chuDeService.layChiTiet(objChuDe.getMaChuDe());

            if (objChuDe01 != null) {
                isInsert = false;
            }

            boolean ketQua = false;

            if (isInsert) {
                ketQua = chuDeService.themMoi(objChuDe);
            } else {
                ketQua = chuDeService.capNhat(objChuDe);
            }

            if (ketQua) {
                return "redirect:/admin/chude";
            }

            return "admin/QuanLyChuDe";
        }
    }

    @RequestMapping(value = "/admin/chude/xoa/{id}")
    public String xoaThongTinChuDe(@PathVariable("id") String id) {

        ChuDe objChuDe = chuDeService.layChiTiet(id);

        if (objChuDe != null) {
            boolean ketQua = chuDeService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/chude";
            }
        }

        return "admin/QuanLyChuDe";
    }
}
