package GGTStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GGTStore.dao.ProducerDAO;
import GGTStore.entity.Producer;
import GGTStore.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerDAO prdao;

    @Override
    public List<Producer> findAll() {
        return prdao.findAll();
    }

    @Override
    public <S extends Producer> S save(S entity) {
        return prdao.save(entity);
    }

    @Override
    public Producer findById(String id) {
        return prdao.findById(id).get();
    }

    @Override
    public Producer create(Producer Producer) {
        return prdao.save(Producer);
    }

    @Override
    public Producer update(Producer Producer) {
        return prdao.save(Producer);
    }

    @Override
    public void delete(String id) {
        prdao.deleteById(id);
    }

    public long count() {
        return prdao.count();
    }

}
