package com.booking.booking_service.service.impl;

import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(UUID id, Booking updatedBooking) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setName(updatedBooking.getName());
        booking.setEmail(updatedBooking.getEmail());
        booking.setServiceType(updatedBooking.getServiceType());
        booking.setBookingTime(updatedBooking.getBookingTime());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(UUID id) {
        return bookingRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBooking(UUID id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking cancelBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setCancelled(true);
        return bookingRepository.save(booking);
    }
}
