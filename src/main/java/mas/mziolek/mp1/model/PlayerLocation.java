package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class PlayerLocation implements Serializable {
    private String locationName;
    private Point coordinates;

    /**
     * Class constructor.
     */
    public PlayerLocation(String localizationName, Point coordinates) {
        setLocationName(localizationName);
        setCoordinates(coordinates);
    }

    public PlayerLocation(String localizationName, int x, int y) {
        setLocationName(localizationName);
        setCoordinates(x, y);
    }

    /**
     * Localization Name.
     */
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        if (locationName == null || locationName.trim().isBlank()) {
            throw new DataValidationException("localization name cannot be null or empty");
        }
        this.locationName = locationName;
    }

    /**
     * Coordinates.
     */
    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        if (coordinates == null) {
            throw new DataValidationException("coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Point(x, y);
    }

    /**
     * toString.
     */
    @Override
    public String toString() {
        return String.format("%s at: x:%d | y:%d", getLocationName(), getCoordinates().x, getCoordinates().y);
    }
    /**
     * Equals and HashCode.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerLocation otherLoc = (PlayerLocation) o;
        return locationName.equals(otherLoc.locationName) &&
                coordinates.equals(otherLoc.coordinates);
    }
    @Override
    public int hashCode() {
        return Objects.hash(locationName, coordinates);
    }

}
