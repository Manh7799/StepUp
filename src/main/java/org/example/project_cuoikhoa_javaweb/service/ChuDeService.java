package org.example.project_cuoikhoa_javaweb.service;


import org.example.project_cuoikhoa_javaweb.entities.ChuDe;
import org.example.project_cuoikhoa_javaweb.model.ChuDeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuDeService {
    @Autowired
    ChuDeDao chuDeDao;

    public List<ChuDe> layDanhSach() {
        return chuDeDao.getList();
    }

    public ChuDe layChiTiet(String maChuDe) {
        return chuDeDao.getById(maChuDe);
    }

    public boolean themMoi(ChuDe objChuDe)
    {
        return chuDeDao.add(objChuDe);
    }

    public boolean capNhat(ChuDe objChuDe)
    {
        return chuDeDao.update(objChuDe);
    }

    public boolean xoa(String maChuDe)
    {
        return chuDeDao.delete(maChuDe);
    }
}
