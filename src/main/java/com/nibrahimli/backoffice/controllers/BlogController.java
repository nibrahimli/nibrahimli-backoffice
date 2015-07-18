package com.nibrahimli.backoffice.controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nibrahimli.backoffice.blog.data.ArticleInfo;
import com.nibrahimli.backoffice.blog.data.AuthorInfo;
import com.nibrahimli.database.blog.dao.ArticleDao;
import com.nibrahimli.database.blog.dao.AuthorDao;
import com.nibrahimli.database.blog.dao.ImageDao;
import com.nibrahimli.database.blog.entity.Article;
import com.nibrahimli.database.blog.entity.Author;
import com.nibrahimli.database.blog.entity.Image;


@Controller
public class BlogController {
	
	private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

	private static final String AVATAR_PATH = "/home/ec2-user/avatars/";
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ImageDao imageDao;	
	
	
	@RequestMapping(value="/blog/articles", method=RequestMethod.GET)
	public ModelAndView articles(ModelAndView mav) throws IOException{
		
		List<Article> articleList = articleDao.getAll();
		mav.addObject(sortByArticle(articleList));
		
		return mav;
	}
	
	private List<Article> sortByArticle(List<Article> articleList) {
		
		List<Article> list = new ArrayList<Article>();
		
		for (Article newArticle : articleList) {
			if(!exist(list, newArticle))
				list.add(newArticle);
		}
		return list;
	}

	private boolean exist(List<Article> list, Article newArticle) {
		for (Article article : list) {
			if(article.getId() == newArticle.getId())
				return true;
		}
		return false;
	}

	@RequestMapping(value="/blog/article", method=RequestMethod.GET)
	public ModelAndView editArticle(@RequestParam(value="id", required=false) Long id, ModelAndView mav){
		
		ArticleInfo articleInfo = new ArticleInfo();
		
		if(id != null){
			Article article = articleDao.getById(id);
			if(article != null)
				articleInfo = articleInfo.create(article);
		}
		mav.addObject("authorList", authorDao.getAll());
		mav.addObject("articleInfo", articleInfo);
		
		return mav;
	}
	
	@RequestMapping(value="/blog/article", method=RequestMethod.POST)
	public String nrtCreateText(@ModelAttribute(value = "articleInfo") ArticleInfo articleInfo, RedirectAttributes redirectAttrs) throws Exception {

		try
		{
			Article article = new Article() ;
			if(CollectionUtils.isNotEmpty(articleInfo.getAuthorList()) && articleInfo.getDate() != null && StringUtils.isNotBlank(articleInfo.getKeywords()) && StringUtils.isNotBlank(articleInfo.getTitle()) && StringUtils.isNotBlank(articleInfo.getText())){
				List<Author> authorList = authorDao.getAll();
				article = articleInfo.update(article, authorList) ;
				articleDao.saveOrUpdate(article);
				redirectAttrs.addAttribute("id", article.getId()) ;
				redirectAttrs.addFlashAttribute("infoMessage", "Article[id=" + article.getId() + "] saved !") ;
			}
			else 
			{
				redirectAttrs.addFlashAttribute("errorMessage","Please complate all requried fields") ;
				return "redirect:/blog/article";
			}
		}
		catch(Exception e)
		{
			logger.error("Error : ", e);
			redirectAttrs.addFlashAttribute("errorMessage","An unexpected error occured !!!") ;
			return "redirect:/blog/article";
		}

		return "redirect:/blog/article?id={id}";
	}
	
	@RequestMapping(value="/blog/article/update", method=RequestMethod.GET)
	public String updateArticle(@RequestParam("id") Long id,
			@RequestParam(value = "action", required = false) String action, RedirectAttributes redirectAttrs) throws Exception {
		try
		{
			logger.info( "Action " + action);
			redirectAttrs.addAttribute("id", id) ;
			Article article = articleDao.getById(id);
			if(action.equals("delete")){
				articleDao.delete(article);
				logger.info( "Article[id=" + article.getId() + "] deleted !");
				redirectAttrs.addFlashAttribute("infoMessage", "Article[id=" + article.getId() + "] deleted !") ;				
			}
			return "redirect:/blog/articles";
		}
		catch(Exception e)
		{
			logger.error("Error : ", e);
			redirectAttrs.addFlashAttribute("errorMessage","An unexpected error occured !!!") ;
			return "redirect:/blog/articles";
		}
	}
	
	@RequestMapping(value="/blog/authors", method=RequestMethod.GET)
	public ModelAndView authors(ModelAndView mav){
		
		List<Author> authorList = authorDao.getAll();
		mav.addObject(authorList);
		return mav;
	}
	
	@RequestMapping(value="/blog/author", method=RequestMethod.GET)
	public ModelAndView editAuthor(@RequestParam(value="id", required=false) Long id, ModelAndView mav){
		
		AuthorInfo authorInfo = new AuthorInfo();
		
		if(id != null){
			Author author = authorDao.getById(id);
			if(author != null)
				authorInfo = authorInfo.create(author);
		}
		mav.addObject("authorInfo", authorInfo);
		
		return mav;
	}
	
