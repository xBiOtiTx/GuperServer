/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 *
 * @author Евгений
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User extends LongIdentifiable{
    private static final long serialVersionUID = 1L;
    
    public static final String TABLE_NAME = "users";
    public static final class Columns {
        public static final String COLUMN_SESSION_ID = "session_id";
        public static final String COLUMN_LAST_LAT = Location.Columns.COLUMN_LAT;
        public static final String COLUMN_LAST_LON = Location.Columns.COLUMN_LON;
        public static final String COLUMN_LOGIN_DATE = "login_date";
    }

    @Column(name = Columns.COLUMN_SESSION_ID)
    private String sessionId;

    @Embedded
    private Location location;
    
    @Column(name = Columns.COLUMN_LOGIN_DATE)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime loginDate;

    public User() {
    }

    public User(String sessionId, Location location, DateTime loginDate) {
        this.sessionId = sessionId;
        this.location = location;
        this.loginDate = loginDate;
    }

    public DateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(DateTime loginDate) {
        this.loginDate = loginDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
