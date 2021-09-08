package cn.com.goodlan.its.api;

import cn.com.goodlan.its.api.entity.Params;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.event.approval.EventApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VehicleController {
    @Autowired
    private EventApprovalService eventApprovalService;

    /**
     * 根据工号查询违章集合
     *
     * @param params
     * @return
     */
    @PostMapping("findAllEvent")
    public Map<String, Object> list(@RequestBody Params params) {
        return eventApprovalService.findByUserId(params.getStudstaffno(), params.getPage());
    }

    /**
     * @param id 违章事件ID
     * @return
     */
    @GetMapping("/detail/{id}")
    public EventVO getOne(@PathVariable String id) {
        return eventApprovalService.getById(id);
    }


}
