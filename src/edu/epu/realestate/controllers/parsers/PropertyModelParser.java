package edu.epu.realestate.controllers.parsers;

import edu.epu.realestate.models.PropertyModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by DuongNArtist on 16/12/2015.
 */
public class PropertyModelParser {

    public static final String BAT_DONG_SAN = "batdongsan";
    public static final String MUA_BAN_NHA_DAT = "muabannhadat";
    public static final String ALO_NHA_DAT = "alonhadat";
    public static final String DO_THI = "dothi";
    public static final String NHA_DAT_24H = "nhadat24h";
    public static final String VN123_NHA_DAT = "123nhadat";
    public static final String CAFELAND = "cafeland";
    public static final String DIA_OC_ONLINE = "diaoconline";
    public static final String DINH_GIA_NHA_DAT = "dinhgianhadat";
    public static final String KENH_BAT_DONG_SAN = "kenhbds";
    public static final String NHA_SO = "nhaso";

    public static PropertyModel parsePropertyModel(String url, int groupId) {
        if (url.contains(BAT_DONG_SAN)) {
            return fromBatDongSan(url, groupId);
        } else if (url.contains(MUA_BAN_NHA_DAT)) {
            return fromMuaBanNhaDat(url, groupId);
        } else if (url.contains(ALO_NHA_DAT)) {
            return fromAloNhaDat(url, groupId);
        } else if (url.contains(DO_THI)) {
            return fromDoThi(url, groupId);
        } else if (url.contains(NHA_DAT_24H)) {
            return fromNhaDat24h(url, groupId);
        } else if (url.contains(VN123_NHA_DAT)) {
            return from123NhaDat(url, groupId);
        } else if (url.contains(CAFELAND)) {
            return fromCafeLand(url, groupId);
        } else if (url.contains(DIA_OC_ONLINE)) {
            return fromDiaOcOnline(url, groupId);
        } else if (url.contains(DINH_GIA_NHA_DAT)) {
            return fromDinhGiaNhaDat(url, groupId);
        } else if (url.contains(KENH_BAT_DONG_SAN)) {
            return fromKenhBatDongSan(url, groupId);
        } else if (url.contains(NHA_SO)) {
            return fromNhaSo(url, groupId);
        }
        return null;
    }

