package com.stc.assessments.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.assessments.enumeration.EnumerationType;
import com.stc.assessments.model.Item;
import com.stc.assessments.model.PermissionGroups;
import com.stc.assessments.model.Permissions;
import com.stc.assessments.repository.ItemRepository;
import com.stc.assessments.repository.PermissionGroupsRepository;
import com.stc.assessments.repository.PermissionsRepository;
import com.stc.assessments.request.ItemRequest;
import com.stc.assessments.response.GeneralResponse;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	PermissionsRepository permissionsRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	PermissionGroupsRepository permissionGroupsRepository;

	@Override
	public GeneralResponse<?> createItem(ItemRequest itemRequest) {
		try {
			if (String.valueOf(EnumerationType.valueOf(itemRequest.getType())).equals("File")) {
				return new GeneralResponse<>(209, "you should use service with name createFile");
			}
			Optional<PermissionGroups> permisssionGroups = permissionGroupsRepository
					.findById(itemRequest.getPermissionGroupId());
			if (permisssionGroups.isEmpty()) {
				return new GeneralResponse<>(209, "The group permission not found");
			}
			Item newItem = new Item();

			newItem.setName(itemRequest.getName());
			newItem.setType(EnumerationType.valueOf(itemRequest.getType()));
			newItem.setPermissionGroupId(permisssionGroups.get());

			Item Item = itemRepository.save(newItem);
			return new GeneralResponse<>(200, Item);
		} catch (Exception e) {
			return new GeneralResponse<>(209, e.getMessage());
		}
	}

	@Override
	public GeneralResponse<?> updateItem(ItemRequest itemRequest) {
		try {
			if (String.valueOf(EnumerationType.valueOf(itemRequest.getType())).equals("File")) {
				return new GeneralResponse<>(209, "you should use service with name updateFile");
			}
			Optional<Item> item = itemRepository.findById(itemRequest.getItemId());
			if (item.isEmpty()) {
				return new GeneralResponse<>(209, "Item not found");
			}
			Optional<Permissions> user = permissionsRepository.findById(itemRequest.getUserId());
			if (user.isEmpty()) {
				return new GeneralResponse<>(209, "You are not registered");
			}
			Optional<PermissionGroups> permissionGroupId = permissionGroupsRepository
					.findById(itemRequest.getPermissionGroupId());
			if (permissionGroupId.isEmpty()) {
				return new GeneralResponse<>(209, "You Have no role in permission groups");
			}

			if (String.valueOf(user.get().getPermissionLevel()).equals("VEIW")) {
				return new GeneralResponse<>(209, "You aren't allowed to edit on this item");
			} else {
				item.get().setName(itemRequest.getName());
				item.get().setType(EnumerationType.valueOf(itemRequest.getType()));
				item.get().setPermissionGroupId(permissionGroupId.get());
				itemRepository.save(item.get());
			}
			return new GeneralResponse<>(200, item);
		} catch (Exception e) {
			return new GeneralResponse<>(209, e.getMessage());
		}
	}

}
