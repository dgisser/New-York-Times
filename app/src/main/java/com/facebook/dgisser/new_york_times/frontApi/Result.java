
package com.facebook.dgisser.new_york_times.frontApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = new ArrayList<String>();
    @SerializedName("org_facet")
    @Expose
    private List<String> orgFacet = new ArrayList<String>();
    @SerializedName("per_facet")
    @Expose
    private List<Object> perFacet = new ArrayList<Object>();
    @SerializedName("geo_facet")
    @Expose
    private List<Object> geoFacet = new ArrayList<Object>();
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = new ArrayList<Multimedium>();
    @SerializedName("short_url")
    @Expose
    private String shortUrl;

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

    public Result withSection(String section) {
        this.section = section;
        return this;
    }

    /**
     * 
     * @return
     *     The subsection
     */
    public String getSubsection() {
        return subsection;
    }

    /**
     * 
     * @param subsection
     *     The subsection
     */
    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public Result withSubsection(String subsection) {
        this.subsection = subsection;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Result withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * 
     * @param _abstract
     *     The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public Result withAbstract(String _abstract) {
        this._abstract = _abstract;
        return this;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Result withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 
     * @return
     *     The byline
     */
    public String getByline() {
        return byline;
    }

    /**
     * 
     * @param byline
     *     The byline
     */
    public void setByline(String byline) {
        this.byline = byline;
    }

    public Result withByline(String byline) {
        this.byline = byline;
        return this;
    }

    /**
     * 
     * @return
     *     The itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 
     * @param itemType
     *     The item_type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Result withItemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    /**
     * 
     * @return
     *     The updatedDate
     */
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * 
     * @param updatedDate
     *     The updated_date
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Result withUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    /**
     * 
     * @return
     *     The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     *     The created_date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Result withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * 
     * @return
     *     The publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * 
     * @param publishedDate
     *     The published_date
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Result withPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    /**
     * 
     * @return
     *     The materialTypeFacet
     */
    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    /**
     * 
     * @param materialTypeFacet
     *     The material_type_facet
     */
    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public Result withMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
        return this;
    }

    /**
     * 
     * @return
     *     The kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     * 
     * @param kicker
     *     The kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public Result withKicker(String kicker) {
        this.kicker = kicker;
        return this;
    }

    /**
     * 
     * @return
     *     The desFacet
     */
    public List<String> getDesFacet() {
        return desFacet;
    }

    /**
     * 
     * @param desFacet
     *     The des_facet
     */
    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public Result withDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
        return this;
    }

    /**
     * 
     * @return
     *     The orgFacet
     */
    public List<String> getOrgFacet() {
        return orgFacet;
    }

    /**
     * 
     * @param orgFacet
     *     The org_facet
     */
    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public Result withOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
        return this;
    }

    /**
     * 
     * @return
     *     The perFacet
     */
    public List<Object> getPerFacet() {
        return perFacet;
    }

    /**
     * 
     * @param perFacet
     *     The per_facet
     */
    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public Result withPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
        return this;
    }

    /**
     * 
     * @return
     *     The geoFacet
     */
    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    /**
     * 
     * @param geoFacet
     *     The geo_facet
     */
    public void setGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public Result withGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
        return this;
    }

    /**
     * 
     * @return
     *     The multimedia
     */
    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    /**
     * 
     * @param multimedia
     *     The multimedia
     */
    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public Result withMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
        return this;
    }

    /**
     * 
     * @return
     *     The shortUrl
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * 
     * @param shortUrl
     *     The short_url
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Result withShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

}
