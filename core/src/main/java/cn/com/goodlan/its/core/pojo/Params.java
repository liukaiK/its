package cn.com.goodlan.its.core.pojo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Params {
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 条数
     */
    private int size = 20;
    /**
     * 工号
     */
    private String studstaffno;
    /**
     * 车牌号
     */
    private String plateNumber;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStudstaffno() {
        return studstaffno;
    }

    public Pageable getPage() {
        return PageRequest.of(this.page - 1, this.size);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStudstaffno(String studstaffno) {
        this.studstaffno = studstaffno;
    }
}
