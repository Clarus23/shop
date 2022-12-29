package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String item); //이름을 통한 검색
    List<Item> findByItemNmOrItemDetail(String itemNM, String itemDetail); //이름과 description을 이용한 or조건 검색
    List<Item> findByPriceLessThan(Integer price); //price 값보다 적은 가격의 상품 검색(Less than)
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); //price 값보다 적은 가격의 상품 내림차순 검색(Less than)

    @Query("Select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc") //Item entity로 부터 data를 select 하겠다.
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); //파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정가능. 변수를 "like % %" 사이에 ":itemDetail"로 value가 들어가도록 작성.
}
