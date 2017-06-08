package com.example.request.upload;

import com.example.request.api.json.BaseResponse;




public interface UploadImageCompleteListener {
    void onPrepare();

    void onUploadImageSuccess(BaseResponse response);

    void onUploadImageFailed();
}
