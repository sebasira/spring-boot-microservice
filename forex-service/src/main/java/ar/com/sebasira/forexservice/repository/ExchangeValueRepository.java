package ar.com.sebasira.forexservice.repository;

import ar.com.sebasira.forexservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
    ExchangeValue findByFromAndTo(String from, String to);
}
