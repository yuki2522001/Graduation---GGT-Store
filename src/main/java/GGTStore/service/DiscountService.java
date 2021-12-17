package GGTStore.service;

import java.util.List;

import GGTStore.entity.Discount;

public interface DiscountService {

    List<Discount> findAll();

    Discount findById(String id);

    Discount create(Discount Discount);

    Discount update(Discount Discount);

    void delete(String id);

    <S extends Discount> S save(S entity);

    long count();
}
