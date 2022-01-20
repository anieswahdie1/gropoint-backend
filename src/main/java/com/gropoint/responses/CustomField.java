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

}
