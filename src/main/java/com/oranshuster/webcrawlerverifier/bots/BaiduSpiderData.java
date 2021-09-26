package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * http://help.baidu.com/question?prod_en=master&amp;class=498&amp;id=1000973
 */
public class BaiduSpiderData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getUserAgentString().contains("Baiduspider");

    /**
     * Source: http://help.baidu.com/question?prod_en=master&class=498&id=1000973
     * "The hostname of Baiduspider is *.baidu.com or *.baidu.jp. Others are fake hostnames."
     */
    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("baidu.com", "baidu.jp");


    private static final BaiduSpiderData INSTANCE = new BaiduSpiderData();

    public static BaiduSpiderData getInstance() {
        return INSTANCE;
    }

    private BaiduSpiderData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "BAIDUSPIDER";
    }

    @Override
    @NotNull
    public Predicate<UserAgent> getUserAgentChecker() {
        return PREDICATE;
    }

    @Override
    @NotNull
    public Set<String> getIps() {
        return Collections.emptySet();
    }

    @Override
    @NotNull
    public Set<String> getHostnames() {
        //usually "crawl.baidu.com"
        return HOSTNAMES;
    }

}
