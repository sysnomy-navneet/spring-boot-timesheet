package io.timesheet.repository;

import io.timesheet.model.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidaysRepository extends JpaRepository<Holidays, Integer> {
}
