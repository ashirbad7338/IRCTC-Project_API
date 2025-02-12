package com.railway.repository;

import com.railway.entity.Booking;
import com.railway.entity.Train;
import com.railway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    Optional<Booking> findByTrainAndSeatNumber(Train train, String seatNumber);
	Booking save(Booking booking);
}
