package com.haiduc.model;

import org.springframework.web.multipart.MultipartFile;

public class GalleryImages extends Gallery {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
