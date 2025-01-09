package com.soarcrm.services;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.soarcrm.dao.CRMClientDao;
import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.Client;

public class CRMClientServiceImpl implements CRMClientService 
{
	@Autowired 
	CRMClientDao CRMclientDao;
	
	public void insertClient(Client client) throws Exception 
	{
		CRMclientDao.insertClient(client);
	}

	public List<Client> getClientList(String roleid, String userid) throws Exception 
	{
		return CRMclientDao.getClientList(roleid, userid);
	}

	public void insertNotes(CRMNotes notes) throws Exception 
	{
		CRMclientDao.insertNotes(notes);
		
	}
	
	public Client getClientDetails(String id) throws Exception 
	{
		return CRMclientDao.getClientDetails(id);
	}

	public void updateClient(Client client) throws Exception 
	{
		CRMclientDao.updateClient(client);
		
	}

	public Client insertData(Sheet sheet, String fileName, Client client, Workbook wb) throws Exception 
	{
		return CRMclientDao.insertData(sheet, fileName, client, wb);
	}

	public List<CRMNotes> getNotesList(String roleId, String userid) throws Exception 
	{
		return CRMclientDao.getNotesList(roleId, userid);
	}

	public Client checkEmail(Client client) throws Exception 
	{
		return CRMclientDao.checkEmail(client);
	}

	public List<Client> getClientAssignmentList(String roleid, String userid) throws Exception
	{
		return CRMclientDao.getClientAssignmentList(roleid, userid);
	}

	public void updateClientAssignment(Client client) throws Exception 
	{
		CRMclientDao.updateClientAssignment(client);
	}

	public Client getClientDetailsAssignemnt(String id, String traceNo) throws Exception 
	{
		return CRMclientDao.getClientDetailsAssignemnt(id, traceNo);
	}

	public List<Client> getViewNotesList(String id) throws Exception 
	{
		
		return CRMclientDao.getViewNotesList(id);
	}

	public List<CRMNotes> getDashboardNotesList(String roleid, String userid) throws Exception 
	{
		return CRMclientDao.getDashboardNotesList(roleid, userid);
	}

	public CRMNotes getNoteDetails(String id) throws Exception 
	{
		return CRMclientDao.getNoteDetails(id);
	}

	public Client checkPhoneNo(Client client) throws Exception 
	{
		return CRMclientDao.checkPhoneNo(client);
	}

	public Client checkClientName(Client client) throws Exception 
	{
		return CRMclientDao.checkClientName(client);
	}

	public Client checkWebsite(Client client) throws Exception 
	{
		return CRMclientDao.checkWebsite(client);
	}

	public void insertLog(Client client) throws Exception
	{
		CRMclientDao.insertLog(client);
	}

	public Client checkEditEmail(Client client) throws Exception
	{
		return CRMclientDao.checkEditEmail(client);
	}

	public Client checkEditPhoneNo(Client client) throws Exception 
	{
		return CRMclientDao.checkEditPhoneNo(client);
	}

	public Client checkEditClientName(Client client) throws Exception
	{
		return CRMclientDao.checkEditClientName(client);
	}

	public Client checkEditWebsite(Client client) throws Exception 
	{
		return CRMclientDao.checkEditWebsite(client);
	}

	public List<Client> getDashboardfollowupList(String roleid, String userid) throws Exception
	{
		return CRMclientDao.getDashboardfollowupList(roleid, userid);
	}

	public List<Client> getExceedFollowupList() throws Exception 
	{
		return CRMclientDao.getExceedFollowupList();
	}

	public List<Client> getExceedEmailList() throws Exception
	{
		return CRMclientDao.getExceedEmailList();
	}
	public List<Client> getClientListsearch(Client client) throws Exception 
	{
		return CRMclientDao.getClientListsearch(client);
	}

	public void updateNotes(CRMNotes notes) throws Exception 
	{
		CRMclientDao.updateNotes(notes);
	}

	public List<CRMNotes> getCalendatNotesList(String roleid, String userid) throws Exception 
	{
		return CRMclientDao.getCalendatNotesList(roleid, userid);
	}

	public Client checkMobileNo(Client client) throws Exception 
	{
		return CRMclientDao.checkMobileNo(client);
	}

	public Client checkaltMobileNo(Client client) throws Exception 
	{
		return CRMclientDao.checkaltMobileNo(client);
	}

	public Client checkEditMobileNo(Client client) throws Exception 
	{
		return CRMclientDao.checkEditMobileNo(client);
	}

	public Client checkEditAltMobileNo(Client client) throws Exception 
	{
		return CRMclientDao.checkEditAltMobileNo(client);
	}

	public LinkedHashMap<String, String> getdeptHashMap() throws Exception 
	{
		return CRMclientDao.getdeptHashMap();
	}

	public LinkedHashMap<String, String> getUserHashMap() throws Exception 
	{
		return CRMclientDao.getUserHashMap();
	}

	public LinkedHashMap<String, String> getEventHashMap() throws Exception 
	{
		return CRMclientDao.getEventHashMap();
	}

	@Override
	public LinkedHashMap<String, String> getServicesHashMap() throws Exception 
	{
		return CRMclientDao.getServicesHashMap();
	}
	
	public LinkedHashMap<String, String> getSourceHashMap() throws Exception
	{
		return CRMclientDao.getSourceHashMap();
	}
	
	public String getStatusId(String traceNo) throws Exception{
		return CRMclientDao.getStatusId(traceNo);
	}
	
	public void modifyNotes(CRMNotes notes) throws Exception
	{
		CRMclientDao.modifyNotes(notes);
	}

	
	public List<Client> getClientListonAddGrp(String roleid, String userid) throws Exception 
	{
		return CRMclientDao.getClientListonAddGrp(roleid, userid);
	}

	@Override
	public List<Client> findClientByTraceNo(String traceNo) {
		
		return CRMclientDao.findClientByTraceNo(traceNo);
	}

	@Override
	public List<Client> findClientByTraceNoOnGroupNotes(String traceNo) {
		
		return CRMclientDao.findClientByTraceNoOnGroupNotes(traceNo);
	}

	@Override
	public void deleteClientByTraceNo(String TraceNo) {
		
		CRMclientDao.deleteClientByTraceNo(TraceNo);	
	}

	@Override
	public void updateClientNotesandDT(CRMNotes notes) throws Exception {
		
		CRMclientDao.updateClientNotesandDT(notes);
	}



}
