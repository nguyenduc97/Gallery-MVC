package com.haiduc.service;

import com.haiduc.model.Gallery;

public interface GalleryService {
    Iterable<Gallery> findAll();

    Gallery findById(Long id);

    void save(Gallery gallery);

    void remove(Long id);
}
