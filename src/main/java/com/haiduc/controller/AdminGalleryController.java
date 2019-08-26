package com.haiduc.controller;

import com.haiduc.model.Category;
import com.haiduc.model.Gallery;
import com.haiduc.model.GalleryImages;
import com.haiduc.service.CategoryService;
import com.haiduc.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class AdminGalleryController {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private CategoryService categoryService;
    private static String UPLOAD_LOCATION = "/home/haiduc/Spring/src/main/webapp/upload/";

    @ModelAttribute("listCate")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    // list Gallery

    @GetMapping("/admin/gallery")
    public ModelAndView HomeGallery() {
        Iterable<Gallery> galleries;
        galleries = galleryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admingallery/list");
        modelAndView.addObject("listgallery", galleries);
        return modelAndView;
    }

    // Create Gallery
    @GetMapping("/admin/creategallery")
    public ModelAndView formCreateGallery() {
        ModelAndView modelAndView = new ModelAndView("/admingallery/create");
        modelAndView.addObject("creategallery", new GalleryImages());
        return modelAndView;
    }

    @PostMapping(value = "/admin/creategallery", produces = "application/json;charset=UTF-8")
    public ModelAndView saveGallery(@Validated @ModelAttribute("creategallery") GalleryImages galleryImages, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admingallery/create");
            return modelAndView;
        }
        MultipartFile file = galleryImages.getMultipartFile();
        String path = UPLOAD_LOCATION + file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pathFile = file.getOriginalFilename();
        Gallery gallerydb = new Gallery();
        gallerydb.setId(galleryImages.getId());
        gallerydb.setNameImage(galleryImages.getNameImage());
        gallerydb.setDescImage(galleryImages.getDescImage());
        gallerydb.setImage(pathFile);
        gallerydb.setCategory(galleryImages.getCategory());
        galleryService.save(gallerydb);
        ModelAndView modelAndView = new ModelAndView("/admingallery/create");
        modelAndView.addObject("creategallery", new GalleryImages());
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }

    // Edit Gallery

    @GetMapping(value = "/admin/editgallery/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView formEditGallery(@PathVariable Long id) {
        Gallery gallery = galleryService.findById(id);
        if (gallery != null) {
            ModelAndView modelAndView = new ModelAndView("/admingallery/edit");
            modelAndView.addObject("editgallery", gallery);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/editgallery", produces = "application/json;charset=UTF-8")
    public ModelAndView updateCGallery(@Validated @ModelAttribute("editgallery") Gallery gallery, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/admingallery/edit");
            return modelAndView;
        }
        galleryService.save(gallery);
        ModelAndView modelAndView = new ModelAndView("/admingallery/edit");
        modelAndView.addObject("editgallery", gallery);
        modelAndView.addObject("message", " Update success");
        return modelAndView;
    }

    // Delete Gallery
    @GetMapping("/admin/deletegallert/{id}")
    public String deleteGallery(@PathVariable Long id) {
        galleryService.remove(id);
        return "redirect:/admin/gallery";
    }
}
