package bj.kiko.projects.kikobiz.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache()
public class Offre {
	@Id Long id;
	@Parent Key subCategoryId;
	String typeOffre;
	@Index Date startDate;
	@Index Date endDate;
	String title;
	@Index int cost;
	String description;
	@Index int nbViews;
	List<String> pictures;
	@Index int realPriority;
	@Index int startPriority;

	public Offre(){

	}
	public Offre(Long subCategoryId,String typeOffre,
			String title,int cost,String description,int nbViews,List<String> pictures){
		this.subCategoryId=KeyFactory.createKey("sousCategorie", subCategoryId);
		this.typeOffre=typeOffre;
		this.startDate=new Date();
		try {
			this.endDate=addDays(startDate, EndDate.DAYSMAXFOROFFER.toInt());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.title=title;
		this.cost=cost;
		this.description=description;
		this.nbViews=nbViews;
		this.pictures=pictures;
		this.startPriority=Priority.BASESTART.toInt();
		this.realPriority=Priority.BASEAFTER.toInt();	
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public Long getSubCategoryId() {
		return subCategoryId.getId();
	}
	public void setSubCategoryId(Key subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getTypeOffre() {
		return typeOffre;
	}
	public void setTypeOffre(String typeOffre) {
		this.typeOffre = typeOffre;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public int getRealPriority() {
		return realPriority;
	}
	public void setRealPriority(int realPriority) {
		this.realPriority = realPriority;
	}
	public int getStartPriority() {
		return startPriority;
	}
	public void setStartPriority(int startPriority) {
		this.startPriority = startPriority;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Date addDays(Date dt, int days) throws ParseException
    {
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		//c.setTime(sdf.parse(dt));
		 c.setTime(dt);
		c.add(Calendar.DATE, days);  // number of days to add
		
		return c.getTime();
		//return sdf.format(c.getTime());
        /*Calendar cal = Calendar.getInstance();
       
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();*/
    }
	

}
