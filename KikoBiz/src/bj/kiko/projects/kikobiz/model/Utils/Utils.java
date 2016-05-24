package bj.kiko.projects.kikobiz.model.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	public static Date addDays(Date dt, int days) throws ParseException
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
