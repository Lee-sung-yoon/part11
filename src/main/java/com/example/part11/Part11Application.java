package com.example.part11;

import com.example.part11.model.Company;
import com.example.part11.scraper.Scraper;
import com.example.part11.scraper.YahooFinanceScraper;
import lombok.var;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.jsoup.nodes.Document;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;

@SpringBootApplication
public class Part11Application {

    public static void main(String[] args) {
        SpringApplication.run(Part11Application.class, args);

    }
}
