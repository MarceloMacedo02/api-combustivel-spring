package br.com.zonesoftware.apicombustivel.excptions;

import java.sql.SQLException;

import org.hibernate.JDBCException;

public class ViolationException  extends JDBCException{


	private static final long serialVersionUID = 1L;
 

	public ViolationException(String message, SQLException cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
 
 

}
