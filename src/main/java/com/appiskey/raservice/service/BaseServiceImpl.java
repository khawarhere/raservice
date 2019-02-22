package com.appiskey.raservice.service;

import com.appiskey.raservice.exception.ResourceNotFoundException;
import com.appiskey.raservice.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by suraksha-pnc on 2/7/19.
 */
//@NoRepositoryBean
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    BaseRepository<T> repository;

    @Override
    public T insert(T item) {
        return repository.saveAndFlush(item);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAllByDeleted() {
        return repository.findAllByDeleted(false);
    }

    @Override
    public T findById(UUID id) {
        Optional<T> itemOpt = repository.findById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Object", "id", id)));
        if (itemOpt.isPresent()) {
            return itemOpt.get();
        } else {
            return null;
        }

    }

//    @Override
//    public Optional<T> findById(UUID id) {
//        return repository.findById(id);
////        Optional<T> itemOpt = Optional.ofNullable(repository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Object", "id", id)));
////        return itemOpt;
//    }

    @Override
    public void delete(T item) {
        repository.delete(item);
    }

    @Override
    public T update(T item) {
        return repository.saveAndFlush(item);
    }
}