    public static PropertyModel fromBatDongSan(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setGroupId(groupId);
        propertyModel.setPropertyUrl(url);
        try {
            Document document = Jsoup.connect(url).get();
            // Body
            Element bodyElement = document.body();
            // Product Detail
            Element productDetailElement = bodyElement.getElementById("product-detail");
            if (productDetailElement != null) {
                // Title
                Element titleElement = productDetailElement.select("div.pm-title").first();
                if (titleElement != null) {
                    String title = titleElement.text().trim();
                    propertyModel.setPropertyTitle(title);
                    System.out.println(title);
                }
                // DetailResult
                Element detailResultElement = productDetailElement.select("div.kqchitiet").first();
                if (detailResultElement != null) {
                    // Price Title
                    Elements priceTitleElements = detailResultElement.select("span.gia-title");
                    if (priceTitleElements != null) {
                        // Price
                        Element priceElement = priceTitleElements.first().getElementsByTag("strong").first();
                        String price = priceElement.text().trim();
                        propertyModel.setPropertyPrice(price);
                        System.out.println(price);
                        // Square
                        Element squareElement = priceTitleElements.last().getElementsByTag("strong").first();
                        String square = squareElement.text().trim();
                        propertyModel.setPropertySquare(square);
                        System.out.println(square);
                    }
                }
                // Description
                Element descriptionElement = productDetailElement.select("div.pm-content").first();
                if (descriptionElement != null) {
                    String description = descriptionElement.text().trim();
                    propertyModel.setPropertyDescription(description);
                    System.out.println(description);
                }
                // Left Detail
                Element leftDetailElement = productDetailElement.select("div.left-detail").first();
                if (leftDetailElement != null) {
                    // Project
                    int index = 0;
                    Element projectElement = leftDetailElement.getElementById("LeftMainContent__productDetail_project");
                    if (projectElement != null) {
                        index++;
                        String project = projectElement.select("div.right").first().text().trim();
                        propertyModel.setPropertyProject(project);
                        System.out.println(project);
                    }
                    // Address
                    Element addressElement = leftDetailElement.select("div.right").get(index);
                    if (addressElement != null) {
                        String address = addressElement.text().trim();
                        propertyModel.setPropertyAddress(address);
                        System.out.println(address);
                    }
                }
                // Customer Info
                Element customerInfoElement = productDetailElement.getElementById("divCustomerInfo");
                if (customerInfoElement != null) {
                    // Contact Name
                    Element contactNameElement = customerInfoElement.getElementById("LeftMainContent__productDetail_contactName");
                    if (contactNameElement != null) {
                        String contact = contactNameElement.select("div.right").first().text().trim();
                        propertyModel.setPropertyContact(contact);
                        System.out.println(contact);
                    } else {
                        contactNameElement = customerInfoElement.getElementById("LeftMainContent__detail_contactName");
                        if (contactNameElement != null) {
                            String contact = contactNameElement.select("div.right").first().text().trim();
                            propertyModel.setPropertyContact(contact);
                            System.out.println(contact);
                        }
                    }
                    // Contact Mobile
                    String mobile = "";
                    Element contactMobile = customerInfoElement.getElementById("LeftMainContent__productDetail_contactMobile");
                    if (contactMobile != null) {
                        mobile += contactMobile.select("div.right").first().text().trim();
                    } else {
                        contactMobile = customerInfoElement.getElementById("LeftMainContent__detail_contactMobile");
                        if (contactMobile != null) {
                            mobile += contactMobile.select("div.right").first().text().trim();
                        }
                    }
                    Element contactPhone = customerInfoElement.getElementById("LeftMainContent__productDetail_contactPhone");
                    if (contactPhone != null) {
                        String phone = contactPhone.select("div.right").first().text().trim();
                        if (mobile.length() > 0 && !mobile.equalsIgnoreCase(phone)) {
                            mobile += " - ";
                            mobile += phone;
                        }
                    } else {
                        contactPhone = customerInfoElement.getElementById("LeftMainContent__detail_contactPhone");
                        if (contactPhone != null) {
                            String phone = contactPhone.select("div.right").first().text().trim();
                            if (mobile.length() > 0 && !mobile.equalsIgnoreCase(phone)) {
                                mobile += " - ";
                                mobile += phone;
                            }
                        }
                    }
                    System.out.println(mobile);
                    propertyModel.setPropertyMobile(mobile);
                    Element emailElement = customerInfoElement.getElementById("LeftMainContent__productDetail_contactEmail");
                    if (emailElement != null) {
                        String email = emailElement.outerHtml();
                        email = email.substring(email.indexOf("mailto:") + "mailto:".length(), email.indexOf("'>"));
                        email = Jsoup.parse(email).body().text();
                        propertyModel.setPropertyEmail(email);
                        System.out.println(email);
                    } else {
                        emailElement = customerInfoElement.getElementById("LeftMainContent__detail_contactEmail");
                        if (emailElement != null) {
                            String email = emailElement.outerHtml();
                            email = email.substring(email.indexOf("mailto:") + "mailto:".length(), email.indexOf("'>"));
                            email = Jsoup.parse(email).body().text();
                            propertyModel.setPropertyEmail(email);
                            System.out.println(email);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromMuaBanNhaDat(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            Element titleElement = body.select("h1.row-fluid").first();
            if (titleElement != null) {
                String title = titleElement.text().trim();
                propertyModel.setPropertyTitle(title);
                System.out.println(title);
            }
            Element detailElement = body.select("table.detail-attrs").first();
            if (detailElement != null) {
                String detail = detailElement.text().trim();
                //System.out.println(detail);
                // Price
                if (detail.contains("Giá") && detail.contains("VNĐ")) {
                    String price = detail.substring(detail.indexOf("Giá") + "Giá".length(), detail.indexOf("VNĐ")).trim();
                    propertyModel.setPropertyPrice(price);
                    System.out.println(price);
                }
                // Square
                if (detail.contains("Diện tích")) {
                    String square = detail.substring(detail.indexOf("Diện tích") + "Diện tích".length(), detail.indexOf("m²") + "m²".length()).trim();
                    propertyModel.setPropertySquare(square);
                    System.out.println(square);
                }
            }

            // Address
            Element addressElement = body.getElementById("MainContent_ctlDetailBox_ctlAddressLocation_lblCity");
            if (addressElement != null) {
                String address = addressElement.text().trim();
                propertyModel.setPropertyAddress(address);
                System.out.println(address);
            }

            // Contact
            Element contactElement = body.getElementById("MainContent_ctlDetailBox_ctlAddressContact_lblName");
            if (contactElement != null) {
                String contact = contactElement.text().trim();
                propertyModel.setPropertyContact(contact);
                System.out.println(contact);
            }

            // Mobile
            Element mobileElement = body.getElementById("MainContent_ctlDetailBox_ctlContactPhone_lblPhone");
            if (mobileElement != null) {
                //System.out.println(mobileElement.outerHtml());
                Element mobileElement1 = mobileElement.getElementsByTag("a").first();
                if (mobileElement1 != null) {
                    String mobile = mobileElement1.text().trim();
                    propertyModel.setPropertyMobile(mobile);
                    System.out.println(mobile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromAloNhaDat(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            Element propertyElement = body.select("div.property").first();
            if (propertyElement != null) {
                System.out.println(propertyElement.outerHtml());
                // Title
                Element titleElement = propertyElement.select("div.title").first();
                if (titleElement != null) {
                    String title = titleElement.text().trim();
                    propertyModel.setPropertyTitle(title);
                    System.out.println(title);
                }
                // Description
                Element descriptionElement = propertyElement.select("div.content.text-content").first();
                if (descriptionElement != null) {
                    String description = descriptionElement.text().trim();
                    propertyModel.setPropertyDescription(description);
                    System.out.println(description);
                }
                // Price
                Element priceElement = propertyElement.select("span.price").first().select("span.value").first();
                if (priceElement != null) {
                    String price = priceElement.text().trim();
                    propertyModel.setPropertyPrice(price);
                    System.out.println(price);
                }
                // Square
                Element squareElement = propertyElement.select("span.square").first().select("span.value").first();
                if (squareElement != null) {
                    String square = squareElement.text().trim();
                    propertyModel.setPropertySquare(square);
                    System.out.println(square);
                }
                // Address
                Element addressElement = propertyElement.select("div.address").first().select("span.value").first();
                if (addressElement != null) {
                    String address = addressElement.text().trim();
                    propertyModel.setPropertyAddress(address);
                    System.out.println(address);
                }
                // Contact
                Element contactElement = propertyElement.select("div.contact").first();
                if (contactElement != null) {
                    Element nameElement = contactElement.select("span.name").first().select("span.value").first();
                    if (nameElement != null) {
                        String name = nameElement.text().trim();
                        propertyModel.setPropertyContact(name);
                        System.out.println(name);
                    }
                    Element phoneElement = contactElement.select("span.phone").first().select("span.value").first();
                    if (phoneElement != null) {
                        String phone = phoneElement.text().trim();
                        propertyModel.setPropertyMobile(phone);
                        System.out.println(phone);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromDoThi(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            //System.out.println(body.outerHtml());
            Element productDetail = body.select("div.pdetail").first();
            if (productDetail != null) {
                //System.out.println(productDetail.outerHtml());
                // Title
                Element titleElement = productDetail.select("div.ptitle").first();
                if (titleElement != null) {
                    String title = titleElement.text().trim();
                    propertyModel.setPropertyTitle(title);
                    System.out.println(title);
                }
                // Price
                Element priceElement = productDetail.select("div.pprice").first();
                if (priceElement != null) {
                    priceElement.getElementsByTag("span").remove();
                    String price = priceElement.text();
                    propertyModel.setPropertyPrice(price);
                    System.out.println(price);
                }
                // Square
                Element squareElement = productDetail.select("div.parea").first();
                if (squareElement != null) {
                    squareElement.getElementsByTag("span").remove();
                    String square = squareElement.text();
                    propertyModel.setPropertySquare(square);
                    System.out.println(square);
                }

                // Description
                Element descriptionElement = productDetail.select("div.text").first();
                if (descriptionElement != null) {
                    String description = descriptionElement.text().trim();
                    propertyModel.setPropertyDescription(description);
                    System.out.println(description);
                }

                //Table 1
                Element tabel1Element = productDetail.getElementById("tbl1");
                if (tabel1Element != null) {
                    // System.out.println(table.outerHtml());
                    //System.out.println(trElement.outerHtml());
                    // Project
                    tabel1Element.select("td.td_info").remove();
                    Element projectElement = tabel1Element.getElementById("MainContent_ProductDetailMobile_trProject");
                    if (projectElement != null) {
                        String project = projectElement.text().trim();
                        propertyModel.setPropertyProject(project);
                        System.out.println(project);
                    }
                    // Address
                    Element addressElement = tabel1Element.getElementById("MainContent_ProductDetailMobile_trAddress");
                    if (addressElement != null) {
                        String address = addressElement.text().trim();
                        propertyModel.setPropertyAddress(address);
                        System.out.println(address);
                    }
                }

                // Table 2
                Element table2Element = productDetail.getElementById("tbl2");
                if (table2Element != null) {
                    // Contact
                    Element contactElement = table2Element.getElementById("MainContent_ProductDetailMobile_trContactName");
                    if (contactElement != null) {
                        String contact = contactElement.getElementsByTag("td").last().text().trim();
                        propertyModel.setPropertyContact(contact);
                        System.out.println(contact);
                    }
                    String mobilePhone = "";
                    // Phone
                    Element phoneElement = table2Element.getElementById("MainContent_ProductDetailMobile_trContactPhone");
                    if (phoneElement != null) {
                        String phone = phoneElement.getElementsByTag("td").last().text().trim();
                        mobilePhone += phone;
                    }
                    // Mobile
                    Element mobileElement = table2Element.getElementById("MainContent_ProductDetailMobile_trContactMobile");
                    if (mobileElement != null) {
                        String mobile = mobileElement.getElementsByTag("td").last().text().trim();
                        if (mobilePhone.length() > 0 && !mobilePhone.equalsIgnoreCase(mobile)) {
                            mobilePhone += (" - " + mobile);
                        }
                    }
                    System.out.println(mobilePhone);
                    propertyModel.setPropertyMobile(mobilePhone);
                    // Mobile
                    Element emailElement = table2Element.getElementById("MainContent_ProductDetailMobile_trContactEmail");
                    if (emailElement != null) {
                        String email = emailElement.getElementsByTag("td").last().outerHtml();
                        email = email.substring(email.indexOf("(\"") + 2, email.indexOf("\")"));
                        email = Jsoup.parse(email).body().text();
                        System.out.println(email);
                        propertyModel.setPropertyEmail(email);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromNhaDat24h(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel from123NhaDat(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            //System.out.println(body.outerHtml());
            // Title
            Element titleElement = body.select("h1.tieude_nhadat").first();
            if (titleElement != null) {
                String title = titleElement.text().trim();
                propertyModel.setPropertyTitle(title);
                System.out.println(title);
            }
            // Price and Square
            Element priceSquareElement = body.select("ul.info_no1").first();
            if (priceSquareElement != null) {
                Elements camcam = priceSquareElement.select("b.camcam");
                if (camcam != null) {
                    String price = camcam.get(0).text().trim();
                    propertyModel.setPropertyPrice(price);
                    System.out.println(price);
                    String square = camcam.get(1).text().trim();
                    propertyModel.setPropertySquare(square);
                    System.out.println(square);
                }
            }
            // Address
            Element addressElement = body.select("ul.info_no2").first();
            if (addressElement != null) {
                addressElement.getElementsByTag("label").remove();
                String address = addressElement.text().trim();
                propertyModel.setPropertyAddress(address);
                System.out.println(address);
            }
            // Contact
            Element contactElement = body.select("div.lienhe_nguoiban").first();
            if (contactElement != null) {
                // Contact
                Elements elements = contactElement.getElementsByTag("li");
                Element nameElement = elements.get(1);
                if (nameElement != null) {
                    String name = nameElement.text().trim();
                    propertyModel.setPropertyContact(name);
                    System.out.println(name);
                }
                for (Element element : elements) {
                    String text = element.text().trim();
                    if (text.contains("Điện thoại")) {
                        // Mobile
                        String mobile = text.replace("Điện thoại:", "").trim();
                        propertyModel.setPropertyMobile(mobile);
                        System.out.println(mobile);
                    } else if (text.contains("Email")) {
                        // Email
                        String email = text.replace("Email:", "").trim();
                        propertyModel.setPropertyEmail(email);
                        System.out.println(email);
                    }
                }
            }
            // Description
            Element descriptionElement = body.select("div.detail_khungxam").first();
            if (descriptionElement != null) {
                String description = descriptionElement.text().trim();
                propertyModel.setPropertyDescription(description);
                System.out.println(description);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromCafeLand(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            // Title
            Element titleElement = body.select("div.sr-title").first();
            if (titleElement != null) {
                String title = titleElement.getElementsByTag("h1").text().trim();
                propertyModel.setPropertyTitle(title);
                System.out.println(title);
            }
            // Content
            Element contentElement = body.select("div.padding").first();
            if (contentElement != null) {
                //System.out.println(contentElement.outerHtml());
                Elements infoElements = contentElement.select("span.sp-info");
                for (Element infoElement : infoElements) {
                    String text = infoElement.text().trim();
                    if (text.contains("DTSD:")) {
                        String square = text.substring(text.indexOf("DTSD:") + "DTSD:".length()).trim();
                        propertyModel.setPropertySquare(square);
                        System.out.println(square);
                    } else if (text.contains("Vị trí nhà:")) {
                        String address = text.replace("Vị trí nhà:", "").trim();
                        propertyModel.setPropertyAddress(address);
                        System.out.println(address);
                    } else if (text.contains("Giá:")) {
                        String price = text.replace("Giá:", "").trim();
                        propertyModel.setPropertyPrice(price);
                        System.out.println(price);
                    } else if (text.contains("Thuộc dự án:")) {
                        String project = text.replace("Thuộc dự án:", "").trim();
                        propertyModel.setPropertyProject(project);
                        System.out.println(project);
                    }
                }
                // Description
                Element descriptionElement = body.select("div.c-content-n").first();
                if (descriptionElement != null) {
                    String description = descriptionElement.text().trim();
                    propertyModel.setPropertyDescription(description);
                    System.out.println(description);
                }
            }
            // Contact
            Elements contactElement = body.select("div.padding");
            if (contactElement.size() > 1) {
                Element element = contactElement.get(1);
                Elements infoElements = element.select("span.sp-info");
                // Name
                Element nameElement = infoElements.first();
                if (nameElement != null) {
                    String name = nameElement.text().trim();
                    propertyModel.setPropertyContact(name);
                    System.out.println(name);
                }
                for (Element infoE : infoElements) {
                    String text = infoE.text().trim();
                    if (text.contains("Điện thoại:")) {
                        String mobile = text.replace("Điện thoại:", "").trim();
                        propertyModel.setPropertyMobile(mobile);
                        System.out.println(mobile);
                    } else if (text.contains("Di động:")) {
                        String mobile = text.replace("Di động:", "").trim();
                        String phone = propertyModel.getPropertyMobile();
                        if (phone.length() > 0 && !phone.equalsIgnoreCase(mobile)) {
                            phone += (" - " + mobile);
                        } else {
                            phone += mobile;
                        }
                        propertyModel.setPropertyMobile(phone);
                        System.out.println(phone);
                    } else if (text.contains("Email:")) {
                        String email = text.replace("Email:", "").trim();
                        propertyModel.setPropertyEmail(email);
                        System.out.println(email);
                    }
                    //System.out.println(text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }


    public static PropertyModel fromDiaOcOnline(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromDinhGiaNhaDat(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            // COntent
            Element contentElement = body.select("div.property-content").first();
            if (contentElement != null) {
                Element titleElement = contentElement.select("h1.property-title.media-heading").first();
                if (titleElement != null) {
                    String title = titleElement.text().trim();
                    propertyModel.setPropertyTitle(title);
                    System.out.println(title);
                }
                Element groupElement = contentElement.select("div.property-address").first();
                if (groupElement != null) {
                    Element addressElement = groupElement.select("div.margin-bottom.bold").first();
                    if (addressElement != null) {
                        addressElement.getElementsByTag("u").remove();
                        String address = addressElement.text().trim();
                        propertyModel.setPropertyAddress(address);
                        System.out.println(address);
                    }
                    Element descriptionElement = groupElement.getElementsByTag("div").last();
                    if (descriptionElement != null) {
                        String description = descriptionElement.text().trim();
                        propertyModel.setPropertyDescription(description);
                        System.out.println(description);
                    }
                }
                Element priceElement = contentElement.select("div.price-sale").first();
                if (priceElement != null) {
                    String price = priceElement.getElementsByTag("strong").first().text().trim().replace("Giá bán:", "").trim();
                    propertyModel.setPropertyPrice(price);
                    System.out.println(price);
                }
                Element contactElement = body.select("div.the-same-auth").first();
                if (contactElement != null) {
                    Element nameElement = contactElement.getElementsByTag("a").first();
                    if (nameElement != null) {
                        String name = nameElement.text().trim();
                        propertyModel.setPropertyContact(name);
                        System.out.println(name);
                    }
                    String mobile = contactElement.text().trim();
                    mobile = mobile.substring(mobile.indexOf("(") + 1, mobile.indexOf(")")).trim();
                    propertyModel.setPropertyMobile(mobile);
                    System.out.println(mobile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromKenhBatDongSan(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
            Element body = document.body();
            //System.out.println(body.outerHtml());
            Element mainElement = body.select("div.tin_dang").first();
            if (mainElement != null) {
                Element titleElement = mainElement.select("div.td_tt").first();
                if (titleElement != null) {
                    String title = titleElement.text().trim();
                    propertyModel.setPropertyTitle(title);
                    System.out.println(title);
                }
                Element descriptionElement = mainElement.select("div.nd_tin_dang").first();
                if (descriptionElement != null) {
                    String description = descriptionElement.text().trim();
                    propertyModel.setPropertyDescription(description);
                    System.out.println(description);
                }
                mainElement.select("div.tt_tin_dang1").remove();
                mainElement.select("div.ds_bds_khac").remove();
                mainElement.select("div.ban_qh").remove();
                mainElement.select("div.ad_td").remove();
                mainElement.select("div.nd_tin_dang").remove();
                Element contentElement = mainElement.select("div.tt_tin_dang").first();
                if (contentElement != null) {
                    Elements divElements = contentElement.getElementsByTag("div");
                    for (Element divElement : divElements) {
                        String text = divElement.text().trim();
                        if (text.contains("Khu vực:")) {
                            String address = text.replace("Khu vực:", "").trim();
                            propertyModel.setPropertyAddress(address);
                            System.out.println(address);
                        } else if (text.contains("Diện tích:")) {
                            String square = text.replace("Diện tích:", "").trim();
                            propertyModel.setPropertySquare(square);
                            System.out.println(square);
                        } else if (text.contains("Giá:")) {
                            String price = text.replace("Giá:", "").trim();
                            propertyModel.setPropertyPrice(price);
                            System.out.println(price);
                        } else if (text.contains("Họ tên:")) {
                            String contact = text.replace("Họ tên:", "").trim();
                            propertyModel.setPropertyContact(contact);
                            System.out.println(contact);
                        } else if (text.contains("Email:")) {
                            String email = text.replace("Email:", "").trim();
                            propertyModel.setPropertyEmail(email);
                            System.out.println(email);
                        } else if (text.contains("Điện thoại:")) {
                            String mobile = text.replace("Điện thoại:", "").trim();
                            propertyModel.setPropertyMobile(mobile);
                            System.out.println(mobile);
                        }
                        // System.out.println(divElement.text().trim());
                    }
                    //System.out.println(contentElement.outerHtml());
                }
                //System.out.println(mainElement.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }

    public static PropertyModel fromNhaSo(String url, int groupId) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropertyUrl(url);
        propertyModel.setGroupId(groupId);
        try {
            Document document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyModel;
    }
}
