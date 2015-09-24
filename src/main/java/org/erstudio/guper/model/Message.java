/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 *
 * @author Евгений
 */
@Entity
@Table(name = Message.TABLE_NAME)
@JsonIgnoreProperties({"user"})
@XmlRootElement
public class Message extends LongIdentifiable {
    public static final String TABLE_NAME = "messages";
    public static final class Columns {
        public static final String COLUMN_LAT = Location.Columns.COLUMN_LAT;
        public static final String COLUMN_LON = Location.Columns.COLUMN_LON;
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_DATE_TIME = "date_time";
    }
 
    @Embedded
    private Location location;

    @Column(name = Columns.COLUMN_TEXT, length = 255)
    private String text;
    
    @Column(name = Columns.COLUMN_DATE_TIME)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateTime;

    public Message() {
    }

    public Message(Location location, String text, DateTime dateTime) {
        this.location = location;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}