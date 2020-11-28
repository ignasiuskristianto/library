/*
 * Created on May 11, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ams.mpe.comon;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionFilter implements Filter {
	//Log log = LogFactory.getFactory().getInstance(this.getClass());
	Category log = Category.getInstance(ActionFilter.class);
	
	protected FilterConfig filterConfig;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
		throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			//HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession session = request.getSession();
			/*Users user = (Users)session.getAttribute(CommonConstants.USER);
			String string = "";
			if (user!=null) {
				
				SimpleDateFormat sdtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = new GregorianCalendar();
				
				try {
					
					string = "["+user.getUserName()+"] "+" ["+(user.getBranchName()!=null?user.getBranchName():"-")+"] "+"["+sdtf.format(cal.getTime())+"] "+"["+request.getRequestURI()+"] \n";
					
					BufferedWriter out = new BufferedWriter(new FileWriter("d:/sementara/logs/mufins_log_"+sdf.format(cal.getTime())+".txt", true));
			        out.write(string);
			        out.close();
			        
					
				} catch(Exception ex){
					//ex.printStackTrace();
				}
			
			}
			
			if (request.getRequestURI().indexOf("home")!=-1) {
				//log.warn(" [ Home filter section! ] ");
				UserStream userstream = (UserStream)session.getAttribute("userstream");
				if (user!=null && userstream!=null) {
					//log.warn(" [ update session userstream : "+session.getId()+" // "+user.getUserName()+ "] ");
					userstream.updateUser(user, request.getRemoteAddr());
				}
			}*/
			//log.info("Test filter : "+request.getRequestURI());
			chain.doFilter(req, resp);
	}
	
	

}
