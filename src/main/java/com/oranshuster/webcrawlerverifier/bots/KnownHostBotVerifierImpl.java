package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.cache.Cache;
import com.oranshuster.webcrawlerverifier.dns.ReverseDnsVerifier;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 *
 */
class KnownHostBotVerifierImpl implements KnownHostBotVerifier {
    private static final Logger log = LoggerFactory.getLogger(KnownHostBotVerifierImpl.class);

    @NotNull
    private final CrawlerData crawlerData;
    @NotNull
    private final ReverseDnsVerifier dnsVerifier;
    @NotNull
    private final Cache<String, BotCheckerResult> cache;

    public KnownHostBotVerifierImpl(@NotNull CrawlerData crawlerData,
                                    @NotNull ReverseDnsVerifier dnsVerifier,
                                    @NotNull Cache<String, BotCheckerResult> dnsResultCache) {
        this.crawlerData = crawlerData;
        this.dnsVerifier = dnsVerifier;
        this.cache = dnsResultCache;
    }

    @NotNull
    @Override
    public String getIdentifier() {
        return crawlerData.getIdentifier();
    }

    @Override
    @NotNull
    public BotCheckerResult check(String userAgent, @NotNull String ip, @NotNull UserAgentAnalyzer uaa) {
        if (StringUtils.isBlank(userAgent)) {
            return BotCheckerResult.IS_NOT;
        }

        UserAgent uaAnalyzerResult = uaa.parse(userAgent);
        if (!crawlerData.getUserAgentChecker().test(uaAnalyzerResult)) {
            return BotCheckerResult.IS_NOT;
        } else {
            Set<String> permittedIps = crawlerData.getIps();
            if (permittedIps.contains(ip)) {
                return BotCheckerResult.IS;
            }

            Set<String> permittedHostnames = crawlerData.getHostnames();
            if (!permittedHostnames.isEmpty()) {
                BotCheckerResult ifPresent = cache.getIfPresent(ip);
                if (ifPresent != null) return ifPresent;
                try {
                    BotCheckerResult result = dnsVerifier.verify(ip, permittedHostnames) ? BotCheckerResult.IS : BotCheckerResult.IMPERSONATOR;
                    cache.put(ip, result);
                    return result;
                } catch (Exception e) {
                    log.debug("DNS verification failed", e);
                    return BotCheckerResult.FAILED;
                }
            }

            //couldn't confirm with ip nor with hostname...
            return BotCheckerResult.IMPERSONATOR;
        }
    }

}
