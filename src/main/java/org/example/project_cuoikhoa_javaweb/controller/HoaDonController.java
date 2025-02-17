package org.example.project_cuoikhoa_javaweb.controller;

import org.example.project_cuoikhoa_javaweb.entities.HoaDon;
import org.example.project_cuoikhoa_javaweb.entities.HoaDonChiTiet;
import org.example.project_cuoikhoa_javaweb.service.HoaDonChiTietService;
import org.example.project_cuoikhoa_javaweb.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HoaDonController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    /*
    Controller phần admin
     */
    @RequestMapping(value = "/admin/donhang")
    public String hienThiDanhSachHoaDon(Model model) {

        List<HoaDon> lstHoaDon = hoaDonService.layDanhSach();

        model.addAttribute("lstHoaDon", lstHoaDon);

        return "admin/DonHang";
    }

    @RequestMapping(value = "/admin/donhang/chitiet-xem/{id}")
    public  String hienThiChiTietHoaDon(@PathVariable("id") String maHoaDon, Model model) {
        List<HoaDonChiTiet> objHoaDon = hoaDonChiTietService.layDanhSachTheoMaHoaDon(maHoaDon);

        model.addAttribute("lstHoaDon", objHoaDon);

        HoaDon hoaDon = hoaDonService.layChiTiet(maHoaDon);

        model.addAttribute("hoaDon", hoaDon);

        return "admin/DonHangChiTiet";
    }

    /*
    Controller phần user
     */

    @RequestMapping(value = "/users/hoadon/xem-id/{id}")
    public String hienThiDanhSachHoaDonTheoTen(@PathVariable("id") String id, Model model) {

        List<HoaDon> hoaDon = hoaDonService.layDanhSachTheoId(id);

        model.addAttribute("hoaDon", hoaDon);

        return "/users/HoaDon";
    }

    @RequestMapping(value = "/users/hoadon/chitiet-ma/{mahoadon}")
    public String hienThiDanhSachChiTietHoaDon(@PathVariable("mahoadon") String maHoaDon, Model model) {

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.layDanhSachTheoMaHoaDon(maHoaDon);

        model.addAttribute("hoaDonChiTiet", hoaDonChiTietList);

        HoaDon hoaDon = hoaDonService.layChiTiet(maHoaDon);

        model.addAttribute("hoaDon", hoaDon);

        return "users/HoaDonChiTiet";
    }
    
}
