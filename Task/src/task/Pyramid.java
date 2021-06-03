/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

/**
 *
 * @author Afnaan
 */
public class Pyramid {
    private String pharaoh;
    private String modern_name;
    private String site;
    private Double height_in_m;
    
    public Pyramid() {
    }

    public Pyramid(String pharaoh, String modern_name, String site, Double height_in_m) {
        this.pharaoh = pharaoh;
        this.modern_name = modern_name;
        this.site = site;
        this.height_in_m = height_in_m;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setHeight_in_m(Double height_in_m) {
        this.height_in_m = height_in_m;
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public String getModern_name() {
        return modern_name;
    }

    public String getSite() {
        return site;
    }

    public Double getHeight_in_m() {
        return height_in_m;
    }
}
