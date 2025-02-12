package com.railway.service;

import com.railway.entity.Booking;
import com.railway.entity.Train;
import com.railway.entity.User;
import com.railway.repository.BookingRepository;
import com.railway.repository.TrainRepo;
import com.railway.repository.Repository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TrainRepo trainRepository;
    private final Repository userRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public BookingService(BookingRepository bookingRepository, TrainRepo trainRepository, Repository userRepository) {
        this.bookingRepository = bookingRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
    }

    public List<Train> findTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }

    @Transactional
    public Booking bookSeat(Long userId, Long trainId) {
        lock.lock();  // Lock to prevent race conditions
        try {
            Train train = trainRepository.findById(trainId)
                    .orElseThrow(() -> new RuntimeException("Train not found"));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            int totalSeats = train.getTotalSeats();
            long bookedSeats = bookingRepository.count();
            if (bookedSeats >= totalSeats) {
                throw new RuntimeException("No seats available");
            }

            // Assign seat number (basic implementation)
            String seatNumber = "S" + (bookedSeats + 1);
            Booking booking = new Booking(null, train, user, seatNumber);
            return bookingRepository.save(booking);
        } finally {
            lock.unlock();  // Release lock after transaction
        }
    }

    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }
}
