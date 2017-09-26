package com.sungwoo.aps.response;

import com.sungwoo.aps.domain.SungWooModel;
import lombok.Data;

import java.util.Collection;

/**
 * @param <T>
 * @author phloc
 */
@Data
public final class ListWrapperResponse<T extends SungWooModel> {

    Collection<T> items;

    public ListWrapperResponse(Collection<T> items) {
        this.items = items;
    }
}