	@RequestMapping(value="/blog/author", method=RequestMethod.POST)
	public String createOrUpdateAuthor(@ModelAttribute(value = "authorInfo") AuthorInfo authorInfo, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) throws Exception {

		try
		{
			Author author = new Author() ;
			if(StringUtils.isNotBlank(authorInfo.getFirstName()) && StringUtils.isNotBlank(authorInfo.getLastName()) && StringUtils.isNotBlank(authorInfo.getEmail()) && StringUtils.isNotBlank(authorInfo.getPassword())){
				if(!file.isEmpty()){
					int size = getNumberOfImage(authorInfo) ;
					size++;
					String avatarName = saveFileInPath(file, authorInfo, size);
					Image avatar = new Image();
					avatar.setPath(avatarName);
					avatar.setAlt(avatarName);
					imageDao.create(avatar);
					authorInfo.setAvatar(avatar);					
				}
				else if(authorInfo.getId() != null){
					Image avatar = authorDao.getById(authorInfo.getId()).getAvatar();
					if(avatar != null)
						authorInfo.setAvatar(avatar);
				}
					
				author = authorInfo.update(author) ;
				authorDao.saveOrUpdate(author);
				redirectAttrs.addAttribute("id", author.getId()) ;
				redirectAttrs.addFlashAttribute("infoMessage", "Author[id=" + author.getId() + "] saved !") ;
			}
			else 
			{
				redirectAttrs.addFlashAttribute("errorMessage","Please complate all requried fields") ;
				return "redirect:/blog/author";
			}
		}
		catch(Exception e)
		{
			logger.error("Error : ", e);
			redirectAttrs.addFlashAttribute("errorMessage","An unexpected error occured !!!") ;
			return "redirect:/blog/author";
		}

		return "redirect:/blog/author?id={id}";
	}
	
	private int getNumberOfImage(AuthorInfo authorInfo) {
		try{
		if(authorInfo.getId() != null) {
			Image avatar = authorDao.getById(authorInfo.getId()).getAvatar();
			if(avatar != null){
				String[] split = avatar.getPath().split("-");
				return Integer.parseInt(split[split.length - 1].substring(0, split[split.length - 1].indexOf("."))); 
			}
		}
		}catch(Exception e){
			logger.info("image number not found {}", e);			
		}		
		return 0;
	}

	private String saveFileInPath(MultipartFile multipartFile, AuthorInfo authorInfo, int size) {

			try {
				if(!multipartFile.getContentType().contains("image/png"))
					throw new Exception("avatar field must be an image/png");
				String[] split = multipartFile.getOriginalFilename().split("\\."); 
				String avatarName = authorInfo.getPseudo()+"-"+size+"."+ split[split.length - 1];				
				
				byte[] bytes = multipartFile.getBytes();
				
				
				
				File file = new File(AVATAR_PATH +"/"+ authorInfo.getPseudo()+"/"+avatarName);
				file.getParentFile().mkdirs();
				
				
				logger.info("file path {}", file.getAbsolutePath());
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(file));
				
				stream.write(bytes);
				stream.close();
				return avatarName ;
			} catch (Exception e) {
				logger.error("error when getting bytes {}", e.getMessage());
			}			
		return null ;
	}

	@RequestMapping(value="/blog/author/update", method=RequestMethod.GET)
	public String updateAuthor(@RequestParam("id") Long id,
			@RequestParam(value = "action", required = false) String action, RedirectAttributes redirectAttrs) throws Exception {
		try
		{
			logger.info( "Action " + action);
			redirectAttrs.addAttribute("id", id) ;
			Author author = authorDao.getById(id);
			if(action.equals("delete")){
				authorDao.delete(author);
				logger.info( "Author[id=" + author.getId() + "] deleted !");
				redirectAttrs.addFlashAttribute("infoMessage", "Author[id=" + author.getId() + "] deleted !") ;
			}
			return "redirect:/blog/authors";
		}
		catch(Exception e)
		{
			logger.error("Error : ", e);
			redirectAttrs.addFlashAttribute("errorMessage","An unexpected error occured !!!") ;
			return "redirect:/blog/authors";
		}
	}
	
	
//	@RequestMapping(value="/blog/fileUpload", method=RequestMethod.GET)
//	public ModelAndView handleFormUpload(ModelAndView mav){
//		logger.info("handleFormUpload");		
//		return mav;
//	}
//	
//	
//	@RequestMapping(value = "/blog/fileUpload", method = RequestMethod.POST)
//	public String handleFormUpload(@RequestParam("name") String name,
//	            @RequestParam("file") MultipartFile file) {
//	
//		
//			Resource rdc = new ClassPathResource("/spring");
//			try {
//				if(rdc.getFile() != null)
//					System.out.println(rdc.getFile().getAbsolutePath());
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				logger.error("rdc is empty folder - {}",e1);
//			}
//	        if (!file.isEmpty()) {
//	            try {
//	            	 byte[] bytes = file.getBytes();
//	                 BufferedOutputStream stream =
//	                         new BufferedOutputStream(new FileOutputStream(new File("src/main/webapp/resources/file/"+file.getOriginalFilename())));
//	                 stream.write(bytes);
//	                 stream.close();
//				} catch (Exception e) {
//					logger.error("error when getting bytes {}",e.getMessage());
//				}
//	            // store the bytes somewhere
//	            return "redirect:home";
//	        }
//	
//	        return "redirect:home";
//	    }
	
	
    @RequestMapping(value = "/error")
    public void exception() throws Exception {
    	throw new Exception();
    }
}
