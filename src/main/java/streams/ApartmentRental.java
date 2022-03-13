package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String location) {
        return apartments.stream().filter(apartment -> location.equals(apartment.getLocation())).toList();
    }

    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartments.stream().filter(apartment -> apartment.getExtras().containsAll(Arrays.asList(extras))).toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType type) {
        return apartments.stream().filter(apartment -> type == apartment.getBathRoomType()).toList().size() > 0;
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream().map(Apartment::getSize).toList();
    }

}
