package cn.com.goodlan.its.dao.primary.system.camera;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.Camera;

public interface CameraRepository extends CustomizeRepository<Camera, String> {

    Camera getByIp(String ip);

    Camera getByPosition(String position);

}
