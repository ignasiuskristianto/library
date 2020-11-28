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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ams.form.SewaForm;
import com.ams.model.Anggota;
import com.ams.model.DetailSewa;
import com.ams.model.Sewa;
import com.ams.model.User;
import com.ams.model.dao.AnggotaDAO;
import com.ams.model.dao.BukuDAO;
import com.ams.model.dao.SewaDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

public class SewaAction extends Action{
	
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
		SewaForm form = (SewaForm) actionForm;
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("sewa");
		
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
				
				List<Sewa> sewaList = new LinkedList<Sewa>();
				//get total
				Criteria criteria = SewaDAO.getInstance().getSession().createCriteria(Sewa.class).createAlias("Anggota", "a");
				if (form.getString("idAnggota")!=null && form.getString("idAnggota").length()>0)
					criteria.add(Restrictions.eq("a.id", form.getLong("idAnggota")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.ge("TglSewa", form.getCalendar("tglSewaMulai").getTime()));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.le("TglSewa", form.getCalendar("tglSewaAkhir").getTime()));
				
				if (form.getCalendar("tglKembaliMulai")!=null)
					criteria.add(Restrictions.ge("TglKembali", form.getCalendar("tglKembaliMulai").getTime()));
				
				if (form.getCalendar("tglKembaliAkhir")!=null)
					criteria.add(Restrictions.le("TglKembali", form.getCalendar("tglKembaliAkhir").getTime()));
				
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = SewaDAO.getInstance().getSession().createCriteria(Sewa.class).createAlias("Anggota", "a");
				if (form.getString("idAnggota")!=null && form.getString("idAnggota").length()>0)
					criteria.add(Restrictions.eq("a.id", form.getLong("idAnggota")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.ge("TglSewa", form.getCalendar("tglSewaMulai").getTime()));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.le("TglSewa", form.getCalendar("tglSewaAkhir").getTime()));
				
				if (form.getCalendar("tglKembaliMulai")!=null)
					criteria.add(Restrictions.ge("TglKembali", form.getCalendar("tglKembaliMulai").getTime()));
				
