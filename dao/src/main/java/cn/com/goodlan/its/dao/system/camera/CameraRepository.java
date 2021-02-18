package cn.com.goodlan.its.dao.system.camera;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.Camera;

public interface CameraRepository extends CustomizeRepository<Camera, String> {

    Camera getByIp(String ip);

    Camera getByPosition(String position);

}
