package com.booking.booking_service.service;

import com.booking.booking_service.dto.AddressUpdateRequest;
import com.booking.booking_service.dto.CancelBookingRequest;
import com.booking.booking_service.dto.CreateBookingRequest;
import com.booking.booking_service.dto.UpdateBookingRequest;
import com.booking.booking_service.model.Booking;
import com.booking.booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;

	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public Booking createBooking(CreateBookingRequest request) {
		Booking booking = new Booking();
		booking.setId(UUID.randomUUID());
		booking.setCustomerName(request.getCustomerName());
		booking.setDate(request.getDate());
		booking.setStatus("CREATED");
		booking.setAddress(request.getAddress());
		return bookingRepository.save(booking);
	}

	public Booking getBookingById(UUID id) {
		return bookingRepository.findById(id).orElse(null);
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking updateBooking(UUID id, UpdateBookingRequest request) {
		Optional<Booking> existingBookingOpt = bookingRepository.findById(id);
		if (existingBookingOpt.isPresent()) {
			Booking booking = existingBookingOpt.get();
			booking.setCustomerName(request.getCustomerName());
			booking.setDate(request.getDate());
			booking.setAddress(request.getAddress());
			return bookingRepository.save(booking);
		}
		return null;
	}

	public boolean cancelBooking(UUID id) {
		Optional<Booking> bookingOpt = bookingRepository.findById(id);
		if (bookingOpt.isPresent()) {
			Booking booking = bookingOpt.get();
			booking.setStatus("CANCELLED");
			bookingRepository.save(booking);
			return true;
		}
		return false;
	}

	public Booking updateAddress(UUID id, AddressUpdateRequest request) {
		Optional<Booking> bookingOpt = bookingRepository.findById(id);
		if (bookingOpt.isPresent()) {
			Booking booking = bookingOpt.get();
			booking.getAddress().setStreet(request.getStreet());
			booking.getAddress().setCity(request.getCity());
			booking.getAddress().setState(request.getState());
			booking.getAddress().setPostalCode(request.getPostalCode());
			return bookingRepository.save(booking);
		}
		return null;
	}
}

