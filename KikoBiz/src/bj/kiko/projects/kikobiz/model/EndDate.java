package bj.kiko.projects.kikobiz.model;

public enum  EndDate {
	DAYSMAXFOROFFER(14),
	NB_HOURS_BEFORE_PRIORITYCOMP_CHANGE(24);
	private int d;
	//Constructeur
	  EndDate(int d){
		  this.d =d;
	  }
	  public int toInt(){
		    return d;
		  }
}
