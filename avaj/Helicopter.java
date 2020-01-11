import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private String type;

	Helicopter(String name, Coordinates coordinates){
		super(name, coordinates);
		type = "Helicopter";
	}
	private void logMessage(String message) {
		String type = this.getType();
		String name = this.name;
		long id = this.id;
		String format = type + "#" + name + "(" + id + "): ";

		FileWriter myWriter = new FileWriter("simulation.txt", true);
		myWriter.write(format + message);
		myWriter.close();
	}
	@Override
	public void updateConditions() {
		String weather = weatherTower.getWeather();
		Coordinates c = super.coordinates;

		switch (weather) {
			case "SUN":
				c.changeLatitude(10);
				c.changeHeight(2);
				if (c.getHeight() > 100)
                c.setHeight(100);
				this.logMessage("This is hot.");
				break;
			case "RAIN":
				c.changeLatitude(5);
				this.logMessage("This is wet.");
				break;
			case "FOG":
				c.changeLatitude(1);
				this.logMessage("I can't see anything!");
				break;
			case "SNOW":
				c.changeHeight(7);
				if (c.getHeight() > 100)
					c.setHeight(100);
				this.logMessage("My rotor is going to freeze!");
				break;
		}
	}
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
	}
	public String getType() {
		return this.type;
	}
}
