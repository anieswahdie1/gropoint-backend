package com.gropoint.responses;

public interface CustomField {

    public interface getRole{
        public Long getid();
        public String getrole_name();
    }

    public interface getPrincipal{
        public Long getid();
        public String getname_principal();
        public String getusername();
        public Long getrole_id();
    }

    public interface getMerchant{
        public Long getid();
        public String getid_merchant();
        public String getname_merchant();
        public String getname_city();
        public String getlattitude();
        public String getlongitude();
        public Long getprincipal_id();
    }

}
