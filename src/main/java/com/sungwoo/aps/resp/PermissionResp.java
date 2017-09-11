package com.sungwoo.aps.resp;

/**
 * @author phloc
 */
public class PermissionResp {
    String value;
    String meaning;

    public PermissionResp(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
