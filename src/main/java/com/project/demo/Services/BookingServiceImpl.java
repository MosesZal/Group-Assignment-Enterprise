package com.project.demo.Services;

import com.project.demo.Entities.Booking;
import com.project.demo.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

  private BookingRepository bookingRepository;

  @Autowired
  public BookingServiceImpl(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  @Override
  public Booking saveBooking(Booking booking) {

    if (booking.getCar().isBooked() == true) {

    }
    booking.setActive(true);

    return bookingRepository.save(booking);
  }

  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking cancelBooking(Booking booking) {

    booking.setActive(false);

    return bookingRepository.save(booking);
  }

  @Override
  public void deleteBooking(int ID) {
    bookingRepository.deleteById(ID);
  }

  @Override
  public Booking findByID(int ID) {
    Booking booking = null;
    Optional<Booking> possibleBooking = bookingRepository.findById(ID);
    if (possibleBooking.isPresent())
      booking = possibleBooking.get();
    return booking;
  }
}
