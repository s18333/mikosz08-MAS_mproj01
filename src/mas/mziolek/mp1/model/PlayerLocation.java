package mas.mziolek.mp1.model;

import mas.mziolek.mp1.model.exceptions.DataValidationException;

import java.awt.*;

public class PlayerLocation {
    String localizationName;
    Point coordinates;

    /**
     * Class constructor.
     */
    public PlayerLocation(String localizationName, Point coordinates) {
        setLocalizationName(localizationName);
        setCoordinates(coordinates);
    }

    public PlayerLocation(String localizationName, int x, int y) {
        setLocalizationName(localizationName);
        setCoordinates(x, y);
    }

    /**
     * Localization Name.
     */
    public String getLocalizationName() {
        return localizationName;
    }

    public void setLocalizationName(String localizationName) {
        if (localizationName == null || localizationName.trim().isBlank()) {
            throw new DataValidationException("localization name cannot be null or empty");
        }
        this.localizationName = localizationName;
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

    @Override
    public String toString() {
        return String.format("%s at: x:%d | y:%d", getLocalizationName(), getCoordinates().x, getCoordinates().y);
    }
}
