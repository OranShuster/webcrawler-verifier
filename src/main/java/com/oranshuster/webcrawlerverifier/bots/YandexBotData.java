package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * http://yandex.com/bots
 * http://help.yandex.com/search/robots/check-robot.xml#check-robot
 * http://searchenginewatch.com/sew/news/2067357/bye-bye-crawler-blocking-parasites
 */
public class YandexBotData implements CrawlerData {

    private static final Predicate<String> PREDICATE = userAgent -> userAgent != null && userAgent.contains("Yandex");

    //exactly as documented by yandex:
    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("yandex.ru", "yandex.net", "yandex.com");


    private static final YandexBotData INSTANCE = new YandexBotData();

    public static YandexBotData getInstance() {
        return INSTANCE;
    }

    private YandexBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "YANDEXBOT";
    }

    @NotNull
    @Override
    public java.util.function.Predicate<String> getUserAgentChecker() {
        return PREDICATE;
    }

    @NotNull
    @Override
    public Set<String> getIps() {
        return Collections.emptySet();
    }

    @NotNull
    @Override
    public Set<String> getHostnames() {
        return HOSTNAMES;
    }
}
