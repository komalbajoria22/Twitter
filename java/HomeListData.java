package com.komal.twitter.HomePackage;

public class HomeListData {

    private String following_name;
    private String following_tweet;
    private String name;

    public HomeListData(String following_name, String following_tweet,String name) {
        this.following_name = following_name;
        this.following_tweet = following_tweet;
        this.name=name;
    }

    public String getFollowing_name() {
        return following_name;
    }

    public void setFollowing_name(String following_name) {

        this.following_name = following_name;
    }

    public String getFollowing_tweet() {
        return following_tweet;
    }

    public void setFollowing_tweet(String following_tweet) {
        this.following_tweet = following_tweet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
