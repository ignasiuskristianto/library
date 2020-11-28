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

import com.ams.form.BukuForm;
import com.ams.model.Buku;
import com.ams.model.KategoriBuku;
import com.ams.model.User;
import com.ams.model.dao.BukuDAO;
import com.ams.model.dao.KategoriBukuDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

public class BukuAction extends Action{

	 
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
		}else if("POPUPBUKU".equalsIgnoreCase(action)){
			forward = performPopUp(mapping, form, request, response);
		}else{
			return mapping.findForward("success");
		}
		return  forward; 
	}
	
	public ActionForward performPartialList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		BukuForm form = (BukuForm) actionForm;
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
			
			request.setAttribute("kategoriBukuList", KategoriBukuDAO.getInstance().getSession().createCriteria(KategoriBuku.class).list());

			//save start and count attribute on session
		
			httpSession.setAttribute(CommonConstants.START,Integer.toString(start));
			httpSession.setAttribute(CommonConstants.COUNT,Integer.toString(count));
			
			if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("search")){
				
				List<Buku> bukuList = new LinkedList<Buku>();
				//get total
				Criteria criteria = BukuDAO.getInstance().getSession().createCriteria(Buku.class).createAlias("Kategori", "k");
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("kodeBuku")!=null && form.getString("kodeBuku").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kodeBuku"), MatchMode.ANYWHERE));
				
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = BukuDAO.getInstance().getSession().createCriteria(Buku.class).createAlias("Kategori", "k");;
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("kodeBuku")!=null && form.getString("kodeBuku").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kodeBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				bukuList = criteria.list();
				request.setAttribute("bukuList", bukuList);
			}else{
				total = 0;
				request.removeAttribute("bukuList");
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
			try {
				KategoriBukuDAO.getInstance().closeSessionForReal();
				BukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward performForm(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		BukuForm form = (BukuForm) actionForm;
		try{
			request.setAttribute("kategoriBukuList", KategoriBukuDAO.getInstance().getSession().createCriteria(KategoriBuku.class).list());
			if (form.getLong("id") > 0){
				Buku buku = (Buku) BukuDAO.getInstance().get(form.getLong("id"));
				form.setString("id", buku.getId());
				form.setString("kodeBuku", buku.getKode());
				form.setString("judulBuku", buku.getJudul());
				form.setString("idKategori", buku.getKategori().getId());
				form.setString("jumlahBuku", buku.getJumlah());
				request.setAttribute("buku", buku);
			}	
				
		}catch(Exception ex) {
		}finally {
			try {
				KategoriBukuDAO.getInstance().closeSessionForReal();
				BukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("success");
	}


	private  ActionForward performSave(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		BukuForm form = (BukuForm) actionForm;
		Buku buku = new Buku();
		Session session = null;
		Transaction transaction = null;
		try {
			session = BukuDAO.getInstance().getSession();
			User user = (User) request.getSession().getAttribute("user");
			KategoriBuku kategoriBuku = KategoriBukuDAO.getInstance().get(form.getLong("idKategori"));
			transaction = session.beginTransaction();
			if (form.getLong("id")==0){
				buku.setKategori(kategoriBuku);
				buku.setKode(form.getString("kodeBuku"));
				buku.setJudul(form.getString("judulBuku"));
				buku.setJumlah(form.getInt("jumlahBuku"));
				buku.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				buku.setCreatedBy(user.getUsername());
				session.save(buku);
			}else{
				buku = (Buku) BukuDAO.getInstance().get(form.getLong("id"));
				buku.setKategori(kategoriBuku);
				buku.setKode(form.getString("kodeBuku"));
				buku.setJudul(form.getString("judulBuku"));
				buku.setJumlah(form.getInt("jumlahBuku"));
				buku.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				buku.setUpdatedBy(user.getUsername());
				session.update(buku);
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
		BukuForm form = (BukuForm) actionForm;
		try{
			Buku buku = BukuDAO.getInstance().get(form.getLong("id"));
			request.setAttribute("buku", buku);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("success");
		}finally{
			try{
				BukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx){
			}
		}
		return mapping.findForward("success");
	}
	
	private  ActionForward performDelete(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = BukuDAO.getInstance().getSession(); 
			BukuForm form = (BukuForm) actionForm;
			Buku buku= BukuDAO.getInstance().get(form.getLong("id"));
			transaction = session.beginTransaction();
			session.delete(buku);
			transaction.commit();
			ActionForward forward = mapping.findForward("success");
			StringBuffer sb = new StringBuffer(forward.getPath());
			return new ActionForward(sb.toString(),true);
		}catch(Exception e){
			e.printStackTrace();
			String error="Buku tidak dapat dihapus";
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
		BukuForm form = (BukuForm) actionForm;
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
			
			request.setAttribute("kategoriBukuList", KategoriBukuDAO.getInstance().getSession().createCriteria(KategoriBuku.class).list());

			//save start and count attribute on session
		
			httpSession.setAttribute(CommonConstants.START,Integer.toString(start));
			httpSession.setAttribute(CommonConstants.COUNT,Integer.toString(count));
			
			if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("search")){
				
				List<Buku> bukuList = new LinkedList<Buku>();
				//get total
				Criteria criteria = BukuDAO.getInstance().getSession().createCriteria(Buku.class);
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("Kategori.id", form.getLong("idKategori")));
				
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("kodeBuku")!=null && form.getString("kodeBuku").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kodeBuku"), MatchMode.ANYWHERE));
				
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = BukuDAO.getInstance().getSession().createCriteria(Buku.class);
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("Kategori.id", form.getLong("idKategori")));
				
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("kodeBuku")!=null && form.getString("kodeBuku").length()>0)
					criteria.add(Restrictions.ilike("Kode", form.getString("kodeBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				bukuList = criteria.list();
				request.setAttribute("bukuList", bukuList);
			}else{
				total = 0;
				request.removeAttribute("bukuList");
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
			try {
				KategoriBukuDAO.getInstance().closeSessionForReal();
				BukuDAO.getInstance().closeSessionForReal();
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


