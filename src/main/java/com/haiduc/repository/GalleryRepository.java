package com.haiduc.repository;

import com.haiduc.model.Gallery;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GalleryRepository extends PagingAndSortingRepository<Gallery,Long> {
}
