//package com.backend.superme.controller.adminItem;
//
//import com.backend.superme.entity.view.Item;
//import com.backend.superme.service.adminService.SalesService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/sales")
//public class SalesController {
//
//    private final SalesService salesService;
//
//    @GetMapping("/required")
//    public ResponseEntity<List<Item>> getRequiredSales() {
//        List<Item> requiredSales = salesService.getRequiredSales();
//        return ResponseEntity.ok(requiredSales);
//    }
//}
