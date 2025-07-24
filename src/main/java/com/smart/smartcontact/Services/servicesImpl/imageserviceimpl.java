package com.smart.smartcontact.Services.servicesImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.smart.smartcontact.Services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;


import java.io.IOException;
import java.util.UUID;

@Service
public class imageserviceimpl implements ImageService {



    public Cloudinary cloudinary;

    public imageserviceimpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactimage, String filename) {
try {
    byte[] data = new byte[contactimage.getInputStream().available()];
    contactimage.getInputStream().read(data);
    cloudinary.uploader().upload(data, ObjectUtils.asMap(
            "public_id",filename
    ));
    return this.getUrlFromPublicId(filename);
}
catch (IOException e) {
    e.printStackTrace();
    return null;
}

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url().transformation(new Transformation<>().width(500)
                .height(500).crop("fill")).generate(publicId);
    }
}
