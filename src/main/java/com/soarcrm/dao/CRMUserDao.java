package com.soarcrm.dao;

import java.util.List;

import com.soarcrm.domain.CRMEmail;
import com.soarcrm.domain.CRMMainmenu;
import com.soarcrm.domain.CRMRolemaster;
import com.soarcrm.domain.CRMSubmenu;
import com.soarcrm.domain.CRMUser;

public interface CRMUserDao {

	public CRMUser isValidUser(String loginName, String password1)throws Exception;

	public void insertUser(CRMUser user)throws Exception;

	public List<CRMUser> getuserlist()throws Exception;

	public CRMUser getUser(String id)throws Exception;

	public void updateUser(CRMUser user)throws Exception;

	public List<CRMMainmenu> getMainMenuList(String roleid) throws Exception;

	public List<CRMSubmenu> getSubMenuList(String menuid, String roleid) throws Exception ;

	public List<CRMRolemaster> getRolelist()throws Exception ;

	public CRMUser isValidSuperUser(String loginName, String password, String password2)throws Exception ;

	public boolean insertExistingClient() throws Exception;
	
	public boolean insertExistingNotes() throws Exception;

	public CRMEmail getsequencelist(CRMEmail mail)throws Exception;

	public void insertEMailDetails(CRMEmail mail)throws Exception;

	public List<CRMEmail> getemailDetails(String id)throws Exception;

	public void inactiveUser(CRMUser user)throws Exception;

	public List<CRMUser> getuserlistwithinactive()throws Exception;
	
	public List<CRMUser> getUserManagerTeam() throws Exception;
}
