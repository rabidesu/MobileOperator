package controllers;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Alexandra on 16.02.2016.
 */
public class ActionFactory {

    public static Action getAction(HttpServletRequest request){

        String requestAction = request.getPathInfo().substring(request.getPathInfo().lastIndexOf("/") + 1);
        System.out.println("RequestAction: " + requestAction);

        try {
            Action action = (Action) Class.forName("controllers." + requestAction + "Action").newInstance();
            return action;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

}
