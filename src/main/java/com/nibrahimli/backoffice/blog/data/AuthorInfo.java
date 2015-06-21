package com.nibrahimli.backoffice.blog.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nibrahimli.database.blog.entity.Author;
import com.nibrahimli.database.blog.entity.Image;

public class AuthorInfo {
	
	private Long id;
	private String pseudo;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
	private String confirmEmail;
	private Image avatar ; 
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the confirmEmail
	 */
	public String getConfirmEmail() {
		return confirmEmail;
	}
	/**
	 * @param confirmEmail the confirmEmail to set
	 */
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}	
	/**
	 * @return the image
	 */
	public Image getAvatar() {
		return avatar;
	}
	/**
	 * @param image the image to set
	 */
	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}
	
	public AuthorInfo create(Author author) {
		this.setId(author.getId());
		this.setPseudo(author.getPseudo());
		this.setFirstName(author.getFirstName());
		this.setLastName(author.getLastName());
		this.setPassword(author.getPassword());
		this.setConfirmPassword(author.getConfirmPassword());
		this.setEmail(author.getEmail());
		this.setConfirmEmail(author.getConfirmEmail());
		this.setAvatar(author.getAvatar());
		return this;
	}
	
	public Author update(Author author) {
		if(this.getId() != null)
			author.setId(this.getId());
		if(StringUtils.isNotBlank(this.getPseudo()))
			author.setPseudo(this.getPseudo());
		author.setFirstName(this.getFirstName());
		author.setLastName(this.getLastName());
		author.setPassword(this.getPassword());
		author.setConfirmPassword(this.getConfirmPassword());
		author.setEmail(this.getEmail());
		author.setConfirmEmail(this.getConfirmEmail());
		if(this.getAvatar() != null)
			author.setAvatar(this.getAvatar());
		return author;
	}
}
