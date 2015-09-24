/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Евгений
 */
@Embeddable
public class Location implements Serializable{
    public static final class Columns {
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LON = "lon";
    }
  
    @Column(name = Message.Columns.COLUMN_LAT, nullable = true)
    private Float lat;

    @Column(name = Message.Columns.COLUMN_LON, nullable = true)
    private Float lon;

    public Location() {
    }
    
    public Location(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    } 
}
