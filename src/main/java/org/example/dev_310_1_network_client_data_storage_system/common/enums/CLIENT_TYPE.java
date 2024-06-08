package org.example.dev_310_1_network_client_data_storage_system.common.enums;

public enum CLIENT_TYPE {
    INDIVIDUAL ("Физическое лицо"),
    LEGAL ("Юридическое лицо");

    private String value;

    CLIENT_TYPE(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CLIENT_TYPE getType(String value){
        if(value==null) return null;
        for(CLIENT_TYPE type : CLIENT_TYPE.values()){
            if((value).equals(type.value)) return type;
        }
        return null;
    }

}
