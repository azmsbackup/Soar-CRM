package com.soarcrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMUserDao;
import com.soarcrm.domain.CRMEmail;
import com.soarcrm.domain.CRMMainmenu;
import com.soarcrm.domain.CRMRolemaster;
import com.soarcrm.domain.CRMSubmenu;
import com.soarcrm.domain.CRMUser;



public class CRMUserServiceImpl implements CRMUserService
{
	@Autowired
	CRMUserDao CRMuserDao;

	public CRMUser isValidUser(String loginName, String password1) throws Exception 
	{
		return CRMuserDao.isValidUser(loginName,password1);
	}

	public void insertUser(CRMUser user) throws Exception 
	{
		 CRMuserDao.insertUser(user);
	}

	public List<CRMUser> getuserlist() throws Exception 
	{
		return CRMuserDao.getuserlist();
	}
	
	public CRMUser getUser(String id) throws Exception 
	{
		return CRMuserDao.getUser(id);
	}

	public void updateUser(CRMUser user) throws Exception 
	{
		CRMuserDao.updateUser(user);		
	}

	public List<CRMMainmenu> getMainMenuList(String roleid) throws Exception 
	{
		return CRMuserDao.getMainMenuList(roleid);
	}

	public List<CRMSubmenu> getSubMenuList(String menuid, String roleid) throws Exception 
	{
		return CRMuserDao.getSubMenuList(menuid, roleid);
	}

	public List<CRMRolemaster> getRolelist() throws Exception
	{
		return CRMuserDao.getRolelist();		
	}

	public CRMUser isValidSuperUser(String loginName, String password, String password2) throws Exception 
	{
		return CRMuserDao.isValidSuperUser(loginName, password, password2);
	}
	
	public boolean insertExistingClient() throws Exception {
		return CRMuserDao.insertExistingClient();
	
	}
	
	public boolean insertExistingNotes() throws Exception {
		
		return CRMuserDao.insertExistingNotes();
	}

	public CRMEmail getsequencelist(CRMEmail mail) throws Exception 
	{
		return CRMuserDao.getsequencelist(mail);		
	}

	public void insertEMailDetails(CRMEmail mail) throws Exception 
	{
		CRMuserDao.insertEMailDetails(mail);
	}

	public List<CRMEmail> getemailDetails(String id) throws Exception 
	{
		return CRMuserDao.getemailDetails(id);	
	}
	
	public void inactiveUser(CRMUser user) throws Exception 
	{
		CRMuserDao.inactiveUser(user);
		
	}
	public List<CRMUser> getuserlistwithinactive() throws Exception
	{
		return CRMuserDao.getuserlistwithinactive();
		 
	}

	@Override
	public List<CRMUser> getUserManagerTeam() throws Exception {
		// TODO Auto-generated method stub
		return CRMuserDao.getUserManagerTeam();
	}
}
