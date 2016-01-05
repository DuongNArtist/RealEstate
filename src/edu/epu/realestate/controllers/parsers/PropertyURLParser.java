package edu.epu.realestate.controllers.parsers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by DuongNArtist on 18/12/2015.
 */
public class PropertyURLParser {

    public static void parseSubUrlsFromMainUrl(ObservableList<String> subUrls, String mainUrl, Label lblLink) {
        if (mainUrl.contains(PropertyModelParser.BAT_DONG_SAN)) {
            PropertyURLParser.fromBatDongSan(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.MUA_BAN_NHA_DAT)) {
            PropertyURLParser.fromMuaBanNhaDat(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.ALO_NHA_DAT)) {
            PropertyURLParser.fromAloNhaDat(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.DO_THI)) {
            PropertyURLParser.fromDoThi(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.NHA_DAT_24H)) {
            PropertyURLParser.fromNhaDat24h(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.VN123_NHA_DAT)) {
            PropertyURLParser.from123NhaDat(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.CAFELAND)) {
            PropertyURLParser.fromCafeLand(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.DIA_OC_ONLINE)) {
            PropertyURLParser.fromDiaOcOnline(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.DINH_GIA_NHA_DAT)) {
            PropertyURLParser.fromDinhGiaNhaDat(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.KENH_BAT_DONG_SAN)) {
            PropertyURLParser.fromKenhBatDongSan(mainUrl, subUrls, lblLink);
        } else if (mainUrl.contains(PropertyModelParser.NHA_SO)) {
            PropertyURLParser.fromNhaSo(mainUrl, subUrls, lblLink);
        }
    }

    public static void addToSubUrls(ObservableList<String> subUrls, String subUrl, Label lblLink) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                subUrls.add(subUrl);
                lblLink.setText("[" + subUrls.size() + "] - " + subUrl);
            }
        });
    }

    public static void fromBatDongSan(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Elements elements = body.select("div.p-title");
            for (Element link : elements) {
                Elements tags = link.getElementsByTag("a");
                for (Element aTag : tags) {
                    String subUrl = mainUrl + aTag.attr("href");
                    addToSubUrls(subUrls, subUrl, lblLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromMuaBanNhaDat(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Elements elements = body.select("a.title-filter-link");
            if (mainUrl.contains("?")) {
                mainUrl = mainUrl.substring(0, mainUrl.indexOf("?"));
            }
            for (Element element : elements) {
                String href = element.attr("href");
                String subUrl = mainUrl + href.substring(href.lastIndexOf('/'));
                System.out.println(subUrl);
                addToSubUrls(subUrls, subUrl, lblLink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromAloNhaDat(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Elements elements = body.select("div.thumbnail");
            for (Element link : elements) {
                Elements tags = link.getElementsByTag("a");
                for (Element aTag : tags) {
                    String subUrl = "http://alonhadat.com.vn" + aTag.attr("href");
                    addToSubUrls(subUrls, subUrl, lblLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromDoThi(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Elements elements = body.select("div.div_proitem");
            for (Element element : elements) {
                Element aTag = element.getElementsByTag("a").first();
                if (aTag != null) {
                    String subUrl = "http://dothi.net" + aTag.attr("href");
                    addToSubUrls(subUrls, subUrl, lblLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromNhaDat24h(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Elements elements = body.select("div.dv-txt");
            for (Element element : elements) {
                Element aTag = element.getElementsByTag("a").first();
                if (aTag != null) {
                    String subUrl = "http://nhadat24h.net" + aTag.attr("href");
                    addToSubUrls(subUrls, subUrl, lblLink);
                    //System.out.println(subUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void from123NhaDat(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            Element boxElement = body.select("div.box_C").get(1);
            if (boxElement != null) {
                //System.out.println(boxElement.outerHtml());
                Elements titleElements = boxElement.select("div.tit_nhadatban");
                for (Element titleElement : titleElements) {
                    String subUrl = titleElement.getElementsByTag("a").attr("href");
                    addToSubUrls(subUrls, subUrl, lblLink);
                    //System.out.println(link);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromCafeLand(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            //System.out.println(body.outerHtml());
            Elements contentElements = body.select("li.vip0");
            for (Element contentElement : contentElements) {
                String subUrl = contentElement.getElementsByTag("a").attr("href");
                addToSubUrls(subUrls, subUrl, lblLink);
                System.out.println(subUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromDiaOcOnline(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        addToSubUrls(subUrls, mainUrl, lblLink);
    }

    public static void fromDinhGiaNhaDat(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            //System.out.println(body.outerHtml());
            Elements contentElements = body.select("div.media-body.margin-right-5");
            for (Element contentElement : contentElements) {
                String subUrl = "http://dinhgianhadat.vn" + contentElement.getElementsByTag("a").attr("href");
                addToSubUrls(subUrls, subUrl, lblLink);
                System.out.println(subUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromKenhBatDongSan(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            // System.out.println(body.outerHtml());
            Elements contentElements = body.select("div.item_thuong");
            for (Element contentElement : contentElements) {
                String subUrl = contentElement.getElementsByTag("a").attr("href");
                subUrl = subUrl.replace("http://m.", "http://");
                addToSubUrls(subUrls, subUrl, lblLink);
                System.out.println(subUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromNhaSo(String mainUrl, ObservableList<String> subUrls, Label lblLink) {
        try {
            Document document = Jsoup.connect(mainUrl).get();
            Element body = document.body();
            System.out.println(body.outerHtml());
            Elements contentElements = body.select("h2.listitem-title");
            for (Element contentElement : contentElements) {
                String subUrl = "http://nhaso.vn" + contentElement.getElementsByTag("a").attr("href");
                addToSubUrls(subUrls, subUrl, lblLink);
                System.out.println(subUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
