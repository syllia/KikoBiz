package bj.kiko.projects.kikobiz;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@SuppressWarnings("serial")
public class KikoBizServlet extends HttpServlet {
	// Enregistrement de la classe persistable auprès d'Objectify
    static {
        ObjectifyService.register(Message.class);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            // Requête Objectify
            List<Message> messages = ofy().load().type(Message.class).order("-date").limit(5).list();
            JSONObject json      = new JSONObject();
            JSONArray  Jmessages = new JSONArray();
            JSONObject message;
            try
            {
        
               for(Message me : messages)
               {
                   message = new JSONObject();
                   message.put("id",me.getId() );
                   message.put("date",me.getDate());
                   message.put("name",me.getName());
                   message.put("message",me.getMessage());
                   Jmessages.put(message);
                 
               }
               json.putOpt("messages", Jmessages);
            }
            catch (JSONException jse)
            { 

            }
            resp.setContentType("application/json");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json.toString());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {  
        
            // Création de l'objet
            Message message = new Message(req.getParameter("name"), req.getParameter("message"));
            // Enregistrement de l'objet dans le Datastore avec Objectify
            ofy().save().entity(message).now();

            //resp.sendRedirect("/");
        
    }
}
