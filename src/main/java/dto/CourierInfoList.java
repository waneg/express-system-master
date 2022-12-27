package dto;

import java.util.List;

public class CourierInfoList {
    public List<CourierDetail> getList() {
        return list;
    }

    public void setList(List<CourierDetail> list) {
        this.list = list;
    }

    public List<CourierDetail> list;
}
