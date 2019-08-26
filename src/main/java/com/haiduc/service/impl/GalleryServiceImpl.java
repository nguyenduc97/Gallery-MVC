package com.haiduc.service.impl;

import com.haiduc.model.Gallery;
import com.haiduc.repository.GalleryRepository;
import com.haiduc.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;

public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    @Override
    public Iterable<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery findById(Long id) {
        return galleryRepository.findOne(id);
    }

    @Override
    public void save(Gallery gallery) {
        galleryRepository.save(gallery);
    }

    @Override
    public void remove(Long id) {
        galleryRepository.delete(id);
    }
}
