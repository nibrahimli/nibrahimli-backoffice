package com.nibrahimli.backoffice.blog.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nibrahimli.database.blog.entity.Article;
import com.nibrahimli.database.blog.entity.Author;

public class ArticleInfo {
	
	static private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
	
	private Long id ;
	private String title;
	private String text;
	private String date ;
	private String keywords;
	private List<Long> authorList;
	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * @return the authorList
	 */
	public List<Long> getAuthorList() {
		return authorList;
	}
	/**
	 * @param authorList the authorList to set
	 */
	public void setAuthorList(List<Long> authorList) {
		this.authorList = authorList;
	}

	public ArticleInfo create(Article article) {
		this.setId(article.getId());
		this.setTitle(article.getTitle());
		this.setText(article.getText());
		this.setKeywords(article.getKeywords());
		this.setDate(sdf.format(article.getDate()));
		this.setAuthorList(getIds(article.getAuthorList()));
		return this;
	}
	
	private List<Long> getIds(Set<Author> authorList) {
		List<Long> ids = new ArrayList<Long>();
		for (Author author : authorList) {
			ids.add(author.getId());
		}
		return ids;
	}
	public Article update(Article article, List<Author> authorList) throws Exception {
		if(this.getId() != null)
			article.setId(this.getId());
		article.setDate(sdf.parse(this.getDate()));
		article.setAuthorList(getAuthors(this.getAuthorList(), authorList));
		article.setKeywords(this.getKeywords());
		article.setTitle(this.getTitle());
		article.setText(this.getText());
		return article;
	}
	private Set<Author> getAuthors(List<Long> authorIdList, List<Author> authorList) {
		Set<Author> authors = new HashSet<Author>(); 
		for (Author author : authorList) {
			if(authorIdList.contains(author.getId()))
				authors.add(author);
		}
		return authors;
	}
}
