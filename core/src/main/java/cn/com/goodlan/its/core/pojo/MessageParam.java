package cn.com.goodlan.its.core.pojo;

public class MessageParam {
    /**
     * 工号
     */
    private String studstaffno;
    private String address;
    private String c_time;
    private String plateNumber;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public MessageParam(String studstaffno, String address, String c_time, String plateNumber) {
        this.studstaffno = studstaffno;
        this.address = address;
        this.c_time = c_time;
        this.plateNumber = plateNumber;
    }

    public MessageParam() {
    }

    public String getStudstaffno() {
        return studstaffno;
    }

    public void setStudstaffno(String studstaffno) {
        this.studstaffno = studstaffno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
