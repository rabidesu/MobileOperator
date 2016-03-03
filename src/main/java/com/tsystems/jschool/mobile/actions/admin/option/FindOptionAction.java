package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Option> options;
        try {
            if (searchText != null) {
                options = app.optionService.getOptionsByName(searchText);
            } else {
                options = app.optionService.getAllOptions();
            }
            request.setAttribute("listOptions", options);
            return "/admin/option/searchOption.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Просмотр опций невозможен! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
