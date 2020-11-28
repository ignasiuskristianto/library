package com.ams.action;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ams.form.LaporanForm;
import com.ams.model.DetailSewa;
import com.ams.model.KategoriBuku;
import com.ams.model.LaporanStok;
import com.ams.model.dao.BukuDAO;
import com.ams.model.dao.DetailSewaDAO;
import com.ams.model.dao.KategoriBukuDAO;
import com.ams.model.dao.LaporanDAO;
import com.ams.model.dao.SewaDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class LaporanAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null||session.getAttribute("user").equals("")) {
			return mapping.findForward("login");
		}
		ActionForward forward = new  ActionForward();
		String action = mapping.getParameter();
		if ("LAPORANSEWA".equalsIgnoreCase(action)) {
			forward = performLaporanSewa(mapping, form, request, response);
		}else if ("LAPORANSTOK".equalsIgnoreCase(action)) {
			forward = performLaporanStok(mapping, form, request, response);
		}return forward;
	}
	
	@SuppressWarnings("unchecked")
	private ActionForward performLaporanSewa(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		LaporanForm form = (LaporanForm) actionForm;
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
				
				List<DetailSewa> detailSewaList = new LinkedList<DetailSewa>();
				//get total
				Criteria criteria = DetailSewaDAO.getInstance().getSession().createCriteria(DetailSewa.class).createAlias("Buku", "b").createAlias("Buku.Kategori", "k").createAlias("Sewa", "s");
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("b.Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.gt("s.TglKembali", form.getCalendar("tglSewaMulai").getTime()));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.lt("s.TglSewa", form.getCalendar("tglSewaAkhir").getTime()));
				
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = DetailSewaDAO.getInstance().getSession().createCriteria(DetailSewa.class).createAlias("Buku", "b").createAlias("Buku.Kategori", "k").createAlias("Sewa", "s");
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("b.Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.gt("s.TglKembali", form.getCalendar("tglSewaMulai").getTime()));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.lt("s.TglSewa", form.getCalendar("tglSewaAkhir").getTime()));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("s.id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				detailSewaList = criteria.list();
				request.setAttribute("detailSewaList", detailSewaList);
			}else if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("excel")) {
				List<DetailSewa> detailSewaList = new LinkedList<DetailSewa>();
				//get total
				Criteria criteria = DetailSewaDAO.getInstance().getSession().createCriteria(DetailSewa.class).createAlias("Buku", "b").createAlias("Buku.Kategori", "k").createAlias("Sewa", "s");
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("b.Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.or(Restrictions.gt("s.TglSewa", form.getCalendar("tglSewaMulai").getTime()), Restrictions.gt("s.TglKembali", form.getCalendar("tglSewaMulai").getTime())));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.or(Restrictions.lt("s.TglSewa", form.getCalendar("tglSewaAkhir").getTime()), Restrictions.lt("s.TglKembali", form.getCalendar("tglSewaAkhir").getTime())));
				
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = DetailSewaDAO.getInstance().getSession().createCriteria(DetailSewa.class).createAlias("Buku", "b").createAlias("Buku.Kategori", "k").createAlias("Sewa", "s");
				if (form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0)
					criteria.add(Restrictions.ilike("b.Judul", form.getString("judulBuku"), MatchMode.ANYWHERE));
				
				if (form.getString("idKategori")!=null && form.getString("idKategori").length()>0)
					criteria.add(Restrictions.eq("k.id", form.getLong("idKategori")));
				
				if (form.getCalendar("tglSewaMulai")!=null)
					criteria.add(Restrictions.or(Restrictions.gt("s.TglSewa", form.getCalendar("tglSewaMulai").getTime()), Restrictions.gt("s.TglKembali", form.getCalendar("tglSewaMulai").getTime())));
				
				if (form.getCalendar("tglSewaAkhir")!=null)
					criteria.add(Restrictions.or(Restrictions.lt("s.TglSewa", form.getCalendar("tglSewaAkhir").getTime()), Restrictions.lt("s.TglKembali", form.getCalendar("tglSewaAkhir").getTime())));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("s.id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				detailSewaList = criteria.list();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				WorkbookSettings wbs = new WorkbookSettings();
		        wbs.setLocale( new Locale("en", "EN") );
		        WritableWorkbook wb = Workbook.createWorkbook(baos, wbs);
		        
		        int rowPerSheet = 65536, sheet = 1, rowOfSheet = 0, numOfData = 1;
		        WritableSheet ws = wb.createSheet("Sheet "+sheet,sheet);
		        for(DetailSewa detailSewa : detailSewaList){
		        	if(rowOfSheet == 0) {
		        		ws.addCell(new Label(0,rowOfSheet,"No."));
			        	ws.addCell(new Label(1,rowOfSheet,"Judul Buku"));
			        	ws.addCell(new Label(2,rowOfSheet,"Jumlah Sewa"));
			        	ws.addCell(new Label(3,rowOfSheet,"Anggota"));
			        	ws.addCell(new Label(4,rowOfSheet,"Tangal Sewa"));
			        	ws.addCell(new Label(5,rowOfSheet,"Tanggal Kembali"));
			        	rowOfSheet++;
		        	}
		        	ws.addCell(new Label(0,rowOfSheet,String.valueOf(numOfData)));
		        	ws.addCell(new Label(1,rowOfSheet,detailSewa.getBuku().getJudul()));
		        	ws.addCell(new Label(2,rowOfSheet,String.valueOf(detailSewa.getJumlah())));
		        	ws.addCell(new Label(3,rowOfSheet,detailSewa.getSewa().getAnggota().getKode()+" - "+detailSewa.getSewa().getAnggota().getNama()));
		        	ws.addCell(new Label(4,rowOfSheet,detailSewa.getSewa().getFormatedTglSewa()));
		        	ws.addCell(new Label(5,rowOfSheet,detailSewa.getSewa().getFormatedTglKembali()));
		        	rowOfSheet++;
		        	numOfData++;
		        	if(rowOfSheet>rowPerSheet) {
		        		rowOfSheet = 0;
		        		sheet++;
		        		ws = wb.createSheet("Sheet "+sheet,sheet);
		        	}
				}
				wb.write();
				wb.close();
				response.setContentType("application/vnd.ms-excel");
	            response.setContentLength(baos.size());
	            response.setHeader("Content-Disposition", "attachment;filename=laporan_sewa.xls");
	            ServletOutputStream out = response.getOutputStream();
	            baos.writeTo(out);
	            out.flush();
	            return null;
			}else{
				total = 0;
				request.removeAttribute("detailSewaList");
			}
			request.setAttribute("total", total);
			String pager = Pager.generatePager(start, count, total);
			String pagerItem = Pager.generatePagerItem(start, count, total);
			request.setAttribute("PAGER",pager);
			request.setAttribute("PAGERITEM",pagerItem);
		} catch (Exception ex) {
			ex.printStackTrace();
			generalError(request,ex);
			return mapping.findForward("list");
		}finally{
			//log.info("[ END "+this.getClass().getName()+" "+users.getUserName()+"  ] ");
			try {
				KategoriBukuDAO.getInstance().closeSessionForReal();
				SewaDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("list");
	}
	
	private ActionForward performLaporanStok(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		LaporanForm form = (LaporanForm) actionForm;
		HttpSession httpSession = request.getSession();
		
		try {
			if(form.getCalendar("tgl")==null)form.setCurentCalendar("tgl");
			
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
				List<LaporanStok> laporanStokList = new LaporanDAO().getLaporanStok(form, start, count, total);
				request.setAttribute("laporanStokList", laporanStokList);
			}else if(form.getString("subaction").length() > 0 && form.getString("subaction").equalsIgnoreCase("excel")) {
				List<LaporanStok> laporanStokList = new LaporanDAO().getLaporanStok(form, start, count, total);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				WorkbookSettings wbs = new WorkbookSettings();
		        wbs.setLocale( new Locale("en", "EN") );
		        WritableWorkbook wb = Workbook.createWorkbook(baos, wbs);
		        
		        int rowPerSheet = 65536, sheet = 1, rowOfSheet = 0, numOfData = 1;
		        WritableSheet ws = wb.createSheet("Sheet "+sheet,sheet);
		        for(LaporanStok laporanStok : laporanStokList){
		        	if(rowOfSheet == 0) {
		        		ws.addCell(new Label(0,rowOfSheet,"No."));
			        	ws.addCell(new Label(1,rowOfSheet,"Judul Buku"));
			        	ws.addCell(new Label(2,rowOfSheet,"Jumlah Buku"));
			        	ws.addCell(new Label(3,rowOfSheet,"Stok Dipinjam"));
			        	ws.addCell(new Label(4,rowOfSheet,"Stok Ada"));
			        	ws.addCell(new Label(5,rowOfSheet,"Tanggal Kembali Pertama"));
			        	rowOfSheet++;
		        	}
		        	ws.addCell(new Label(0,rowOfSheet,String.valueOf(numOfData)));
		        	ws.addCell(new Label(1,rowOfSheet,laporanStok.getJudulBuku()));
		        	ws.addCell(new Label(2,rowOfSheet,String.valueOf(laporanStok.getJumlahBuku())));
		        	ws.addCell(new Label(3,rowOfSheet,String.valueOf(laporanStok.getStokSewa())));
		        	ws.addCell(new Label(4,rowOfSheet,String.valueOf(laporanStok.getStokAda())));
		        	ws.addCell(new Label(5,rowOfSheet,laporanStok.getFormatedTglKembaliPertama()));
		        	rowOfSheet++;
		        	numOfData++;
		        	if(rowOfSheet>rowPerSheet) {
		        		rowOfSheet = 0;
		        		sheet++;
		        		ws = wb.createSheet("Sheet "+sheet,sheet);
		        	}
				}
				wb.write();
				wb.close();
				response.setContentType("application/vnd.ms-excel");
	            response.setContentLength(baos.size());
	            response.setHeader("Content-Disposition", "attachment;filename=laporan_stok.xls");
	            ServletOutputStream out = response.getOutputStream();
	            baos.writeTo(out);
	            out.flush();
	            return null;
			}else{
				total = 0;
				request.removeAttribute("detailSewaList");
			}
			request.setAttribute("total", total);
			String pager = Pager.generatePager(start, count, total);
			String pagerItem = Pager.generatePagerItem(start, count, total);
			request.setAttribute("PAGER",pager);
			request.setAttribute("PAGERITEM",pagerItem);
		} catch (Exception ex) {
			ex.printStackTrace();
			generalError(request,ex);
			return mapping.findForward("list");
		}finally{
			//log.info("[ END "+this.getClass().getName()+" "+users.getUserName()+"  ] ");
			try {
				BukuDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("list");
	}
	
	private void generalError(HttpServletRequest request, Exception ex) {
		ActionMessages errors = new ActionMessages();
		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.global",ex.getMessage()));
		saveErrors(request,errors);
	}
}