package drivers;

public class DriverStrategyImplementer {

    public static DriverStrategy chooseStrategy (String strategy) {

        switch (strategy) {

            case "drivers.Chrome":
                return new Chrome();

            case "drivers.PhantomJs":
                return new PhantomJs();

            case "drivers.Firefox":
                return new Firefox();

            default:
                return null;
        }
    }
}
