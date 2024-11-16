package com.hexaware.ams.repository;
//Author: Arghya Mandal
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ams.entity.Asset;
import com.hexaware.ams.entity.Asset.Status;
import com.hexaware.ams.entity.AssetCategory;
@Repository
public interface IAssetRepository extends JpaRepository<Asset, Integer> {
	List<Asset> findAssetByCategory(AssetCategory categoryName);
	List<Asset> findByStatus(Status status);
}
