package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.servlet.http.HttpSession;
import org.example.project_cuoikhoa_javaweb.entities.*;
import org.example.project_cuoikhoa_javaweb.model.GioHangRepository;
import org.example.project_cuoikhoa_javaweb.model.HoaDonChiTietRepository;
import org.example.project_cuoikhoa_javaweb.model.HoaDonRepository;
import org.example.project_cuoikhoa_javaweb.service.GioHangService;
import org.example.project_cuoikhoa_javaweb.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
public class GioHangController {

    @Autowired
    GioHangService gioHangService;

    @Autowired
    GioHangRepository gioHangResoisitory;

    @Autowired
    private SanPhamService sanPhamService;

    @RequestMapping("/users/giohang")
    public String xemGioHang(Model model, HttpSession session) {
        if (session.getAttribute("gioHang") == null) {
            session.setAttribute("gioHang", new ArrayList<>());
        }
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        int tongTien = gioHang.stream().mapToInt(GioHangItem::getThanhTien).sum();

        model.addAttribute("gioHang", gioHang);
        model.addAttribute("tongTien", tongTien);

        return "users/GioHang";
    }

    @RequestMapping(value = "/users/giohang/them", method = RequestMethod.GET)
    @ResponseBody
    public GioHangItem themVaoGioHang(HttpSession session, @RequestParam String maSanPham) {

        SanPham sanPham = sanPhamService.layChiTiet(maSanPham);

        if (sanPham != null) {

            List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
            if (gioHang == null) {
                gioHang = new ArrayList<>();
            }

            boolean isExisting = false;
            for (GioHangItem item : gioHang) {
                if (item.getMaSanPham().equals(maSanPham)) {
                    item.setSoLuong(item.getSoLuong() + 1);
                    item.setThanhTien(item.getSoLuong() * item.getDonGia());
                    isExisting = true;
                    return item;
                }
            }

            if (!isExisting) {
                GioHangItem newItem = new GioHangItem();
                newItem.setMaSanPham(sanPham.getMaSanPham());
                newItem.setTenSanPham(sanPham.getTenSanPham());
                newItem.setAnh(sanPham.getAnh());
                newItem.setDonGia(sanPham.getGiaTien());
                newItem.setSoLuong(1);
                newItem.setThanhTien(newItem.getSoLuong() * newItem.getDonGia());
                gioHang.add(newItem);
                return newItem;
            }
        }

        return null;
    }

    @RequestMapping(value = "/users/giohang/xoa", method = RequestMethod.GET)
    @ResponseBody
    public GioHangItem xoaSanPhamKhoiGioHang(HttpSession session, @RequestParam String maSanPham) {

        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        if (gioHang != null) {
            for(GioHangItem item: gioHang){
                if(item.getMaSanPham().equals(maSanPham)){
                    item.setSoLuong(item.getSoLuong() - 1);
                    if(item.getSoLuong() == 0){
                        gioHang.remove(item);
                        session.setAttribute("gioHang",gioHang);
                        return new GioHangItem();
                    }
                    item.setThanhTien(item.getSoLuong() * item.getDonGia());
                    session.setAttribute("gioHang",gioHang);
                    return item;
                }
            }
        }

        return new GioHangItem();
    }

    @GetMapping("/users/giohang/tongtien")
    public ResponseEntity<TongTienDto> getTongTien(HttpSession session) {
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        if(gioHang == null || gioHang.isEmpty()){
            return ResponseEntity.ok(new TongTienDto(0));
        }

        int tongTien = gioHang.stream().mapToInt(GioHangItem::getThanhTien).sum();

        return ResponseEntity.ok(new TongTienDto(tongTien));
    }

    @GetMapping("/users/giohang/modal")
    public String getModalContent(Model model,HttpSession session) {

        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        int tongTien = 0;
        if(gioHang != null) {
            tongTien = gioHang.stream().mapToInt(GioHangItem::getThanhTien).sum();
        }

        model.addAttribute("gioHang", gioHang);
        model.addAttribute("tongTien", tongTien);
        return "/users/ThanhToan";
    }

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @PostMapping("/users/giohang/thanhtoan")
    public String thanhToanDonHang(@RequestParam("inpHo") String ho,
                                   @RequestParam("inpTen") String ten,
                                   @RequestParam("inpDiaChi") String diaChi,
                                   @RequestParam("inpEmail") String email,
                                   @RequestParam("inpSoDienThoai") String soDienThoai,
                                   @RequestParam("inpGhiChu") String ghiChu,
                                   Model model, HttpSession session) {

        String idNguoiDung = (String) session.getAttribute("userIdNguoiDung");

        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
            return "redirect:/users/giohang";
        }

        String maHoaDon = "HD" + new Random().nextInt(1000000);

        int tongTien = gioHang.stream().mapToInt(item -> item.getSoLuong() * item.getDonGia()).sum();

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setIdNguoiDung(idNguoiDung);
        hoaDon.setNguoiMua(ho + " " + ten);
        hoaDon.setDiaChi(diaChi);
        hoaDon.setEmail(email);
        hoaDon.setSoDienThoai(soDienThoai);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setNgayDatHang(LocalDate.now());
        hoaDon.setSize(null);
        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);


        for (GioHangItem item : gioHang) {
            HoaDonChiTiet chiTiet = new HoaDonChiTiet();
            chiTiet.setIdNguoiDung(idNguoiDung);
            chiTiet.setMaHoaDon(maHoaDon);
            chiTiet.setMaSanPham(item.getMaSanPham());
            chiTiet.setTenSanPham(item.getTenSanPham());
            chiTiet.setAnhSanPham(item.getAnh());
            chiTiet.setSoLuong(item.getSoLuong());
            chiTiet.setGiaTien(item.getSoLuong() * item.getDonGia());

            hoaDonChiTietRepository.save(chiTiet);
            System.out.println("Đã lưu sản phẩm: " + chiTiet.getMaSanPham());
        }

        session.removeAttribute("gioHang");

        model.addAttribute("message", "Đặt hàng thành công! Mã hóa đơn: " + maHoaDon);

        return "redirect:/users/giohang";
    }

}
