package me.kopkaj.wuthichai.configuration;

import me.kopkaj.wuthichai.controller.TableController;
import me.kopkaj.wuthichai.repository.ReservationRepository;
import me.kopkaj.wuthichai.repository.ReservationRepositoryInMemory;
import me.kopkaj.wuthichai.repository.TableRepository;
import me.kopkaj.wuthichai.repository.TableRepositoryInMemory;
import me.kopkaj.wuthichai.service.TableService;
import me.kopkaj.wuthichai.service.TableServiceSimpleReservation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableConfiguration {

    @Bean
    public TableRepository tableRepository() {
        return TableRepositoryInMemory.getInstance();
    }

    @Bean
    public ReservationRepository reservationRepository() {
        return ReservationRepositoryInMemory.getInstance();
    }

    @Bean
    public TableService tableService(TableRepository tableRepository, ReservationRepository reservationRepository) {
        return new TableServiceSimpleReservation(tableRepository, reservationRepository);
    }
}
