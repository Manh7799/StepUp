package org.example.project_cuoikhoa_javaweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.project_cuoikhoa_javaweb.entities.*;
import org.example.project_cuoikhoa_javaweb.model.SanPhamModel;
import org.example.project_cuoikhoa_javaweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SanPhamController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    AnhSanPhamService anhSanPhamService;

    @RequestMapping(value = "/trangchu", method = RequestMethod.GET)
    public String hienThiDanhSachSanPham(Model model) {
        List<SanPham> danhSachSanPham = sanPhamService.layDanhSach();
        List<SanPham> sanPhamDaDuyet = danhSachSanPham.stream()
                .filter(s -> s.getDaDuyet() == 1)
                .collect(Collectors.toList());
        model.addAttribute("lstSanPham", sanPhamDaDuyet);

        return "/TrangChu";
    }

    @RequestMapping(value = "/sanpham")
    public String hienThiDanhSachSanPhamDaDuyet(@ModelAttribute("sanPham") SanPhamModel objSanPham, Model model) {

        List<SanPham> danhSachSanPham = sanPhamService.timKiemThongTinSanPham(objSanPham.getTuKhoa(), objSanPham.getMaChuDe());

        List<SanPham> sanPhamDaDuyet = danhSachSanPham.stream()
                .filter(s -> s.getDaDuyet() == 1)
                .collect(Collectors.toList());

        model.addAttribute("lstSanPham", sanPhamDaDuyet);

        return "/QuanLySanPham";
    }

    @RequestMapping(value = "/admin/sanpham")
    public String hienThiDanhSachSanPham(@ModelAttribute("sanPham") SanPhamModel objSanPham, @RequestParam(value = "giaTienMin", required = false) String giaTienMinStr, @RequestParam(value = "giaTienMax", required = false) String giaTienMaxStr, Model model) {

        Double giaTienMin = null;
        Double giaTienMax = null;

        if (giaTienMinStr != null && !giaTienMinStr.isEmpty()) {
            giaTienMin = Double.parseDouble(giaTienMinStr);
        }
        if (giaTienMaxStr != null && !giaTienMaxStr.isEmpty()) {
            giaTienMax = Double.parseDouble(giaTienMaxStr);
        }


        List<SanPham> danhSachSanPham = sanPhamService.timKiemSanPham(
                objSanPham.getTuKhoa(),
                objSanPham.getMaChuDe(),
                objSanPham.getMaHang(),
                objSanPham.getMaNhaCungCap(),
                giaTienMin,
                giaTienMax
        );

        if (danhSachSanPham != null){
            List<SanPham> sanPhamDaDuyet = danhSachSanPham.stream()
                    .filter(s -> s.getDaDuyet() == 1)
                    .collect(Collectors.toList());

            model.addAttribute("lstSanPham", sanPhamDaDuyet);
            model.addAttribute("danhSachSanPham", danhSachSanPham);
        }

        model.addAttribute("sanPham", objSanPham);
        model.addAttribute("lstChuDe", sanPhamService.getAllChuDe());
        model.addAttribute("lstHangGiay", sanPhamService.getAllHangGiay());
        model.addAttribute("lstNhaCungCap", sanPhamService.getAllNhaCungCap());
        model.addAttribute("giaTienMin", giaTienMinStr);
        model.addAttribute("giaTienMax", giaTienMaxStr);
        return "/admin/QuanLySanPham";
    }

    @RequestMapping(value = "/admin/duyetsanpham", method = RequestMethod.GET)
    public String hienThiDanhSachSanPhamChuaDuyet(@ModelAttribute("sanPham") SanPhamModel objSanPham,@RequestParam(value = "giaTienMin", required = false) String giaTienMinStr, @RequestParam(value = "giaTienMax", required = false) String giaTienMaxStr, Model model) {

        Double giaTienMin = null;
        Double giaTienMax = null;

        if (giaTienMinStr != null && !giaTienMinStr.isEmpty()) {
            giaTienMin = Double.parseDouble(giaTienMinStr);
        }

        if (giaTienMaxStr != null && !giaTienMaxStr.isEmpty()) {
            giaTienMax = Double.parseDouble(giaTienMaxStr);
        }

        List<SanPham> danhSachSanPham = sanPhamService.timKiemSanPham(
                objSanPham.getTuKhoa(),
                objSanPham.getMaChuDe(),
                objSanPham.getMaHang(),
                objSanPham.getMaNhaCungCap(),
                giaTienMin,
                giaTienMax
        );

        if(danhSachSanPham != null) {
            List<SanPham> sanPhamChuaDuyet = danhSachSanPham.stream()
                    .filter(s -> s.getDaDuyet() == 0)
                    .collect(Collectors.toList());
            model.addAttribute("lstSanPham", sanPhamChuaDuyet);
            model.addAttribute("danhSachSanPham", danhSachSanPham);
        }

        model.addAttribute("sanPham", objSanPham);
        model.addAttribute("lstChuDe", sanPhamService.getAllChuDe());
        model.addAttribute("lstHangGiay", sanPhamService.getAllHangGiay());
        model.addAttribute("lstNhaCungCap", sanPhamService.getAllNhaCungCap());
        model.addAttribute("giaTienMin",giaTienMin);
        model.addAttribute("giaTienMax",giaTienMax);


        return "/admin/DuyetSanPham";
    }

    @RequestMapping(value = "/admin/sanpham/them")
    public String hienThiThemMoiSanPham(Model model) {
        model.addAttribute("sanPham", new SanPham());

        return "/admin/SanPhamAdd";
    }

    @RequestMapping(value = "/admin/sanpham/sua/{id}")
    public String hienThiChiTietSanPham(@PathVariable("id") String id, Model model) {

        SanPham objSanPham = sanPhamService.layChiTiet(id);

        model.addAttribute("sanPham", objSanPham);

        return "admin/SanPhamAdd";
    }

    @RequestMapping(value = "/trangchitiet/{id}")
    public String xemChiTiet(@PathVariable("id") String id, Model model) {

        SanPham objSanPham = sanPhamService.layChiTiet(id);

        model.addAttribute("sanPham", objSanPham);

        List<AnhSanPham>  objAnh = anhSanPhamService.layDanhSachTheoMaHoaDon(id);

        model.addAttribute("lstAnh", objAnh);

        return "TrangChiTiet";
    }

    @RequestMapping(value = "/admin/sanpham/xoa/{id}")
    public String xoaThongTinSanPham(@PathVariable("id") String id) {

        SanPham objSanPham = sanPhamService.layChiTiet(id);

        if (objSanPham != null) {
            boolean ketQua = sanPhamService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/sanpham";
            }
        }

        return "/admin/QuanLySanPham";
    }

    @Value("fileupload.path")
    private String fileUploadPath;

    @RequestMapping(value = "/admin/sanpham/themMoiSanPham", method = RequestMethod.POST)
    public String themMoiHoacSuaSanPham(@Valid @ModelAttribute("sanPham") SanPham objSanPham, BindingResult result, @RequestParam("fUpload") MultipartFile fUpload, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("sanPham", objSanPham);

            return "admin/SanPhamAdd";

        } else {
            HttpSession session = request.getSession();
            String tenDangNhap = (String) session.getAttribute("userOnline");
            String tenAnh = "";
            boolean isInsert = true;

            // Neu da co sach thi la sua
            SanPham objSanPham01 = sanPhamService.layChiTiet(objSanPham.getMaSanPham());

            if (objSanPham01 != null) {
                isInsert = false;
                tenAnh = objSanPham01.getAnh();
            }

            // Xu ly upload file
            if (fUpload != null && !fUpload.isEmpty()) {
                //Lấy tên ảnh
                tenAnh = fUpload.getOriginalFilename();
                String strPath = fileUploadPath;
                try {
                    //Tạo file
                    File file = new File(strPath, tenAnh);
                    System.out.println("Đường dẫn upload: " + strPath);
                    fUpload.transferTo(file);
                } catch (IOException ex) {
                    System.out.println("Có lỗi xảy ra khi upload file: " + ex.getMessage());
                }
            }

            objSanPham.setAnh(tenAnh);

            boolean ketQua = false;

            if (isInsert) {
                objSanPham.setNgayTao(new Date());
                objSanPham.setNgayCapNhat(new Date());
                objSanPham.setDaDuyet(0);

                ketQua = sanPhamService.themMoi(objSanPham);
            } else {
                objSanPham.setNgayCapNhat(new Date());
                objSanPham.setDaDuyet(1);
                objSanPham.setNguoiDuyet(tenDangNhap);

                ketQua = sanPhamService.capNhat(objSanPham);
            }

            if (ketQua) {
                return "redirect:/admin/sanpham";
            }

            return "admin/SanPhamAdd";
        }
    }

    @RequestMapping(value = "/admin/duyet/duyetsanpham", method = RequestMethod.POST)
    public String duyetSanPham(@RequestParam("maSanPham") SanPham objSanPham,
                               HttpServletRequest request,
                               Model model) {

        HttpSession session = request.getSession();

        String tenDangNhap = (String) session.getAttribute("userOnline");

        boolean isInsert = true;
        SanPham objSanPham01 = sanPhamService.layChiTiet(objSanPham.getMaSanPham());

        if (objSanPham01 != null) {
            isInsert = false;
        }

        boolean ketQua = false;

        if (isInsert) {
            ketQua = sanPhamService.themMoi(objSanPham);
        } else {
            objSanPham.setNgayDuyet(new Date());
            objSanPham.setDaDuyet(1);
            objSanPham.setNguoiDuyet(tenDangNhap);

            ketQua = sanPhamService.capNhat(objSanPham);
        }

        if (ketQua) {
            return "redirect:/admin/duyetsanpham";
        }

        model.addAttribute("errorMessage", "Có lỗi xảy ra khi duyệt sản phẩm.");
        return "admin/DuyetSanPham";
    }

    @PostMapping("/admin/sanpham/duyet-hang-loat")
    public ResponseEntity<Map<String, Object>> duyetHangLoat(@RequestBody List<String> productIds, HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        HttpSession session = request.getSession();

        String nguoiDuyet = (String) session.getAttribute("userOnline");

        if (productIds != null && !productIds.isEmpty()) {
            try {
                for (String productId : productIds) {
                    SanPham sp = sanPhamService.layChiTiet(productId);
                    if (sp != null) {
                        sp.setNguoiDuyet(nguoiDuyet);
                        sp.setDaDuyet(1);
                        sp.setNgayDuyet(new Date());

                        sanPhamService.capNhat(sp);
                    }

                }
                response.put("status", "success");
                response.put("message", "Duyệt thành công");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                response.put("status", "error");
                response.put("message", "Lỗi trong quá trình duyệt: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            response.put("status", "error");
            response.put("message", "Không có sản phẩm nào được chọn");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/admin/sanpham/bo-duyet-hang-loat")
    public ResponseEntity<Map<String, Object>> boDuyetHangLoat(@RequestBody List<String> productIds) {

        Map<String, Object> response = new HashMap<>();

        if (productIds != null && !productIds.isEmpty()) {
            try {
                for (String productId : productIds) {
                    SanPham sp = sanPhamService.layChiTiet(productId);

                    if (sp != null) {
                        sp.setNguoiDuyet(null);
                        sp.setDaDuyet(0);
                        sp.setNgayDuyet(null);
                        sanPhamService.capNhat(sp);
                    } else {
                        System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
                        response.put("status", "error");
                        response.put("message", "Không tìm thấy sản phẩm với mã: " + productId);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
                response.put("status", "success");
                response.put("message", "Bỏ duyệt thành công");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
                e.printStackTrace(); // In ra stack trace để debug
                response.put("status", "error");
                response.put("message", "Lỗi trong quá trình bỏ duyệt: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            response.put("status", "error");
            response.put("message", "Không có sản phẩm nào được chọn");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Autowired
    HangGiayService hangGiayService;

    @ModelAttribute("lstHangGiay")
    public List<HangGiay> hienThiDanhSachHangGiay() {

        List<HangGiay> lstHangGiay = hangGiayService.layDanhSach();

        return lstHangGiay;
    }

    @Autowired
    ChuDeService chuDeService;

    @ModelAttribute("lstChuDe")
    public List<ChuDe> hienThiDanhSachChuDe() {

        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        return lstChuDe;
    }

    @Autowired
    NhaCungCapService nhaCungCapService;

    @ModelAttribute("lstNhaCungCap")
    public List<NhaCungCap> hienThiDanhSachNhaCungCap() {

        List<NhaCungCap> lstNhaCungCap = nhaCungCapService.layDanhSach();

        return lstNhaCungCap;
    }
}
