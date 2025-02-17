package org.example.project_cuoikhoa_javaweb.service;

import org.example.project_cuoikhoa_javaweb.entities.NhaCungCap;
import org.example.project_cuoikhoa_javaweb.model.NhaCungCapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaCungCapService {
    @Autowired
    NhaCungCapDao nhaCungCapDao;

    public List<NhaCungCap> layDanhSach() {
        return nhaCungCapDao.getList();
    }

    public NhaCungCap layChiTiet(String maNhaCungCap) {
        return nhaCungCapDao.getById(maNhaCungCap);
    }

    public boolean themMoi(NhaCungCap objNhaCungCap)
    {
        return nhaCungCapDao.add(objNhaCungCap);
    }

    public boolean capNhat(NhaCungCap objNhaCungCap)
    {
        return nhaCungCapDao.update(objNhaCungCap);
    }

    public boolean xoa(String maNhaCungCap)
    {
        return nhaCungCapDao.delete(maNhaCungCap);
    }
}
