package com.example.part11.web;

import com.example.part11.model.Company;
import com.example.part11.persist.entity.CompanyEntity;
import com.example.part11.service.CompanyService;
import io.netty.util.internal.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 자동 완성
     * @param keyword
     * @return
     */
    // 방법 1
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        var result = this.companyService.autocomplete(keyword);

        return ResponseEntity.ok(result);
    }

    // 방법 2
//    @GetMapping("/autocomplete")
//    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
//        var result = this.companyService.getCompanyNamesByKeyword(keyword);
//
//        return ResponseEntity.ok(result);
//    }

    /**
     * 회사 목록 조회
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<CompanyEntity> companies = this.companyService.getAllCompany(pageable);
        return ResponseEntity.ok(companies);
    }

    /**
     * 회사 및 배당금 정보 추가
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company request) {
        String ticker = request.getTicker().trim();
        if (ObjectUtils.isEmpty(ticker)) {
            throw new RuntimeException("ticker is empty");
        }
        Company company = this.companyService.save(ticker);
        this.companyService.addAutocompleteKeyword(company.getName());

        return ResponseEntity.ok(company);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
