package com.example.part11.service;

import com.example.part11.model.Company;
import com.example.part11.model.Dividend;
import com.example.part11.model.ScrapedResult;
import com.example.part11.persist.CompanyRepository;
import com.example.part11.persist.DividendRepository;
import com.example.part11.persist.entity.CompanyEntity;
import com.example.part11.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FinanceService {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    public ScrapedResult getDividendByCompanyName(String companyName) {
        
        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));

        // 2. 조회된 회사 ID 로 배당금 정보 조회
        List< DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        
        // 3. 결과 조합 후 반환
        // dividendEntities 을 ScrapedResult 의 List<Dividend> dividends 처럼 변환하기 위한 방법 두가지!
        // 방법1
//        List<Dividend> dividends = new ArrayList<>();
//        for (var entity: dividendEntities) {
//            dividends.add(Dividend.builder()
//                            .date(entity.getDate())
//                            .dividend(entity.getDividend())
//                            .build());
//        }

        // 방법2
        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> Dividend.builder()
                        .date(e.getDate())
                        .dividend(e.getDividend())
                        .build())
                .collect(Collectors.toList());


        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(), dividends);
    }
    
}
