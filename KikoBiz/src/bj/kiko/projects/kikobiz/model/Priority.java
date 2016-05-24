package bj.kiko.projects.kikobiz.model;

public enum Priority {
	URGENT(5),
	BASESTART(5),
	BASEAFTER(3);
	private int d;
	//Constructeur
	Priority(int d){
		  this.d =d;
	  }
	  public int toInt(){
		    return d;
		  }
}
