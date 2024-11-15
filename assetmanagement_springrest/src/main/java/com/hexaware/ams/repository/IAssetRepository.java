package com.hexaware.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.Asset;
@Repository
public interface IAssetRepository extends JpaRepository<Asset, Integer> {
	List<Asset> findAssetByCategory(String categoryName);
}
