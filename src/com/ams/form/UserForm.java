package com.ams.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ams.mpe.comon.BaseDynaForm;

public class UserForm extends BaseDynaForm {

	private static final long serialVersionUID = 1L;

	/** 
	* Method validate
	* @param ActionMapping mapping
	* @param HttpServletRequest request
	* @return ActionErrors
	*/
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
	return null;
	}

	/** 
	* Method reset
	* @param ActionMapping mapping
	* @param HttpServletRequest request
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	

}
