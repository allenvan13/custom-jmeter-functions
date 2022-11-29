package com.model;

import lombok.Data;


/**
 * 文件信息(ProviderFile)表实体类
 *
 * @author Lu
 * @since 2022-10-13 11:33:13
 */

public class ProviderFile {
    /**
     * 文件名
     */
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 文件大小
     */
    private Double fileSize;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 供应商编码
     */
    private String providerCode;
    /**
     * 业务id
     */
    private Long businessId;
    /**
     * 文件类型
     */
    private String fileType;
}

