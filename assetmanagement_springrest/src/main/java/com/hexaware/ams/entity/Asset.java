package com.hexaware.ams.entity;
// Author: Arghya Mandal
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asset")
public class Asset {

    public enum Status {
        Available,
        Borrowed
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assetId;

    @NotNull(message = "Asset name cannot be null")
    @Size(min = 2, max = 100, message = "Asset name must be between 2 and 100 characters")
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @NotNull(message = "Category cannot be null")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private AssetCategory category;

    @Size(max = 50, message = "Asset model must be at most 50 characters")
    @Column(name = "asset_model")
    private String assetModel;

    @PastOrPresent(message = "Manufacturing date cannot be in the future")
    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Future(message = "Expiry date must be in the future")
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @NotNull(message = "Asset value cannot be null")
    @Column(name = "asset_value", nullable = false)
    private double assetValue;  

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.Available;

    public Asset() {
        super();
    }

    public Asset(int assetId,
                 @NotNull(message = "Asset name cannot be null") @Size(min = 2, max = 100, message = "Asset name must be between 2 and 100 characters") String assetName,
                 @NotNull(message = "Category cannot be null") AssetCategory category,
                 @Size(max = 50, message = "Asset model must be at most 50 characters") String assetModel,
                 @PastOrPresent(message = "Manufacturing date cannot be in the future") LocalDate manufacturingDate,
                 @Future(message = "Expiry date must be in the future") LocalDate expiryDate,
                 @NotNull(message = "Asset value cannot be null") double assetValue,  
                 @NotNull(message = "Status cannot be null") Status status) {
        super();
        this.assetId = assetId;
        this.assetName = assetName;
        this.category = category;
        this.assetModel = assetModel;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.assetValue = assetValue;
        this.status = status;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public AssetCategory getCategory() {
        return category;
    }

    public void setCategory(AssetCategory category) {
        this.category = category;
    }

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getAssetValue() {
        return assetValue;  // Returns a double
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;  // Accepts a double
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asset [assetId=" + assetId + ", assetName=" + assetName + ", category=" + category + ", assetModel="
                + assetModel + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate
                + ", assetValue=" + assetValue + ", status=" + status + "]";
    }
}

