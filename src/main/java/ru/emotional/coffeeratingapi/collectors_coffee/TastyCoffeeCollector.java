package ru.emotional.coffeeratingapi.collectors_coffee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.emotional.coffeeratingapi.entity.Coffee;

import java.io.IOException;
import java.util.*;

public class TastyCoffeeCollector {
    public static List<String> getAllLinks() {
        Set<String> linksToCoffee = new HashSet<>();
        for (int i = 1; i < 100; i++) {
            int sizeCounter = linksToCoffee.size();
            try {
                Document document = Jsoup.connect("https://shop.tastycoffee.ru/coffee?methods=1a,2a,3a,4a&page=" + i).get();
                Elements elements = document.select(".tovarImg-wrap").select("a");
                elements.forEach(e -> linksToCoffee.add(e.attr("href")));
                if (linksToCoffee.size() == sizeCounter) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new LinkedList<String>(linksToCoffee);
    }

    public static Coffee getCoffee(String link) throws IOException {

        LinkedHashMap<String, String> parsingMap = new LinkedHashMap<>();
        Document document = Jsoup.connect(link).get();

        for (int i = 0; i < 8; i++) {
            try {
                String key = document.select(".text-p").get(i).text().split(":")[0];
                String value = document.select(".bold-text").get(i).text();
                parsingMap.put(key, value);
            } catch (RuntimeException e) {
                break;
            }
        }

        Coffee coffee = new Coffee();
        coffee.setName(document.select(".h2").text()); // NAME
        coffee.setLink(link); // LINK
        coffee.setCompany("TastyCoffee"); // COMPANY
        coffee.setImageLink(document.select("img").get(9).attr("src")); // IMAGE LINK
        coffee.setRoasting(parsingMap.getOrDefault("Степень обжарки", null)); // ROASTING
        coffee.setDescription(document.select(".textGoods").text()); //DESCRIPTION
        coffee.setWeight1(Integer.valueOf(document.select(".priceCb__wt")
                .get(0).text().split(" ")[0])); //WEIGHT1
        coffee.setPrice1(Integer.valueOf(document.select(".priceCb")
                .get(0).text().split(" ")[0])); //PRICE1
        coffee.setWeight2(Integer.valueOf(document.select(".priceCb__wt")
                .get(1).text().split(" ")[0])); //WEIGHT2
        coffee.setPrice2(Integer.valueOf(document.select(".priceCb")
                .get(0).text().split(" ")[0]));//PRICE2
        coffee.setProcessing(parsingMap.getOrDefault("Способ обработки", null));
        coffee.setRegion(parsingMap.getOrDefault("Регион", null));

        try {
            coffee.setqGrade(Float.valueOf(parsingMap.get("Оценка Q-грейдера")));
        } catch (RuntimeException e) {
            coffee.setqGrade(null);
        }
//        coffee.setqGrade(Float.valueOf(parsingMap.getOrDefault("Оценка Q-грейдера", null)));

        return coffee;
    }

    public static List<Coffee> getAllCoffee() throws IOException {
        List<String> allLinks = getAllLinks();
        List<Coffee> allCoffee = new LinkedList<>();

        for (String link : allLinks) {
            Coffee coffee = getCoffee(link);
            allCoffee.add(coffee);
        }

        return allCoffee;
    }
}
