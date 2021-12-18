package cn.com.goodlan.its.api.entity;

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
    private int size = 10;
    /**
     * 工号
     */
    private String studstaffno;

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
