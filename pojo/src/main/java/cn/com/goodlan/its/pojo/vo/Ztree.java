package cn.com.goodlan.its.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Ztree树结构
 *
 * @author liukai
 */
@Data
public class Ztree implements Serializable {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 节点父ID
     */
    private String parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 是否勾选
     */
    private boolean checked = false;

    /**
     * 是否展开
     */
    private boolean open = false;

    /**
     * 是否能勾选
     */
    private boolean nocheck = false;

}
