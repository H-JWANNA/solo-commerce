package com.e.commerce.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.commerce.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
