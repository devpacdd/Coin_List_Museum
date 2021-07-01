package com.example.androidapi_5;

public class ConnectionFilter {

    // esta clase se utiliza para crear objetos para la conexion

    String dedalo_get;
    String code;
    String db_name;
    String table;
    String ar_fields;
    String section_id;
    String sql_fullselect;
    String sql_filter;
    String lang;
    String order;
    int limit;
    String group;
    int offset;
    boolean count;

    //-------------------------------------------------------//


    public String getDedalo_get() {
        return dedalo_get;
    }

    public void setDedalo_get(String dedalo_get) {
        this.dedalo_get = dedalo_get;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAr_fields() {
        return ar_fields;
    }

    public void setAr_fields(String ar_fields) {
        this.ar_fields = ar_fields;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSql_fullselect() {
        return sql_fullselect;
    }

    public void setSql_fullselect(String sql_fullselect) {
        this.sql_fullselect = sql_fullselect;
    }

    public String getSql_filter() {
        return sql_filter;
    }

    public void setSql_filter(String sql_filter) {
        this.sql_filter = sql_filter;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }
}
