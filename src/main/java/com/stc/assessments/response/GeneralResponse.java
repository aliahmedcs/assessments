package com.stc.assessments.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralResponse<T> {
	private int status;
	private T objResponse;

}
