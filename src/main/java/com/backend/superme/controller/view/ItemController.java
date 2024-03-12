package com.backend.superme.controller.view;

import ch.qos.logback.core.model.Model;
import com.backend.superme.service.view.ItemService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    //상품 등록페이지 (GET)

    //상품 등록 (POST)

    //상품 수정 페이지 (GET)

    //상품 수정 (POST)

    //상품 상세페이지 : 아이템 아이디로 상세페이지

    /*
    @GetMapping("/item/view/{id}")
        public String itemView(Model model, @PathVariable("id") Long id){
        model.addAttribute("item", itemService.itemView(id));
        return "/seller/itemView";
        }
     */

    //상품 삭제



}
