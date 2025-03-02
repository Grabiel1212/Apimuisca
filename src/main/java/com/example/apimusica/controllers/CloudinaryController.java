package com.example.apimusica.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
@RequestMapping("/archivos")
public class CloudinaryController {

    @Autowired
    private Cloudinary cloudinary;

    // SUBIR ARCHIVO
    @PostMapping("/subir")
    public Map<String, Object> subirArchivo(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "public_id", required = false) String publicId) throws IOException {
        // Si no se proporciona un public_id, usar el nombre del archivo sin la
        // extensión
        if (publicId == null || publicId.trim().isEmpty()) {
            publicId = file.getOriginalFilename().replaceAll("\\..+$", "");
        }

        Map<String, Object> options = ObjectUtils.asMap("public_id", publicId);
        return cloudinary.uploader().upload(file.getBytes(), options);
    }

    // ELIMINAR ARCHIVO
    @DeleteMapping("/eliminar/{publicId}")
    public Map<String, Object> eliminarArchivo(@PathVariable String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    // ACTUALIZAR ARCHIVO (Reemplazar uno existente con el mismo public_id)
    @PostMapping("/actualizar")
    public Map<String, Object> actualizarArchivo(@RequestParam("file") MultipartFile file,
            @RequestParam("public_id") String publicId) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap("public_id", publicId, "overwrite", true);
        return cloudinary.uploader().upload(file.getBytes(), options);
    }

    // BUSCAR ARCHIVO
    @GetMapping("/buscar/{publicId}")
    public Map<String, Object> buscarArchivo(@PathVariable String publicId) throws Exception {
        return cloudinary.api().resource(publicId, ObjectUtils.asMap(
                "resource_type", "image"));
    }

}
