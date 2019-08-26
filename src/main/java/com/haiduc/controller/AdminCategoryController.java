package com.haiduc.controller;

import com.haiduc.model.Category;
import com.haiduc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    // list Category

    @GetMapping("/admin/category")
    public ModelAndView HomeCategory() {
        Iterable<Category> categories;
        categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admincategory/list");
        modelAndView.addObject("listcategory", categories);
        return modelAndView;
    }


    // Create Category
    @GetMapping("/admin/createcategory")
    public ModelAndView formCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("/admincategory/create");
        modelAndView.addObject("createcategory", new Category());
        return modelAndView;
    }

    @PostMapping(value = "/admin/createcategory", produces = "application/json;charset=UTF-8")
    public ModelAndView saveCategory(@ModelAttribute("createcategory") Category category, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("/admincategory/create");
//            return modelAndView;
//        }
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/admincategory/create");
        modelAndView.addObject("createcategory", new Category());
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }


    // Edit Category

    @GetMapping(value = "/admin/editcategory/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView formEditCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/admincategory/edit");
            modelAndView.addObject("editcategory", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/editcategory", produces = "application/json;charset=UTF-8")
    public ModelAndView updateCategory(@Validated @ModelAttribute("editcategory") Category category, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("/admincategory/edit");
//            return modelAndView;
//        }
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/admincategory/edit");
        modelAndView.addObject("editcategory", category);
        modelAndView.addObject("message", " Update success");
        return modelAndView;
    }

    // Delete Category


    @GetMapping("/admin/deletecategory/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.remove(id);
        return "redirect:/admin/category";
    }
}
