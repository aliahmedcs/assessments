package com.stc.assessments.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilesRequest {
	private int userId;
	///in case of update only
	private int fileId;
	private MultipartFile file;
	private int itemId,permissionGroupId;
	private String fileName;
}
