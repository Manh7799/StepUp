package org.example.project_cuoikhoa_javaweb.controller;

import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.entities.VaiTro;
import org.example.project_cuoikhoa_javaweb.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class VaiTroController {

    @Autowired
    VaiTroService vaiTroService;

    @RequestMapping(value = "/admin/vaitro", method = RequestMethod.GET)
    public String hienThiDanhSachVaiTro(Model model) {
        List<VaiTro> lstVaiTro = vaiTroService.layDanhSach();

        model.addAttribute("lstVaiTro", lstVaiTro);

        return "admin/QuanLyVaiTro";
    }
}
