package com.api_management_3scale.imagen.controller;

import com.api_management_3scale.imagen.model.Imagen;
import com.api_management_3scale.imagen.service.ImagenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/imagen")
public class ImagenController {

    ImagenService imagenServ;

    @Autowired
    public ImagenController(ImagenService imagenServ){
        this.imagenServ = imagenServ;
    }

    @GetMapping()
    public ResponseEntity<List<Imagen>>  listarImagenes(){
        List<Imagen> imagenes = imagenServ.listAll();
        if(imagenes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(imagenes, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> buscarporID(@PathVariable (name= "id")Long id){
        Optional<Imagen> imagenrecuperado = imagenServ.getOptional(id);
        if(imagenrecuperado.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(imagenrecuperado.get(),HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Imagen> nuevaImagen(@Valid @RequestBody Imagen imagen){
        return new ResponseEntity<>(imagenServ.save(imagen),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Imagen> borrarImagen(@PathVariable Long id){
        Optional<Imagen> imagenBorrar = imagenServ.getOptional(id);
        if(imagenBorrar.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            imagenServ.delete(imagenBorrar.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> actualizarImagen(@PathVariable (name = "id") Long id,@RequestBody Imagen input){
        Optional<Imagen> imagenactualizar = imagenServ.getOptional(id);
        if(imagenactualizar.isPresent()){
            imagenactualizar.get().setId(input.getId());
            imagenactualizar.get().setNombre(input.getNombre());
            imagenactualizar.get().setBase64(input.getBase64());
            imagenactualizar.get().setIdTraspaso(input.getIdTraspaso());
            return new ResponseEntity<>(imagenServ.save(imagenactualizar.get()),HttpStatus.OK);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

}
