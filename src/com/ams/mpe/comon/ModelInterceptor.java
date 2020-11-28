package com.ams.mpe.comon;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class ModelInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());
	
	

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		super.afterTransactionCompletion(tx);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		// attribute change_on of all entity replaced with new date
		for (int i=0; i<propertyNames.length; i++) {
			if ("changeOn".equalsIgnoreCase(propertyNames[i])) {
				currentState[i] = new Date();
			}
		}
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// attribute change_on of all entity replaced with new date
		for (int i=0; i<propertyNames.length; i++) {
			if ("createOn".equalsIgnoreCase(propertyNames[i])) {
				state[i] = new Date();
			}
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}
	
	

}
