package com.backend.superme.repository.view;

import com.backend.superme.entity.view.CartItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class CartItemRepository implements JpaRepository<CartItem, Long> {

    @Override
    public void flush() {

    }

    @Override
    public <S extends CartItem> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CartItem> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CartItem getOne(Long aLong) {
        return null;
    }

    @Override
    public CartItem getById(Long aLong) {
        return null;
    }

    @Override
    public CartItem getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CartItem> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CartItem> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CartItem> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CartItem> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CartItem> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CartItem, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends CartItem> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CartItem> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CartItem> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public List<CartItem> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CartItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CartItem> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CartItem> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CartItem> findAll(Pageable pageable) {
        return null;
    }
}
