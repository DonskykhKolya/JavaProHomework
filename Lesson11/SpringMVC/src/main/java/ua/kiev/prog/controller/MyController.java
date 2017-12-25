package ua.kiev.prog.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.prog.exceptions.PhotoErrorException;
import ua.kiev.prog.exceptions.PhotoNotFoundException;
import ua.kiev.prog.model.Photo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    private Map<Long, Photo> photos = new HashMap<>();

    @RequestMapping("/")
    public ModelAndView onIndex() {
        return new ModelAndView("index", "photos", photos.values());
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(@RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, new Photo(id, photo.getOriginalFilename(), photo.getBytes()));
            return "redirect:/";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete")
    public String onDelete(@RequestParam List<Long> ids) {
        for(Long id : ids) {
            if (photos.remove(id) == null)
                throw new PhotoNotFoundException();
        }
        return "redirect:/";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id).getImage();
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