				if (form.getCalendar("tglKembaliAkhir")!=null)
					criteria.add(Restrictions.le("TglKembali", form.getCalendar("tglKembaliAkhir").getTime()));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				sewaList = criteria.list();
				request.setAttribute("sewaList", sewaList);
			}else{
				total = 0;
				request.removeAttribute("sewaList");
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
				SewaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward performForm(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		SewaForm form = (SewaForm) actionForm;
		HttpSession httpSession = request.getSession();
		try{
			Sewa sewa = null;
			if(httpSession.getAttribute("sewa")!=null) sewa = (Sewa) httpSession.getAttribute("sewa");
			else if (form.getLong("id") > 0) sewa = SewaDAO.getInstance().get(form.getLong("id"));
			httpSession.setAttribute("sewa", sewa);
			request.setAttribute("detailSewaList", sewa.getDetailSewaList());
			form.setString("id", sewa.getId());
			if(form.getString("idAnggota").isEmpty()) form.setString("idAnggota", sewa.getAnggota().getId());
			if(form.getString("namaAnggota").isEmpty()) form.setString("namaAnggota", sewa.getAnggota().getNama());
			if(form.getString("tglSewa").isEmpty()) form.setString("tglSewa", sewa.getFormatedTglSewa());
			if(form.getString("tglKembali").isEmpty()) form.setString("tglKembali", sewa.getFormatedTglKembali());
			form.setString("idBuku", "");
			form.setString("idBukuList", "");
			form.setString("judulBuku", "");
			form.setString("jumlahSewa", "");
			form.setString("subaction", "");
			if(form.getCalendar("tglSewa")==null)form.setCurentCalendar("tglSewa");
		}catch(Exception ex) {
		}finally {
			try {
				SewaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("form");
	}


	private  ActionForward performSave(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		SewaForm form = (SewaForm) actionForm;
		Session session = null;
		Transaction transaction = null;
		Sewa sewa = null;
		try {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("user");
			if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("addDetail")){
				if(httpSession.getAttribute("sewa")!=null) { 
					sewa = (Sewa) httpSession.getAttribute("sewa");
					for(DetailSewa detailSewa : sewa.getDetailSewaList()) {
						if(detailSewa.getBuku().getId()==form.getLong("idBuku")) {
							detailSewa.setJumlah(detailSewa.getJumlah()+form.getInt("jumlahSewa"));
							detailSewa.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
							detailSewa.setUpdatedBy(user.getUsername());
							httpSession.setAttribute("sewa", sewa);
							return mapping.findForward("form");
						}
					}
				} else {
					sewa = new Sewa();
				}
				DetailSewa detailSewa = new DetailSewa();
				detailSewa.setBuku(BukuDAO.getInstance().get(form.getLong("idBuku")));
				detailSewa.setJumlah(form.getInt("jumlahSewa"));
				detailSewa.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				detailSewa.setCreatedBy(user.getUsername());
				sewa.getDetailSewaList().add(detailSewa);
				httpSession.setAttribute("sewa", sewa);
				return mapping.findForward("form");
			}else if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("updDetail")){
				sewa = (Sewa) httpSession.getAttribute("sewa");
				if(form.getLong("idBuku")!=form.getLong("idBukuList")) {
					sewa.getDetailSewaList().removeIf(detailSewa -> detailSewa.getBuku().getId()==form.getLong("idBukuList"));
				}
				for(DetailSewa detailSewa : sewa.getDetailSewaList()) {
					if(detailSewa.getBuku().getId()==form.getLong("idBuku")) {
						if(form.getLong("idBuku")==form.getLong("idBukuList")) {
							detailSewa.setJumlah(form.getInt("jumlahSewa"));
						} else if(form.getLong("idBuku")!=form.getLong("idBukuList")) {
							detailSewa.setJumlah(detailSewa.getJumlah()+form.getInt("jumlahSewa"));
						}
						detailSewa.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
						detailSewa.setUpdatedBy(user.getUsername());
						httpSession.setAttribute("sewa", sewa);
						return mapping.findForward("form");
					}
				}
				DetailSewa detailSewa = new DetailSewa();
				detailSewa.setBuku(BukuDAO.getInstance().get(form.getLong("idBuku")));
				detailSewa.setJumlah(form.getInt("jumlahSewa"));
				detailSewa.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				detailSewa.setCreatedBy(user.getUsername());
				sewa.getDetailSewaList().add(detailSewa);
				httpSession.setAttribute("sewa", sewa);
				return mapping.findForward("form");
			}else if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("delDetail")){
				sewa = (Sewa) httpSession.getAttribute("sewa");
				sewa.getDetailSewaList().removeIf(detailSewa -> detailSewa.getBuku().getId()==form.getLong("idBuku"));
				httpSession.setAttribute("sewa", sewa);
				return mapping.findForward("form");
			}else if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("addSewa")){
				Anggota anggota = AnggotaDAO.getInstance().get(form.getLong("idAnggota"));
				if(form.getCalendar("tglSewa").getTime().before(anggota.getTglGabung())) {
					ActionMessages errors = new ActionMessages();
					errors.add("error.tglSewaKurang", new ActionMessage("error.tglSewaKurang", anggota.getFormatedTglGabung()));
					saveErrors(request,errors);
					return mapping.findForward("form");
				}
				sewa = (Sewa) httpSession.getAttribute("sewa");
				if(sewa.getDetailSewaList().isEmpty()) {
					ActionMessages errors = new ActionMessages();
					errors.add("error.nolBukuSewa", new ActionMessage("error.nolBukuSewa"));
					saveErrors(request,errors);
					return mapping.findForward("form");
				}
			
				Integer stok;
				for(DetailSewa detailSewa : sewa.getDetailSewaList()) {
					stok = SewaDAO.getInstance().cekStok(form, detailSewa.getBuku()); 
					if(stok<detailSewa.getJumlah()) {
						ActionMessages errors = new ActionMessages();
						errors.add("error.stokBukuHabis", new ActionMessage("error.stokBukuHabis", detailSewa.getBuku().getJudul(), stok));
						saveErrors(request,errors);
						return mapping.findForward("form");
					}
				}
				session = SewaDAO.getInstance().getSession();
				transaction = session.beginTransaction();
				if (form.getLong("id")==0){
					sewa.setAnggota(anggota);
					sewa.setTglSewa(form.getCalendar("tglSewa").getTime());
					sewa.setTglKembali(form.getCalendar("tglKembali").getTime());
					sewa.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
					sewa.setCreatedBy(user.getUsername());
					for(DetailSewa detailSewa : sewa.getDetailSewaList()) {
						detailSewa.setSewa(sewa);
					}
					session.save(sewa);
				}else{
					if(sewa.getAnggota().getId()!=anggota.getId()) sewa.setAnggota(anggota);
					sewa.setTglSewa(form.getCalendar("tglSewa").getTime());
					sewa.setTglKembali(form.getCalendar("tglKembali").getTime());
					sewa.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
					sewa.setUpdatedBy(user.getUsername());
					for(DetailSewa detailSewa : sewa.getDetailSewaList()) {
						if(detailSewa.getSewa()==null) detailSewa.setSewa(sewa);
					}
					session.merge(sewa);
				}
				transaction.commit();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			generalError(request, new Exception(ex));
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
		SewaForm form = (SewaForm) actionForm;
		try{
			Sewa sewa = SewaDAO.getInstance().get(form.getLong("id"));
			request.setAttribute("sewa", sewa);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("success");
		}finally{
			try{
				SewaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx){
			}
		}
		return mapping.findForward("success");
	}
	
	private  ActionForward performDelete(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = SewaDAO.getInstance().getSession();
			SewaForm form = (SewaForm) actionForm;
			Sewa sewa = SewaDAO.getInstance().get(form.getLong("id"));
			transaction = session.beginTransaction();
			session.delete(sewa);
			transaction.commit();
			ActionForward forward = mapping.findForward("success");
			StringBuffer sb = new StringBuffer(forward.getPath());
			return new ActionForward(sb.toString(),true);
		}catch(Exception e){
			String error="Sewa tidak dapat dihapus";
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


