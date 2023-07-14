package com.stc.assessments.service;





import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.stc.assessments.model.Files;
import com.stc.assessments.request.FilesRequest;

import com.stc.assessments.response.GeneralResponse;
@Component
public interface FilesService {

	GeneralResponse<?> store(MultipartFile file,int permissionGroupId);
	GeneralResponse<?> updateFile(FilesRequest filesRequest);
	GeneralResponse<?>getMetaData(FilesRequest filesRequest);
    ResponseEntity<?> getFile(String id) ;

	Stream<Files> getAllFiles();
    
}
