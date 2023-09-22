package com.example.cacti.config;

import com.example.cacti.model.Shipper;
import com.example.cacti.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile({"mysql"})
public class DBSeeder implements CommandLineRunner {
    public static final List<Shipper> DB_SEEDER_SHIPPERS = List.of(
            new Shipper(2L, "United Package", "(503) 555-1234"),
            new Shipper(3L, "Federal Shipping", "(503) 555-4221"),
            new Shipper(4L, "CocaCola", "(503) 555-4232"),
            new Shipper(5L, "John Doe", "082 65723900"),
            new Shipper(6L, "John Doe", "082 65723900"),
            new Shipper(7L, "Speedy Express", "(503) 555-9831")
    );
    @Autowired
    private ShipperRepository shipperRepository;

    @Override
    public void run(String... args) throws Exception{
        shipperRepository.saveAll(DB_SEEDER_SHIPPERS);
    }
}
