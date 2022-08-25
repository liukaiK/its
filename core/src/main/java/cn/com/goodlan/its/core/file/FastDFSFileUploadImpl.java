package cn.com.goodlan.its.core.file;

import cn.hutool.core.codec.Base64Decoder;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * fastdfs上传文件实现
 *
 * @author liukai
 */
@Component
public class FastDFSFileUploadImpl implements FileUpload {

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(String base64ImageStr) {
        if (StringUtils.isEmpty(base64ImageStr)) {
            return null;
        }
        byte[] bytes = Base64Decoder.decode(base64ImageStr);
        StorePath storePath = uploadFile(bytes);
        return File.separator + storePath.getFullPath();
    }

    /**
     * 上传文件
     */
    private synchronized StorePath uploadFile(byte[] b) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
        FastImageFile fastImageFile = new FastImageFile.Builder().withFile(inputStream, inputStream.available(), "png").build();
        return storageClient.uploadImage(fastImageFile);
    }


}
