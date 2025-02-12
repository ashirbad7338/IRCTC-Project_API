package com.railway.controller;

import com.railway.entity.Booking;
import com.railway.entity.Train;
import com.railway.security.JwtUtil;
import com.railway.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final BookingService bookingService;
    private final JwtUtil jwtUtil;

    public UserController(BookingService bookingService, JwtUtil jwtUtil) {
        this.bookingService = bookingService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/trains")
    public List<Train> getAvailableTrains(@RequestParam String source, @RequestParam String destination) {
        return bookingService.findTrains(source, destination);
    }

    @PostMapping("/book/{trainId}")
    public Map<String, String> bookSeat(@PathVariable Long trainId, @RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        Long userId = jwtUtil.getUserIdFromUsername(username);
        Booking booking = bookingService.bookSeat(userId, trainId);
        return Map.of("message", "Seat booked successfully", "seatNumber", booking.getSeatNumber());
    }

    @GetMapping("/bookings")
    public List<Booking> getUserBookings(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        Long userId = jwtUtil.getUserIdFromUsername(username);
        return bookingService.getUserBookings(userId);
    }
}
