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

import com.ams.form.KategoriBukuForm;
import com.ams.model.KategoriBuku;
import com.ams.model.User;
import com.ams.model.dao.KategoriBukuDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

public class KategoriBukuAction extends Action{

	 
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
		}else{
			return mapping.findForward("success");
		}
		return  forward; 
	}
	
	public ActionForward performPartialList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		KategoriBukuForm form = (KategoriBukuForm) actionForm;
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
				
				List<KategoriBuku> kategoriBukuList = new LinkedList<KategoriBuku>();
				//get total
				
				Criteria criteria = KategoriBukuDAO.getInstance().getSession().createCriteria(KategoriBuku.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
	
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = KategoriBukuDAO.getInstance().getSession().createCriteria(KategoriBuku.class);
				if (form.getString("nama")!=null && form.getString("nama").length()>0)
					criteria.add(Restrictions.ilike("Nama", form.getString("nama"), MatchMode.ANYWHERE));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				kategoriBukuList = criteria.list();
				request.setAttribute("kategoriBukuList", kategoriBukuList);
				
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
				
				KategoriBukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward performForm(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		KategoriBukuForm form = (KategoriBukuForm) actionForm;
		try{
			if (form.getLong("id") > 0){
				KategoriBuku kategoriBuku = KategoriBukuDAO.getInstance().get(form.getLong("id"));
				form.setString("id", kategoriBuku.getId());
				form.setString("nama", kategoriBuku.getNama());
				request.setAttribute("kategoriBuku", kategoriBuku);
			}	
				
		}catch(Exception ex) {
		}finally {
			try {
				KategoriBukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("success");
	}


	private  ActionForward performSave(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		KategoriBukuForm form = (KategoriBukuForm) actionForm;
		KategoriBuku kategoriBuku = new KategoriBuku();
		Session session = null;
		Transaction transaction = null;
		try {
			session = KategoriBukuDAO.getInstance().getSession();
			User user = (User) request.getSession().getAttribute("user");
			transaction = session.beginTransaction();
			if (form.getLong("id")==0){
				kategoriBuku.setNama(form.getString("nama"));
				kategoriBuku.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				kategoriBuku.setCreatedBy(user.getUsername());
				session.save(kategoriBuku);
			}else{
				kategoriBuku = (KategoriBuku) KategoriBukuDAO.getInstance().get(form.getLong("id"));
				kategoriBuku.setNama(form.getString("nama"));
				kategoriBuku.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				kategoriBuku.setUpdatedBy(user.getUsername());
				session.update(kategoriBuku);
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
		KategoriBukuForm form = (KategoriBukuForm)actionForm;
		
		try{
			KategoriBuku kategoriBuku = KategoriBukuDAO.getInstance().get(form.getLong("id"));
			request.setAttribute("kategoriBuku", kategoriBuku);
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
			session = KategoriBukuDAO.getInstance().getSession();
			KategoriBukuForm form = (KategoriBukuForm) actionForm;
			KategoriBuku kategoriBuku= KategoriBukuDAO.getInstance().get(form.getLong("id"));
			transaction = session.beginTransaction();
			session.delete(kategoriBuku);
			transaction.commit();
			ActionForward forward = mapping.findForward("success");
			StringBuffer sb = new StringBuffer(forward.getPath());
			return new ActionForward(sb.toString(),true);
		}catch(Exception e){
			String error="Kategori Buku tidak dapat dihapus";
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
	
	private void generalError(HttpServletRequest request, Exception ex) 
	{
		ActionMessages errors = new ActionMessages();
		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.global",ex.getMessage()));
		saveErrors(request,errors);
	}
	
}


