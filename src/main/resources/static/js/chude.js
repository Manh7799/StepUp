function layDanhSachChuDe() {
    //Lấy danh sách chủ đề
    $.ajax({
        type: "GET",
        url: "/api/chude",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: true,
        success: function (result) { //Khi lấy được dữ liệu

            var thongtin = "";
            //Duyệt từng dòng lấy được
            $.each(result, function (index, cd) {
                thongtin += "<tr>";
                thongtin += "<td>" + cd.maChuDe + "</td>";
                thongtin += "<td>" + cd.tenChuDe + "</td>";
                var sua = "suaThongTin('" + cd.maChuDe + "')";
                var xoa = "xoaThongTin('" + cd.maChuDe + "')";
                thongtin += "<td><a data-bs-toggle=\"modal\" data-bs-target=\"#modalChuDe\" href='#' onclick=\"" + sua + "\" title='Sửa chủ đề'>Sửa</a>&nbsp;";
                thongtin += "<a href='#' data-bs-toggle=\"modal\" data-bs-target=\"#modalXoa\" title='Xóa người dùng' onclick=\"" + xoa + "\">Xóa</a></td>";
                thongtin += "</tr>";
            });

            $("#dsChuDe").append(thongtin);
        }
    });
}

function xuLyThemMoi() {
    //Lấy thông tin trên giao diện
    var ma = $("#hChuDeId").val();

    var urlPost = '/api/chude/add';
    var methodType = "POST";
    //TH sửa
    if (ma.length > 0) {
        urlPost = '/api/chude/' + ma;
        methodType = "PUT";
    }

    var maCD = $("#maChuDe").val();
    var tenCD = $("#tenChuDe").val();

    //Khai báo mảng
    var formData = {}
    formData["maChuDe"] = maCD;
    formData["tenChuDe"] = tenCD;

    console.log("Mã chủ đề: " + maCD);
    console.log("Tên chủ đề: " + tenCD);

    $.ajax({
        url: urlPost,
        contentType: "application/json; charset=utf-8;",
        dataType: "json",
        data: JSON.stringify(formData),
        type: methodType,
        success: function (data) {
            if (data.maChuDe != null) {
                $("#modalChuDe").modal("hide")
                //Reload lại trang
                window.location.reload();
            } else {
                $('#tile-body').nextAll(".spanError").remove();
                $('#tile-body').after('<div class="alert alert-dismissible alert-danger spanError">' + data.name + '</div>')

            }
        },
        error: function (error) {
            alert("Có lỗi xảy ra " + error.name);
        }
    });
}

/*
        * Hàm hiển thị thông tin chi tiết chủ đề bằng jquery ajax
*/
function thongTinChiTiet(maCD) {
    $.ajax({
        type: "GET",
        url: "/api/chude/" + maCD,
        data: {
            id: maCD
        },
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        async: true,
        success: function (data) {
            //Hiển thị lên giao diện
            $("#modalTitle").text("Sửa thông tin chủ đề");
            $("#hChuDeId").val(data.maChuDe);
            $("#maChuDe").val(data.maChuDe);
            $("#tenChuDe").val(data.tenChuDe);
        }
    });
}

function thucHienXoa() {
    var maCD = $("#hChuDeId").val();

    $.ajax({
        url: '/api/chude/' + maCD,
        contentType: "application/json; charset=utf-8;",
        dataType: "json",
        type: "DELETE",
        success: function (data) {
            if (data.name != null) {
                $("#modalXoa").modal("hide")
                //Reload lại trang
                window.location.reload();
            } else {
                $('#title-delete').nextAll(".spanError").remove();
                $('#title-delete').after('<div class="alert alert-dismissible alert-danger spanError">' + data.name + '</div>')

            }
        },
        error: function (error) {
            alert("Có lỗi xảy ra " + error.name);
        }
    });
}
