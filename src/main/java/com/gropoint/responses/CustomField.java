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

    public interface getWarehouse{
        public Long getid();
        public String getid_warehouse();
        public String getname_warehouse();
    }

    public interface getCategory{
        public Long getid();
        public String getname_category();
    }

    public interface  getProduct {
        public Long getid();
        public String getid_product();
        public String getname_product();
        public String getname_category();
        public int getqty_stock();
        public int getcurr_stock();
        public Long getprogram_id();
        public int getprincipal_id();
        public Long getwarehouse_id();
        public Long getcategory_id();
        public String getname_principal();
        public String getname_warehouse();
        public String getcreated_date();
        public String getupdated_date();
        public int getprice();
    }

    public interface getProductByCategory{
        public Long getid();
        public String getid_product();
        public String getname_product();
        public int getcurr_stock();
        public int getqty_stock();
        public String getname_category();
        public String getname_warehouse();
    }

    public interface getProductDropdown{
        public Long getid();
        public String getname_product();
    }

    public interface getProductById{
        public Long getid();
        public String getid_product();
        public String getname_product();
        public int getcurr_stock();
        public Long getcategory_id();
        public Long getprincipal_id();
        public Long getwarehouse_id();
        public Long getprogram_id();
        public String getcreated_date();
        public String getupdated_date();
        public boolean getdeleted();
    }

    public interface getProgram{
        public Long getid();
        public String getid_program();
        public String getname_program();
        public int getpoint();
        public int getamount_transaction();
        public int getamount_usage();
        public String getdescription();
        public String getduration();
        public boolean getmultiple();
        public boolean getrepeats();
        public String getstart_date();
        public String getend_date();
        public String getstatus();
        public String getcreated_date();
        public String getupdated_date();
        public Long getproduct_id();
    }

}
