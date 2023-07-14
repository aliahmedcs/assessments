package com.stc.assessments.service;

import org.springframework.stereotype.Component;
import com.stc.assessments.response.GeneralResponse;

import com.stc.assessments.request.ItemRequest;

@Component
public interface ItemService {
	GeneralResponse<?> createItem(ItemRequest itemRequest);

	GeneralResponse<?> updateItem(ItemRequest itemRequest);

}
