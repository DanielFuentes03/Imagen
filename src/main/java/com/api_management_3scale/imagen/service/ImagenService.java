package com.api_management_3scale.imagen.service;

import com.api_management_3scale.imagen.model.Imagen;
import com.api_management_3scale.imagen.repository.ImagenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenService {

    public final ImagenRepository imagRepo;

    @Autowired
    public ImagenService(ImagenRepository imagRepo){
        this.imagRepo = imagRepo;
    }

    public List<Imagen> listAll (){return imagRepo.findAll();}

    public Optional<Imagen> getOptional(Long id){return imagRepo.findById(id);}

    public Imagen save(Imagen imagen){return imagRepo.save(imagen);}

    public void delete(Long id){imagRepo.deleteById(id);}

}
