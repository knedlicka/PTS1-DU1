package game;

import java.util.Objects;

public class Queen {
    private final Integer points;
    public Queen(Integer points) {
        this.points = points;
    }
    public Integer getPoints() {
        return points;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Queen other = (Queen) obj;
        if (!Objects.equals(this.points, other.points)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Queen{" + "points=" + points + '}';
    }
}
