package com.soarcrm.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.soarcrm.domain.CRMNotes;
import com.soarcrm.domain.CRMStatus;
import com.soarcrm.domain.Client;

public interface CRMClientDao 
{

	public void insertClient(Client client)throws Exception;

	public List<Client> getClientList(String roleid, String userid)throws Exception;
	
	public List<Client> getClientListonAddGrp(String roleid, String userid)throws Exception;

	public void insertNotes(CRMNotes notes)throws Exception;

	public Client getClientDetails(String id)throws Exception;

	public void updateClient(Client client)throws Exception;

	public Client insertData(Sheet sheet, String fileName, Client client, Workbook wb)throws Exception;

	public List<CRMNotes> getNotesList(String roleId, String userid)throws Exception;

	public Client checkEmail(Client client)throws Exception;

	public List<Client> getClientAssignmentList(String roleid, String userid)throws Exception;

	public void updateClientAssignment(Client client)throws Exception;

	public Client getClientDetailsAssignemnt(String id, String traceNo)throws Exception;

	public List<Client> getViewNotesList(String id)throws Exception;

	public List<CRMNotes> getDashboardNotesList(String roleid, String userid)throws Exception;

	public CRMNotes getNoteDetails(String id)throws Exception;

	public Client checkPhoneNo(Client client)throws Exception;

	public Client checkClientName(Client client)throws Exception;

	public Client checkWebsite(Client client)throws Exception;

	public void insertLog(Client client)throws Exception;

	public Client checkEditEmail(Client client)throws Exception;

	public Client checkEditPhoneNo(Client client)throws Exception;

	public Client checkEditClientName(Client client)throws Exception;

	public Client checkEditWebsite(Client client)throws Exception;

	public List<Client> getDashboardfollowupList(String roleid, String userid)throws Exception;

	public List<Client> getExceedFollowupList()throws Exception;

	public List<Client> getExceedEmailList()throws Exception;

	public List<Client> getClientListsearch(Client client)throws Exception;

	public void updateNotes(CRMNotes notes)throws Exception;

	public List<CRMNotes> getCalendatNotesList(String roleid, String userid)throws Exception;

	public Client checkMobileNo(Client client)throws Exception;

	public Client checkaltMobileNo(Client client)throws Exception;

	public Client checkEditMobileNo(Client client)throws Exception;

	public Client checkEditAltMobileNo(Client client)throws Exception;

	public LinkedHashMap<String, String> getdeptHashMap()throws Exception;

	public LinkedHashMap<String, String> getUserHashMap()throws Exception;

	public LinkedHashMap<String, String> getEventHashMap()throws Exception;

	public LinkedHashMap<String, String> getServicesHashMap()throws Exception;

	public LinkedHashMap<String, String> getSourceHashMap() throws Exception;

	public String getStatusId(String traceNo)throws Exception;
	
	public void modifyNotes(CRMNotes notes) throws Exception;

	public List<Client> findClientByTraceNo(String traceNo);

	List<Client> findClientByTraceNoOnGroupNotes(String traceNo);

	public void deleteClientByTraceNo(String traceNo);

	public void updateClientNotesandDT(CRMNotes notes) throws Exception;
}
