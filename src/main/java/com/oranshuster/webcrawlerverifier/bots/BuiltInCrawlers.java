package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Provides access to the built-in crawler data.
 */
public class BuiltInCrawlers {

    /**
     * The list is returned non-strictly defined order of global importance.
     * This way, the more important ones are checked first when iterating.
     *
     * <p>Don't want all? Filter on {@link CrawlerData#getIdentifier()}.</p>
     *
     * @return Returns a list of default crawlers
     */
    @NotNull
    public static List<CrawlerData> get() {
        return ImmutableList.of(
                //currently, Bing seems to be spidering the most, but still...
                GoogleBotData.getInstance(),
                GoogleAdsBotData.getInstance(),
                GoogleAdSenseBotData.getInstance(),
                FacebookBotData.getInstance(),
                AppleBotData.getInstance(),
                BingBotData.getInstance(),

                //Baidu spiders a lot too.
                BaiduSpiderData.getInstance(),

                //I don't know about these...
                YandexBotData.getInstance(),
                //SogouspiderData.getInstance(),

                //here come the unimportant ones.
                DuckduckBotData.getInstance(),

                //Slurp is end-of-life, can probably be removed soon.
                YahooSlurpData.getInstance(),
                QwantBotData.getInstance(),
                InternetArchiveBotData.getInstance(),
                CriteoBotData.getInstance(),
                WeboramaBotData.getInstance(),
                BuckBotData.getInstance(),
                GumGumBotData.getInstance(),
                IntegralAdScienceBotData.getInstance(),
                MaxPointBotData.getInstance(),
                SottopopBotData.getInstance(),
                OmgiliBotData.getInstance()
        );
    }

}
