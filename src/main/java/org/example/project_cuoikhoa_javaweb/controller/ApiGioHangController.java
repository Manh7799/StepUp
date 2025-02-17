package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.servlet.http.HttpSession;
import org.example.project_cuoikhoa_javaweb.entities.GioHangItem;
import org.example.project_cuoikhoa_javaweb.entities.GioHangDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/giohang")
public class ApiGioHangController {

    @GetMapping("/soluong")
    public ResponseEntity<?> laySoLuongSanPhamTrongGio(HttpSession session) {
        if (session.getAttribute("gioHang") == null) {
            return ResponseEntity.ok(new GioHangDto(0));
        }
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        int soLuongSanPham = gioHang.stream().mapToInt(GioHangItem::getSoLuong).sum();
        return ResponseEntity.ok(new GioHangDto(soLuongSanPham));
    }
}


