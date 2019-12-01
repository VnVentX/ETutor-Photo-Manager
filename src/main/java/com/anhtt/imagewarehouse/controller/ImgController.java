package com.anhtt.imagewarehouse.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.anhtt.imagewarehouse.DTO.ImageDTO;
import com.anhtt.imagewarehouse.message.request.ImageForm;
import com.anhtt.imagewarehouse.model.Image;
import com.anhtt.imagewarehouse.repository.ImageRepository;
import com.google.common.io.ByteSource;

@RestController
@RequestMapping("/api/images")
public class ImgController {
	@Autowired
	private ImageRepository imgRepo;

	@PostMapping
	public ResponseEntity<String> postImg(@RequestBody ImageForm image) {
		Image img = new Image(image.getName(), image.getData());
		imgRepo.save(img);
		String url = "http://photos.etutor.top:8021/PhotosManager/api/images/" + imgRepo.findIdByName(image.getName());
		return ResponseEntity.ok(url);
	}

	@GetMapping
	public ResponseEntity<List<ImageDTO>> getAllImg() {
		List<ImageDTO> img = imgRepo.getAll();
		return ResponseEntity.ok(img);
	}

	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getImg(@PathVariable UUID id) throws IOException {
		String data = imgRepo.findDataById(id);
		byte[] imageByteArray = Base64.getDecoder().decode(data);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "image/png");
		List<Charset> acceptCharset = Collections.singletonList(StandardCharsets.UTF_8);
		headers.setAcceptCharset(acceptCharset);
		InputStream in = ByteSource.wrap(imageByteArray).openStream();
		return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
	}

	
	// @PostMapping
	// public ResponseEntity<String> post(@RequestBody ImageDTO image) {
	// // String path = "src\\main\\resources\\images\\" + image.getName() + ".png";
	// v1
	// String path = "src\\main\\webapp\\WEB-INF\\images\\" + image.getName() +
	// ".png";
	// UtilBase64Img.decoder(image.getData(), path);
	// // String savePath = "/images/" + image.getName() + ".png"; v1
	// String savePath = "/WEB-INF/images/" + image.getName() + ".png";
	// Image img = new Image(image.getName(), savePath);
	// imgRepo.save(img);
	// return ResponseEntity.ok(imgRepo.findIdByPath(savePath));
	// }

	// v1
	// @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	// @ResponseBody
	// public ResponseEntity<byte[]> getImage(@PathVariable UUID id) throws
	// IOException {
	// InputStream in = getClass().getResourceAsStream(imgRepo.findPathById(id));
	// final HttpHeaders headers = new HttpHeaders();
	// headers.add("Content-Type", "image/png");
	// List<Charset> acceptCharset =
	// Collections.singletonList(StandardCharsets.UTF_8);
	// headers.setAcceptCharset(acceptCharset);
	// return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
	// }

	// // current
	// @GetMapping(value = "/{id}", produces = { MediaType.IMAGE_PNG_VALUE,
	// MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.ALL_VALUE)
	// @ResponseBody
	// public ResponseEntity<Resource> getImageAsResource(@PathVariable UUID id,
	// HttpServletResponse response) {
	// final HttpHeaders headers = new HttpHeaders();
	// headers.add("Content-Type", "image/png");
	// List<Charset> acceptCharset =
	// Collections.singletonList(StandardCharsets.UTF_8);
	// headers.setAcceptCharset(acceptCharset);
	// servletContext.getMimeType("image/png");
	// Resource resource = new ServletContextResource(servletContext,
	// imgRepo.findPathById(id));
	// return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	// }
}
