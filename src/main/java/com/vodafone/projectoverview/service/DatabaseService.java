package com.vodafone.projectoverview.service;

import com.vodafone.projectoverview.common.TypedPage;
import com.vodafone.projectoverview.data.DataNode;
import com.vodafone.projectoverview.data.Database;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import com.vodafone.projectoverview.repository.DatabaseRepository;
import com.vodafone.projectoverview.request.CreateRequest;
import com.vodafone.projectoverview.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseRepository databaseRepository;

    @Autowired
    public DatabaseService(final DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public Database createDatabase(CreateRequest createRequest) {
        Database database = new Database();
        database.setName(createRequest.getName());
        database.setAlias(createRequest.getAlias());
        database.setPoints(createRequest.getPoints());
        database.setWeight(createRequest.getWeight());
        database.setDescription(createRequest.getDescription());
        database.setActive(createRequest.getActive());
        this.databaseRepository.save(database);
        return database;
    }

    public Database getDatabaseById(Long id) {
        return this.databaseRepository.findById(id)
            .orElseThrow(()->
                new ResourceNotFoundException(String.format("Database '%s' not found.", id)));
    }

    public List<Database> getDatabaseByName(String name) {
        return this.databaseRepository.findByName(name);
    }

    public List<Database> getAllDatabase() {
        return this.databaseRepository.findAll();
    }

    public TypedPage<Database> fetchDatabases(final Integer page, final Integer size) {
        final Page<Database> resultPage =
            this.databaseRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
            );
        return TypedPage.of(resultPage.getContent(), resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    public Database updateDatabase(UpdateRequest updateRequest) {
        Database database = this.databaseRepository.findById(updateRequest.getId()).get();
        database.setName(updateRequest.getName());
        database.setAlias(updateRequest.getAlias());
        database.setPoints(updateRequest.getPoints());
        database.setWeight(updateRequest.getWeight());
        database.setActive(updateRequest.getActive());
        database.setDescription(updateRequest.getDescription());
        this.databaseRepository.save(database);
        return database;
    }

    public String deleteDatabase(Long id) {
        this.databaseRepository.deleteById(id);
        return "Database_Node Deleted";
    }
}
