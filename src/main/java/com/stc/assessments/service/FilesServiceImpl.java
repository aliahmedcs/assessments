package com.stc.assessments.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stc.assessments.enumeration.EnumerationType;
import com.stc.assessments.model.Files;
import com.stc.assessments.model.Item;
import com.stc.assessments.model.PermissionGroups;
import com.stc.assessments.model.Permissions;
import com.stc.assessments.repository.FilesRepository;
import com.stc.assessments.repository.ItemRepository;
import com.stc.assessments.repository.PermissionGroupsRepository;
import com.stc.assessments.repository.PermissionsRepository;
import com.stc.assessments.request.FilesRequest;
import com.stc.assessments.response.GeneralResponse;

import org.springframework.http.HttpHeaders;

@Service
public class FilesServiceImpl implements FilesService {
	@Autowired
	PermissionsRepository permissionsRepository;
	@Autowired
	private FilesRepository filesRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	PermissionGroupsRepository permissionGroupsRepository;

	@Override
	public GeneralResponse<?> store(MultipartFile file, int permissionGroupId) {
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Optional<PermissionGroups> permisssionGroups = permissionGroupsRepository.findById(permissionGroupId);
			if (permisssionGroups.isEmpty()) {
				return new GeneralResponse<>(209, "The group permission not found");
			}
			Item newItem = new Item();

			newItem.setName(fileName);
			newItem.setType(EnumerationType.File);
			newItem.setPermissionGroupId(permisssionGroups.get());

			Item item = itemRepository.save(newItem);

			Files fileDB = new Files();// (fileName, file.getContentType(), file.getBytes());

			fileDB.setItem(item);
			fileDB.setBbbbb(file.getBytes());
			Files savedFile = filesRepository.save(fileDB);
			return new GeneralResponse<>(200, savedFile.getId());
		} catch (Exception e) {
			return new GeneralResponse<>(209, e.getMessage());
		}
	}

	@Override
	public GeneralResponse<?> updateFile(FilesRequest filesRequest) {
		try {
			Optional<Permissions> user = permissionsRepository.findById(filesRequest.getUserId());
			if (user.isEmpty()) {
				return new GeneralResponse<>(209, "You are not registered");
			}
			Optional<Files> file = filesRepository.findById(filesRequest.getFileId());
			if (file.isEmpty()) {
				return new GeneralResponse<>(209, "Item not found");
			}
			Optional<Item> item = itemRepository.findById(filesRequest.getItemId());
			if (item.isEmpty()) {
				return new GeneralResponse<>(209, "Parent Item not found");
			}

			Files newFile = new Files();

			if (String.valueOf(user.get().getPermissionLevel()).equals("VEIW")) {
				return new GeneralResponse<>(209, "You aren't allowed to edit on this file");
			} else {
				newFile.setItem(item.get());
				newFile.setBbbbb(filesRequest.getFile().getBytes());
				filesRepository.save(newFile);
			}
			return new GeneralResponse<>(200, newFile);
		} catch (Exception e) {
			return new GeneralResponse<>(209, e.getMessage());
		}

	}

	@Override
	public GeneralResponse<?> getMetaData(FilesRequest filesRequest) {
		try {
			Optional<Permissions> user = permissionsRepository.findById(filesRequest.getUserId());
			if (user.isEmpty()) {
				return new GeneralResponse<>(209, "You are not registered");
			}

			Optional<Files> file = filesRepository.findById(filesRequest.getFileId());
			if (file.isEmpty()) {
				return new GeneralResponse<>(209, "Item not found");
			}
			if (String.valueOf(user.get().getPermissionLevel()).equals("VEIW")) {
				return new GeneralResponse<>(209, "You aren't allowed to access this file");
			}

			return new GeneralResponse<>(200, filesRepository.getMetaData(filesRequest.getFileId()));
		} catch (Exception e) {
			return new GeneralResponse<>(209, e.getMessage());
		}
	}

	@Override
	public Stream<Files> getAllFiles() {
		return filesRepository.findAll().stream();
	}

	public ResponseEntity<?> getFile(String id) {
		
		Files file = filesRepository.findById(Integer.parseInt(id)).get();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getItem().getName() + "\"")
				.body(file.getBbbbb());
	}

}