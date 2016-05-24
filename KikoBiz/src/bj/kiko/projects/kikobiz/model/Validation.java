package bj.kiko.projects.kikobiz.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {
	public void setEffectivePriority(Offre o){
        /*Date currentDate = new Date();
        //long milliseconds = currentDate.getTime() - this.toDate(o.getStartDate()).getTime();
        int hours   = (int) ((milliseconds / (1000*60*60)));
        if(hours >= EndDate.NB_HOURS_BEFORE_PRIORITYCOMP_CHANGE.toInt()){
           // o.setPriorityToCompare(o.getRealPriority());
        }else{
        	//o.setPriorityToCompare(o.getStartPriority());
        }*/
    }
	private Date toDate(String date){
        Date convertedDate = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            convertedDate = sdf.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return convertedDate;
    }
}
