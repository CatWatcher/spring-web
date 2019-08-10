package com.example.springweb.controllers;

import com.example.springweb.entity.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

// говорит что данный класс будет обрабатывать запрос
@Controller
public class UserController {
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String showForm (AppUser appUser) {

        return "registration";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    // тут проверка данных
    public String submitForm (@ModelAttribute @Valid AppUser appUser, // валид - если что-то проверяем
                              // для реакции
                              BindingResult result) {
        // если есть ошибки в форме
        if (result.hasErrors()){

        }
        // если нет ошибок в форме
        System.out.println(appUser);
        // TODO добавить в базу
        // TODO отобразить страницу с афторизацией
        return "registration";
    }

}
