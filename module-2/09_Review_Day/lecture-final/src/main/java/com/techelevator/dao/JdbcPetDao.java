package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Pet createPet(Pet petToCreate) {
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();

        String sql = "" +
                "select * " +
                "from pet " +
                "order by pet_name asc;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Pet currentPet = mapRowToPet(results);
            allPets.add(currentPet);
        }

        return allPets;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "" +
                "select * " +
                "from pet " +
                "where pet_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);

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
                "select count(pet_id) as visit_count " +
                "from pet_visit " +
                "where visitdate between ? and ? " +
                "      and pet_id = ?;";


        return jdbcTemplate.queryForObject(sql, int.class, startDate, endDate, petId);
    }

    @Override
    public Pet updatePet(Pet petToUpdate) {
        return null;
    }

    @Override
    public void deletePetById(int petId) {

    }

    @Override
    public void deletePetsByType(String petType) {
        String sqlDeletePetVisit = "delete from pet_visit where pet_id in (select pet_id from pet where pet_type = ?);";
        String sqlDeletePet = "delete from pet where pet_type = ?;";

        jdbcTemplate.update(sqlDeletePetVisit, petType);
        jdbcTemplate.update(sqlDeletePet, petType);
    }

    private Pet mapRowToPet(SqlRowSet results) {
        Pet pet = new Pet();

        int petId = results.getInt("pet_id");
        pet.setId(petId);

        pet.setName(results.getString("pet_name"));
        pet.setType(results.getString("pet_type"));

        if (results.getDate("pet_birthdate") != null) {
            pet.setBirthDate(results.getDate("pet_birthdate").toLocalDate());
        }

        pet.setOwner(results.getString("owner"));

        return pet;
    }
}
