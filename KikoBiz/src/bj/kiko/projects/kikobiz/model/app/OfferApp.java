package bj.kiko.projects.kikobiz.model.app;


import java.util.List;




public class OfferApp {
	private Long id;
	private Long subCategoryId;
	private String typeOffre;
	private String startDate;
	private String title;
	private int cost;
	private String description;
	private int nbViews;
	private List<String> pictures;
	public OfferApp(){

	}
	public OfferApp(Long subCategoryId,String typeOffre,String startDate,
			String title,int cost,String description,int nbViews,List<String> pictures){
		this.subCategoryId=subCategoryId;
		this.typeOffre=typeOffre;
		this.startDate=startDate;
		this.title=title;
		this.cost=cost;
		this.description=description;
		this.nbViews=nbViews;
		this.pictures=pictures;

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getTypeOffre() {
		return typeOffre;
	}
	public void setTypeOffre(String typeOffre) {
		this.typeOffre = typeOffre;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbViews() {
		return nbViews;
	}
	public void setNbViews(int nbViews) {
		this.nbViews = nbViews;
	}
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
}
