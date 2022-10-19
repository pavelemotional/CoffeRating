package ru.emotional.coffeeratingapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.emotional.coffeeratingapi.entity.Coffee;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Set<String> linksToCoffee = new LinkedHashSet<>();

        linksToCoffee.addAll(getAllLinksTastyCoffee());
//        linksToCoffee.addAll(getAllLinksTheWelderCatrine());
//        linksToCoffee.addAll(getAllLinksSubmarine());

        for (String link : linksToCoffee) {
            Document document = Jsoup.connect(link).get();
            Coffee coffee = new Coffee();
            coffee.setLink(link);
            //parsing name
            System.out.println(document.select(".h2").text());

            LinkedHashMap<String, String> parsingMap = new LinkedHashMap<>();

            for (int i = 0; i < 8; i++) {
                String key = String.valueOf(i);
                String value = String.valueOf(i);
                try {
                    key = document.select(".text-p").get(i).text().split(":")[0];
                    value = document.select(".bold-text").get(i).text();
                } catch (Exception e) {
//                    System.out.println(e);
                }
                parsingMap.put(key, value);
            }
            parsingMap.forEach((s, s2) -> System.out.println(s + ":" + s2));
        }

//        AtomicInteger counter = new AtomicInteger();
//        linksToCoffee.forEach(l -> {
//            counter.getAndIncrement();
//            System.out.println(counter + " " + l);
//        });
    }

    private static Collection<String> getAllLinksSubmarine() {
        Set<String> linksToCoffee = new HashSet<>();
        String[] pages = {"https://sbmrne.ru/magazine#section20",
                "https://sbmrne.ru/magazine#section21"};

        for (String page : pages) {
            try {

                Document document = Jsoup.connect(page).get();
                Elements elements = document.select(".catalog-products").select(".product-item ").select("a");
                elements.forEach(element -> linksToCoffee.add(element.attr("href")));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return linksToCoffee;
    }

    private static Set<String> getAllLinksTastyCoffee() {
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

        return linksToCoffee;
    }

    private static Set<String> getAllLinksTheWelderCatrine() {
        Set<String> linksToCoffee = new HashSet<>();
        String[] pages = {"https://theweldercatherine.ru/catalog/dlya_espresso/",
                "https://theweldercatherine.ru/catalog/pod_molochko_1/",
                "https://theweldercatherine.ru/catalog/dlya_filtra/"};

        for (String page : pages) {

            for (int i = 1; i < 100; i++) {
                int sizeCounter = linksToCoffee.size();

                try {

                    Document document = Jsoup.connect(page + "?PAGEN_1=" + i).get();
                    Elements elements = document.select(".main-info").select("a");
                    elements.forEach(element -> linksToCoffee.add("https://theweldercatherine.ru/" + element.attr("href")));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (linksToCoffee.size() == sizeCounter) break;
            }
        }

        return linksToCoffee;
    }
}
