package ru.emotional.coffeeratingapi.collectors_coffee;

import antlr.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.emotional.coffeeratingapi.entity.Coffee;

import java.io.IOException;
import java.util.*;

public class SbmrneCollector {

    public static List<String> getAllLinks(){
        Set<String> linksToCoffee = new HashSet<>();
        String[] pages = {"https://sbmrne.ru/magazine/black-espresso",
                "https://sbmrne.ru/magazine/light-filter"};

        for (String page : pages) {
            try {
                Document document = Jsoup.connect(page).get();
                Elements elements = document.select(".catalog-products").select(".product-item ").select("a");
                elements.forEach(element -> linksToCoffee.add(element.attr("href")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new LinkedList<>(linksToCoffee);
    };

    public static Coffee getCoffee(String link) throws IOException {
        Document document = Jsoup.connect(link).get();
        Elements elements = document.select(".catalog-products");

        Coffee coffee = new Coffee();

        coffee.setName(document.select("img").get(6).attr("alt"));
        coffee.setImageLink(document.select("img").get(6).attr("src"));
        coffee.setDescription(document.select(".card-item--composition").text());
        coffee.setLink(link);
        coffee.setCompany("Submarine");
        coffee.setProcessing(document.select(".characteristics-sort--item-name").get(1).text());
        coffee.setRoasting(document.select("a[itemprop]").get(2).text());

        String region = document.select("div.h1").text().toLowerCase();
        region = region.substring(0,1).toUpperCase() + region.substring(1);
        coffee.setRegion(region);

        coffee.setWeight1(Integer.valueOf(document.select(".col-5").get(1)
                .select("span").text().split(" ")[0]));
        coffee.setPrice1(Integer.valueOf(document.select(".product-item--prices-item_price").text()));

        try {
            coffee.setqGrade(Float.valueOf(document.select(".rating-count").text()));
        }catch (RuntimeException e){
            coffee.setqGrade(null);
        }

        return coffee;
    };

    public static List<Coffee> getAllCoffee() throws IOException {
        List<String> allLinks = getAllLinks();
        List<Coffee> allCoffee = new LinkedList<>();

        for (String link : allLinks) {
            Coffee coffee = getCoffee(link);
            allCoffee.add(coffee);
        }

        return allCoffee;
    };
}
