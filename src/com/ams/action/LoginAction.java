package com.ams.action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.criterion.Restrictions;

import com.ams.form.LoginForm;
import com.ams.model.User;
import com.ams.model.dao.UserDAO;

public class LoginAction extends Action {

	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		try {
			request.getSession().invalidate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		ActionForward forward = null;
		String action = mapping.getParameter();
		if("PERFORMLOGIN".equalsIgnoreCase(action)){
			forward = performLogin(mapping, form, request, response);
		}
		return forward;
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
	
	private ActionForward performLogin(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		LoginForm form = (LoginForm) actionForm;
		try{
			if(form.getString("username").length()>0) {
				User user = (User) UserDAO.getInstance().getSession().createCriteria(User.class).add(Restrictions.eq("Username", form.getString("username"))).uniqueResult();
				if(user!=null) {
					String tes = user.getPassword();
					String te = getMD5(form.getString("password"));
					if(user.getPassword().equals(getMD5(form.getString("password")))) {
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						return mapping.findForward("success");
					}else {
						ActionMessages errors = new ActionMessages();
						errors.add("error.wrongPass", new ActionMessage("error.wrongPass",form.getString("username")));
						saveErrors(request,errors);
					}
				}else {
					ActionMessages errors = new ActionMessages();
					errors.add("error.noUser", new ActionMessage("error.noUser",form.getString("username")));
					saveErrors(request,errors);
				}
			}
		}catch(Exception ex) {
		}finally {
			try {
				UserDAO.getInstance().closeSessionForReal();
			}catch(Exception exx) {
			}
		}
		return mapping.findForward("failed");
	}
}
