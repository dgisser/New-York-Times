
package com.facebook.dgisser.new_york_times.frontApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class NytFront {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("num_results")
    @Expose
    private int numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public NytFront withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 
     * @param copyright
     *     The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public NytFront withCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    /**
     * 
     * @return
     *     The section
     */
    public String getSection() {
        return section;
    }

    /**
     * 
     * @param section
     *     The section
     */
    public void setSection(String section) {
        this.section = section;
    }

    public NytFront withSection(String section) {
        this.section = section;
        return this;
    }

    /**
     * 
     * @return
     *     The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 
     * @param lastUpdated
     *     The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public NytFront withLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    /**
     * 
     * @return
     *     The numResults
     */
    public int getNumResults() {
        return numResults;
    }

    /**
     * 
     * @param numResults
     *     The num_results
     */
    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public NytFront withNumResults(int numResults) {
        this.numResults = numResults;
        return this;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public NytFront withResults(List<Result> results) {
        this.results = results;
        return this;
    }

}
