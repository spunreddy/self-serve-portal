package com.snaplogic.portal.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Parameters
 *    email - string - the user’s email address
 *    first_name - string - user’s first name
 *    last_name - string - user’s last name
 *    organization - string - name of the organization, such as, Snaplogic
 *    administrator - boolean - whether the user is an administrator or not
 *    allow_password_login - boolean - whether the user can log in with password
 *    create_home_directory - boolean - whether or not to create a default project space for this user. The path is /<org>/projects/<last name> <first name>.
 */

public class User {
	
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@JsonProperty("first_name") 
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("organization")
	private String org;
	
	@JsonProperty("administrator")
	private boolean isAdmin;
	
	@JsonProperty("allow_password_login")
	private boolean allowPasswordLogin;
	
	@JsonProperty("create_home_directory")
	private boolean createHomeDirectory;

	public User() {
		super();
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param org
	 * @param isAdmin
	 * @param allowPasswordLogin
	 * @param createHomeDirectory
	 */
	
/*
	public User(@JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
			@JsonProperty("email") String email, @JsonProperty("organization") String org,
			@JsonProperty("administrator") boolean isAdmin,
			@JsonProperty("allow_password_login") boolean allowPasswordLogin,
			@JsonProperty("create_home_directory") boolean createHomeDirectory) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.org = org;
		this.isAdmin = isAdmin;
		this.allowPasswordLogin = allowPasswordLogin;
		this.createHomeDirectory = createHomeDirectory;
	}

*/	public String getFirstName() {
		return firstName;
	}

	public User(String firstName, String lastName, String email, String org, boolean isAdmin,
			boolean allowPasswordLogin, boolean createHomeDirectory) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.org = org;
		this.isAdmin = isAdmin;
		this.allowPasswordLogin = allowPasswordLogin;
		this.createHomeDirectory = createHomeDirectory;
		logger.info(this.toString());
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAllowPasswordLogin() {
		return allowPasswordLogin;
	}

	public void setAllowPasswordLogin(boolean allowPasswordLogin) {
		this.allowPasswordLogin = allowPasswordLogin;
	}

	public boolean isCreateHomeDirectory() {
		return createHomeDirectory;
	}

	public void setCreateHomeDirectory(boolean createHomeDirectory) {
		this.createHomeDirectory = createHomeDirectory;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", org=" + org
				+ ", isAdmin=" + isAdmin + ", allowPasswordLogin=" + allowPasswordLogin + ", createHomeDirectory="
				+ createHomeDirectory + "]";
	}

	
	
	
/*	@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("email: ")
                .append(this.email).append("\n")
                .append("first_name: ")
                .append(this.firstName).append("\n")
                .append("last_name: ")
                .append(this.lastName).append("\n")
                .append("organization: ")
                .append(this.org).append("\n")
                .append("administrator: ")
                .append(this.isAdmin).append("\n")
                .append("allow_password_login: ")
                .append(this.allowPasswordLogin).append("\n")
                .append("create_home_directory: ")
                .append(this.createHomeDirectory).append("\n");
        
        return stringBuilder.toString();
	}
*/
	
	
}
