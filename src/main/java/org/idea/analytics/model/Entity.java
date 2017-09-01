package org.idea.analytics.model;


import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;

import java.io.Serializable;


public class Entity implements Serializable  {
    private String type;
    private String mention;
    private int count;

    public Entity() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMention());
        return sb.toString();
    }
}
