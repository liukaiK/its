package cn.com.goodlan.its;

import cn.com.goodlan.its.core.service.event.approval.EventService;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class FastDFSTest {


    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    private EventService eventService;

    @Test
    public void uploadFile() {
//        EventVO event = eventApprovalService.getById("004e80db-4115-4ac7-b70e-ffd778c54a20");
//        String imageStr = event.getImage().substring(21);
//
//        byte[] b = Base64Decoder.decode(imageStr);
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
//        System.out.println(b.length);
//        FastImageFile fastImageFile = new FastImageFile.Builder().withFile(inputStream, b.length, "png").build();
//        StorePath storePath = storageClient.uploadImage(fastImageFile);
//        System.out.println(storePath.getFullPath());
//        System.out.println(storePath.getPath());


    }


}
