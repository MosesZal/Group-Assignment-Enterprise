package com.project.demo.Services;

import com.project.demo.Entities.Booking;

import java.util.List;

public interface BookingService {
  Booking saveBooking(Booking booking);

  List <Booking> getAllBookings();

  Booking cancelBooking(Booking booking);
  void deleteBooking (int ID);
  Booking findByID (int ID);


}
