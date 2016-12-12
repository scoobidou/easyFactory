package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import beans.User;
import dao.DAOException;
import dao.UserDAO;

public class InscriptionForm {
	
	private static int workload = 12;
	
	private UserDAO userDAO;
	private static final String ALGO_CHIFFREMENT = "SHA-256";
	
	public InscriptionForm(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String CONF = "conf";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	
	private String result;
	private Map<String,String> errors = new HashMap<>();
	
	public String getResult(){
		return result;
	}
	
	public Map<String,String> getErrors(){
		return errors;
	}
	
	public User signUser(HttpServletRequest request) {
		String email = getFieldValue(request,EMAIL);
		String firstName = getFieldValue(request,FIRST_NAME);
		String password =  getFieldValue(request,PASSWORD);
		String lastName = getFieldValue(request,LAST_NAME);
		String conf = getFieldValue(request,CONF);
		
		User user = new User();
		try {
			emailTraitement(email,user);
			passwordTraitement(password,conf,user);
			nameTraitement(firstName,lastName,user);
			
			if (errors.isEmpty()){
				userDAO.create(user);
				result = "Inscription Successful";
			}else{
				result = "Inscription failure";
			}
		} catch(DAOException e){
			result = "Fail to signin : unexpected error happened, thanks to wait until we correct it";
			e.printStackTrace();
		}
		return user;
	}
	
	
	private void validateEmail(String email) throws FormValidationException{
		if (email != null ){
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormValidationException( "Please write a valid mail" );
	        }else if (userDAO.find(email) != null ){
	        	throw new FormValidationException("This mail isn't disponible");
	        }
		} else {
			throw new FormValidationException("Wrong mail format");
		}
	}
	
	private void validatePassword(String password, String conf) throws FormValidationException {
		  if ( password != null && conf != null ) {
		        if ( !password.equals( conf ) ) {
		            throw new FormValidationException( "Passwords are different" );
		        } else if ( password.length() < 3 ) {
		            throw new FormValidationException( "Password must contain at least 3 characters" );
		        }
		    } else {
		        throw new FormValidationException( "Please enter a password and a confirmation" );
		    }
	}
	
	private void validateName(String name) throws FormValidationException {
		if (name != null ){
			if ( !name.matches("^[A-Z]'?[- a-zA-Z]( [a-zA-Z])*$")){
				throw new FormValidationException("Wrong name format");
			}
		}else {
			throw new FormValidationException("Please enter name");
		}
	}
	
	private void setError(String field,String message){
		errors.put(field,message);
	}
	
	private static String getFieldValue(HttpServletRequest request, String field){
		String value = request.getParameter(field);
		if (value == null || value.trim().length()==0 ){
			return null;
		}else{
			return value.trim();
		}
	}
	
	private void emailTraitement(String email, User user){
		try {
			validateEmail(email);
		}catch (FormValidationException e){
			setError(EMAIL,e.getMessage());
		}
		user.setEmail(email);
	}
	private void passwordTraitement(String password, String conf, User user){
		try {
			validatePassword(password,conf);
		}catch (FormValidationException e){
			setError(PASSWORD,password);
			setError(CONF,conf);
		}
		
		String passwordHash = hashPassword(password);
		
		user.setPassword(passwordHash);
		
	}
	
	private static String hashPassword(String pass){
		String  salt = BCrypt.gensalt(workload);
		String hashPassword = BCrypt.hashpw(pass,salt);
		
		return hashPassword;
	}
	
	private void nameTraitement(String firstName, String lastName, User user){
		try {
			validateName(firstName);
		}catch (FormValidationException e){
			setError(FIRST_NAME,e.getMessage());
		}
		user.setFirstName(firstName);
		try {
			validateName(lastName);
		}catch (FormValidationException e){
			setError(LAST_NAME,e.getMessage());
		}
		user.setLastName(lastName);
	}
}
