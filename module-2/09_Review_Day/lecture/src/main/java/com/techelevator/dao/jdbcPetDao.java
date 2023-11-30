package com.techelevator.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.Pet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class jdbcPetDao implements PetDao {


    private final JdbcTemplate jdbcTemplate;

    public jdbdTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pet createPet(Pet petToCreate) {
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        String sql = "" +
                "select * " +
                "from pet " +
                "order by pet_name asc;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        return null;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "" +
                "select * " +
                "from pet " +
                "where pet_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,petId);
        if (results.next()) {
            return mapRowToPet(results);
        }
        return null;
    }

    @Override
    public int numberOfPets(String owner, boolean useWildcard) {
        return 0;
    }

    @Override
    public int visitCount(int petId, LocalDate startDate, LocalDate endDate) {
        String sql = "" +
                "Select count(pet_id) as visit_count" +
                "from pet_visit " +
                "Where visitdate between ? and ? and pet_id = ?";

        return jdbcTemplate.queryForObject(sql, int.class, startDate, endDate, petId);

    }

    @Override
    public Pet update(Pet petToUpdate) {
        return null;
    }

    @Override
    public void deletePetById(int petId) {

    }

    @Override
    public void deletePetsByType(String petType) {
        String sqlDeletePetVisit = "delete from pet_visit where pet_id in(select pet_id from pet where pet_type = ?)";
        String sqlDeletePet= "delete from pet where pet_type = ?";

        jdbcTemplate.update(sqlDeletePetVisit, petType);
        jdbcTemplate.update(sqlDeletePet, petType);
    }


    private Pet mapRowToPet(SqlRowSet results) {
        Pet pet = new Pet();
        int PetId = pet.setId (results.getInt("Pet_id");
        pet.setId(PetId)
    }
}
