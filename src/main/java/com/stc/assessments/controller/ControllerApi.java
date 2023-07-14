package com.stc.assessments.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stc.assessments.request.FilesRequest;
import com.stc.assessments.request.ItemRequest;
import com.stc.assessments.response.GeneralResponse;
import com.stc.assessments.response.ResponseFile;
import com.stc.assessments.service.FilesService;
import com.stc.assessments.service.ItemService;



import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/auth")
public class ControllerApi {

	@Autowired
	ItemService itemService;
	@Autowired
	FilesService filesService;

	@PostMapping("/createItem")
	@RolesAllowed({ "ROLE_ADMIN" })
	public GeneralResponse<?> createItem(@RequestBody ItemRequest itemRequest) {

		return itemService.createItem(itemRequest);

	}

	@PostMapping("/updateItem")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })

	public GeneralResponse<?> updateItem(@RequestBody ItemRequest itemRequest) {

		return itemService.updateItem(itemRequest);

	}

	@PostMapping("/createFile")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })

	public GeneralResponse<?> store(@RequestParam("file") MultipartFile file,
			@RequestParam("permissionGroupId") int permissionGroupId) {

		return filesService.store(file, permissionGroupId);

	}

	@PostMapping("/updateFile")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })

	public GeneralResponse<?> updateFile(@RequestBody FilesRequest filesRequest) {

		return filesService.updateFile(filesRequest);

	}

	@PostMapping("/getMetaData")

	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })

	public GeneralResponse<?> getMetaData(@RequestBody FilesRequest filesRequest) {

		return filesService.getMetaData(filesRequest);

	}

	@QueryMapping
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })

	public GeneralResponse<?> getMetaDataGraghQL(@RequestBody FilesRequest filesRequest) {

		return filesService.getMetaData(filesRequest);

	}

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = filesService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId().toString()).toUriString();

			return new ResponseFile(

					dbFile.getItem().getName(), fileDownloadUri

			);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/getFile/{id}")
	public ResponseEntity<?> getFile(@PathVariable String id) {
		try {
			return filesService.getFile(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(e.getMessage());
		}

	}

}