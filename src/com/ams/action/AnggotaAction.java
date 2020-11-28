package com.ams.action;


import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ams.form.AnggotaForm;
import com.ams.model.Anggota;
import com.ams.model.User;
import com.ams.model.dao.AnggotaDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

public class AnggotaAction extends Action{

	 
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null||session.getAttribute("user").equals("")) {
			return mapping.findForward("login");
		}
		ActionForward forward = new  ActionForward();
		String action = mapping.getParameter();
		if("PARTIALLIST".equalsIgnoreCase(action)){
			forward = performPartialList(mapping, form, request, response);
		}else if("FORM".equalsIgnoreCase(action)){
			forward = performForm(mapping, form, request, response);
		}else if("SAVE".equalsIgnoreCase(action)){
			forward = performSave(mapping, form, request, response);
		}else if("DETAIL".equalsIgnoreCase(action)){
			forward = performDetail(mapping, form, request, response);
		}else if("DELETE".equalsIgnoreCase(action)){
			forward = performDelete(mapping, form, request, response);
		}else if("POPUPANGGOTA".equalsIgnoreCase(action)){
			forward = performPopUp(mapping, form, request, response);
		}else{
			return mapping.findForward("success");
		}
		return  forward; 
	}
	
	public ActionForward performPartialList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		AnggotaForm form = (AnggotaForm) actionForm;
		HttpSession httpSession = request.getSession();
		
		try {
			int start = 0;
			int count = 5;
			int total = 0;
			try {
				
				start = Integer.parseInt(request.getParameter("start"));
			}catch(Exception ex) {
			}
			try {
			
				count = Integer.parseInt(request.getParameter("count"));
			}catch(Exception ex) {
				try {
					
					ResourceBundle prop = ResourceBundle.getBundle("resource.ApplicationResources.properties");
					count = Integer.parseInt(prop.getString("max.item.per.page"));
				}catch(Exception exx) {
				}
			}

			//save start and count attribute on session
		
			httpSession.setAttribute(CommonConstants.START,Integer.toString(start));
			httpSession.setAttribute(CommonConstants.COUNT,Integer.toString(count));
			
			if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("search")){
				
				List<Anggota> anggotaList = new LinkedList<Anggota>();
				//get total
				
				Criteria criteria = AnggotaDAO.getInstance().getSession().createCriteria(Anggota.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
				
				if (form.getString("kode")!=null && form.getString("kode").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kode"), MatchMode.ANYWHERE));
				
				if (form.getCalendar("fromBirthDate")!=null)
					criteria.add(Restrictions.ge("TglLahir", form.getCalendar("fromBirthDate").getTime()));
				
				if (form.getCalendar("toBirthDate")!=null)
					criteria.add(Restrictions.le("TglLahir", form.getCalendar("toBirthDate").getTime()));
				
				if (form.getCalendar("fromDate")!=null)
					criteria.add(Restrictions.ge("TglGabung", form.getCalendar("fromDate").getTime()));
				
				if (form.getCalendar("toDate")!=null)
					criteria.add(Restrictions.le("TglGabung", form.getCalendar("toDate").getTime()));
	
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = AnggotaDAO.getInstance().getSession().createCriteria(Anggota.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
				
				if (form.getString("kode")!=null && form.getString("kode").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kode"), MatchMode.ANYWHERE));

				if (form.getCalendar("fromBirthDate")!=null)
					criteria.add(Restrictions.ge("TglLahir", form.getCalendar("fromBirthDate").getTime()));
				
				if (form.getCalendar("toBirthDate")!=null)
					criteria.add(Restrictions.le("TglLahir", form.getCalendar("toBirthDate").getTime()));
				
				if (form.getCalendar("fromDate")!=null)
					criteria.add(Restrictions.ge("TglGabung", form.getCalendar("fromDate").getTime()));
				
				if (form.getCalendar("toDate")!=null)
					criteria.add(Restrictions.le("TglGabung", form.getCalendar("toDate").getTime()));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				anggotaList = criteria.list();
				request.setAttribute("anggotaList", anggotaList);
//				form.setString("nama", "");
//				form.setString("kode", "");
//				form.setString("fromBirthDate", "");
//				form.setString("toBirthDate", "");
//				form.setString("fromDate", "");
//				form.setString("toDate", "");
				
			}else{
				
				total = 0;
				request.removeAttribute("anggotaList");
			}
		
			request.setAttribute("total", total);
			String pager = Pager.generatePager(start, count, total);
			String pagerItem = Pager.generatePagerItem(start, count, total);
			request.setAttribute("PAGER",pager);
			request.setAttribute("PAGERITEM",pagerItem);
//			form.setString("nama", "");
//			form.setString("kode", "");
//			form.setString("fromBirthDate", "");
//			form.setString("toBirthDate", "");
//			form.setString("fromDate", "");
//			form.setString("toDate", "");
		} catch (Exception ex) {
			ex.printStackTrace();
			generalError(request,ex);
			return mapping.findForward("success");
		}finally{
			//log.info("[ END "+this.getClass().getName()+" "+users.getUserName()+"  ] ");
			try {
				
				AnggotaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward performForm(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		AnggotaForm form = (AnggotaForm) actionForm;
		try{
			if(form.getCalendar("tglGabung")==null)form.setCurentCalendar("tglGabung");
			if (form.getLong("id") > 0){
				Anggota anggota = AnggotaDAO.getInstance().get(form.getLong("id"));
				form.setString("id", anggota.getId());
				form.setString("kode", anggota.getKode());
				form.setString("nama", anggota.getNama());
				form.setString("tglLahir", anggota.getFormatedTglLahir());
				form.setString("tglGabung", anggota.getFormatedTglGabung());
				request.setAttribute("anggota", anggota);
			}	
				
		}catch(Exception ex) {
		}finally {
			try {
				AnggotaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("success");
	}


	private  ActionForward performSave(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		AnggotaForm form = (AnggotaForm) actionForm;
		Anggota anggota = new Anggota();
		Session session = null;
		Transaction transaction = null;
		try {
			session = AnggotaDAO.getInstance().getSession();
			User user = (User) request.getSession().getAttribute("user");
			transaction = session.beginTransaction();
			if (form.getLong("id")==0){
				anggota.setKode(form.getString("kode"));
				anggota.setNama(form.getString("nama"));
				anggota.setTglLahir(form.getCalendar("tglLahir").getTime());
				anggota.setTglGabung(form.getCalendar("tglGabung").getTime());
				anggota.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				anggota.setCreatedBy(user.getUsername());
				session.save(anggota);
			}else{
				anggota = AnggotaDAO.getInstance().get(form.getLong("id"));
				anggota.setKode(form.getString("kode"));
				anggota.setNama(form.getString("nama"));
				anggota.setTglLahir(form.getCalendar("tglLahir").getTime());
				anggota.setTglGabung(form.getCalendar("tglGabung").getTime());
				anggota.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				anggota.setUpdatedBy(user.getUsername());
				session.update(anggota);
			}
			transaction.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			if(transaction!=null) transaction.rollback();
		}finally{
			try{
				if(session!=null) session.close();
			}catch(Exception exx){
			}
		}
		ActionForward forward = mapping.findForward("success");
		StringBuffer sb = new  StringBuffer(forward.getPath());
		return  new  ActionForward(sb.toString(),true);
	}
	
	private  ActionForward performDetail(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response){
		AnggotaForm form = (AnggotaForm)actionForm;
		
		try{
			Anggota anggota = AnggotaDAO.getInstance().get(form.getLong("id"));
			request.setAttribute("anggota", anggota);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}
	
	private  ActionForward performDelete(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = AnggotaDAO.getInstance().getSession();
			AnggotaForm form = (AnggotaForm) actionForm;
			Anggota anggota= AnggotaDAO.getInstance().get(form.getLong("id"));
			transaction = session.beginTransaction();
			session.delete(anggota);
			transaction.commit();
			ActionForward forward = mapping.findForward("success");
			StringBuffer sb = new StringBuffer(forward.getPath());
			return new ActionForward(sb.toString(),true);
		}catch(Exception e){
			String error="Anggota sedang menyewa buku";
			generalError(request, new Exception(error));
			if(transaction!=null) transaction.rollback();
    		return (new ActionForward(mapping.getInput()));
		}finally{
			try{
				if(session!=null) session.close();
			}catch(Exception exx){
			}
		}
	}
	
	private ActionForward performPopUp(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		AnggotaForm form = (AnggotaForm) actionForm;
		HttpSession httpSession = request.getSession();
		
		try {
			int start = 0;
			int count = 10;
			int total = 0;
			try {
				
				start = Integer.parseInt(request.getParameter("start"));
			}catch(Exception ex) {
			}
			try {
			
				count = Integer.parseInt(request.getParameter("count"));
			}catch(Exception ex) {
				try {
					
					ResourceBundle prop = ResourceBundle.getBundle("resource.ApplicationResources.properties");
					count = Integer.parseInt(prop.getString("max.item.per.page"));
				}catch(Exception exx) {
				}
			}

			//save start and count attribute on session
		
			httpSession.setAttribute(CommonConstants.START,Integer.toString(start));
			httpSession.setAttribute(CommonConstants.COUNT,Integer.toString(count));
			
			if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("search")){
				
				List<Anggota> anggotaList = new LinkedList<Anggota>();
				//get total
				
				Criteria criteria = AnggotaDAO.getInstance().getSession().createCriteria(Anggota.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
				
				if (form.getString("kode")!=null && form.getString("kode").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kode"), MatchMode.ANYWHERE));
				
				if (form.getCalendar("fromDate")!=null)
					criteria.add(Restrictions.ge("TglGabung", form.getCalendar("fromDate").getTime()));
				
				if (form.getCalendar("toDate")!=null)
					criteria.add(Restrictions.le("TglGabung", form.getCalendar("toDate").getTime()));
	
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = AnggotaDAO.getInstance().getSession().createCriteria(Anggota.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
				
				if (form.getString("kode")!=null && form.getString("kode").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kode"), MatchMode.ANYWHERE));
				
				if (form.getCalendar("fromDate")!=null)
					criteria.add(Restrictions.ge("TglGabung", form.getCalendar("fromDate").getTime()));
				
				if (form.getCalendar("toDate")!=null)
					criteria.add(Restrictions.le("TglGabung", form.getCalendar("toDate").getTime()));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				anggotaList = criteria.list();
				request.setAttribute("anggotaList", anggotaList);
				
			}else{
				total = 0;
				request.removeAttribute("anggotaList");
			}
		
			request.setAttribute("total", total);
			String pager = Pager.generatePager(start, count, total);
			String pagerItem = Pager.generatePagerItem(start, count, total);
			request.setAttribute("PAGER",pager);
			request.setAttribute("PAGERITEM",pagerItem);
		} catch (Exception ex) {
			ex.printStackTrace();
			generalError(request,ex);
			return mapping.findForward("success");
		}finally{
			//log.info("[ END "+this.getClass().getName()+" "+users.getUserName()+"  ] ");
			try {
				
				AnggotaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	
	}
	private void generalError(HttpServletRequest request, Exception ex) 
	{
		ActionMessages errors = new ActionMessages();
		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.global",ex.getMessage()));
		saveErrors(request,errors);
	}
	
}


