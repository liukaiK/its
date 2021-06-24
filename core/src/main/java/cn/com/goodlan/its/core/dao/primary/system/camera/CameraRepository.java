package cn.com.goodlan.its.core.dao.primary.system.camera;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Camera;

public interface CameraRepository extends CustomizeRepository<Camera, String> {

    Camera getByIp(String ip);

    Camera getByPosition(String position);

}
