package com.sungwoo.aps.resp;

import com.sungwoo.aps.models.SungWooModel;
import lombok.Data;

import java.util.List;

/**
 * @author phloc
 * @param <T>
 */
public @Data
class ModelContainer<T extends SungWooModel> {

    List<T> datas;

    public ModelContainer() {
    }

    public ModelContainer(List<T> datas) {
        this.datas = datas;
    }
}
