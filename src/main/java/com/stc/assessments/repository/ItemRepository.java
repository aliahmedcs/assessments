package com.stc.assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.assessments.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
