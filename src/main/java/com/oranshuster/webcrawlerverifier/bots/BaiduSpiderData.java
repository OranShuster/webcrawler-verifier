package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

/**
 * Resources:
 * http://help.baidu.com/question?prod_en=master&class=498&id=1000973
 */
public class BaiduSpiderData implements CrawlerData {

    private static final Predicate<String> PREDICATE = new Predicate<String>() {
        @Override
        public boolean apply(String userAgent) {
            return userAgent != null && userAgent.contains("Baiduspider");
        }
    };

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

    @Override @NotNull
    public Predicate<String> getUserAgentChecker() {
        return PREDICATE;
    }

    @Override @NotNull
    public Set<String> getIps() {
        return Collections.emptySet();
    }

    @Override @NotNull
    public Set<String> getHostnames() {
        //usually "crawl.baidu.com"
        return HOSTNAMES;
    }

}
