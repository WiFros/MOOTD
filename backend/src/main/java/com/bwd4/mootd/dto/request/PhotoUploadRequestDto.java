package com.bwd4.mootd.dto.request;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public record PhotoUploadRequestDto(
        @RequestPart MultipartFile originImageFile,
        @RequestPart String DeviceId){
}
