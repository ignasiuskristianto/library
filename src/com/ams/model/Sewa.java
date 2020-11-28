package com.ams.model;

import java.text.SimpleDateFormat;

import com.ams.model.base.BaseSewa;



public class Sewa extends BaseSewa {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Sewa () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Sewa (long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	public String getFormatedTglSewa()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getTglSewa());
	}
	
	public String getFormatedTglKembali()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getTglKembali());
	}
}