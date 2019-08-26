package com.haiduc.controller;

import com.haiduc.model.Category;
import com.haiduc.model.Gallery;
import com.haiduc.service.CategoryService;
import com.haiduc.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private GalleryService galleryService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public ModelAndView listHome() {
        Iterable<Gallery> galleries;
        Iterable<Category> categories;
        galleries = galleryService.findAll();
        categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/home/index");
        modelAndView.addObject("listgallery", galleries);
        modelAndView.addObject("listcategory",categories);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView DetailGallery(@PathVariable Long id) {
        Gallery gallery = galleryService.findById(id);
        if (gallery != null) {
            ModelAndView modelAndView = new ModelAndView("/home/detail");
            modelAndView.addObject("detailgallery", gallery);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
}
