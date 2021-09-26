package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.oranshuster.webcrawlerverifier.dns.DnsjavaReverseDnsVerifier;
import com.oranshuster.webcrawlerverifier.dns.ReverseDnsVerifier;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class KnownHostBotVerifierBuilder {

    private CrawlerData crawlerData;
    private ReverseDnsVerifier dnsVerifier;
    private Cache<String, BotCheckerResult> dnsResultCache;
    private UserAgentAnalyzer uaa;

    /**
     * @param crawlerData Crawler data for verification
     * @return Builder
     */
    public KnownHostBotVerifierBuilder crawlerData(@NotNull CrawlerData crawlerData) {
        if (this.crawlerData != null) throw new IllegalStateException("The crawlerData was set already!");
        this.crawlerData = crawlerData;
        return this;
    }

    /**
     * @param dnsVerifier verifier
     * @return Builder
     */
    public KnownHostBotVerifierBuilder dnsVerifier(@NotNull ReverseDnsVerifier dnsVerifier) {
        if (this.dnsVerifier != null) throw new IllegalStateException("The dnsVerifier was set already!");
        this.dnsVerifier = dnsVerifier;
        return this;
    }

    /**
     * Uses the {@link DnsjavaReverseDnsVerifier} with the default name server(s) provided by the system.
     *
     * @return builder
     */
    public KnownHostBotVerifierBuilder dnsVerifierDefault() {
        return dnsVerifier(new DnsjavaReverseDnsVerifier());
    }

    /**
     * Useful and required because verifications are expensive.
     * If you must, for testing, then pass in a dummy cache that drops all.
     *
     * @param dnsResultCache A cache of previous DNS results
     * @return Builder
     */
    public KnownHostBotVerifierBuilder dnsResultCache(@NotNull Cache<String, BotCheckerResult> dnsResultCache) {
        if (this.dnsResultCache != null) throw new IllegalStateException("The dnsResultCache was set already!");
        this.dnsResultCache = dnsResultCache;
        return this;
    }

    /**
     * Uses maximumSize(1_000) and expireAfterWrite(3*24, TimeUnit.HOURS)
     *
     * @return Builder
     */
    public KnownHostBotVerifierBuilder dnsResultCacheDefault() {
        Cache<String, BotCheckerResult> cache = CacheBuilder.newBuilder()
                .maximumSize(1_000)
                .expireAfterWrite(3 * 24, TimeUnit.HOURS)
                .build();
        return dnsResultCache(cache);
    }


    /**
     * @return A built instance of KnownHostBotVerifier
     */
    @NotNull
    public KnownHostBotVerifierImpl build() {
        if (dnsVerifier == null) throw new IllegalArgumentException("No dnsVerifier provided!");
        if (dnsResultCache == null) throw new IllegalArgumentException("No cache provided!");
        return new KnownHostBotVerifierImpl(crawlerData, dnsVerifier, dnsResultCache);
    }

}
