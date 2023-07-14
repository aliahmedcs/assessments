package com.stc.assessments.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileUploadResponse {
	private String fileName;
    private String downloadUri;
	private long size;
}
