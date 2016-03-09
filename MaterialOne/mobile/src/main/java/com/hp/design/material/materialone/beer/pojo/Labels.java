package com.hp.design.material.materialone.beer.pojo;

/**
 * Created by dashab on 3/7/16.
 */
public class Labels {
    private String icon;

    private String large;

    private String medium;

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getLarge ()
    {
        return large;
    }

    public void setLarge (String large)
    {
        this.large = large;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [icon = "+icon+", large = "+large+", medium = "+medium+"]";
    }

}
