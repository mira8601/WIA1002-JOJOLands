package jojoland;

public class stand {
    private String stand;
    private String standUser;
    private String destructivePower;
    private String speed;
    private String range;
    private String stamina;
    private String precision;
    private String developmentPotential;

    public stand(String stand, String standUser, String destructivePower, String speed, String range, String stamina, String precision, String developmentPotential) {
        this.stand = stand;
        this.standUser = standUser;
        this.destructivePower = destructivePower;
        this.speed = speed;
        this.range = range;
        this.stamina = stamina;
        this.precision = precision;
        this.developmentPotential = developmentPotential;
    }

    public String getStand() {
        return stand;
    }

    public String getStandUser() {
        return standUser;
    }

    public String getDestructivePower() {
        return destructivePower;
    }

    public String getSpeed() {
        return speed;
    }

    public String getRange() {
        return range;
    }

    public String getStamina() {
        return stamina;
    }

    public String getPrecision() {
        return precision;
    }

    public String getDevelopmentPotential() {
        return developmentPotential;
    }

    @Override
    public String toString() {
        return "Stand: " + stand + "\n" +
                "Stand User: " + standUser + "\n" +
                "Destructive Power: " + destructivePower + "\n" +
                "Speed: " + speed + "\n" +
                "Range: " + range + "\n" +
                "Stamina: " + stamina + "\n" +
                "Precision: " + precision + "\n" +
                "Development Potential: " + developmentPotential + "\n";
    }
}
