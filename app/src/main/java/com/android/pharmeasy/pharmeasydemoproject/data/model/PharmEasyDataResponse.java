package com.android.pharmeasy.pharmeasydemoproject.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 12/10/2017.
 */

public class PharmEasyDataResponse {
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("per_page")
    @Expose
    private String per_page;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("data")
    @Expose
    private List<Data> data;

    private String total_pages;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }
}
