package ru.emotional.coffeeratingapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.emotional.coffeeratingapi.collectors_coffee.TastyCoffeeCollector;
import ru.emotional.coffeeratingapi.entity.Coffee;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Coffee> allCoffee = TastyCoffeeCollector.getAllCoffee();
        allCoffee.forEach(System.out::println);
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
