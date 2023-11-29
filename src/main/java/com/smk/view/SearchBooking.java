package com.smk.view;

import com.smk.MainView;
import com.smk.model.Booking;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Search Booking")
@Route(value = "search-booking", layout = MainView.class)
public class SearchBooking extends VerticalLayout {

    private final TextField flightNumber;
    private final DatePicker departureDate;
    private final Button searchButton;
    private final Grid<Booking> bookingGrid;

    public SearchBooking() {
        flightNumber = new TextField("Flight number");
        departureDate = new DatePicker("Departure date");
        searchButton = new Button("Search");
        bookingGrid = new Grid<>(Booking.class);

        searchButton.addClickListener(e -> searchFlights());

        bookingGrid.setColumns("id", "name", "price");
        bookingGrid.setVisible(false);

        add(flightNumber, departureDate, searchButton, bookingGrid);
    }

    private void searchFlights() {
        String flightNumberValue = flightNumber.getValue();
        LocalDate departureDateValue = departureDate.getValue();

        if (flightNumberValue == null || flightNumberValue.isEmpty() || departureDateValue == null) {
            Notification.show("Please enter a flight number and select a departure date");
            return;
        }

        List<Booking> bookings = getDummyBookings(); // Mengambil data booking (simulasi)

        List<Booking> foundBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (flightNumberValue.equals(booking.getFlightNumber())
                    && departureDateValue.equals(booking.getDepartureDate())) {
                foundBookings.add(booking);
            }
        }

        if (!foundBookings.isEmpty()) {
            bookingGrid.setItems(foundBookings);
            bookingGrid.setVisible(true);
        } else {
            Notification.show("No booking found for the given flight number and departure date");
            bookingGrid.setVisible(false);
        }
    }

    // Metode simulasi untuk mendapatkan data booking
    private List<Booking> getDummyBookings() {
        // Simulasi data booking
        List<Booking> bookings = new ArrayList<>();
        // Tambahkan data booking ke dalam list
        // Contoh:
        Booking booking1 = new Booking();
        booking1.setFlightNumber("ABC123");
        booking1.setDepartureDate(LocalDate.of(2023, 12, 10));
        bookings.add(booking1);

        // Tambahkan data booking lainnya...

        return bookings;
    }
}
