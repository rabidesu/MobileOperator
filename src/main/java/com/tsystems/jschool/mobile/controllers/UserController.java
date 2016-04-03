package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.validators.ChangeContractValidator;
import com.tsystems.jschool.mobile.validators.CompatibilityOptionValidator;
import com.tsystems.jschool.mobile.validators.NewContractValidator;
import com.tsystems.jschool.mobile.validators.TariffOptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"loggedUser"})
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"users/page/{pageNumber}/{searchText}/{searchField}", "users/page/{pageNumber}"}, method = RequestMethod.GET)
    public String getUsersPage(@PathVariable Integer pageNumber, @PathVariable Optional<String> searchText, @PathVariable Optional<String> searchField,
                               @RequestParam(value = "search_text", required = false) String text,
                               @RequestParam(value = "search_field", required = false) String field,
                               Model model) {

        int pageSize = 3;
        int totalCount;
        List<User> pageUsers;



        if (text != null && !text.isEmpty()){
            pageUsers = getListBySearchField(pageNumber, pageSize, field, text);
            totalCount = getCountBySearchField(field, text);
        } else if (searchText.isPresent()){
            pageUsers = getListBySearchField(pageNumber, pageSize, searchField.get(), searchText.get());
            totalCount = getCountBySearchField(searchField.get(), searchText.get());
        } else {
            pageUsers = userService.getUserList(pageNumber, pageSize);
            totalCount = userService.getCountUsers();
        }

        int current = pageNumber;
        int begin = Math.max(1, current - 5);
        long totalPage = totalCount % 3 == 0 ? totalCount/3 : totalCount/3 + 1;
        long end = Math.min(begin + 10, totalPage);


        model.addAttribute("listUsers", pageUsers);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchText", text != null ? text : searchText.isPresent() ? searchText.get() : "");
        model.addAttribute("searchField", field != null ? field : searchField.isPresent() ? searchField.get() : "");

        return "/admin/client/searchClient";
    }

    private List<User> getListBySearchField(int pageNumber, int pageSize, String field, String searchText) {
        switch (field){
            case "email": {
                return userService.getUserListByEmail(pageNumber, pageSize, searchText);
            }
            case "phone": {
                return userService.getUserListByContract(pageNumber, pageSize, searchText);
            }
            case "surname": {
                return userService.getUserListBySurname(pageNumber, pageSize, searchText);
            }
            default: return userService.getUserList(pageNumber, pageSize);
        }
    }

    private int getCountBySearchField(String field, String searchText) {
        switch (field){
            case "email": {
                return userService.getCountUserByEmail(searchText);
            }
            case "phone": {
                return userService.getCountUserByNumber(searchText);
            }
            case "surname": {
                return userService.getCountUserBySurname(searchText);
            }
            default: return userService.getCountUsers();
        }
    }


    @RequestMapping(value = "findClientProfile")
    public String findClientProfile(@RequestParam(value = "user_id")String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/admin/client/clientProfile";
    }
}
