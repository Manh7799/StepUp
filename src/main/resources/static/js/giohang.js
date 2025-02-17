// Hàm thêm sản phẩm vào giỏ hàng
function themVaoGioHang(maSanPham) {
    $.ajax({
        url: '/admin/giohang/them',
        type: 'GET',
        data: { maSanPham: maSanPham },
        success: function(response) {
            // Cập nhật giỏ hàng sau khi thêm sản phẩm mới
            updateGioHang(response);
        },
        error: function() {
            alert('Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng');
        }
    });
}

// Cập nhật giỏ hàng trên giao diện
function updateGioHang(gioHang) {
    var gioHangHTML = '';
    var tongTien = 0;

    gioHang.forEach(function(item) {
        gioHangHTML += '<div class="product-cart d-flex">';
        gioHangHTML += '<div class="one-forth">';
        gioHangHTML += '<div class="product-img">';
        gioHangHTML += '<img src="/images/' + item.anh + '" class="img-fluid" alt="Hình ảnh sản phẩm">';
        gioHangHTML += '</div><div class="display-tc"><h3>' + item.tenSanPham + '</h3></div>';
        gioHangHTML += '</div><div class="one-eight text-center"><span class="price">' + item.donGia.toLocaleString() + '</span></div>';
        gioHangHTML += '<div class="one-eight text-center"><span class="price">' + item.soLuong + '</span></div>';
        gioHangHTML += '<div class="one-eight text-center"><span class="price">' + item.thanhTien.toLocaleString() + '</span></div>';
        gioHangHTML += '<div class="one-eight text-center"><a href="javascript:void(0);" class="ti-plus" onclick="themVaoGioHang(\'' + item.maSanPham + '\')"></a></div>';
        gioHangHTML += '<div class="one-eight text-center"><a href="javascript:void(0);" class="ti-minus" onclick="xoaSanPham(\'' + item.maSanPham + '\')"></a></div>';
        gioHangHTML += '</div>';

        tongTien += item.thanhTien;
    });

    // Cập nhật giỏ hàng trong DOM
    $('#gioHangContainer').html(gioHangHTML);

    // Cập nhật tổng tiền
    $('#tongTien').text(tongTien.toLocaleString() + ' VNĐ');
}

// Hàm xóa sản phẩm khỏi giỏ hàng
function xoaSanPham(maSanPham) {
    // Tạo yêu cầu AJAX để xóa sản phẩm
    $.ajax({
        url: '/admin/giohang/xoa',
        type: 'GET',
        data: { maSanPham: maSanPham },
        success: function(response) {
            updateGioHang(response);  // Cập nhật lại giỏ hàng sau khi xóa
        },
        error: function() {
            alert('Có lỗi xảy ra khi xóa sản phẩm');
        }
    });
}