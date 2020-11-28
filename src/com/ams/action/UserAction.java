package com.ams.action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.ams.form.UserForm;
import com.ams.model.User;
import com.ams.model.dao.UserDAO;
import com.ams.mpe.comon.CommonConstants;
import com.ams.mpe.comon.Pager;

public class UserAction extends Action {


	 
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
	
	private static String getMD5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
	
	public ActionForward performPartialList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		UserForm form = (UserForm) actionForm;
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
				
				
				List<User> userList = new LinkedList<User>();
				//get total
				
				Criteria criteria = UserDAO.getInstance().getSession().createCriteria(User.class);
				
				if (form.getString("username")!=null && form.getString("username").length()>0)
					criteria.add(Restrictions.ilike("Username", form.getString("username"), MatchMode.ANYWHERE));
				
				System.out.println("lewat");
				criteria.setProjection(Projections.rowCount());
				total = ((Integer)(criteria.list().iterator().next())).intValue();
				
				//partial data
				criteria = UserDAO.getInstance().getSession().createCriteria(User.class);
				if (form.getString("username")!=null && form.getString("username").length()>0)
					criteria.add(Restrictions.ilike("Username", form.getString("username"), MatchMode.ANYWHERE));
				
				if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
					criteria.addOrder(Order.asc(form.getString("orderBy")));
				}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
						criteria.addOrder(Order.desc(form.getString("orderBy")));
				}else{	
					criteria.addOrder(Order.asc("id"));
				}
				
				criteria.setFirstResult(start);
				criteria.setMaxResults(count);
				userList = criteria.list();
				request.setAttribute("userList", userList);
				
				
			}else{
				
				total = 0;
				request.removeAttribute("userList");
			}
		
			request.setAttribute("total", total);
			String pager = Pager.generatePager(start, count, total);
			String pagerItem = Pager.generatePagerItem(start, count, total);
			request.setAttribute("PAGER",pager);
			request.setAttribute("PAGERITEM",pagerItem);
			form.setString("username", "");
			form.setString("password", "");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			generalError(request,ex);
			return mapping.findForward("success");
		}finally{
			//log.info("[ END "+this.getClass().getName()+" "+users.getUserName()+"  ] ");
			try {
				
				UserDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward performForm(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		UserForm form = (UserForm) actionForm;
		try{
			if (form.getLong("id") > 0){
				User user = (User) UserDAO.getInstance().getSession().createCriteria(User.class).add(Restrictions.eq("Id", form.getLong("id"))).uniqueResult();
				form.setString("id", user.getId());
				form.setString("username", user.getUsername());
				request.setAttribute("user", user);
			}	
				
		}catch(Exception ex) {
		}finally {
			try {
				UserDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("success");
	}


	private  ActionForward performSave(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request, HttpServletResponse response) {		
		UserForm form = (UserForm) actionForm;
		User user = new User();
		Session session = null;
		Transaction transaction = null;
		try {
			session = UserDAO.getInstance().getSession();
			User userSession = (User) request.getSession().getAttribute("user");
			transaction = session.beginTransaction();
			if (form.getLong("id")==0){
				user.setUsername(form.getString("username"));
				user.setPassword(getMD5(form.getString("password")));
				user.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				user.setCreatedBy(userSession.getUsername());
				session.save(user);
			}else{
				user = UserDAO.getInstance().get(form.getLong("id"));
				user.setUsername(form.getString("username"));
				user.setPassword(getMD5(form.getString("password")));
				user.setUpdatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
				user.setUpdatedBy(userSession.getUsername());
				session.update(user);
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
		UserForm form = (UserForm)actionForm;
		try{
			User user = UserDAO.getInstance().get(form.getLong("id"));
			request.setAttribute("user", user);
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
			session = UserDAO.getInstance().getSession();
			UserForm form = (UserForm) actionForm;
			User user= UserDAO.getInstance().get(form.getLong("id"));
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
			ActionForward forward = mapping.findForward("success");
			StringBuffer sb = new StringBuffer(forward.getPath());
			return new ActionForward(sb.toString(),true);
		}catch(Exception e){
			String error="User sedang dalam transaksi";
			generalError(request, new Exception(error));
			if(transaction!=null) transaction.rollback();
    		return (new ActionForward(mapping.getInput()));
		}finally {
			try {
				if(session!=null) session.close();
			}catch(Exception exx) {
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
