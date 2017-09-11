package com.sungwoo.aps.resp;

import com.sungwoo.aps.models.SungWooModel;

import java.util.List;

/**
 * @author phloc
 * @param <T>
 */
public class ModelContainer<T extends SungWooModel> {

    List<T> datas;

    public ModelContainer() {
    }

    public ModelContainer(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
