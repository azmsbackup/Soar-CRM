package com.soarcrm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMDepartmentDao;
import com.soarcrm.domain.CRMDepartment;

public class CRMDepartmentServiceImpl implements CRMDepartmentService {

    @Autowired
    private CRMDepartmentDao CRMdepartmentDao;

    @Override
    public void insertDepartment(CRMDepartment department) throws Exception {
        CRMdepartmentDao.insertDepartment(department);
    }

    @Override
    public List<CRMDepartment> getDepartmentList() throws Exception {
        return CRMdepartmentDao.getDepartmentList();
    }

    @Override
    public CRMDepartment getDepartment(String id) throws Exception {
        return CRMdepartmentDao.getDepartment(id);
    }

    @Override
    public void updateDepartment(CRMDepartment department) throws Exception {
        CRMdepartmentDao.updateDepartment(department);
    }

    @Override
    public CRMDepartment checkDepartmentname(CRMDepartment department) throws Exception {
        return CRMdepartmentDao.checkDepartmentname(department);
    }

    @Override
    public CRMDepartment checkEditDepartmentname(CRMDepartment department) throws Exception {
        return CRMdepartmentDao.checkEditDepartmentname(department);
    }

    @Override
    public void insertUserDeptMap(CRMDepartment department) throws Exception {
        CRMdepartmentDao.insertUserDeptMap(department);
    }

    @Override
    public List<CRMDepartment> getUserDepartmentList(String userId) throws Exception {
        return CRMdepartmentDao.getUserDepartmentList(userId);
    }

    @Override
    public ArrayList<CRMDepartment> getUsermappingList(String id) throws Exception {
        return CRMdepartmentDao.getUsermappingList(id);
    }

    @Override
    public ArrayList<CRMDepartment> getUsermappingListforuser(String id) throws Exception {
        return CRMdepartmentDao.getUsermappingListforuser(id);
    }

    @Override
    public void inactiveDepartment(CRMDepartment department) throws Exception {
        CRMdepartmentDao.inactiveDepartment(department);
    }

    @Override
    public List<CRMDepartment> getDepartmentListwithInactive() throws Exception {
        return CRMdepartmentDao.getDepartmentListwithInactive();
    }

}
