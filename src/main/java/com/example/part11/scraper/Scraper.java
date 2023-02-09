package com.example.part11.scraper;

import com.example.part11.model.Company;
import com.example.part11.model.ScrapedResult;

public interface Scraper {

    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
